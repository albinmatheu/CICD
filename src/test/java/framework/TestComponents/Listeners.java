package framework.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import framework.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReporterObject();
	ThreadLocal<ExtentTest> extentTest =  new ThreadLocal<>();

	   @Override
	    public void onTestStart(ITestResult result) {

		  test=  extent.createTest(result.getMethod().getMethodName());
		  extentTest.set(test);
	        System.out.println("Test started: " + result.getName());
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	    	test.log(Status.PASS, "Test Passed");
	        System.out.println("Test passed: " + result.getName());
	    }

	    @Override
	    public void onTestFailure(ITestResult result)
	    {
	    	extentTest.get().fail(result.getThrowable()); // mark the test is failed in reporting tool
	    	try {
				driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (Exception e1) {
				e1.printStackTrace(); //retrieve driver to capture screenshot
			}

	    	String filepath = null;
	    	try {
	    		filepath = getScreenshot(result.getMethod().getMethodName(),driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());

	        System.out.println("Test failed: " + result.getName());
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        System.out.println("Test skipped: " + result.getName());
	    }

	    @Override
	    public void onFinish(org.testng.ITestContext context) {
	    	extent.flush();
	        System.out.println("All tests finished: " + context.getName());
	    }
	    //Comment


}
