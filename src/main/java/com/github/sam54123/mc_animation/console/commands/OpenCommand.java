package com.github.sam54123.mc_animation.console.commands;

import com.github.sam54123.mc_animation.console.CommandBase;
import com.github.sam54123.mc_animation.console.Console;
import com.github.sam54123.mc_animation.system.Animation;
import com.github.sam54123.mc_animation.system.AnimationFactory;

public class OpenCommand extends CommandBase {

	public OpenCommand() {
		requiredArgs = 1;
	}
	
	@Override
	public boolean onRun(Console console, String[] args) {
		
		Animation anim = AnimationFactory.getInstance().loadAnimation(args[0]);
		
		if (anim == null) {
			System.out.println("Animation failed to initialize");
			return false;
		}
		
		System.out.println("Loaded animation: " + anim.name);
		System.out.println("Animation ID: " + anim.id());
		
		console.loadedAnim = anim;
		
		return true;
	}

	@Override
	public String getUsage() {
		return "open [file]";
	}

	@Override
	public String getName() {
		return "open";
	}

	@Override
	public String getDescription() {
		return "Open a .mcanim file";
	}

}
