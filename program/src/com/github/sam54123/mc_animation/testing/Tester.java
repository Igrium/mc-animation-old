package com.github.sam54123.mc_animation.testing;

import java.util.Scanner;

import com.github.sam54123.mc_animation.system.Animation;

public class Tester {

	public static void main(String[] args) 
	{
		System.out.println("Please enter the path to the file:");
		
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		String in = reader.next();
		
		reader.close();
		
		Animation animation = new Animation(in);
	}

}
