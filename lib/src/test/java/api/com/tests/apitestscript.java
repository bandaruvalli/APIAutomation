
package api.com.tests;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import api.com.core.BaseTest;
import api.com.pojo.cityModels;
import api.com.pojo.postpojo;
import api.com.pojo.postpojocomplex;
import api.com.util.Helperclass;
import api.com.util.ReadTestData;
import api.com.util.extentReport;
import api.com.util.randomDataGeneration;
import io.restassured.response.Response;


public class apitestscript extends BaseTest{
            private String Accesstoken;
            static randomDataGeneration data=new randomDataGeneration();
            private static String name=data.stringDataGeneration(6);
            private static String job=data.stringDataGeneration(7);
	   //TestNG
	@Test (description="validateStatusCodeGetUsers",groups="SmokeSuite")
	public void validateStatusCodeGetUsers() throws IOException, ParseException
	{
		//System.out.println(ReadTestData.getTestData("getUsersRequestUri"));
		extentReport.extentlog=extentReport.extentreport.startTest("ValidatestatusCodeGetUsers", "Validate the status code for get users endpoint");
		Response resp= given()
				        .when().get(ReadTestData.getTestData("getUsersRequestUri"));
		int actualstatuscode=resp.statusCode();
		/*doubt that if i get the expeced status code using testdata then it is throwing error */
	 //	assertEquals(actualstatuscode, ReadTestData.getTestData("ExpectedStatusCode"));
		assertEquals(actualstatuscode, 200);						       
	}
	
	@Test(description="validateStatusCodeGetUser",groups="SmokeSuite")
	public void validateStatusCodeGetUser() throws IOException, ParseException
	{
		extentReport.extentlog=extentReport.extentreport.startTest("ValidatestatusCodeGetUser", "Validate the status code for get user endpoint");
		Response resp= given()
				        .when().get(Helperclass.getUserUrl());
		int actualstatuscode=resp.statusCode();
		assertEquals(actualstatuscode, 200);
								       
	}
	
	@Test(description="validateStatusCodeGetUserforinvaliduri",groups="SmokeSuite")
	public void validateStatusCodeGetUserforinvaliduri()
	{
		extentReport.extentlog=extentReport.extentreport.startTest("validateStatusCodeGetUserforinvaliduri", "Validate the status code for get user invalid uri");
		Response resp= given()
				        .when().get("https://reqres.in/api1/users?page=3");
		int actualstatuscode=resp.statusCode();
		assertEquals(actualstatuscode, 404);
								       
	}
	
	@Test(description="validateStatusCodeGetUserwithqueryparam",enabled=true,groups={"SmokeSuite","RegressionSuite"})
	public void validateStatusCodeGetUserwithqueryparam() throws IOException, ParseException
	{
		extentReport.extentlog=extentReport.extentreport.startTest("validateStatusCodeGetUserwithqueryparam", "Validate the status code for get user with query param");
		Response resp= given().queryParam("page",4)
				      .when()
				      .get("https://reqres.in/api/users"); //restassured
		int actualstatuscode=resp.statusCode(); //restassured
		assertEquals(actualstatuscode, 200); //Testng
		//System.out.println(resp.asString());
	
								       
	}
	
	@Test(description="validateGetUsersResponseBody",enabled=true,groups={"SmokeSuite","RegressionSuite"})
	public void validateGetUsersResponseBody()
	{
		extentReport.extentlog=extentReport.extentreport.startTest("validateGetUsersResponseBody", "Validate the status code for get user with query param");
		Response resp= given().queryParam("page", 4)
				        .when().get("https://reqres.in/api/users"); //restassured
		int actualstatuscode=resp.statusCode(); //restassured
		assertEquals(actualstatuscode, 200); //Testng
		//System.out.println(resp.asString());
	    int actualtotalvalue=resp.path("total");
		assertEquals(actualtotalvalue,12);
		
								       
	}
	
	@Test(description="validateGetUsersSupportUrlResponseBody",enabled=true,groups="RegressionSuite")
	public void validateGetUsersSupportUrlResponseBody()
	{
		extentReport.extentlog=extentReport.extentreport.startTest("validateGetUsersSupportUrlResponseBody", "Validate the supporturl from the responsebody");
		Response resp= given().queryParam("page", 4)
				        .when().get("https://reqres.in/api/users"); //restassured
		int actualstatuscode=resp.statusCode(); //restassured
		assertEquals(actualstatuscode, 200); //Testng
		System.out.println(resp.asString());
	    String actualsupporturl=resp.path("support.url");
		System.out.println(actualsupporturl);
		assertEquals(actualsupporturl,"https://reqres.in/#support-heading");
										       
	}
	
	@Test
	public void validateGetUsersEmailResponseBody()
	{
		Response resp= given().queryParam("page", 2)
				        .when().get("https://reqres.in/api/users"); //restassured
		int actualstatuscode=resp.statusCode(); //restassured
		assertEquals(actualstatuscode, 200); //Testng
		System.out.println(resp.asString());
		String actualemail=resp.path("data[0].email");
	    assertEquals(actualemail,"michael.lawson@reqres.in");
		
								       
	}
	
