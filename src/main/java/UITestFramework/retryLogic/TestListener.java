package UITestFramework.retryLogic;


import UITestFramework.CreateSession;
import UITestFramework.ExtentReportConfig;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class TestListener extends CreateSession implements ITestListener {
	
	ExtentReports extent = ExtentReportConfig.extentReportGenerator();
    	ExtentTest test;
	
	public void onFinish(ITestContext context) {
		Set<ITestResult> failedTests = context.getFailedTests().getAllResults();
		for (ITestResult temp : failedTests) {
			ITestNGMethod method = temp.getMethod();
			if (context.getFailedTests().getResults(method).size() > 1) {
				failedTests.remove(temp);
			} else {
				if (context.getPassedTests().getResults(method).size() > 0) {
					failedTests.remove(temp);
				}
			}
		}
		extent.flush();
	}

	public void onTestStart(ITestResult result) {  
		test= extent.createTest(result.getMethod().getMethodName());

	}

	public void onTestSuccess(ITestResult result) {   
		test.log(Status.PASS, "No Issues encountered!");
	}
   
	@Override
	public void onTestFailure(ITestResult result) { 
		Object TestListener = result.getInstance();
		WebDriver webDriver = ((CreateSession) TestListener).getDriver();
		if (webDriver != null)
		{
			File scr = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);		
			String filename =  new SimpleDateFormat("yyyyMMddhhmmss'.jpg'").format(new Date());
    			File dest = new File("./Screenshots/" + filename); //Directory where Screenshot get saved.
   			try 
			{
				FileUtils.copyFile(scr, dest);
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		test.fail(result.getThrowable()); 
	   }

	public void onTestSkipped(ITestResult result) {   }

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {   }

	public void onStart(ITestContext context) { }

}
