package tests.testngTests;

import Reports.ExtentReportManager;
import Reports.Log;
import UITestFramework.GenericMethods;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import IntegrationTests.coreLogic.base.*;
import IntegrationTests.coreLogic.android.*;
import IntegrationTests.coreLogic.iOS.*;
import UITestFramework.CreateSession;

import java.io.IOException;

import static Reports.ExtentReportManager.captureScreenShot;
import static UITestFramework.GenericMethods.*;

/**
 * automated test to verify login to android/iOS app.
 */
public class LoginTest extends CreateSession {
    private static ExtentTest test;
    LoginCoreLogic loginCoreLogic;
    String userName;
    String password;
    private ExtentReports extent = ExtentReportManager.extent;


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


    @Test
    public void LoginVerification() throws InterruptedException, IOException {

        Thread.sleep(5000);
        test = extent.createTest("Verification of application");
        test = test.createNode("Login page check");
        test.assignAuthor("Chetan");
        test.assignCategory("Regression");
        Log.info("Running login test");
//			loginCoreLogic.verifyLoginScenario(userName, password);
        Log.info("Verified the login");
        test.log(Status.INFO, "Test Case");
        test.log(Status.PASS, "Test Case");
        Thread.sleep(5000);


    }


    @Test
    public void ValidLoginVerification() throws InterruptedException, IOException {

        Thread.sleep(5000);
        test = test.createNode("Valid Login page check");
        test.assignAuthor("Chetan");
        test.assignCategory("Regression");
        Log.info("Running login test");
//		driver.findElement(By.id("chetan"));
//			loginCoreLogic.verifyLoginScenario(userName, password);
        Log.info("Verified the login");
        test.log(Status.INFO, "Test Case 2");
        String pathShot = ExtentReportManager.captureScreenShot(driver);
//        test.addScreenCaptureFromPath(pathShot);
        System.out.println("" + pathShot);

        Thread.sleep(5000);


    }



}
