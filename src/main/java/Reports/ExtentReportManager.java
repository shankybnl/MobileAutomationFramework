package Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentKlovReporter;
import io.appium.java_client.AppiumDriver;


import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ExtentReportManager {

    private static WebDriver driver = null;
    private static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    public static ExtentTest test;
    public static ExtentReports extent = new ExtentReports() ;






    @BeforeClass
    @SuppressWarnings("Duplicates")
    public static ExtentReports report() {
        String projectName = "Finale";
        try {

            ExtentKlovReporter klovReporter = new ExtentKlovReporter(projectName);
            klovReporter.initMongoDbConnection("135.181.192.22", 27017);
            klovReporter.initKlovServerConnection("135.181.192.22");
            String css = ".r-img {width: 50%;}";
            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
            extent.setSystemInfo("User Name", System.getProperty("user.name"));
            extent.setSystemInfo("Architecture", System.getProperty("os.arch"));
            extent.attachReporter(klovReporter);

        } catch (Exception e) {
            System.out.println(e);
        }
        return extent;
    }

    public static String captureScreenShot(WebDriver driver) throws IOException {

        TakesScreenshot screen = (TakesScreenshot) driver;
        File src = screen.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir");
        String dest = path+"\\Screenshots\\Screenshots" + getCurrentDateAndTime() + ".png";
        File target = new File(dest);
        FileUtils.copyFile(src, target);
        return dest;
    }

    private static String getCurrentDateAndTime() {
        String str = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
            Date date = new Date();
            str = dateFormat.format(date);
            str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }


}