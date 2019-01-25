package com.github.sam54123.mc_animation.console;

import java.io.PrintStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.github.sam54123.mc_animation.console.commands.*;
import com.github.sam54123.mc_animation.console.commands.MCCommand.MCCommand;
import com.github.sam54123.mc_animation.system.Animation;
import com.github.sam54123.mc_animation.utils.ProgramConstants;

public class Console {

	public static void main(String[] args) 
	{
		Console console = new Console(System.in, System.out);
		console.activate(args);
	}
	
	public InputStream source;
	public PrintStream out;
	private Scanner reader;
	public Animation loadedAnim;
	
	public static Map<String, CommandBase> commands;
	
	public Console(InputStream source, PrintStream out)
	{
		this.source = source;
		this.out = out;
	}

	public void activate(String[] args)
	{
		// register commands
		commands = new HashMap<String, CommandBase>();
		registerCommands();

		this.out.println("Minecraft Animation System "+ ProgramConstants.VERSION);
		this.out.println("Type 'help' for a list of commands.");
		
		reader = new Scanner(source);
		
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
	
	private void registerCommands()
	{
		new OpenCommand().register(commands);
		new Get().register(commands);
		new Export().register(commands);
		new Save().register(commands);
		new Set().register(commands);
		new MCCommand().register(commands);
		new Quit().register(commands);
		new Help(commands).register(commands);
	}
	
	private void loop()
	{
		String input = reader.nextLine();
		
		TypedCommand command = new TypedCommand(input);
		
		// make sure the user actually typed something
		if(command.name().matches(""))
		{
			return;
		}
		
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
	
	public Scanner reader()
	{
		return reader;
	}
	
	
	
	
}
