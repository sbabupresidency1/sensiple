import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

public class SampleFactory {

    @Factory(dataProvider = "dp")
    public Object[] createInstances(int id, String account) {
//	return new Object[] { new SomeTest(id, account) };
	return null;
    }

    @DataProvider(name = "dp")
    public static Object[][] dataProvider() {
	Object[][] dataArray = { { 1, "user1" }, { 2, "user2" } };
	return dataArray;
    }

}
