package com.github.sam54123.mc_animation.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class MCAnimValidator {
	
	public static boolean validate(JSONObject jsonObject) {
		
		// Check for version
		String version;
		try {
			version = jsonObject.getString("version");
		} catch(JSONException e) {
			return false;
		}
		
		
		if (new ArrayList<String>(Arrays.asList(ProgramConstants.SUPPORTED_VERSIONS)).contains(version)) {
			// Check for ID
			try {
				jsonObject.getInt("id");
			} catch(JSONException e) {
				return false;
			}
			
			// Check for looping
			try {
				jsonObject.getBoolean("looping");
			} catch(JSONException e) {
				return false;
			}
			
			// Check frames
			JSONArray frames;
			try {
				frames = jsonObject.getJSONArray("frames");
			} catch(JSONException e) {
				return false;
			}
			
			
			// Check each frame
			JSONObject frame;
			for (int i = 0; i < frames.length(); i++) {
				frame = frames.getJSONObject(i);
				
				try {
					if (!checkArray(frame.getJSONArray("body")) || !checkArray(frame.getJSONArray("left_arm")) ||
							!checkArray(frame.getJSONArray("right_arm")) || !checkArray(frame.getJSONArray("left_leg")) ||
							!checkArray(frame.getJSONArray("right_leg")) || !checkArray(frame.getJSONArray("head")) || 
							(!checkArray(frame.getJSONArray("location")) && !version.matches("0.1")))
					{
						return false;
					}
					
				} catch(JSONException e) {
					return false;
				}
				
				try {
					frame.getDouble("rotation");
				} catch (JSONException e) {
					return false;
				}
			}
		}
		else{
			System.out.println("Incorrect version: "+ version +" Expected "+ ProgramConstants.ANIMVERSION);
			return false;
		}
		return true;
	}
	
	// makes sure array is proper array of 3 integers
	private static boolean checkArray(JSONArray array) {
		
		if (array == null || array.length() != 3)
		{
			return false;
		}
		
		return true;
	}
}
