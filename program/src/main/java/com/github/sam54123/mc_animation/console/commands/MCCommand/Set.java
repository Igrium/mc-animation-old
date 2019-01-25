package com.github.sam54123.mc_animation.console.commands.MCCommand;

import java.util.ArrayList;

import com.github.sam54123.mc_animation.console.CommandBase;
import com.github.sam54123.mc_animation.console.Console;
import com.github.sam54123.mc_animation.system.AnimCommand;

public class Set extends CommandBase
{
    public Set()
    {
        this.requiredArgs = 2;
        this.requiresAnim = true;
    }

    @Override 
    protected boolean onRun(Console console, String[] args) 
    {
        try
        {
            int frame = Integer.parseInt(args[0]);

            // turn args into arraylist for sublisting
			ArrayList<String> argsList = new ArrayList<String>();
			for (String s : args)
			{
				argsList.add(s);
			}
			
			// turn remaining arguments into string
			String string = String.join(" ", argsList.subList(1, argsList.size()));
			
			console.loadedAnim.setCommand(frame, string);
			console.out.println("Added command '"+ string + "' to frame "+ frame);
			console.out.println("Note: Animation will not play if command is improperly formatted");
			console.loadedAnim.isSaved = false;
			return true;
            
        // In case the user entered a non-number
        } catch(NumberFormatException e) {
            console.out.println("Incorrectly formatted frame. Usage: " + getUsage());
            return false;
        }
    }

    @Override
	public String getName() 
	{
		return "set";
	}

	@Override
	public String getUsage()
	{
		return "command set [frame] [command]";
	}

	@Override
	public String getDescription() 
	{
		return "Sets the command to run at a given frame. Will override existing commands, use functions to run multiple.";
	}
}