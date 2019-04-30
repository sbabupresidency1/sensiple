

public class OurListener {/*extends ATUReportsListener 
    List<ITestResult> passedTests;
    List<ITestResult> failedTests;
    List<ITestResult> skippedTests;
    public OurListener()
    {
        passedTests = new ArrayList<ITestResult>();
        failedTests = new ArrayList<ITestResult>();
        skippedTests = new ArrayList<ITestResult>();
    }
    
    @Override
    public void onTestSuccess(ITestResult itestresult) {
        try
        {
            if(itestresult.getAttribute("passedButFailed").equals("passedButFailed"))
            {
                itestresult.setStatus(2);
                itestresult.setThrowable(new ATUReporterStepFailedException());
                failedTests.add(itestresult);
                return;
            }
        }
        catch(NullPointerException nullpointerexception) { }
        passedTests.add(itestresult);
    }
    
    @Override
    public void onTestFailure(ITestResult itestresult) {
        failedTests.add(itestresult);
        }
    
    @Override
    public void onTestSkipped(ITestResult itestresult) {
        if(itestresult.getThrowable() instanceof SkipException)
        {
            skippedTests.add(itestresult);
            return;
        } else
        {
            createReportDir(itestresult);
            skippedTests.add(itestresult);
            return;
        }
    }
    
    @Override
    public void onFinish() {
        try
        {
            int runCount = Integer.parseInt(SettingsFile.get("run").trim());
            String s =  SettingsFile.get("passedList");
            String s1 = SettingsFile.get("failedList");
            String s2 = SettingsFile.get("skippedList");
//            SettingsFile.set("passedList", s);
//            SettingsFile.set("failedList", s1);
//            SettingsFile.set("skippedList", s2);
            setTickInterval(passedTests, failedTests, skippedTests);
            HTMLDesignFilesJSWriter.lineChartJS(s, s1, s2, runCount);
            HTMLDesignFilesJSWriter.barChartJS(s, s1, s2, runCount);
            HTMLDesignFilesJSWriter.pieChartJS(passedTests.size(), failedTests.size(), skippedTests.size(), runCount);
            generateIndexPage();
            long l = ((Long)Attributes.getAttribute("startExecution")).longValue();
            generateConsolidatedPage();
            generateCurrentRunPage(passedTests, failedTests, skippedTests, l, System.currentTimeMillis());
            startReportingForPassed(passedTests);
            startReportingForFailed(failedTests);
            startReportingForSkipped(skippedTests);
            if(Directory.generateExcelReports)
                ExcelReports.generateExcelReport((new StringBuilder()).append(Directory.RUNDir).append(Directory.SEP).append("(").append(Directory.REPORTSDIRName).append(") ").append(Directory.RUNName).append(runCount).append(".xlsx").toString(), passedTests, failedTests, skippedTests);
            if(Directory.generateConfigReports)
                ConfigurationListener.startConfigurationMethodsReporting(runCount);
            
            runCount = Integer.parseInt(SettingsFile.get("run").trim()) + 1;
        }
        catch(Exception exception)
        {
            throw new IllegalStateException(exception);
        }
    }

*/}
