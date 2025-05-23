package com.fss.everythingapp.simulations.projectilemotion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fss.everythingapp.simulations.KinematicObject;
import com.fss.everythingapp.simulations.Vector2D;

public class ProjectileMotion {
    private List<KinematicObject> projectiles = new ArrayList<>();
    private Vector2D gravity = new Vector2D(0, -9.8); // default gravity (downward)
    private double launchAngle = 45.0; // degrees
    private double launchSpeed = 20.0; // units/sec
    private double projectileRadius = 0.5;
    private double projectileMass = 1.0;

    public void setGravity(Vector2D gravity) {
        this.gravity = gravity;
    }

    public void setLaunchAngle(double angleDegrees) {
        this.launchAngle = angleDegrees;
    }

    public void setLaunchSpeed(double speed) {
        this.launchSpeed = speed;
    }

    public void setProjectileRadius(double radius) {
        this.projectileRadius = radius;
    }

    public void setProjectileMass(double mass) {
        this.projectileMass = mass;
    }

    public void launchProjectile(Vector2D position) {
        double radians = Math.toRadians(launchAngle);
        double vx = launchSpeed * Math.cos(radians);
        double vy = launchSpeed * Math.sin(radians);
        KinematicObject proj = new KinematicObject(position.x, position.y, projectileRadius, projectileMass, false);
        proj.velocity = new Vector2D(vx, vy);
        projectiles.add(proj);
    }

    public void update(double dt) {
        Iterator<KinematicObject> iter = projectiles.iterator();
        while (iter.hasNext()) {
            KinematicObject proj = iter.next();
            proj.applyForce(new Vector2D(gravity.x * proj.mass, gravity.y * proj.mass));
            proj.update(dt);
            // Remove if below ground (y < 0)
            if (proj.position.y - proj.radius < 0) {
                iter.remove();
            }
        }
    }

    public void reset() {
        projectiles.clear();
    }

    public List<KinematicObject> getProjectiles() {
        return projectiles;
    }

    public double calculateRange(KinematicObject projectile) {
        // Optional: implement analytical range calculation
        return -1;
    }

    public double calculateMaxHeight(KinematicObject projectile) {
        // Optional: implement analytical max height calculation
        return -1;
    }
}