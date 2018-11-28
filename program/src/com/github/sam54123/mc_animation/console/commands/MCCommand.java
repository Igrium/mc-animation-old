package com.github.sam54123.mc_animation.console.commands;

import java.util.ArrayList;
import java.util.Scanner;

import com.github.sam54123.mc_animation.console.CommandBase;
import com.github.sam54123.mc_animation.console.Console;
import com.github.sam54123.mc_animation.system.AnimCommand;

public class MCCommand extends CommandBase {

	Scanner reader;
	
	public MCCommand()
	{
		this.requiredArgs = 2;
		this.requiresAnim = true;
	}
	
	@Override
	protected boolean onRun(Console console, String[] args) 
	{
		// turn the passed frame into an int
		int frame;
		
		try
		{
			frame = Integer.parseInt(args[1]);
		}
		catch(NumberFormatException e) 
		{
			System.out.println("Usage: "+getUsage());
			return false;
		}
		
		if(args[0].matches("get"))
		{
			
			AnimCommand command = console.loadedAnim.getCommandByFrame(frame);
			if (command == null)
			{
				System.out.println("No command exists at frame "+args[1]);
				return false;
			}
			else
			{
				System.out.println(command.getCommand());
				return true;
			}
		}
		
		if(args[0].matches("set"))
		{
			// Make sure we have a third arguement
			if (args.length < 3)
			{
				System.out.println("Usage: command set [frame] [command]");
				return false;
			}
			
			AnimCommand command = console.loadedAnim.getCommandByFrame(frame);

			// make sure user knows he/she is overriding a command
			if (command != null)
			{
				System.out.println("Command '"+ command.getCommand() +"' already exists at frame "+ args[1] +". Override? (y/n)");
				String input = console.reader().nextLine();
					
				if (!input.toLowerCase().matches("y"))
				{
					return false;
				}
			}
			
			// turn args into arraylist for sublisting
			ArrayList<String> argsList = new ArrayList<String>();
			for (String s : args)
			{
				argsList.add(s);
			}
			
			// turn remaining arguments into string
			String string = String.join(" ", argsList.subList(2, argsList.size()));
			
			console.loadedAnim.setCommand(frame, string);
			System.out.println("Added command '"+ string + "' to frame "+ frame);
			System.out.println("Note: Animation will not play if command is improperly formatted");
			return true;
		}
		
		return false;
	}

	@Override
	public String getName() 
	{
		return "command";
	}

	@Override
	public String getUsage()
	{
		return "command [operation] [frame] [params]";
	}

	@Override
	public String getDescription() 
	{
		return "Manipulates minecraft console commands to be run during the animation";
	}

}
