package com.github.sam54123.mc_animation;

import java.io.IOException;
import java.util.Scanner;

import com.github.sam54123.mc_animation.system.Animation;
import com.github.sam54123.mc_animation.system.compiler.AnimCompiler;
import com.github.sam54123.mc_animation.utils.ProgramConstants;

public class Console {

	public static void main(String[] args) 
	{
		Console console = new Console();
		console.activate(args);
		
	}
	
	Scanner reader;
	
	public void activate(String[] args)
	{
		System.out.println("Minecraft Animation System "+ ProgramConstants.VERSION);
		System.out.println("Note: Only converts .mcanim to .mcfunction for now");
		System.out.println("");
		
		reader = new Scanner(System.in);
		
		// run on the the given file if present
		if (args.length > 1)
		{
			loop(args[0], args[1]);
			  
			System.exit(0);
		}
		
		while (true)
		{
			loop("","");
			System.out.println("");
		}
	}
	
	private void loop(String input, String output)
	{
		
		// get input from user if not fed
		if (input == null || input.isEmpty())
		{
			System.out.print("Path to .mcanim: ");
			input = reader.nextLine();
		}
		
		
		// create animation object and make sure is valid
		Animation animation = new Animation(input);
		if (!animation.isInitialized())
		{
			return;
		}
		
		if (output == null || output.isEmpty())
		{
			System.out.print("Path to output: ");
			output = reader.nextLine();
		}
		
		try
		{
			AnimCompiler.compileAnimation(animation, output);
			System.out.println("Add '"+ AnimCompiler.formatAnimCall(animation) +"' to any tick function to activate.");
		} catch (IOException  e)
		{
			System.out.println("Unable to compile");
			e.printStackTrace();
		}
		
	}
	

}
