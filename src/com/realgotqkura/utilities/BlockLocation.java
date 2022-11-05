package com.realgotqkura.utilities;

import org.joml.Vector3f;

public class BlockLocation {

   private int x;
   private int y;
   private int z;


    public BlockLocation(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getZ(){
        return z;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setZ(int z){
        this.z = z;
    }

    public static BlockLocation translateFromVec3(Vector3f vec3){
        return new BlockLocation((int)vec3.x, (int)vec3.y, (int)vec3.z);
    }

    public static Vector3f translateToVec3(BlockLocation loc){
        return new Vector3f((float)loc.getX(),(float)loc.getY(),(float)loc.getZ());
    }

    public Vector3f translateToVec3(){
        return new Vector3f((float) this.x,(float)this.y,(float)this.z);
    }

    public String toString(){
        return "Location[" + this.x + ", " + this.y + ", " + this.x + "]";
    }


}
