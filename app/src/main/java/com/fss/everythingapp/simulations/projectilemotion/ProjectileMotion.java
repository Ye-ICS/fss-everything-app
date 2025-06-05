package com.fss.everythingapp.simulations.projectilemotion;

import java.util.List;

import com.fss.everythingapp.simulations.KinematicObject;
import com.fss.everythingapp.simulations.Vector2D;

public class ProjectileMotion {
    List<KinematicObject> projectiles;
    static private double v0;
    static private double angle;
    static private double height;
    static private double acceleration;
    static private double time;

    static double calculateTime(){

        if (acceleration == 0.0 || v0 == 0.0){
            return 0;
        }

        double vSin = v0 * Math.sin(angle);
        double discriminant = Math.pow(vSin, 2) - 2 * acceleration * height;
        if (discriminant < 0){
            return 0;
        }

        double t1 = (-vSin + Math.sqrt(discriminant)) / 2*acceleration;
        double t2 = (-vSin - Math.sqrt(discriminant)) / 2*acceleration;

        time = Math.max(t1, t2);
        return time;
    }
    double calculateRange(){
        double x;
        x = v0*Math.cos(angle)*time;
        if (x == 0 && height == 0|| acceleration == 0){
            //System.out.println(0);
            return 0;
        }
        return x;
    }

    Vector2D launchProjectile(double height, double speed, double gravity, double angle, double time) {        
            double x =  + speed * Math.cos(Math.toRadians(angle)) * time;
            double y =  height + speed * Math.sin(Math.toRadians(angle)) * time + 0.5 * gravity * time * time;
        return new Vector2D(x, y);
    }

    void setGravity(Vector2D gravity) {

    }

    static Vector2D getVelocity(double height, double speed, double gravity, double angle, double time) {

        double vy = speed * Math.sin(Math.toRadians(angle)) + gravity*time;
        double vx = speed * Math.cos(Math.toRadians(angle)); 
        return new Vector2D(vx,vy);
    }
    
   

    double calculateMaxHeight(KinematicObject projectile) {
       return -1;
    }

    public void setInitialVelocity(double v0){
        this.v0 = v0;
    } 
    public void setInitialAngle(double angle){
        this.angle = Math.toRadians(angle);
    }
    public void setHeight(double height){
        this.height = height;
    }
    public void setAcceleration(double acceleration){
        this.acceleration = acceleration;
    }

    double calculateRange(KinematicObject projectile) {
        double x;
        x = v0*Math.cos(angle)*time;
        //System.out.println(x);
        return x;
    }

}
