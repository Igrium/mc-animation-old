package com.github.sam54123.mc_animation.utils;


public class MCAnimStatics {
	public static String formatPath(String path) {
		path = path.replace("\\", "/");
		
		if (!path.substring(path.length() - 1).matches("/")) {
			path = path+"/";
		}
		
		return path;
	}
	
	public static boolean boolFromString(String string) {
		// If the user entered a number, return true if it's not 0
		try {
			int integer = Integer.parseInt(string);
			if (integer >= 1) {
				return true;
			} else {
				return false;
			}
		}
		catch(NumberFormatException e) {} 
		
		string = string.toLowerCase();
		char letter = string.charAt(0);
		
		if (letter == 't') {
			return true;
		} else {
			return false;
		}
	}
	
	// Rotates the given vector around the origin by the given value in radians.
	public static float[] rotateVector2D(float[] vec, double rot) {
		float[] vector = new float[2];
		vector[0] = (float)(vec[0] * Math.cos(rot) - vec[1] * Math.sin(rot));
	    vector[1] = (float)(vec[0] * Math.sin(rot) + vec[1] * Math.cos(rot));
		
		return vector;
	}

}
