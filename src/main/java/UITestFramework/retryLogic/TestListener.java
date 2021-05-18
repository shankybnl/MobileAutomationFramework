package UITestFramework.retryLogic;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import UITestFramework.CreateSession;

public class TestListener extends CreateSession implements ITestListener {
	
	
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
	}

	public void onTestStart(ITestResult result) {   }

	public void onTestSuccess(ITestResult result) {   }
   
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
	   }

	public void onTestSkipped(ITestResult result) {   }

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {   }

	public void onStart(ITestContext context) { }

}



