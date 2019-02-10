package com.github.sam54123.mc_animation.console.commands.MCCommand;

import com.github.sam54123.mc_animation.console.CommandBase;
import com.github.sam54123.mc_animation.console.Console;
import com.github.sam54123.mc_animation.system.AnimCommand;

public class List extends CommandBase {
    public List() {
        this.requiresAnim = true;
    }

    @Override 
    protected boolean onRun(Console console, String[] args) {
        AnimCommand[] commands = console.loadedAnim.getCommandsAsArray();

        if (commands.length < 1) {
            console.out.println("No commands exist in animation");
        }

        for (AnimCommand c : commands) {
            console.out.println("Frame "+c.getFrame()+": "+c.getCommand());
        }
        return true;
    }

    @Override
	public String getName() {
		return "list";
	}

	@Override
	public String getUsage() {
		return "command list";
	}

	@Override
	public String getDescription() {
		return "Lists all the commands in the loaded animation";
	}
}