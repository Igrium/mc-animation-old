package com.github.sam54123.mc_animation.system.compiler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.github.sam54123.mc_animation.system.AnimFrame;
import com.github.sam54123.mc_animation.system.Animation;

public class AnimCompiler 
{
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
	
	public static String formatFrame(AnimFrame frame, String selector)
	{
		return "data merge entity " + selector + " {" + formatPose(frame) + "}";
	}
	
	// Compiles the animation into a .mcfunction file
	public static void compileAnimation(Animation animation, String outputPath) throws IOException
	{
		// Get all the animation frames
		AnimFrame[] frames = animation.getFramesAsArray();
		
		String filepath = outputPath + animation.getCompiledAnimName()+".mcfunction";
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
		
		String generated;
		for (int i = 0; i < frames.length; i++)
		{
			generated = formatFrame(frames[i], "@s[scores={mc-anim.frame="+i+"}]");
			writer.write(generated);
		}
	}
	
	// formats a float into the 3.0f format
	private static String formatFloat(float num)
	{
		String string = Float.toString(num);
		return string+"f";
	}
}
