package com.github.sam54123.mc_animation.system;

// Passive object that represents the pose of one frame of animation 
public class AnimFrame 
{
	
	public float[] body;
	public float[] leftArm;
	public float[] rightArm;
	public float[] leftLeg;
	public float[] rightLeg;
	public float[] head;
	public float[] location;
	public float rotation;
	
	public AnimFrame(float[] body, float[] leftArm, float[] rightArm, float[] leftLeg, float[] rightLeg, float[] head, float[] location, float rotation)
	{
		this.body = body;
		this.leftArm = leftArm;
		this.rightArm = rightArm;
		this.leftLeg = leftLeg;
		this.rightLeg = rightLeg;
		this.head = head;
		this.location = location;
		this.rotation = rotation;
	}
	
	public String toString()
	{
		String body ="[" + this.body[0] + ", "  + this.body[1] + ", "  + this.body[2] + "]";
		String leftArm ="[" + this.leftArm[0] + ", "  + this.leftArm[1] + ", "  + this.leftArm[2] + "]";
		String rightArm ="[" + this.rightArm[0] + ", "  + this.rightArm[1] + ", "  + this.rightArm[2] + "]";
		String leftLeg ="[" + this.leftLeg[0] + ", "  + this.leftLeg[1] + ", "  + this.leftLeg[2] + "]";
		String rightLeg ="[" + this.rightLeg[0] + ", "  + this.rightLeg[1] + ", "  + this.rightLeg[2] + "]";
		String head ="[" + this.head[0] + ", "  + this.head[1] + ", "  + this.head[2] + "]";
		
		return("[" + body + ", " + leftArm + ", " + rightArm + ", " + leftLeg + ", " + rightLeg + ", " + head + "]");
	}
}
