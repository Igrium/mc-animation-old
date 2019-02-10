package com.github.sam54123.mc_animation.console.commands;

import java.io.IOException;

import com.github.sam54123.mc_animation.console.CommandBase;
import com.github.sam54123.mc_animation.console.Console;

public class Quit extends CommandBase {

	@Override
	protected boolean onRun(Console console, String[] args) {
		// Save animation before close
		if((console.loadedAnim != null) && !console.loadedAnim.isSaved) {
			System.out.println("You have unsaved changes. Would you like to save your animation first? (y/n)");
			
			String input = console.reader().nextLine();
			
			if(input.toLowerCase().matches("y") || input.toLowerCase().matches("yes"))
			{
				try {
					console.loadedAnim.save();
				} catch (IOException e) {
					System.out.println("Unable to save. Canceling exit.");
					return false;
				}
			}
		}
		
		System.exit(0);;
		return true;
	}

	@Override
	public String getName() {
		return "quit";
	}

	@Override
	public String getUsage() {
		return "quit";
	}

	@Override
	public String getDescription() {
		return "Quits the program";
	}

}
