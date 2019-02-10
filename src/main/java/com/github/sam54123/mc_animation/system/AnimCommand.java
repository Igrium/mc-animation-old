package com.github.sam54123.mc_animation.system;

// Stores a command to be run by Minecraft on a given frame (can be a function call)
public class AnimCommand  {
	private String command;
	private int frame;
	
	AnimCommand(String command, int frame) {
		this.command = command;
		this.frame = frame;
	}
	
	public String getCommand() {
		return command;
	}
	
	public int getFrame() {
		return frame;
	}
	
	public String toString() {
		return command;
	}
}
