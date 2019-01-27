package com.github.sam54123.mc_animation.system;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.*;

import com.github.sam54123.mc_animation.system.Animation;
import com.github.sam54123.mc_animation.utils.JSONUtils;
class AnimFactory0_2 extends AnimFactoryBase
{
    @Override
	public Animation loadAnimation(String path) 
	{
        System.out.println("Opening "+path);
		
		// Validate path
		
		File file = new File(path);
		
		Animation animation = new Animation();
		
		animation.path = file.getAbsolutePath();
		
		// Create animation
		try
		{
			animation.jsonObject = JSONUtils.getJSONObjectFromFile(path);
		}
		catch(JSONException e)
		{
			System.out.println("Incorrectly Formatted .mcanim");
			return null;
		}
		
		if (!validate(animation.jsonObject))
		{
			System.out.println("Incorrectly Formatted .mcanim");
			return null;
		}
		
		animation.commands = new ArrayList<AnimCommand>();
		
		interpritJSON(animation, animation.jsonObject); 
		
		// set name
		animation.name = path.substring((path.lastIndexOf("\\")+1), path.lastIndexOf("."));

		return animation;
	}

	private void interpritJSON(Animation animation, JSONObject jsonObject) 
	{
		String version = jsonObject.getString("version");
		
		// output frames to frames array
		if (version.matches(getVersion()))
		{
			System.out.println("Interpreting mcanim version "+version);
			
			JSONArray jsonArray = jsonObject.getJSONArray("frames");
			JSONObject object;
			String command;
			
			// get ID
			animation.id = jsonObject.getInt("id");
			
			// get is Looping
			animation.looping = jsonObject.getBoolean("looping");
			
			// resetWhenDone is optional
			try
			{
				animation.resetPos = jsonObject.getBoolean("resetPos");
			}
			catch(JSONException e)
			{
				animation.resetPos = false;
			}
			
			// create new AnimFrame array of the right length
			animation.frames = new AnimFrame[jsonArray.length()];
			
			// Iterate over entire JSON array
			for(int i = 0; i < jsonArray.length(); i++)
			{
				object = jsonArray.getJSONObject(i);
				
				// Make a new AnimFrame with the right values
				
				
				animation.frames[i] = new AnimFrame(JSONArrayToFloat(object.getJSONArray("body")),
					JSONArrayToFloat(object.getJSONArray("left_arm")),
					JSONArrayToFloat(object.getJSONArray("right_arm")),
					JSONArrayToFloat(object.getJSONArray("left_leg")),
					JSONArrayToFloat(object.getJSONArray("right_leg")),
					JSONArrayToFloat(object.getJSONArray("head")),
					JSONArrayToFloat(object.getJSONArray("location")),
					(float)object.getDouble("rotation"));
				
				
				// Output command (if exists) to commands arraylist
				
				command = getCommand(object);
				
				if (command != null && !command.matches(""))
				{
					animation.commands.add(new AnimCommand(command, i));
				}
				
			}
			
		}
		else
		{
			System.out.println("Unknown file version: " + version);
		}
	}

	private boolean validate(JSONObject jsonObject)
	{
		// Check for version
		String version;
		try
		{
			version = jsonObject.getString("version");
		} catch(JSONException e) {
			return false;
		}
		
		
		if (version.matches(getVersion()))
		{
			// Check for ID
			try 
			{
				jsonObject.getInt("id");
			} catch(JSONException e) {
				return false;
			}
			
			// Check for looping
			try 
			{
				jsonObject.getBoolean("looping");
			} catch(JSONException e) {
				return false;
			}
			
			// Check frames
			JSONArray frames;
			try
			{
				frames = jsonObject.getJSONArray("frames");
			} catch(JSONException e) {
				return false;
			}
			
			
			// Check each frame
			JSONObject frame;
			for (int i = 0; i < frames.length(); i++)
			{
				frame = frames.getJSONObject(i);
				
				try 
				{
					if (!checkArray(frame.getJSONArray("body")) || !checkArray(frame.getJSONArray("left_arm")) ||
							!checkArray(frame.getJSONArray("right_arm")) || !checkArray(frame.getJSONArray("left_leg")) ||
							!checkArray(frame.getJSONArray("right_leg")) || !checkArray(frame.getJSONArray("head")) || 
							!checkArray(frame.getJSONArray("location")))
					{
						return false;
					}
					
				} catch(JSONException e)
				{
					return false;
				}
				
				try
				{
					frame.getDouble("rotation");
				} catch (JSONException e) {
					return false;
				}
				
			}
		}
		else
		{
			System.out.println("Unsupported version: "+ version);
			return false;
		}
		return true;
	}
	
	// makes sure array is proper array of 3 integers
	private static boolean checkArray(JSONArray array)
	{
		
		if (array == null || array.length() != 3)
		{
			return false;
		}
		
		return true;
	}

	@Override
	protected String getVersion() {
		return "0.2";
	}
}
