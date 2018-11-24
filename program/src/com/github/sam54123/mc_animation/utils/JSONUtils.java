package com.github.sam54123.mc_animation.utils;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

import org.json.JSONObject;

public class JSONUtils 
{
	public static String getJSONStringFromFile(String path)
	{
		// Open file
		Scanner scanner;
		try 
		{
			InputStream in = FileHandle.inputStreamFromFile(path);
			scanner = new Scanner(in);
			// Get JSON as string without spaces or newlines
			String json = scanner.useDelimiter("\\Z").next();
			
			// Close file
			scanner.close();
			
			return json;
			
		}
		catch(Exception e)
		{
			System.out.println(e.getStackTrace());
			return null;
		}
		
		
	}
	
	public static JSONObject getJSONObjectFromFile(String path)
	{
		File file = new File(path);
		if (!file.exists())
		{
			System.out.println("Invalid Path");
			return null;
		}
		
		String string = getJSONStringFromFile(path);
		return new JSONObject(getJSONStringFromFile(string));
	
	}
	
	
	
}
