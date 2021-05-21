package UITestFramework.retryLogic;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

 

public class ExtentReportR 
{
    static ExtentReports extent;
    public static ExtentReports extentReportGenerator()
    {
    String path= System.getProperty("user.dir") + "\\report\\result.html";     
    ExtentSparkReporter reporter = new ExtentSparkReporter(path);
    reporter.config().setReportName(""); //Give report name
    extent= new ExtentReports();
    extent.attachReporter(reporter);
    extent.setSystemInfo(""); //Set system info whatever you want to show in report
    return extent;

 

}
}