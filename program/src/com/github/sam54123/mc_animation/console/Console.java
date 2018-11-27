package com.github.sam54123.mc_animation.console;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.github.sam54123.mc_animation.console.commands.*;
import com.github.sam54123.mc_animation.system.Animation;
import com.github.sam54123.mc_animation.utils.ProgramConstants;

public class Console {

	public static void main(String[] args) 
	{
		commands = new HashMap<String, CommandBase>();
		registerCommands();
		
		Console console = new Console();
		console.activate(args);
	}
	
	private Scanner reader;
	public Animation loadedAnim;
	
	public static Map<String, CommandBase> commands;
	
	public void activate(String[] args)
	{
		System.out.println("Minecraft Animation System "+ ProgramConstants.VERSION);
		System.out.println("Note: Only converts .mcanim to .mcfunction for now");
		System.out.println("");
		
		reader = new Scanner(System.in);
		
		
		// run on the the given file if present
		if (args.length > 1)
		{
			System.exit(0);
		}
		
		while (true)
		{
			loop();
		}
	}
	
	private static void registerCommands()
	{
		new OpenCommand().register();
		new Help().register();
		new Get().register();
		new Export().register();
		new Save().register();
		new Set().register();
	}
	
	private void loop()
	{
		String input = reader.nextLine();
		
		Command command = new Command(input);
		
		if (commands.containsKey(command.name()))
		{
			commands.get(command.name()).run(this, command.args());
		}
		else
		{
			System.out.println("Unknown command. Type 'help' for a list of comands.");
		}
		
		command = null;
	}
	
	
	
	
}
