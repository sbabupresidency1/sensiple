import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.util.concurrent.Uninterruptibles;

public class ParrallelTest {
    @DataProvider(name = "data", parallel = true)
    public static Iterator<Object[]> getData() {
	List<Object[]> data = new ArrayList<Object[]>();

	// Our array types must match the @Factory input (String, Integer,
	// Boolean)
	data.add(new Object[] { "Object 1", 1, true });
	data.add(new Object[] { "Object 2", 2, false });

	return data.iterator();
    }

    @Test(dataProvider = "data")
    public void unitTest(String s, Integer i, Boolean b) {
	System.out.println("unitTest-  Started");
	Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
	System.out.println(s + ", " + i + ", " + b);
    }
}