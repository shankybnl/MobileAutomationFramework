package IntegrationTests.screens.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import UITestFramework.GenericMethods;



/**
 * contains all the locators present on the login Screen
 */
public class AndroidLoginScreen extends GenericMethods {

	public AndroidLoginScreen(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	

	// Locators on the login screen
	public By loginViaSlideShare = By.xpath("//android.widget.TextView[@text='Sign in with your SlideShare account']");
	public By userName = By.xpath("//android.widget.RelativeLayout//android.widget.EditText");
	public By password = By.xpath("//android.widget.RelativeLayout//android.widget.EditText[2]");
	public By signInButton = By.xpath("//android.widget.RelativeLayout//android.widget.Button");
	public By startedButton = By.id("net.slideshare.mobile:id/get_started_button");


	public By searchIcon = By.id("net.slideshare.mobile:id/action_search");
	public By titleBar = By.id("net.slideshare.mobile:id/title");



	
}

