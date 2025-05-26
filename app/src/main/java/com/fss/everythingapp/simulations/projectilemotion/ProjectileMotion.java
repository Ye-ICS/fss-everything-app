package com.fss.everythingapp.simulations.projectilemotion;

import java.util.List;

import com.fss.everythingapp.simulations.KinematicObject;
import com.fss.everythingapp.simulations.Vector2D;

public class ProjectileMotion {
    List<KinematicObject> projectiles;
    Vector2D gravity;
    private double v0;
    private double angle;
    private double height;
    private double acceleration;
    private double time;

    double calculateTime(){

        if (acceleration == 0.0 || v0 == 0.0){
            return 0;
        }

        double vSin = v0 * Math.sin(angle);
        double discriminant = Math.pow(vSin, 2) - 2 * acceleration * height;
        if (discriminant < 0){
            return 0;
        }

        double t1 = (-vSin + Math.sqrt(discriminant)) / acceleration;
        double t2 = (-vSin - Math.sqrt(discriminant)) / acceleration;

        time = Math.max(t1, t2);
        System.out.println("Time: " + time);
        return time;
    }
    double calculateRange(){
        double x;
        x = v0*Math.cos(angle)*time;
        if (x == 0 && height == 0|| acceleration == 0){
            System.out.println(0);
            return 0;
        }

        System.out.println("Range: " + x);
        return x;
        

    }

    void launchProjectile(Vector2D position, Vector2D velocity) {
        
    }

    void setGravity(Vector2D gravity) {

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
        System.out.println(x);
        return x;
    }

}
