package com.github.sam54123.mc_animation.system;

import java.util.ArrayList;

import org.json.*;

import com.github.sam54123.mc_animation.utils.JSONUtils;

public class Animation 
{
	
	public JSONObject jsonObject;
	private int id;
	private AnimFrame[] frames;
	private ArrayList<AnimCommand> commands;
	
	
	// Import an mcanim (json) file
	public Animation(String path)
	{
		jsonObject = JSONUtils.getJSONObjectFromFile(path);
		
		commands = new ArrayList<AnimCommand>();
		
		interpretJSON(jsonObject); 
		
	}
	
	public AnimFrame[] getFramesAsArray() 
	{
		return frames;
	}
	
	public AnimCommand[] getCommandsAsArray()
	{
		AnimCommand[] array = new AnimCommand[commands.size()];
		for (int i = 0; i < commands.size(); i++)
		{
			array[i] = commands.get(i);
		}
		
		return array;
	}
	
	public int length()
	{
		return frames.length;
	}
	
	public AnimFrame getFrame(int index)
	{
		return frames[index];
	}
	
	public int id()
	{
		return id;
	}
	
	public void setId(int id)
	{
		if (id > 0)
		{
			this.id = id;
		}
		else
		{
			System.out.println("Can't set ID to less than 1");
		}
	}
	
	public String getCompiledAnimName()
	{
		return "anim-"+id;
	}
	
	// interpret the JSONObject and setup all this Animation Object's variables, will crash if invalid JSON.
	private void interpretJSON(JSONObject jsonObject)
	{
		String version = jsonObject.getString("version");
		
		// output frames to frames array
		if (version.matches("0.1"))
		{
			System.out.println("Interpreting mcanim version 0.1");
			
			JSONArray jsonArray = jsonObject.getJSONArray("frames");
			JSONObject object;
			String command;
			
			id = jsonObject.getInt("id");
			
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
				
				command = getCommand(object);
				
				if (command != null && !command.matches(""))
				{
					commands.add(new AnimCommand(command, i));
				}
			}
			
		}
		else
		{
			System.out.println("Unknown file version: " + version);
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
