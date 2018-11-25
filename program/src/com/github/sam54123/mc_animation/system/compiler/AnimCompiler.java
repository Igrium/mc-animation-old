package com.github.sam54123.mc_animation.system.compiler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import com.github.sam54123.mc_animation.utils.MCCommandConstants;

import com.github.sam54123.mc_animation.system.AnimCommand;
import com.github.sam54123.mc_animation.system.AnimFrame;
import com.github.sam54123.mc_animation.system.Animation;

public class AnimCompiler 
{
	// Formats a single frame of an animation
	public static String formatFrame(AnimFrame frame, String selector)
	{
		return "data merge entity " + selector + " {" + formatPose(frame) + "}";
	}
	
	public static String formatAnimCall(Animation anim)
	{
		return "execute as @e[scores={"+MCCommandConstants.ANIMATION+"="+anim.id()+"}] unless score @s "+MCCommandConstants.PAUSED+" matches 1.. run function "+MCCommandConstants.NAMESPACE+":animations/"+anim.getCompiledAnimName();
	}
	
	// Compiles the animation into a .mcfunction file
	public static void compileAnimation(Animation animation, String outputPath) throws IOException
	{
		// Make sure path is formatted properly
		
		outputPath = outputPath.replace("\\", "/");
			
		if (!outputPath.substring(outputPath.length() - 1).matches("/")) 
		{
			outputPath = outputPath+"/";
		}
		
		//Create a writer
		String filepath = outputPath + animation.getCompiledAnimName()+".mcfunction";
		BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
			
		// Get all the animation frames
		AnimFrame[] frames = animation.getFramesAsArray();
		
		// Info at top of file
		writer.write("# name: "+ animation.name);
		writer.newLine();
		writer.write("# id: "+ animation.id());
		writer.newLine();
		writer.write("# looping: "+ animation.looping);
		writer.newLine();
		
		// Close animation if not looping
			if (!animation.looping)
			{
				writer.write("execute if score @s "+ MCCommandConstants.FRAME +" matches "+ (frames.length) +".. run scoreboard players set @s "+ MCCommandConstants.ANIMATION +" 0");
				writer.newLine();
			}
			writer.write("execute if score @s "+ MCCommandConstants.FRAME +" matches "+ (frames.length) +".. run scoreboard players set @s "+ MCCommandConstants.FRAME +" 0");
			writer.newLine();
		
		// Output all frames to file
		writer.write("# frames:");
		writer.newLine();
			
		String generated;
		for (int i = 0; i < frames.length; i++)
		{
			generated = formatFrame(frames[i], "@s[scores={"+ MCCommandConstants.FRAME + "="+i+"}]");
			writer.write(generated);
			writer.newLine();
		}
		// Get all commands
		AnimCommand[] commands = animation.getCommandsAsArray();
			
		// Output commands to file
		writer.write("# commands:");
		writer.newLine();
			
		for (int i = 0; i < commands.length; i++)
		{
			writer.write(formatCommand(commands[i], commands[i].getFrame()));
			writer.newLine();
		}
			
		// Next frame
		writer.write("# next frame code");
		writer.newLine();
		writer.write("scoreboard players add @s "+ MCCommandConstants.FRAME + " 1");
		writer.newLine();
			
		
		
		
		writer.close();
		
		System.out.println("Wrote to "+ filepath);
	
	}
		
	private static String formatPose(AnimFrame frame)
	{
		//Format the indivdual parts
		String body = "Body:[" + formatFloat(frame.body[0]) + "," + formatFloat(frame.body[1]) + "," + formatFloat(frame.body[2]) + "]";
		String leftArm = "LeftArm:[" + formatFloat(frame.leftArm[0]) + "," + formatFloat(frame.leftArm[1]) + "," + formatFloat(frame.leftArm[2]) + "]";
		String rightArm = "RightArm:[" + formatFloat(frame.rightArm[0]) + "," + formatFloat(frame.rightArm[1]) + "," + formatFloat(frame.rightArm[2]) + "]";
		String leftLeg = "LeftLeg:[" + formatFloat(frame.leftLeg[0]) + "," + formatFloat(frame.leftLeg[1]) + "," + formatFloat(frame.leftLeg[2]) + "]";
		String rightLeg = "RightLeg:[" + formatFloat(frame.rightLeg[0]) + "," + formatFloat(frame.rightLeg[1]) + "," + formatFloat(frame.rightLeg[2]) + "]";
		String head = "Head:[" + formatFloat(frame.head[0]) + "," + formatFloat(frame.head[1]) + "," + formatFloat(frame.head[2]) + "]";
		
		String data = "Pose:{" + body + "," + leftArm + "," + rightArm + "," + leftLeg + "," + rightLeg + "," + head + "}";
		
		return data;
	}
	
	
	
	private static String formatCommand(AnimCommand command, int frame)
	{
		String output = "execute at @s if score @s " + MCCommandConstants.FRAME + " matches " + frame + " run " + command;
		return output;
	}
	
	// formats a float into the 3.0f format
	private static String formatFloat(float num)
	{
		String string = Float.toString(num);
		return string+"f";
	}
	
	
}
