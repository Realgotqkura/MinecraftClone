package com.realgotqkura.utilities;

import org.joml.Vector3f;
import org.lwjgl.system.CallbackI;

public class Location {

    private double x;
    private double y;
    private double z;

    //private Vector3f rawDir;
    private float yaw;
    private float pitch;

    public Location(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Location(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Location(float x, float y, float z,float yaw, float pitch){
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getZ(){
        return z;
    }

    public void setX(float x){
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }

    public void setZ(float z){
        this.z = z;
    }

    public static Location translateFromVec3(Vector3f vec3){
        return new Location(vec3.x, vec3.y, vec3.z);
    }

    public static Vector3f translateToVec3(Location loc){
        return new Vector3f((float)loc.getX(),(float)loc.getY(),(float)loc.getZ());
    }

    public Vector3f translateToVec3(){
        return new Vector3f((float) this.x,(float)this.y,(float)this.z);
    }

    public String toString(){
        return "Location[" + this.x + ", " + this.y + ", " + this.x + "]";
    }


}
