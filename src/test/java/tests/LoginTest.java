package tests;
import logger.Log;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import IntegrationTests.coreLogic.base.*;
import IntegrationTests.coreLogic.android.*;
import IntegrationTests.coreLogic.iOS.*;
import UITestFramework.CreateSession;

/**
 * automated test to verify login to android/iOS app.
 */
public class LoginTest extends CreateSession {
	LoginCoreLogic loginCoreLogic;
	String userName;
	String password;
	
	
	/** 
	 * this method instantiate required helpers depending on the platform(android or iOS)
	 * @param invokeDriver android or iOS
	 */
	@Parameters({"os"})
	@BeforeMethod
	public void instantiateHelpers(String invokeDriver){
		
		userName = localeConfigProp.getProperty("userName");
		password = localeConfigProp.getProperty("password");
		
		if (invokeDriver.equalsIgnoreCase("android")){
			loginCoreLogic = new AndroidLoginCoreLogic(driver);
		}																		         
		else if (invokeDriver.equalsIgnoreCase("iOS")){
			loginCoreLogic = new IOSLoginCoreLogic(driver);
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
		loginCoreLogic.verifyLoginScenario(userName, password);
		Log.info("Verified the login");
	}
	
	
}
