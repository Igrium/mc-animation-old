package com.github.sam54123.mc_animation.testing;



import java.io.IOException;

import com.github.sam54123.mc_animation.system.AnimCommand;
import com.github.sam54123.mc_animation.system.Animation;
import com.github.sam54123.mc_animation.system.compiler.AnimCompiler;

public class Tester {

	public static void main(String[] args) 
	{
		
		
		Animation animation = new Animation("Template.mcanim");
		
		Object[] commands = animation.getCommandsAsArray();
		
		for (Object i : commands)
		{
			System.out.println(i);
		}
		
		System.out.println(animation.length());
		System.out.println(animation.getFrame(1));
		System.out.println(animation.id());
		
		try {
			AnimCompiler.compileAnimation(animation, "C:\\Users\\Sam54123\\Documents\\MinecraftAnimationSystem\\program");
		} catch (IOException e) {
			System.out.println("failed");
			e.printStackTrace();
		}
	}

}
