package api.com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Properties;

import org.json.simple.parser.ParseException;

public class Helperclass {
	
	private final static String  filepath= System.getProperty("user.dir")+ "\\common.properties";
	public static String propertyReader(String keyname)
	{
		String value=null;
		try 
		      ( InputStream input=new FileInputStream(filepath))
		       {
		       Properties prop=new Properties();
		       prop.load(input);
		       value=prop.getProperty(keyname);
		}
		catch(IOException Ex)
		{
			Ex.printStackTrace();
		}
		return value;
	}
	
	public static String getUserUrl() throws IOException, ParseException
	{
        String userUrl= Helperclass.propertyReader("baseUrl")+ReadTestData.getTestData("endPoint");
        return userUrl;
	}
	
	public static String getBasicAuthUrl() throws IOException, ParseException
	{
        String basicAuthUrl= Helperclass.propertyReader("basicAuthUrl")+ReadTestData.getTestData("basicAuthendpoint");
        return basicAuthUrl;
	}
	
	public static String getUserName()
	{
        String userName= Helperclass.propertyReader("UserName");
		return userName;
     
	}
	public static String getUserPassword()
	{
		   String userpassword= Helperclass.propertyReader("Password");
		   return userpassword;
	}
	
	/*
	 * Create Folder
	 */
	public static void CreateFolder(String path)
	{
		File file=new File(path);
		if(!file.exists())
		{
			file.mkdir();
		}
		else
		{
			System.out.println("Folder Already Created");
		}
		
	}
	/*
	 * Return time Stamp
	 */
	public static String TimeStamp()
	{
		Date now=new Date(0);
		String TimeStamp=now.toString().replace(":", "-");
		return TimeStamp;
	}
}
