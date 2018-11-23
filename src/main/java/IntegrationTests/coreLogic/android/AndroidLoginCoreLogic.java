package IntegrationTests.coreLogic.android;

import logger.Log;

import org.openqa.selenium.WebDriver;


import IntegrationTests.screens.android.AndroidLoginScreen;
import IntegrationTests.coreLogic.base.*;


/**
 * contains all methods to login on android app
 */
public class AndroidLoginCoreLogic extends LoginCoreLogic {
    AndroidLoginScreen androidLoginScreen;


    public AndroidLoginCoreLogic(WebDriver driver) {
        androidLoginScreen = new AndroidLoginScreen(driver);
    }

    /**
     * method to login to android app
     *
     * @param userName emailId to be used for login
     * @param password - valid password
     */
    @Override
    public void verifyLoginScenario(String userName, String password) throws InterruptedException {
        androidLoginScreen.waitForVisibility(androidLoginScreen.loginViaSlideShare);
        androidLoginScreen.findElement(androidLoginScreen.loginViaSlideShare).click();
        androidLoginScreen.waitForVisibility(androidLoginScreen.userName);
        androidLoginScreen.findElement(androidLoginScreen.userName).sendKeys(userName);
        androidLoginScreen.findElement(androidLoginScreen.password).sendKeys(password);
        androidLoginScreen.findElement(androidLoginScreen.signInButton).click();


        Log.info("Login Successful");


        //	verify if "Get Started" button is displayed
        androidLoginScreen.waitForVisibility(androidLoginScreen.startedButton);
        androidLoginScreen.findElement(androidLoginScreen.startedButton).click();
        androidLoginScreen.waitForVisibility(androidLoginScreen.titleBar);

        // scroll down twice with each duration of 500 ms
        androidLoginScreen.scrollDown(2, 500);
        androidLoginScreen.waitForVisibility(androidLoginScreen.searchIcon);

        // long press search icon
        androidLoginScreen.longPress(androidLoginScreen.searchIcon);

        // press back key
        androidLoginScreen.back();

        // below code will enable airplane mode on the device
        //  androidLoginScreen.setNetworkConnection(true,false,false);

    }


}

