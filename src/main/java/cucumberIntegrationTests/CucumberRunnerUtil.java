package cucumberIntegrationTests;


import UITestFramework.CreateSession;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.apache.commons.configuration2.Configuration;
import org.junit.runner.RunWith;
import org.testng.annotations.*;

import java.lang.reflect.Method;


@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        features = "src/test/java/cucumberTests/features",
        glue = {"cucumberIntegrationTests"},
        tags = {"@ai"},
        plugin = "json:target/cucumber-report.json"

)
public class CucumberRunnerUtil {

    private TestNGCucumberRunner testNGCucumberRunner;
    CreateSession createSession;
    public static Configuration loadTestData;
    public static String environment;

    @BeforeSuite(alwaysRun = true)
    public void setCreateSession() throws Exception {

        try {
           // create appium session
            createSession = new CreateSession();

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }


    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }


    @Parameters({"os"})
    @BeforeMethod(alwaysRun = true)
    public void driverObjectCreation(String os, Method methodName,
                                     @Optional String browserName){
        try {
            createSession.createDriver(os,methodName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void scenario(PickleEventWrapper pickleEvent, CucumberFeatureWrapper cucumberFeature) throws Throwable {
        testNGCucumberRunner.runScenario(pickleEvent.getPickleEvent());
    }

    @DataProvider
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }


    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }


    @AfterSuite
    public void cleanUp(){
           }


}

