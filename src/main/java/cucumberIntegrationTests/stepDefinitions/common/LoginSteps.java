package cucumberIntegrationTests.stepDefinitions.common;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberIntegrationTests.CreateSessionCucumber;
import cucumberIntegrationTests.screens.android.AndroidLoginScreen;
import cucumberIntegrationTests.screens.iOS.IOSLoginScreen;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class LoginSteps {
    AndroidLoginScreen androidLoginScreen;
    IOSLoginScreen iosLoginScreen;
    WebDriver driver;
    String userName;
    String password;
    Properties configFileObject;
    BaseSteps baseStepsContext;



    public LoginSteps(BaseSteps baseSteps) {
        baseStepsContext = baseSteps;
        driver = baseStepsContext.driver;
        androidLoginScreen = baseStepsContext.androidLoginScreen;
        iosLoginScreen = baseStepsContext.iosLoginScreen;
        configFileObject = CreateSessionCucumber.localeConfigProp;
    }



    @And("user has \"([^\"]*)\" username and password")
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

    @And("taps on \"([^\"]*)\" button")
    public void tapsOnButton(String arg0) {
        androidLoginScreen.findElement(androidLoginScreen.signInButton).click();


    }

    @Then("\"([^\"]*)\" button should be visible")
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
