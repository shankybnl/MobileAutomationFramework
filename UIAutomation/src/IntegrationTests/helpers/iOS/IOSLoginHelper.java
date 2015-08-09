package IntegrationTests.helpers.iOS;

import logger.Log;

import org.openqa.selenium.WebDriver;

import IntegrationTests.screens.iOS.IOSLoginScreen;
import IntegrationTests.helpers.base.LoginHelper;



/**
 * contains method to login on iOS app
 */
public class IOSLoginHelper extends LoginHelper{
	IOSLoginScreen iosLoginScreen;

	public IOSLoginHelper(WebDriver driver){
		iosLoginScreen = new IOSLoginScreen(driver);
	}

/**
 * method to verify login scenario on iOS app
 * @param userName emailId to be used for login
 * @param password - valid password
 */
	@Override
	public void verifyLoginScenario(String userName, String password)
			throws InterruptedException {
	
		
		// write the flow as per your app (similar to AndroidLoginhelper). Below is the appium code of iOS app used in this framework.
		
		
		/*
		iosLoginScreen.waitForVisibility(iosLoginScreen.slideShareButtonLabel);
		iosLoginScreen.waitForVisibility(iosLoginScreen.slideShareButton);
		iosLoginScreen.findElement(iosLoginScreen.slideShareButton).click();
		iosLoginScreen.waitForVisibility(iosLoginScreen.userName);
		iosLoginScreen.findElement(iosLoginScreen.userName).sendKeys(userName);
		iosLoginScreen.findElement(iosLoginScreen.password).sendKeys(password);
		iosLoginScreen.findElement(iosLoginScreen.signInButton).click();
		iosLoginScreen.waitForVisibility(iosLoginScreen.continueButton);
		Log.info("Login verified for iOS app");*/
	}

	

}

