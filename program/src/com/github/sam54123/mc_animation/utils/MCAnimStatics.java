package com.github.sam54123.mc_animation.utils;

public class MCAnimStatics 
{
	public static String formatPath(String path)
	{
		path = path.replace("\\", "/");
		
		if (!path.substring(path.length() - 1).matches("/"))
		{
			path = path+"/";
		}
		
		return path;
	}
	
	public static boolean boolFromString(String string)
	{
		// If the user entered a number, return true if it's not 0
		try
		{
			int integer = Integer.parseInt(string);
			if (integer >= 1)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(NumberFormatException e) {} 
		
		string = string.toLowerCase();
		char letter = string.charAt(0);
		
		if (letter == 't')
		{
			return true;
		}
		else 
		{
			return false;
		}
		
	}
}
