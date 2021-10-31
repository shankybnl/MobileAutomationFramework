package UITestFramework.retryLogic;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

 

public class ExtentReportSetup 
{
    static ExtentReports extent;
    public static ExtentReports extentReportGenerator()
    {
    String path= System.getProperty("user.dir") + File.separator + "target" + File.separator + "report" + File.separator + "result.html";    
    ExtentSparkReporter reporter = new ExtentSparkReporter(path);
    reporter.config().setReportName(""); //Give report name
    extent= new ExtentReports();
    extent.attachReporter(reporter);
    extent.setSystemInfo(""); //Set system info whatever you want to show in report
    return extent;
}
}
