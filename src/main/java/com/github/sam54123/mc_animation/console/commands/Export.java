package com.github.sam54123.mc_animation.console.commands;

import java.io.IOException;

import com.github.sam54123.mc_animation.console.CommandBase;
import com.github.sam54123.mc_animation.console.Console;
import com.github.sam54123.mc_animation.system.compiler.AnimCompiler;
import com.github.sam54123.mc_animation.utils.MCAnimStatics;

public class Export extends CommandBase {

	public Export()	{
		requiredArgs = 0;
		requiresAnim = true;
	}
	
	@Override
	public boolean onRun(Console console, String[] args) {
		String path;
		
		if (args.length < 1) {
			path = console.loadedAnim.getFolder();
		} else {
			path = MCAnimStatics.formatPath(args[0]);
		}
		
		try {
			AnimCompiler.compileAnimation(console.loadedAnim, path);
		} catch(IOException e) {
			console.out.println("Compile failed");
			return false;
		}
		
		console.out.println("Compile Successful!");
		console.out.println("Insert file into the functions/animations folder of your datapack and add '"
				+AnimCompiler.formatAnimCall(console.loadedAnim)+"' to any tick function to activate.");
		
		return true;
	}

	@Override
	public String getName() {
		return "export";
	}

	@Override
	public String getUsage() {
		return "export <path>";
	}

	@Override
	public String getDescription() {
		return "Exports the loaded animation to a .mcfunction that can be read by Minecraft";
	}

}
