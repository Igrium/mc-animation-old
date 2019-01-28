package com.github.sam54123.mc_animation.console.commands;

import com.github.sam54123.mc_animation.console.CommandBase;
import com.github.sam54123.mc_animation.console.Console;
import com.github.sam54123.mc_animation.system.AnimationFactory;

import java.util.Set;

public class AnimVersions extends CommandBase
{
    @Override
    protected boolean onRun(Console console, String[] args) {
        // Get versions from Animation Factory
        AnimationFactory factory = AnimationFactory.getInstance();
        Set<String> keys = factory.animFactories.keySet();
        
        // Print keys
        console.out.println("Supported mcanim versions: " + String.join(",", keys));
        return true;
    }
   
    @Override
    public String getName() {
        return "animversions";
    }
   
    @Override
    public String getUsage() {
        return "animversions";
    }

    @Override
    public String getDescription() {
        return "Print supported mcanim versions";
    }

}