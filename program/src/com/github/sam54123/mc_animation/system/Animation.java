package com.github.sam54123.mc_animation.system;

import java.util.ArrayList;

import org.json.*;

import com.github.sam54123.mc_animation.utils.JSONUtils;

public class Animation 
{
	
	public JSONObject jsonObject;
	
	private AnimFrame[] frames;
	
	
	// Import an mcanim (json) file
	public Animation(String path)
	{
		jsonObject = JSONUtils.getJSONObjectFromFile(path);
		
		/** try
		{
			jsonObject = JSONUtils.getJSONObjectFromFile(path);
		}
		catch(Exception e)
		{
			System.out.println("invalid file");
			return;
		}**/
		
		interpretJSON(jsonObject); 
		
	}
	
	// interpret the JSONObject and setup all this Animation Object's variables, will crash if invalid JSON.
	private void interpretJSON(JSONObject jsonObject)
	{
		String version = jsonObject.getString("version");
		
		// output frames to frames array
		if (true)
		{
			JSONArray jsonArray = jsonObject.getJSONArray("frames");
			
			JSONObject object;
			
			System.out.println("version1");
			
			// create new AnimFrame array of the right length
			frames = new AnimFrame[jsonArray.length()];
			
			// Iterate over entire JSON array
			for(int i = 0; i < jsonArray.length(); i++)
			{
				object = jsonArray.getJSONObject(i);
				
				// Make a new AnimFrame with the right value
				frames[i] = new AnimFrame(JSONArrayToFloat(object.getJSONArray("body")),
						JSONArrayToFloat(object.getJSONArray("left_arm")),
						JSONArrayToFloat(object.getJSONArray("right_arm")),
						JSONArrayToFloat(object.getJSONArray("left_leg")),
						JSONArrayToFloat(object.getJSONArray("right_leg")),
						JSONArrayToFloat(object.getJSONArray("head")));
				
				// Output command (if exists) to commands arraylist
				
			}
		}
	}
	
	// Convert a JSONArray to a float array
	private float[] JSONArrayToFloat(JSONArray array)
	{
		float[] outArray = new float[array.length()];
		
		for (int i = 0; i < array.length(); i++)
		{
			outArray[i] = array.getFloat(i);
		}
		
		
		return outArray;
	}
	
	// returns the command from the JASON object, if any
	private String getCommand(JSONObject object)
	{
		
		try
		{
			return object.getString("command");
		}
		catch(Exception e)
		{
			return null;
		}
	}
}
