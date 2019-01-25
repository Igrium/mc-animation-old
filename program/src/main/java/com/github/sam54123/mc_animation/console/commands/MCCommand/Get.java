package com.github.sam54123.mc_animation.console.commands.MCCommand;

import com.github.sam54123.mc_animation.console.CommandBase;
import com.github.sam54123.mc_animation.console.Console;
import com.github.sam54123.mc_animation.system.AnimCommand;

public class Get extends CommandBase
{
    public Get()
    {
        this.requiresAnim = true;
        this.requiredArgs = 1;
    }

    @Override 
    protected boolean onRun(Console console, String[] args) 
    {
        try
        {
            int frame = Integer.parseInt(args[0]);
            AnimCommand command = console.loadedAnim.getCommandByFrame(frame);

			if (command == null)
			{
				console.out.println("No command exists at frame "+args[0]);
				return false;
			}
			else
			{
                console.out.println(command.getCommand());
                return true;
            }
            
        // In case the user entered a non-number
        } catch(NumberFormatException e) {
            console.out.println("Incorrectly formatted frame. Usage: " + getUsage());
            return false;
        }
    }

    @Override
	public String getName() 
	{
		return "get";
	}

	@Override
	public String getUsage()
	{
		return "command get [frame]";
	}

	@Override
	public String getDescription() 
	{
		return "Returns the console command(s) at a given frame";
	}
}