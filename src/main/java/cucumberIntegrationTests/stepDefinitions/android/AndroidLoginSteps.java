package cucumberIntegrationTests.stepDefinitions.android;

import UITestFramework.CreateSession;
import cucumberIntegrationTests.screens.android.AndroidLoginScreen;

public class AndroidLoginSteps {
    AndroidLoginScreen androidLoginScreen;


    public AndroidLoginSteps(CreateSession createSession) {
        androidLoginScreen = new AndroidLoginScreen(createSession.driver);
    }

}
