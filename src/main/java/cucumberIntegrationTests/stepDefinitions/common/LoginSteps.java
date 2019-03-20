package cucumberIntegrationTests.stepDefinitions.common;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberIntegrationTests.screens.android.AndroidLoginScreen;
import cucumberIntegrationTests.screens.iOS.IOSLoginScreen;
import UITestFramework.CreateSession;
import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class LoginSteps {
    AndroidLoginScreen androidLoginScreen;
    IOSLoginScreen iosLoginScreen;
    WebDriver driver;
    String userName;
    String password;
    Properties configFileObject;


    public LoginSteps(CreateSession createSession) {
        driver = createSession.driver;
        configFileObject = CreateSession.localeConfigProp;
    }

    @Given("User has slideshare {string} app")
    public void userHasSlideshareApp(String invokeDriver) {
        if (invokeDriver.equalsIgnoreCase("android")) {
            androidLoginScreen = new AndroidLoginScreen(driver);
        } else if (invokeDriver.equalsIgnoreCase("iOS")) {
            iosLoginScreen = new IOSLoginScreen(driver);
        }
    }


    @And("username and password is {string}")
    public void usernameAndPasswordIs(String credentialsValidations) {
        if(credentialsValidations.equalsIgnoreCase("valid")){
            userName = configFileObject.getProperty("userName");
            password = configFileObject.getProperty("password");
        }
        else{
            userName = configFileObject.getProperty("abc@gmail.com");
            password = configFileObject.getProperty("password");
        }
    }

    @When("user enters credentials")
    public void userEntersCredentials() {
        androidLoginScreen.waitForVisibility(androidLoginScreen.loginViaSlideShare);
        androidLoginScreen.findElement(androidLoginScreen.loginViaSlideShare).click();
        androidLoginScreen.waitForVisibility(androidLoginScreen.userName);
        androidLoginScreen.findElement(androidLoginScreen.userName).sendKeys(userName);
        androidLoginScreen.findElement(androidLoginScreen.password).sendKeys(password);
    }

    @And("taps on {string} button")
    public void tapsOnButton(String arg0) {
        androidLoginScreen.findElement(androidLoginScreen.signInButton).click();


    }

    @Then("{string} button should be visible")
    public void buttonShouldBeVisible(String button) {
        //	verify if "Get Started" button is displayed
        if(button.equalsIgnoreCase("Get Started")) {
            androidLoginScreen.waitForVisibility(androidLoginScreen.startedButton);
            androidLoginScreen.findElement(androidLoginScreen.startedButton).click();
            androidLoginScreen.waitForVisibility(androidLoginScreen.titleBar);
        }
    }

    @And("user should be able to scroll")
    public void userShouldBeAbleToScroll() {
        // scroll down twice with each duration of 500 ms
        androidLoginScreen.scrollDown(2, 500);
        androidLoginScreen.waitForVisibility(androidLoginScreen.searchIcon);
    }

    @And("long press the search icon")
    public void longPressTheSearchIcon() {
        // long press search icon
        androidLoginScreen.longPress(androidLoginScreen.searchIcon);
    }
}
