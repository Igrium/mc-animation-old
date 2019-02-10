package com.github.sam54123.mc_animation.console.commands;


import com.github.sam54123.mc_animation.console.CommandBase;
import com.github.sam54123.mc_animation.console.Console;
import com.github.sam54123.mc_animation.utils.MCAnimStatics;

public class Set extends CommandBase {
 
	public Set() {
		this.requiredArgs = 2;
	}
	
	@Override
	public boolean onRun(Console console, String[] args) {
		
		if (console.loadedAnim == null) {
			console.out.println("No animation loaded!");
			return false;
		}
		
		if(args[0].matches("id")) {
			console.loadedAnim.setId(Integer.parseInt(args[1]));
			console.out.println("Set ID to " + console.loadedAnim.id());
		}
		
		if(args[0].matches("looping")) {
			console.loadedAnim.looping = MCAnimStatics.boolFromString(args[1]);
			console.out.println("Set looping to " + console.loadedAnim.looping);
		}
		
		else {
			console.out.println("Parameters: id, looping");
			return false;
		}
		
		console.loadedAnim.isSaved = false;
		return true;
	}

	@Override
	public String getName() {
		return "set";
	}

	@Override
	public String getUsage() {
		return "set [param] [value]";
	}

	@Override
	public String getDescription() {
		return "Sets a paramater in the currently loaded animation";
	}

}
