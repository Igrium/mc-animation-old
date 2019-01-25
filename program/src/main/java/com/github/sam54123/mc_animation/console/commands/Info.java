package com.github.sam54123.mc_animation.console.commands;

import com.github.sam54123.mc_animation.console.CommandBase;
import com.github.sam54123.mc_animation.console.Console;
import com.github.sam54123.mc_animation.utils.ProgramConstants;

public class Info extends CommandBase
{
    @Override
    protected boolean onRun(Console console, String[] args) {
        console.out.println("Minecraft Animation System "+ ProgramConstants.VERSION);
        console.out.println("For Minecraft "+ ProgramConstants.MINECRAFT_VERSION);
		console.out.println("Type 'help' for a list of commands.");
        
        return true;
    }
   
    @Override
    public String getName() {
        return "info";
    }
   
    @Override
    public String getUsage() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "Print information about the program";
    }

}