	@Test
	public void validateResponseBodyGetHeader()
	{
		Response resp= given().headers("Content-Type","application/json")
				        .when()
				        .get("https://gorest.co.in/public-api/users"); //restassured
		int actualstatuscode=resp.statusCode(); //restassured
		assertEquals(actualstatuscode, 200); //Testng
		System.out.println(resp.asString());							       
	}
	
	@Test
	public void validateResponseBodyGetPathParam()
	{
		String racseasonvalue="2017";
		Response resp= given().pathParam("race", racseasonvalue)
				        .when()
				        .get("http://ergast.com/api/f1/{race}/circuits.json"); //restassured
		int actualstatuscode=resp.statusCode(); //restassured
		assertEquals(actualstatuscode, 200); //Testng
		System.out.println(resp.asString());							       
	}
	
	@Test
	public void validateResponseBodyBasicAuth() throws IOException, ParseException
	{
		
		Response resp= given()
				       .auth()
				       .basic(Helperclass.getUserName(),Helperclass.getUserPassword())
				       .when().get(Helperclass.getBasicAuthUrl()); //restassured
		int actualstatuscode=resp.statusCode(); //restassured
		assertEquals(actualstatuscode, 200); //Testng
	//	System.out.println(resp.asString());	
		Accesstoken=resp.path("token");
	}
	
	@Test
	public void validateResponseBodyDigestAuth()
	{
		
		Response resp= given()
				       .auth()
				       .digest("postman", "password")
				       .when().get("https://postman-echo.com/basic-auth/"); //restassured
		int actualstatuscode=resp.statusCode(); //restassured
		assertEquals(actualstatuscode, 200); //Testng
		System.out.println(resp.asString());							       
	}
	
	@Test
	public void validateResponseBodyGetOAuth1Auth()
	{
		
		Response resp= given()
				       .auth()
				       .oauth("consumerkey", "consumerSecret","accessToken","secretToken")				       
				       .when().get("https://reqres.in/api/users/2"); //restassured
		int actualstatuscode=resp.statusCode(); //restassured
		assertEquals(actualstatuscode, 200); //Testng
		System.out.println(resp.asString());							       
	}
	
	@Test
	public void validateResponseBodyGetOAuth2Auth() throws IOException, ParseException
	{
		
		Response resp= given()
				       .auth()
				       .oauth2("secretToken")			       
				       .when().get(Helperclass.getUserUrl()); //restassured
		int actualstatuscode=resp.statusCode(); //restassured
		assertEquals(actualstatuscode, 200); //Testng
		//System.out.println(resp.asString());							       
	}
	
	@Test
	public void validateResponseBodyGetOAuth2withHeader()
	{
		
		Response resp= given()
				       .header("Authorization", Accesstoken)
				       .when()
				       .get("https://reqres.in/api/users/2"); //restassured
		int actualstatuscode=resp.statusCode(); //restassured
		assertEquals(actualstatuscode, 200); //Testng
		System.out.println(resp.asString());							       
	}
	
	@Test(description="validate the response body for the create users")
	public void validateResponseBodyPostPojoRequest() throws IOException
	{
		FileInputStream file=new FileInputStream(new File(System.getProperty("user.dir")+ "/Resources/Testdata/postpojorequest.json"));
		Response resp= given().body(IOUtils.toString(file))
				       .header("Content-Type","application/json")
				       .when()
				       .post("https://reqres.in/api/users"); //restassured
	//	int actualstatuscode=resp.statusCode(); //restassured
		assertEquals(resp.statusCode(),201); //Testng
		System.out.println(resp.body().asString());							       
	}
	
	@Test(description="validate the response body for the create users using postpojo")
	public void validateResponseBodyPojoRequest() throws IOException
	{	
		
		Response resp= given()
				       .body(pojo())
				       .header("Content-Type","application/json")
				       .when()
				       .post("https://reqres.in/api/users"); //restassured
	//	int actualstatuscode=resp.statusCode(); //restassured
		assertEquals(resp.statusCode(),201); //Testng
		//System.out.println(resp.body().asString());							       
	}
	
	@Test(description="validate the response body for the create users using postpojocomplexdata")
	public void validateResponseBodyPojoComplexRequest() throws IOException
	{	
		
		
		Response resp= given()
				       .body(pojocomplex())
				       .header("Content-Type","application/json")
				       .when()
				       .post("https://reqres.in/api/users"); //restassured
	//	int actualstatuscode=resp.statusCode(); //restassured
		assertEquals(resp.statusCode(),201); //Testng
		//System.out.println(resp.body().asString());							       
	}
	public postpojo pojo()
	{
		postpojo pojo=new postpojo();
		pojo.setName(name);
		pojo.setJob(job);
		return pojo;
	}
	
	public postpojocomplex pojocomplex()
	{
		postpojocomplex pojocomplex=new postpojocomplex();
		pojocomplex.setName("morpheus");
		List<String> job=new ArrayList<>();
		job.add("tester");
		job.add("developer");
		job.add("support");
		pojocomplex.setJobs(job);
		
		cityModels city=new cityModels();
		city.setCityname("bangalore");
		city.setTemperature("30");
		cityModels city2=new cityModels();
		city2.setCityname("delhi");
		city2.setTemperature("40");
		List<cityModels> citylist=new ArrayList<>();
		citylist.add(city);
		citylist.add(city2);
		pojocomplex.setCitymodels(citylist);
				
		return pojocomplex;
	}
}
