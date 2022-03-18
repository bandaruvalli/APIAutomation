package api.com.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;





public class ReadTestData {
	
	public static JSONObject getJsonData() throws IOException, ParseException
	{
		String FilePath="C:\\Users\\PANBANDA\\eclipse-workspace\\APIAutomation\\lib\\Resources\\Testdata\\testdata.json";
		File filename= new File(FilePath);
		//convert json file string
		String json=FileUtils.readFileToString(filename, "UTF-8");
		//parse the string into oject
		Object obj=new JSONParser().parse(json);
		//give jsonobject that i can return it to the function everytime i called
		JSONObject jsonobject=(JSONObject) obj;
		return jsonobject;
		
		
	}
	
	public static String getTestData(String Keyname) throws IOException, ParseException
	{
		String testData;
		return testData= (String) getJsonData().get(Keyname);
	}

}
