package com.github.sam54123.mc_animation.console.commands;

import java.util.Set;

import com.github.sam54123.mc_animation.console.CommandBase;
import com.github.sam54123.mc_animation.console.Console;

public class Help extends CommandBase {

	@Override
	public boolean onRun(Console console, String[] args) 
	{
		Set<String> keys = Console.commands.keySet();
		
		CommandBase command;
		for (String k : keys)
		{
			command = Console.commands.get(k);
			System.out.println(command.getUsage()+": "+command.getDescription());
		}
		
		return true;
	}

	@Override
	public String getName() 
	{
		return "help";
	}

	@Override
	public String getUsage() 
	{
		return "help";
	}

	@Override
	public String getDescription() 
	{
		return "Show help menu";
	}

}
