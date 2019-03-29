package cucumberIntegrationTests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.junit.runner.RunWith;
import org.testng.annotations.*;
import java.lang.reflect.Method;


@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        features = "src/test/java/tests/cucumberTests/features",
        glue = {"cucumberIntegrationTests/stepDefinitions"},
        plugin = {"pretty", "html:target/cucumber"}

)
public class CucumberRunnerUtil {

    private TestNGCucumberRunner testNGCucumberRunner;
    public CreateSessionCucumber createSession;

    @BeforeSuite(alwaysRun = true)
    public void setCreateSession() throws Exception {

     // write if anything needs to be set up once before tests run. e.g. connection to database
    }


    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }


    @Parameters({"os"})
    @BeforeMethod(alwaysRun = true)
    public void driverObjectCreation(String os, Method methodName){

        // write code here in case something needs to be run before each scenario
    }


    @Test(groups = "cucumber", description = "Runs Cucumber Feature",dataProvider = "features" )
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }


    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }



    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }


    @AfterSuite
    public void cleanUp(){
        // close if something enabled in @before suite. e.g. closing connection to DB
    }


}
