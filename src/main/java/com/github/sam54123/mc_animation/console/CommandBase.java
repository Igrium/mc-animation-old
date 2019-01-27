package com.github.sam54123.mc_animation.console;

import java.util.Map;

public abstract class CommandBase 
{
	protected abstract boolean onRun(Console console, String[] args);
	
	public boolean run(Console console, String[] args)
	{
		if (args.length >= requiredArgs)
		{
			if (this.requiresAnim)
			{
				if (console.loadedAnim == null)
				{
					System.out.println("No animation loaded!");
					return false;
				}
				else
				{
					return this.onRun(console, args);
				}
				
			}
			else
			{
				return this.onRun(console, args);
			}
			
		}
		else
		{
			System.out.println("Usage: " + this.getUsage());
			return false;
		}
	}
	
	protected int requiredArgs = 0;
	
	protected boolean requiresAnim = false;
	
	public abstract String getName();
	
	public abstract String getUsage();
	
	public abstract String getDescription();
	
	public void register(Map<String, CommandBase> commands)
	{
		commands.put(this.getName(), this);
	}
}
