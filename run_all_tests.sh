#!/bin/sh -x



usage() {
  set +x
  echo "Runs a suite of tests against the given environment." >&2
  echo "usage: $0 [OPTIONS] environment" >&2
  echo "options:" >&2
  echo "  -b browsers  list of browsers (for example 'chrome,firefox', default chrome)" >&2
  echo "  -g URL       run on the given selenium grid (default: run locally)" >&2
  echo "  -t csv       CSV file listing test cases" >&2
  echo "examples:" >&2
  echo "  $0 Test" >&2
  echo "  $0 -g 'http://grid.selenium/wd/hub' -t Nightly.csv Kulfi" >&2
}



browsers=chrome
grid=
testcases=
while getopts b:g:t: opt ; do
  case $opt in
    b) browsers="$OPTARG" ;;
    g) grid="$OPTARG" ;;
    t) testcases="$OPTARG" ;;
    ?) usage ; exit 1 ;;
  esac
done
shift $(($OPTIND - 1))



environment="$1"
if [ ! "$environment" ] ; then
  usage
  exit 1
fi
echo "environment: $environment"
if [ ! -r Zillion/testcases/ConfigurationFile-$environment.properties ] ; then
  echo "Cannot file config file Zillion/testcases/ConfigurationFile-$environment.properties"
  exit 1
fi



echo "testcases: $testcases"
if [ "$testcases" ] ; then
  cd Zillion/testcases
  tmpdir=$(mktemp -d)
  mv -v TestCaseExeSheet* $tmpdir
  cp -v "$testcases" ./TestCaseExeSheet.csv
  cd -
fi



update_config() {
  sed -i.bak -e "s|^ *$1 *=.*|$1=$2|" Zillion/testcases/zillion.properties
}

update_config zado.browser.name $browsers
update_config zado.browser.liveSession.coach.name $browsers
update_config zado.environment "ConfigurationFile-$environment"
update_config zado.proj.testcasePath ../Zillion/testcases/
update_config zado.mail.zipfolder ../Zillion/OutputFile/
update_config zado.reports.dir ../Zillion/Reports/
update_config zado.mail.cc.address selenium_notification@zillion.com
update_config zado.mail.to.address cmosher@zillion.com

grid_arg=
if [ "$grid" ] ; then
  update_config zado.Grid.name "$grid"
  grid_arg=-Dzado.grid=grid
fi

parseq=parallel
slots=$(curl -s $grid/../../grid/api/hub | jq '.slotCounts.total')
if [ ! "$slots" ] ; then
  slots=0
fi
if [ "$slots" -le 1 ] ; then
  parseq=sequential
fi

echo "Using the following properties:"
cat Zillion/testcases/zillion.properties | grep -v '^$' | grep -v '^ *#'



cd zillionqa

mvn --batch-mode install:install-file -DgroupId=com.hynnet -DartifactId=oracle-driver-ojdbc -Dversion=12.1.0.2 -Dpackaging=jar -Dfile=src/main/resources/jars/ojdbc7.jar -DgeneratePom=true
mvn --batch-mode package

java -jar \
  -Dcom.zillion.qa.workaround3951=true \
  -Dcom.zillion.qa.log_tests=log \
  -Djava.util.logging.SimpleFormatter.format='%1$tY-%1$tm-%1$tdT%1$tH:%1$tM:%1$tS %4$-7s %5$s%6$s%n' \
  -Dzado.reporter.config=../Zillion/testcases/zillion.properties \
  -DlogfileLoc=./logs \
  -Dzado.execution.parseq=$parseq \
  $grid_arg \
  target/zillionqa-jar-with-dependencies.jar
