package com.github.sam54123.mc_animation.console.commands;


import com.github.sam54123.mc_animation.console.CommandBase;
import com.github.sam54123.mc_animation.console.Console;
import com.github.sam54123.mc_animation.system.AnimCommand;
import com.github.sam54123.mc_animation.system.AnimFrame;

public class Get extends CommandBase {
 
	public Get()
	{
		this.requiredArgs = 1;
	}
	
	@Override
	public boolean onRun(Console console, String[] args) 
	{
		
		if (console.loadedAnim == null)
		{
			System.out.println("No animation loaded!");
			return false;
		}
		
		if(args[0].matches("name"))
		{
			System.out.println(console.loadedAnim.name);
		}
		
		
		if(args[0].matches("id"))
		{
			System.out.println(console.loadedAnim.id());
		}
		
		if(args[0].matches("looping"))
		{
			System.out.println(console.loadedAnim.looping);
		}
		
		if(args[0].matches("path"))
		{
			System.out.println(console.loadedAnim.path);
		}
		
		if(args[0].matches("length"))
		{
			System.out.println(console.loadedAnim.getFramesAsArray().length);
		}
		
		if(args[0].matches("commands"))
		{
			AnimCommand[] commands = console.loadedAnim.getCommandsAsArray();
			
			for (AnimCommand command : commands) 
			{
				System.out.println("Frame: " + command.getFrame() + ", Command: \"" + command.getCommand() + "\"");
			}
		}
		
		if(args[0].matches("frames"))
		{
			AnimFrame frames[] = console.loadedAnim.getFramesAsArray();
			AnimFrame f;
			for (int i = 0; i < frames.length; i++)
			{
				f = frames[i];
				System.out.println(String.valueOf(i) + ": " + f.toString());
			}
		}
		else
		{
			System.out.println("Parameters: name, id, looping, path, length, commands");
		}
		
		return true;
	}

	@Override
	public String getName() {
		return "get";
	}

	@Override
	public String getUsage() {
		return "get [param]";
	}

	@Override
	public String getDescription() {
		return "Gets a paramater from the currently loaded animation";
	}

}
