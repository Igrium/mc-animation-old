package com.github.sam54123.mc_animation.console.commands;

import com.github.sam54123.mc_animation.console.CommandBase;
import com.github.sam54123.mc_animation.console.Console;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Base class for commands meant to run other commands
 */
public abstract class SubCommandBase extends CommandBase {
    public SubCommandBase() {
        // We need at least one arguement, the command
        this.requiredArgs = 1;
    } 

    Map<String, CommandBase> commands;

    // Register all sub-commands
	@Override
	public void register(Map<String, CommandBase> consoleCommands) {
        super.register(consoleCommands);
        
        // Only register commands if they're not already registered
        if(commands == null) {
            commands = new HashMap<String, CommandBase>();

            registerCommands(commands);

            // manually register Help command
            new Help(commands, getPath() + " ").register(commands);
        }
    }

    @Override
    protected boolean onRun(Console console, String[] args) {
        // Obtain and remove the first arguement (the command)
		ArrayList<String> argsList = new ArrayList<String>();
		for (String s : args)
		{
			argsList.add(s);
		}
        String[] commandArgs = new String[argsList.size()-1];
        argsList.subList(1, argsList.size()).toArray(commandArgs);

        // Actually run the command 
        if (commands.containsKey(args[0])) {
			return commands.get(args[0]).run(console, commandArgs);
		} else {
            console.out.println("Unknown command '" + getPath() + " " + args[0] + "'. Type '"+getPath()+" help' for a list of comands.");
            return false;
		}
    }
    
    /**
     * Register all sub commands
     * @param commands The commands map to pass to the CommandBase objects
     */
    protected abstract void registerCommands(Map<String, CommandBase> commands);

    /**
     * Get the usage of the sub-command without the parameters
     * @return path
     */
    protected abstract String getPath();
    
}

