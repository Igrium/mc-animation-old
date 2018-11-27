package com.github.sam54123.mc_animation.console.commands;

import java.io.IOException;

import com.github.sam54123.mc_animation.console.CommandBase;
import com.github.sam54123.mc_animation.console.Console;
import com.github.sam54123.mc_animation.system.compiler.AnimCompiler;

public class Export extends CommandBase {

	public Export()
	{
		requiredArgs = 1;
	}
	
	@Override
	public boolean onRun(Console console, String[] args) 
	{
		if (console.loadedAnim == null) 
		{
			System.out.println("No animation loaded!");
			return false;
		}
		
		try 
		{
			AnimCompiler.compileAnimation(console.loadedAnim, args[0]);
			return true;
		}
		catch(IOException e)
		{
			System.out.println("Compile failed");
			return false;
		}
	}

	@Override
	public String getName() 
	{
		return "export";
	}

	@Override
	public String getUsage() 
	{
		return "export [export path]";
	}

	@Override
	public String getDescription() 
	{
		return "Exports the loaded animation to a .mcfunction that can be read by Minecraft";
	}

}
