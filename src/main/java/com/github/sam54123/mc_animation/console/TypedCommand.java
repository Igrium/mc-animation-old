package com.github.sam54123.mc_animation.console;

import java.util.ArrayList;

/**
 * Class that handles the seperation of arguements of a console command
 */
public class TypedCommand {
	private String name;
	private String[] args;
	
	public TypedCommand(String name, String[] args) {
		this.name = name;
		this.args = args;
	}
	
	public TypedCommand(String input) {
		String[] split = input.split(" ");
		ArrayList<String> argsList = new ArrayList<String>();
		
		for (String i : split)
		{
			argsList.add(i);
		}
		
		argsList.remove(0);
		name = split[0];
		
		args = new String[argsList.size()];
		
		argsList.toArray(args);
		
	}
	
	public String name() {
		return name;
	}
	
	public String[] args() {
		return args;
	}
}
