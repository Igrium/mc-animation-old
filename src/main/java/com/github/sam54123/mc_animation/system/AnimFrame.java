package com.github.sam54123.mc_animation.system;

import org.json.JSONArray;
import org.json.JSONObject;

// Passive object that represents the pose of one frame of animation 
public class AnimFrame  {
	
	public float[] body;
	public float[] leftArm;
	public float[] rightArm;
	public float[] leftLeg;
	public float[] rightLeg;
	public float[] head;
	public float[] location;
	public float rotation;
	
	public AnimFrame(float[] body, float[] leftArm, float[] rightArm, float[] leftLeg, float[] rightLeg, float[] head, float[] location, float rotation) {
		this.body = body;
		this.leftArm = leftArm;
		this.rightArm = rightArm;
		this.leftLeg = leftLeg;
		this.rightLeg = rightLeg;
		this.head = head;
		this.location = location;
		this.rotation = rotation;
	}
	
	public String toString() {
		String body ="body: " + this.body[0] + ", "  + this.body[1] + ", "  + this.body[2];
		String leftArm ="left arm: " + this.leftArm[0] + ", "  + this.leftArm[1] + ", "  + this.leftArm[2];
		String rightArm ="right arm:" + this.rightArm[0] + ", "  + this.rightArm[1] + ", "  + this.rightArm[2];
		String leftLeg ="left leg: " + this.leftLeg[0] + ", "  + this.leftLeg[1] + ", "  + this.leftLeg[2];
		String rightLeg ="right leg: " + this.rightLeg[0] + ", "  + this.rightLeg[1] + ", "  + this.rightLeg[2];
		String head ="head: " + this.head[0] + ", "  + this.head[1] + ", "  + this.head[2];
		
		return("[" + body + ", " + leftArm + ", " + rightArm + ", " + leftLeg + ", " + rightLeg + ", " + head + "]");
	}
	
	public JSONObject toJSONObject(AnimCommand command) {
		JSONObject jsonFrame = new JSONObject();
		
		// Make JSONArrays of all the the body parts
		JSONArray body = new JSONArray(this.body);
		JSONArray left_arm = new JSONArray(this.leftArm);
		JSONArray right_arm = new JSONArray(this.rightArm);
		JSONArray left_leg = new JSONArray(this.leftLeg);
		JSONArray right_leg = new JSONArray(this.rightLeg);
		JSONArray head = new JSONArray(this.head);
		JSONArray location = new JSONArray(this.location);
		
		// Add all JSONArrays to frame
		jsonFrame.put("body", body);
		jsonFrame.put("left_arm", left_arm);
		jsonFrame.put("right_arm", right_arm);
		jsonFrame.put("left_leg", left_leg);
		jsonFrame.put("right_leg", right_leg);
		jsonFrame.put("head", head);
		jsonFrame.put("location", location);
		jsonFrame.put("rotation", rotation);
		
		
		if (command != null) {
			jsonFrame.put("command", command.getCommand());
		}
		
		return jsonFrame;
	}
}
