package IntegrationTests.Tests;
import logger.Log;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import IntegrationTests.coreLogic.*;
import IntegrationTests.coreLogic.base.*;
import IntegrationTests.coreLogic.android.*;
import IntegrationTests.coreLogic.iOS.*;
import UITestFramework.CreateSession;

/**
 * automated test to verify login to android/iOS app.
 */
public class LoginTest extends CreateSession {
	LoginHelper loginHelper;
	String userName;
	String password;
	
	
	/** 
	 * this method instantiate required helpers depending on the platform(android or iOS)
	 * @param invokeDriver android or iOS
	 */
	@Parameters({"invokeDriver"})	 
	@BeforeMethod
	public void instantiateHelpers(String invokeDriver){
		
		userName = localeConfigProp.getProperty("userName");
		password = localeConfigProp.getProperty("password");
		
		if (invokeDriver.equalsIgnoreCase("android")){
			loginHelper = new AndroidLoginHelper(driver);
		}																		         
		else if (invokeDriver.equalsIgnoreCase("iOS")){
			loginHelper = new IOSLoginHelper(driver);
		}
	}

	/** 
	 * method to verify login to android and iOS app
	 * @throws InterruptedException Thrown when a thread is waiting, sleeping, 
	 * or otherwise occupied, and the thread is interrupted, either before
	 *  or during the activity.
	 */ 
	@Test
	public void LoginVerification() throws InterruptedException {
		Log.info("Running login test");
		loginHelper.verifyLoginScenario(userName, password);
		Log.info("Verified the login");
	}
	
	
}
