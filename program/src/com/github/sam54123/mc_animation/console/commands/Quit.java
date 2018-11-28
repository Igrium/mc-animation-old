package com.github.sam54123.mc_animation.console.commands;

import com.github.sam54123.mc_animation.console.CommandBase;
import com.github.sam54123.mc_animation.console.Console;

public class Quit extends CommandBase {

	@Override
	protected boolean onRun(Console console, String[] args) 
	{
		// Save animation before close
		if(console.loadedAnim != null)
		{
			System.out.println("Would you like to save your animation first? (y/n)");
			
			String input = console.reader().nextLine();
			
			if(input.toLowerCase().matches("y") || input.toLowerCase().matches("yes"))
			{
				Console.commands.get("save").run(console, args);
			}
		}
		
		System.exit(0);;
		return true;
	}

	@Override
	public String getName() 
	{
		return "quit";
	}

	@Override
	public String getUsage() 
	{
		return "quit";
	}

	@Override
	public String getDescription() 
	{
		return "Quits the program";
	}

}
