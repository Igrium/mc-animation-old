package com.github.sam54123.mc_animation.utils;

import java.io.InputStream;

public class FileHandle 
{
	public static InputStream inputStreamFromFile(String path)
	{
		try
		{
			InputStream inputStream = FileHandle.class.getResourceAsStream(path);
			return inputStream;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
		
	}
}
