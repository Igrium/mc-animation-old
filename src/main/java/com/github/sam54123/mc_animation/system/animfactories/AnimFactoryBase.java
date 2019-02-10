package com.github.sam54123.mc_animation.system.animfactories;

import com.github.sam54123.mc_animation.system.Animation;
import org.json.*;

import java.util.Map;

/**
 * Base class for anim factories. Each mcanim version will have it's own anim factory.
 */
public abstract class AnimFactoryBase
{
    /**
     * Load an animation object from an mcanim file. Returns null if file invalid.
     * @param path Path to file to load
     * @return Animation object
     */
    public abstract Animation loadAnimation(String path);

    /**
     * Get the version the factory is for in string format
     * @return version
     */
    protected abstract String getVersion();

    /**
     * Convert a json array to an array of floats
     * @param array json array
     * @return float array
     */
	protected float[] JSONArrayToFloat(JSONArray array)
	{
		float[] outArray = new float[array.length()];
		
		for (int i = 0; i < array.length(); i++)
		{
			outArray[i] = (float)array.getDouble(i);
		}
		
		
		return outArray;
    }
    
    /**
     * returns the command from the JSON object, if any
     * @param object JSONObject
     * @return Command
     */
	protected String getCommand(JSONObject object)
	{
		try
		{
			return object.getString("command");
		}
		catch(Exception e)
		{
			return null;
		}
    }
    
    /**
     * Registers this factory onto a factory map
     * @param factoryMap
     */
    public void register(Map<String, AnimFactoryBase> factoryMap)
    {
        factoryMap.put(getVersion(), this);
    }
}