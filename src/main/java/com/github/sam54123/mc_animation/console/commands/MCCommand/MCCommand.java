package com.github.sam54123.mc_animation.console.commands.MCCommand;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

import com.github.sam54123.mc_animation.console.CommandBase;
import com.github.sam54123.mc_animation.console.commands.SubCommandBase;
import com.github.sam54123.mc_animation.console.Console;


public class MCCommand extends SubCommandBase {

	Scanner reader;
	public static Map<String, CommandBase> commands;

	@Override
	protected void registerCommands(Map<String, CommandBase> commands) 
	{
		new Get().register(commands);
		new Set().register(commands);
		new List().register(commands);
	}

	@Override
	public String getName() 
	{
		return "command";
	}

	@Override
	public String getUsage()
	{
		return "command [operation]";
	}

	@Override
	public String getPath()
	{
		return "command";
	}

	@Override
	public String getDescription() 
	{
		return "Manipulates minecraft console commands to be run during the animation";
	}
	


}
