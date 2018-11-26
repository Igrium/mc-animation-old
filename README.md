# Minecraft Animation System
This is a system that allows you to export animations from blender into a custom file format called .mcanim, which can be imported by my java application and exported to a .mcfunction file.

The plugin is the python file in addon/, so make sure that's what you install. The plugin requires a very specific rig which is provided.

The java application must be compiled from source, unless you want to use the (outdated) jar file in releases. In any case, once you have it, it's a command line program, so it must be run from a console. To do that on windows, navigate to the jar file in cmd, then type java -jar [jarname], and follow  the instructions. The resulting file must be placed in the animations subfolder of the functions folder of your datapack. Make one if it does not exist.

This system is in very early alpha, and will be a lot more user friendly later on. Make sure to add an issue to this repo if you find a bug.

Have Fun!
