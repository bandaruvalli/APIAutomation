package api.com.core;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.relevantcodes.extentreports.LogStatus;
import api.com.util.Helperclass;
import api.com.util.extentReport;

public class BaseTest {
     public static String baseurl="";
     
     @Parameters({"ReportName","FlowType"})
     @BeforeSuite(alwaysRun=true)
     
    public void config(@Optional("optional name Automation") String reportname, @Optional("API Report") String timestamp)
      throws IOException
     {
    	 //Create the path in which we will create folders to keep html reports
    	 String subfolderpath=System.getProperty("user.dir")+ "/HtmlReports/" + Helperclass.TimeStamp();
    	 
    	 //create subfolder
    	 Helperclass.CreateFolder(subfolderpath);
    	 extentReport.initialize(subfolderpath +"/"+"API_Automation.html");
    	 
    	 }
     @BeforeMethod(alwaysRun=true)
     public static void logBeforeMethod()
     {
    	//final Logging log =Logging.getInstance();
    	  // Log.info("Test Case", "************************");
    	 System.out.println("Test case execution started >>>>>>>>>>>>");
    	  
     }
     @AfterMethod(alwaysRun=true)
 	public void getResult(ITestResult result) {
 		if (result.getStatus() == ITestResult.SUCCESS) {
			extentReport.extentlog.log(LogStatus.PASS, "Test Case : "+ result.getName()+" is passed ");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			extentReport.extentlog.log(LogStatus.FAIL, "Test case : "+ result.getName()+" is failed ");
			extentReport.extentlog.log(LogStatus.FAIL, "Test case is failed due to:  " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentReport.extentlog.log(LogStatus.SKIP, "Test case is Skiped " + result.getName());
		}
 		extentReport.extentreport.endTest(extentReport.extentlog);
     }
     @AfterSuite(alwaysRun=true)
     public void endReport()
     {
    	// extentReport.extentreport.flush(); 
    	 extentReport.extentreport.close();
    	// Logging.setInstanceNull();









     }


}
