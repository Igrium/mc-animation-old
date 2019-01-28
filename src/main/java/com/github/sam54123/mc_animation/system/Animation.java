package com.github.sam54123.mc_animation.system;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.*;

import com.github.sam54123.mc_animation.utils.MCAnimStatics;
import com.github.sam54123.mc_animation.utils.ProgramConstants;

/**
 * Represents a single animation that can be played on an armor stand
 */
public class Animation 
{
	
	public JSONObject jsonObject;
	public String name;
	public boolean looping;
	public String path;
	public boolean resetPos;
	protected ArrayList<AnimCommand> commands;
	protected int id;
	protected AnimFrame[] frames;
	
	/**
	 * Has this animation been saved to disk since it's last modification
	 */
	public boolean isSaved = true;
	
	private boolean isInitialized = false;
	
	/**
	 * Saves the animation as a .mcanim file and updates path accordingly
	 * @param path Path to save to
	 * @return Was save successful
	 * @throws IOException
	 */
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
	
	/**
	 * Returns the folder this animatin is in, excluding the file itself
	 * @return Folder
	 */
	public String getFolder()
	{
		String path = MCAnimStatics.formatPath(this.path);
		
		// get the last index of '/'
		int index = path.substring(0, path.length() - 1).lastIndexOf('/');
		
		return path.substring(0, index);
	}
	
	/**
	 * Returns an array of all this animation's frames
	 * @return Frames
	 */
	public AnimFrame[] getFramesAsArray() 
	{
		return frames;
	}
	
	/**
	 * Returns an array of all this animation's commands
	 * @return Commands
	 */
	public AnimCommand[] getCommandsAsArray()
	{
		AnimCommand[] array = new AnimCommand[commands.size()];
		for (int i = 0; i < commands.size(); i++)
		{
			array[i] = commands.get(i);
		}
		
		return array;
	}
	
	/**
	 * How long this animation is in frames
	 * @return Length
	 */
	public int length()
	{
		return frames.length;
	}
	
	/**
	 * Returns the Frame object at the given index of the animation
	 * @param index
	 * @return Frame
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public AnimFrame getFrame(int index) throws ArrayIndexOutOfBoundsException
	{
		return frames[index];
	}
	
	/**
	 * Returns this animation's animation ID
	 * @return ID
	 */
	public int id()
	{
		return id;
	}
	
	/**
	 * Sets this animation's animation ID
	 */
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
	
	/**
	 * Gets the command at the given frame
	 * @param frame
	 * @return
	 */
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
	
	/**
	 * Sets a command at the given frame
	 * @param frame
	 * @param command Command to set
	 */
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
	
	/**
	 * Gets the function name this animation will take when compiled, excluding file extention
	 * @return Compiled animation name
	 */
	public String getCompiledAnimName()
	{
		return "anim-"+id;
	}
	
	public boolean isInitialized()
	{
		return isInitialized;
	}

	
	/**
	 * Outputs this animation to a JSONObject (used when saving to .mcanim)
	 * @param jsonObject
	 */
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
}
