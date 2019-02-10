package com.github.sam54123.mc_animation.system;

import java.util.Map;
import java.util.HashMap;

import java.io.File;
import org.json.*;

import com.github.sam54123.mc_animation.utils.JSONUtils;

import com.github.sam54123.mc_animation.system.animfactories.*;

/**
 * Master animation factory class that runs the appropriate animation factory
 */
public class AnimationFactory extends AnimFactoryBase {
    public Map<String, AnimFactoryBase> animFactories;

    // Optional singleton for easy method calling
    private static AnimationFactory instance;
    public static AnimationFactory getInstance() {
        if (instance == null) {
            instance = new AnimationFactory();
        }
        return instance;
    }

    // Register animation factories
    public AnimationFactory() {
        animFactories = new HashMap<String, AnimFactoryBase>();

        new AnimFactory0_1().register(animFactories);
        new AnimFactory0_2().register(animFactories);
    }
    
    @Override
    public Animation loadAnimation(String path) {
        System.out.println("Opening "+path);
		
		// Validate path and load json object to get version
		
		File file = new File(path);
		
		if (!file.exists()) {
			System.out.println("Unknown File");
			return null;
		}
		
		try {
			String extention = path.substring(path.lastIndexOf("."));
			if (!extention.matches(".mcanim"))
			{
				System.out.println("Unknown filetype: " + extention);
				return null;
			}
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("Unknown filetype");
			return null;
        }

        String version;
        try {
            JSONObject jsonObject = JSONUtils.getJSONObjectFromFile(path);
            version = jsonObject.getString("version");
		}
		catch(JSONException e) {
			System.out.println("Incorrectly Formatted .mcanim");
			return null;
		}
        
        // Make sure version is a supported version and create the animation
        if (animFactories.containsKey(version)) {
            return animFactories.get(version).loadAnimation(path);
        }
        else {
            System.out.println("Unsupported version: "+version);
            return null;
        }
        

        
    }

    @Override
    protected String getVersion() {
        return "";
    }
}