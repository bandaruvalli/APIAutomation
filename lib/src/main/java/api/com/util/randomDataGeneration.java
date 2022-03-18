package api.com.util;

import java.security.SecureRandom;

public class randomDataGeneration {
	public static SecureRandom random=new SecureRandom();
	
	public String stringDataGeneration(int length)
	{
		String text="abc";
		StringBuilder sb=new StringBuilder(length);
		for(int i=0;i<length;i++)
			sb.append(text.charAt(random.nextInt(text.length())));
		return sb.toString();
		
	}

}
