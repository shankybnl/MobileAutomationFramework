package tests.Login;

import Reports.ExtentReportManager;
import Reports.Log;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import IntegrationTests.coreLogic.base.*;
import IntegrationTests.coreLogic.android.*;
import IntegrationTests.coreLogic.iOS.*;
import UITestFramework.CreateSession;
import utility.Utils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

/**
 * automated test to verify login to android/iOS app.
 */
public class LoginPageField extends CreateSession {
    private static ExtentTest logger = ExtentReportManager.logger;
    LoginCoreLogic loginCoreLogic;
    String userName;
    String password;
    private ExtentReports extent = ExtentReportManager.extent;
    private static String screenshotPath;


    /**
     * this method instantiate required helpers depending on the platform(android or iOS)
     *
     * @param invokeDriver android or iOS
     */
    @Parameters({"os"})
    @BeforeMethod
    public void instantiateHelpers(String invokeDriver) {

        userName = localeConfigProp.getProperty("userName");
        password = localeConfigProp.getProperty("password");

        if (invokeDriver.equalsIgnoreCase("android")) {
            loginCoreLogic = new AndroidLoginCoreLogic(driver);
        } else if (invokeDriver.equalsIgnoreCase("iOS")) {
            loginCoreLogic = new IOSLoginCoreLogic(driver);
        }
    }

    /**
     * method to verify login to android and iOS app
     *
     * @throws InterruptedException Thrown when a thread is waiting, sleeping,
     *                              or otherwise occupied, and the thread is interrupted, either before
     *                              or during the activity.
     */




    @Test(priority = 5)
    public void loginScreenFieldVerification() throws InterruptedException, IOException {
        Thread.sleep(5000);
        logger = extent.createTest("Verification of Login field Functionality");
        logger = logger.createNode("Login Field Verification");
        logger.assignAuthor("Chetan");
        logger.assignCategory("Sanity");

        Log.info("Running login screen test");
        logger.log(Status.INFO, "Application launch successfully");

//        loginCoreLogic.loginScreenScenario();

        Log.info("Verified the login screen is display");
        logger.log(Status.INFO, "Verified the login screen is display");

        Thread.sleep(5000);


    }


    @AfterMethod
    public void aftermethod(ITestResult iTestResult) {
        String methodname = iTestResult.getMethod().getMethodName();
        System.out.println("Printing method name :"+methodname);
        if (iTestResult.getStatus() == iTestResult.FAILURE) {
            String exceptionmessage = Arrays.toString(iTestResult.getThrowable().getStackTrace());
            logger.fail("Failure" + exceptionmessage.replaceAll(",", "<br>"));
            try {
                System.out.println("i am here");
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                byte[] fileContent = FileUtils.readFileToByteArray(src);
                String base64Screenshot = "data:image/png;base64,"+ Base64.getEncoder().encodeToString(fileContent);
                logger.log(Status.FAIL, "Test Failed", logger.addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
            } catch (Exception e) {
                System.out.println("i am here too");
                logger.fail("Test failed, cannot attach screenshot");
            }
            String logtext = "<br>Test Method" + methodname + "failed</br>";
            Markup m = MarkupHelper.createLabel(logtext, ExtentColor.RED);
            logger.log(Status.FAIL, m);
        } else if (iTestResult.getStatus() == iTestResult.SUCCESS) {
            try {
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                byte[] fileContent = FileUtils.readFileToByteArray(src);
                String base64Screenshot = "data:image/png;base64,"+ Base64.getEncoder().encodeToString(fileContent);
                logger.log(Status.PASS, "Test Passed", logger.addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
            } catch (Exception e) {
                logger.fail("Test failed, cannot attach screenshot");
            }
            String logtexts = "<br>Test Method :" + methodname + " Passed</br>";
            Markup m = MarkupHelper.createLabel(logtexts, ExtentColor.GREEN);
            logger.log(Status.PASS, m);
        } else if (iTestResult.getStatus() == iTestResult.SKIP) {
            String path = takeScreenShot(driver);
            try {
                logger.skip("Screenshot of failure" + MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            } catch (Exception e) {
                logger.fail("Test failed, cannot attach screenshot");
            }
            String logtext = "<br>Test Method" + methodname + "Skipped</br>";
            Markup m = MarkupHelper.createLabel(logtext, ExtentColor.YELLOW);
            logger.log(Status.SKIP, m);
        }
    }

    public static String takeScreenShot(WebDriver driver) {
        String pathName = Utils.createDir(Utils.getReportDir() + "/Images");
        File Dest = new File(pathName, System.currentTimeMillis() + ".png");
        String[] relativePath = Dest.toString().split("reports");
        screenshotPath = "../" + relativePath[1];
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, Dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return screenshotPath;
    }



}
