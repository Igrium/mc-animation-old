package com.github.sam54123.mc_animation.console.commands;

import java.util.Set;
import java.util.Map;

import com.github.sam54123.mc_animation.console.CommandBase;
import com.github.sam54123.mc_animation.console.Console;

public class Help extends CommandBase {

	private Map<String, CommandBase> commands;

	// A string of text to put at the beginning of help command usage. Useful for subcommands
	private String prefix;

	public Help(Map<String, CommandBase> commands, String prefix) {
		this.commands = commands;
		this.prefix = prefix;
	}

	public Help(Map<String, CommandBase> commands) {
		this.commands = commands;
	}

	@Override
	public boolean onRun(Console console, String[] args)  {
		Set<String> keys = commands.keySet();
		
		CommandBase command;
		if (args.length < 1) {
			
			for (String k : keys) {
				command = commands.get(k);
				System.out.println(command.getUsage()+": "+command.getDescription());
			}
			return true;
		} else {
			if (keys.contains(args[0])) {
				command = commands.get(args[0]);
				System.out.println(command.getUsage()+": "+command.getDescription());
				
				return true;
			} else {
				System.out.println("Unknown command");
				return false;
			}
		}
		
		
		
	}

	@Override
	public String getName() {
		return "help";
	}

	@Override
	public String getUsage() {
		return prefix + "help <command>";
	}

	@Override
	public String getDescription() {
		return "Show help menu";
	}

}
