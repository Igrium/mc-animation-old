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
}
