package api.com.util;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

public class extentReport {
	public static ExtentReports extentreport = null;

	public static ExtentTest extentlog;

    

	public static void initialize(String path) {

		if (extentreport == null) {

			extentreport = new ExtentReports(path, true);
		//	extentreport.startTest("Verifiy the title");

			//extentreport.addSystemInfo("Host Name", System.getProperty("user.name"));

			extentreport.addSystemInfo("Environment", Helperclass.propertyReader("executionEnv"));

			extentreport.loadConfig(new File(System.getProperty("user.dir") + "/extentconfig.xml"));

		}

	}
  public static void setinstanceNull()
  {
	 extentreport=null;
  }
  //Display response time and status code in Extent "report DO NOT USE"
  public static void ExtentReport_logs(Response resp)
  {
	  ExtentReportInfoLog("Response time is" + String.valueOf(resp.getTime())+ "ms");
	  ExtentReportInfoLog("Response of API is" + String.valueOf(resp.getStatusCode()));
  }
public static void ExtentReportInfoLog(String message) {
	// TODO Auto-generated method stub
	extentlog.log(LogStatus.INFO,message);
}
public static void ExtentReportErrorLog(String message) {
	// TODO Auto-generated method stub
	extentlog.log(LogStatus.ERROR,message);
}

}
