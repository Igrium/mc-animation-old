# What is this?
The Minecraft Animation System is a set of programs designed to let you animate armor stands in Minecraft. This is a standalone Java program designed to import .mcanim files produced by a [Blender plugin](https://github.com/Sam54123/mc-animation-blender/) and compile them into a .mcfunction file that can be called in Minecraft. [Video Tutorial](https://youtu.be/QrI1A568HvQ)

## Installation
To install, first make sure you have Java installed on your computer. If you don't have it, download it from the [Oracle website](https://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html).

After you have obtained Java, download this program ether from the releases tab of this repo or download the source code and compile it yourself. The source code version will be more up to date, but might be more unstable, as it is under development.

## To Use
The Minecraft Animation Compiler is a command line program, which means you'll have to launch it from the command line. To do this, open Command Prompt on Windows or Terminal on Mac and navigate to the folder where you downloaded the jar. To open the program, type `java -jar mcanim.jar`  This will launch the program in the command line.

To open an animation, type `open [mcanim file]`. Export the animation to an .mcfunction file by typing "export". You can optionally specify a folder path to export to. Once it has exported, place the resulting .mcfunction in a subfolder called "animations" of your functions folder in your datapack. The program will give you a command that you need to put in any tick function in order for the animation to work. You can also type `command set [frame] [command]` to add Minecraft console commands to be played on a particular frame. Type `help` to see other things the program can do.

To play the animation ingame, add a 3 dummy objectives called `mc-anim.anim`, `mc-anim.frame`, and `mc-anim.pause` to your world. This can be done through a function or manually. Next, set the `mc-anim.anim` objective of any armor stand (preferably one without gravity or a base and with arms) to the Animation ID of your animation. If `mc-anim.pause` is not 1 for the armor stand, the animation should play. Animation IDs MUST be unique (no more than one animation per ID) or the system will NOT work.

## Contact

This is still very early alpha software. If you spot any bugs, make sure to tell me. You can email me at thesam54123@gmail.com. If you feel you can improve apon this and the related repos, feel free to create a pull request. This is build in Java 1.8.0