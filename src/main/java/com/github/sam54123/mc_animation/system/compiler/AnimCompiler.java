package com.github.sam54123.mc_animation.system.compiler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.github.sam54123.mc_animation.utils.MCAnimStatics;
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
		writer.write("# resetPos: "+ animation.resetPos);
		writer.newLine();
		
		// Close animation if not looping
		if (!animation.looping)
		{
			writer.write("execute if score @s "+ MCCommandConstants.FRAME +" matches "+ (frames.length) +".. run scoreboard players set @s "+ MCCommandConstants.ANIMATION +" 0");
			writer.newLine();
		}
		
		// Calculate first 0,0,0 relative to last frame and add to function
		if (animation.resetPos)
		{
			AnimFrame lastFrame = animation.getFrame(animation.length()-1);
			
			float resetX = lastFrame.location[0]*-1;
			float resetY = lastFrame.location[1]*-1;
			float resetZ = lastFrame.location[2]*-1;
			
			writer.write("execute if score @s "+ MCCommandConstants.FRAME +" matches "+ (frames.length) +".. run tp @s ~"+resetX+" ~"+resetY+" ~"+resetZ);
			writer.newLine();
		}
		
		// Output all frames to file
		writer.write("# frames:");
		writer.newLine();
					
		writer.write("execute if score @s "+ MCCommandConstants.FRAME +" matches "+ (frames.length) +".. run scoreboard players set @s "+ MCCommandConstants.FRAME +" 0");
		writer.newLine();
		
		float[] relativePos;
		float relativeRot;
		for (int i = 0; i < frames.length; i++)
		{
			
			writer.write(formatFrame(frames[i], "@s[scores={"+ MCCommandConstants.FRAME + "="+i+"}]"));
			writer.newLine();
			
			// figure out position relative to last frame.
			if (i == 0)
			{
				relativePos = frames[i].location;
			}
			else
			{
				relativePos = new float[3];
				relativePos[0] = frames[i].location[0] - frames[i-1].location[0];
				relativePos[1] = frames[i].location[1] - frames[i-1].location[1];
				relativePos[2] = frames[i].location[2] - frames[i-1].location[2];
			}
			
			// Convert the relative position to one relative to the armor stand's initial rotation
			float[] relativePos2D = {relativePos[0], relativePos[2]};
			relativePos2D = MCAnimStatics.rotateVector2D(relativePos2D, Math.toRadians(frames[i].rotation)*-1);
			relativePos[0] = relativePos2D[0];
			relativePos[2] = relativePos2D[1];
			
			// find rotation relative to last frame
			if (i==0)
			{
				relativeRot = frames[i].rotation;
			}
			else
			{
				relativeRot = frames[i].rotation - frames[i-1].rotation;
			}
						
			// Only write rotation command if there's rotation
			if(relativeRot != 0.0f) 
			{
				writer.write("execute at @s run tp @s[scores={"+ MCCommandConstants.FRAME + "="+i+"}] ~ ~ ~ ~"+ relativeRot +" ~");
				writer.newLine();
			}
			
			
			// Only write teleport command if there's movement
			if (!(relativePos[0] == 0.0f && relativePos[1] == 0.0f && relativePos[2] == 0.0f))
			{
				// Round so Minecraft doesn't throw a fit
				writer.write("execute at @s run tp @s[scores={"+ MCCommandConstants.FRAME + "="+i+"}] ^"+Math.round(relativePos[0]*1000d)/1000d+" ^"+Math.round(relativePos[1]*1000d)/1000d+" ^"+Math.round(relativePos[2]*1000d)/1000d);
				writer.newLine();
			}
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
