package com.github.sam54123.mc_animation.system;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.json.*;

import com.github.sam54123.mc_animation.utils.JSONUtils;
import com.github.sam54123.mc_animation.utils.MCAnimStatics;
import com.github.sam54123.mc_animation.utils.MCAnimValidator;
import com.github.sam54123.mc_animation.utils.ProgramConstants;

public class Animation 
{
	
	public JSONObject jsonObject;
	public String name;
	public boolean looping;
	public String path;
	public boolean resetPos;
	private ArrayList<AnimCommand> commands;
	private int id;
	private AnimFrame[] frames;
	
	public boolean isSaved = true;
	
	
	private boolean isInitialized = false;
	
	
	// Import an mcanim (json) file
	public Animation(String path)
	{
		
		
		System.out.println("Opening "+path);
		
		// Validate path
		
		File file = new File(path);
		
		if (!file.exists())
		{
			System.out.println("Unknown File");
			return;
		}
		
		try
		{
			String extention = path.substring(path.lastIndexOf("."));
			if (!extention.matches(".mcanim"))
			{
				System.out.println("Unknown filetype: " + extention);
				return;
			}
		}
		catch(IndexOutOfBoundsException e)
		{
			System.out.println("Unknown filetype");
			return;
		}
			
		
		
		this.path = file.getAbsolutePath();
		
		// Create animation
		try
		{
			jsonObject = JSONUtils.getJSONObjectFromFile(path);
		}
		catch(JSONException e)
		{
			System.out.println("Incorrectly Formatted .mcanim");
			return;
		}
		
		if (!MCAnimValidator.validate(jsonObject))
		{
			System.out.println("Incorrectly Formatted .mcanim");
			return;
		}
		
		commands = new ArrayList<AnimCommand>();
		
		interpretJSON(jsonObject); 
		
		// set name
		name = path.substring((path.lastIndexOf("\\")+1), path.lastIndexOf("."));
		
		isInitialized = true;
	}
	
	// Saves the animation as a .mcanim file and updates path accordingly
	public File save(String path) throws IOException
	{
		outputToJSON(jsonObject);
		
		// write the json to file
		FileWriter file = new FileWriter(path);
		file.write(jsonObject.toString());
		file.close();
			
		this.path = path;
		System.out.println("Sucessfully wrote to "+ path);
		
		isSaved = true;
		return new File(path);
		
	}
	
	public File save() throws IOException
	{
		return save(path);
	}
	
	// Returns the folder this animatin is in, excluding the file itself
	public String getFolder()
	{
		String path = MCAnimStatics.formatPath(this.path);
		
		// get the last index of '/'
		int index = path.substring(0, path.length() - 1).lastIndexOf('/');
		
		return path.substring(0, index);
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
	
	// finds the command of the given frame
	public AnimCommand getCommandByFrame(int frame) 
	{

		for (AnimCommand command : getCommandsAsArray())
		{
			if (command.getFrame() == frame)
			{
				return command;
			}
		}
		
		return null;
	}
	
	public void setCommand(int frame, String command)
	{
		// remove current command in frame
		for (int i = 0; i < commands.size(); i++)
		{
			if (commands.get(i).getFrame() == frame)
			{
				commands.remove(i);
			}
		}
		
		commands.add(new AnimCommand(command, frame));
	}
	
	public String getCompiledAnimName()
	{
		return "anim-"+id;
	}
	
	public boolean isInitialized()
	{
		return isInitialized;
	}
	// interpret the JSONObject and setup all this Animation Object's variables, will crash if invalid JSON.
	private void interpretJSON(JSONObject jsonObject)
	{
		String version = jsonObject.getString("version");
		
		// output frames to frames array
		if (version.matches(ProgramConstants.ANIMVERSION))
		{
			System.out.println("Interpreting mcanim version "+version);
			
			JSONArray jsonArray = jsonObject.getJSONArray("frames");
			JSONObject object;
			String command;
			
			// get ID
			id = jsonObject.getInt("id");
			
			// get is Looping
			looping = jsonObject.getBoolean("looping");
			
			// resetWhenDone is optional
			try
			{
				resetPos = jsonObject.getBoolean("resetPos");
			}
			catch(JSONException e)
			{
				resetPos = false;
			}
			
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
						JSONArrayToFloat(object.getJSONArray("head")),
						JSONArrayToFloat(object.getJSONArray("location")),
						(float)object.getDouble("rotation"));
				
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
	
	public void outputToJSON(JSONObject jsonObject)
	{
		// Set basic metadata
		jsonObject.put("version", ProgramConstants.ANIMVERSION);
		jsonObject.put("id", id);
		jsonObject.put("looping", looping);
		jsonObject.put("resetPos", resetPos);
		
		// add frames and commands
		JSONArray jsonFrames = new JSONArray();
		
		for (int i = 0; i < frames.length; i++)
		{
			jsonFrames.put(frames[i].toJSONObject(getCommandByFrame(i)));
		}
		
		jsonObject.put("frames", jsonFrames);
	}
	
	// Convert a JSONArray to a float array
	private float[] JSONArrayToFloat(JSONArray array)
	{
		float[] outArray = new float[array.length()];
		
		for (int i = 0; i < array.length(); i++)
		{
			outArray[i] = (float)array.getDouble(i);
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
