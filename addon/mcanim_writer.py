import bpy
import math
import json

def write_mcanim(context, object, id, looping, path):
    
    frames = []
    
    # output all frames into frames array
    for i in range(context.scene.frame_start, context.scene.frame_end):
       frames.append(write_frame(context,object,i))
    
    # add additional metadata to file
    output = {
        "version": "0.1",
        "id": id,
        "looping": looping,
        "frames": frames
    }
    
    # create json string
    formatted = json.dumps(output, sort_keys=True, indent=4, separators=(',', ': '))
    
    # output to file
    file = open(path, "w")
    file.write(formatted)
    file.close
    
    print("Outputted to: "+path)

# returns a dictionary with a single frame of animation
def write_frame(context, object, frame):
    
    # make sure we're on the right frame
    context.scene.frame_current = frame;
    
    # get all the bones in the armature
    bones = object.pose.bones
    
    # get values from said bones
    body = convert_array(bones['body'].rotation_euler, False)
    left_arm = convert_array(bones['left_arm'].rotation_euler, False)
    right_arm = convert_array(bones['right_arm'].rotation_euler, False)
    left_leg = convert_array(bones['left_leg'].rotation_euler, False)
    right_leg = convert_array(bones['right_leg'].rotation_euler, False)
    head = convert_array(bones['head'].rotation_euler, True)
    rotation = round(math.degrees(bones['root'].rotation_euler[1]), 2)
    
    # output found values to dictionary
    output = {
        "body": body,
        "left_arm": left_arm,
        "right_arm": right_arm,
        "left_leg": left_leg,
        "right_leg": right_leg,
        "head": head,
        "rotation": rotation
    }
    
    return output
    

# takes an array attained by armature.pose.bones[bone].rotation_euler, converts it to degrees, and does correct formulas.
def convert_array(array, isHead):
    
    if isHead:
        new_array = [array[0]*-1, array[1]*-1, array[2]]
    else:
        new_array = [array[2], array[1], array[0]*-1]
        
    new_array[0] = round(math.degrees(new_array[0]), 2)
    new_array[1] = round(math.degrees(new_array[1]), 2)
    new_array[2] = round(math.degrees(new_array[2]), 2)
    
    return new_array

# makes sure a file path is formatted properly
def format_path(path):
    path = path.replace('\\','/')
    
    if not path.endswith('/'):
        path = path+'/'
    
    return path
    

if __name__ == "__main__":
    context = bpy.context
    write_mcanim(context, context.scene.objects.active, 23, True, format_path("C:\\Users\\Sam54123\\Documents\\MinecraftAnimationSystem")+"test.mcanim")
