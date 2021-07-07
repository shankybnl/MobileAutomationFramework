package UITestFramework.retryLogic;

import Reports.ExtentReportManager;
import Reports.Log;
import UITestFramework.CreateSession;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;



public class TestListener extends CreateSession implements ITestListener {


	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}


	@Override
	public void onTestStart(ITestResult iTestResult) {
		System.out.println("on test method " +  getTestMethodName(iTestResult) + " start");
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		String testName = iTestResult.getName();
		if (iTestResult != null) {
			Log.pass(testName + " is Passed");
		}
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		String testName = iTestResult.getName();
//		try {
//			ExtentReportManager.captureScreenShot();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		if (iTestResult != null) {
			Log.fail(testName + " is FAILED...");
		}
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		System.out.println("I am in onStart method " + iTestContext.getName());
		extent = ExtentReportManager.report();
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		extent.flush();
	}
}






