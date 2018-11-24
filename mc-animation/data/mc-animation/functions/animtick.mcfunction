#called every 1/20 second by mc-animation:tick
#executes all the animation updates on the proper armor stands (must be updated manually or by program to include all animations)

#put anim ticks here

#example: 

execute as @e[scores={mc-anim.anim=1,}] unless score @s mc-anim.paused matches 1.. run function mc-animation:animations/template_anim

