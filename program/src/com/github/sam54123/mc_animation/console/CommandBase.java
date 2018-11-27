package com.github.sam54123.mc_animation.console;

public abstract class CommandBase 
{
	protected abstract boolean onRun(Console console, String[] args);
	
	public boolean run(Console console, String[] args)
	{
		if (args.length >= requiredArgs)
		{
			return this.onRun(console, args);
		}
		else
		{
			System.out.println("Usage: " + this.getUsage());
			return false;
		}
	}
	
	protected int requiredArgs = 0;
	
	public abstract String getName();
	
	public abstract String getUsage();
	
	public abstract String getDescription();
	
	public void register()
	{
		Console.commands.put(this.getName(), this);
	}
}
