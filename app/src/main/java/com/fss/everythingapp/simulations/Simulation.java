package com.fss.everythingapp.simulations;
//~m :)
import com.fss.everythingapp.simulations.projectilemotion.ProjectileMotion;
import com.fss.everythingapp.simulations.Vector2D;
import java.util.List;

public class Simulation extends SimulationManager
{
    // Simulation-specific properties and methods
    //
    String name;
    String description;
    private ProjectileMotion projectileMotion;

    public void initialize() {
        projectileMotion = new ProjectileMotion();
    }

    public double update(double deltaTime) {
        if (projectileMotion != null) {
            projectileMotion.update(deltaTime);
        }
        return super.update(deltaTime);
    }

    public void render() {
        // Rendering will be handled by JavaFX using getProjectiles()
    }
    
    public void reset() {
        if (projectileMotion != null) {
            projectileMotion.reset();
        }
    }

    // UI hooks for JavaFX controls
    public void setLaunchAngle(double angle) {
        if (projectileMotion != null) projectileMotion.setLaunchAngle(angle);
    }
    public void setLaunchSpeed(double speed) {
        if (projectileMotion != null) projectileMotion.setLaunchSpeed(speed);
    }
    public void setGravity(Vector2D gravity) {
        if (projectileMotion != null) projectileMotion.setGravity(gravity);
    }
    public void launchProjectile(Vector2D position) {
        if (projectileMotion != null) projectileMotion.launchProjectile(position);
    }
    public List<KinematicObject> getProjectiles() {
        if (projectileMotion != null) return projectileMotion.getProjectiles();
        return null;
    }

    //Getters and shtuff

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
}
