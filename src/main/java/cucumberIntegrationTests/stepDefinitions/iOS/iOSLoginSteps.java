package cucumberIntegrationTests.stepDefinitions.iOS;

import UITestFramework.CreateSession;
import cucumberIntegrationTests.screens.android.AndroidLoginScreen;
import cucumberIntegrationTests.screens.iOS.IOSLoginScreen;

public class iOSLoginSteps {
    IOSLoginScreen iosLoginScreen;

    public iOSLoginSteps(CreateSession createSession) {
        iosLoginScreen = new IOSLoginScreen(createSession.driver);
    }
}
