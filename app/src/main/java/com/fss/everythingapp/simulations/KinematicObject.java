package main.java.com.fss.everythingapp.simulations;
/**
 * Represents a kinematic object in a 2D space with kinematic properties.
 * Encapsulates position, velocity, acceleration, mass and radius for collisions.
 */

public class KinematicObject {
    public Vector2D position; // Position of the object
    public Vector2D velocity; // Velocity of the object
    public Vector2D acceleration; // Acceleration of the object
    public double mass; // Mass of the object
    public double radius; // Radius of the object (for collision detection)
    public boolean isStatic; //wether the object is moving or not


    // Constructor to initialize the kinematic object with position, velocity, acceleration, mass and radius
    public KinematicObject (double x, double y, double radius, double mass, boolean isStatic) {
        this.position = new Vector2D(x, y);
        this.velocity = new Vector2D(0, 0);
        this.acceleration = new Vector2D(0, 0);
        this.mass = mass;
        this.radius = radius;
        this.isStatic = isStatic;
    }

    // Applies a force to the object, updating its acceleration based on the force and mass (F = ma)
    public void applyForce(Vector2D force) {
        if (!isStatic) {
            // Calculate acceleration from force and mass
            Vector2D acceleration = force.copy();
            acceleration.divide(this.mass);
            this.acceleration.add(acceleration);
        }
    }

    // Updates the object's position and velocity based on its current acceleration and time step
    public void update(double dt) {
        if (!isStatic) {
            // v = v + a * dt
            Vector2D dv = acceleration.copy();
            dv.multiply(dt);
            velocity.add(dv);

            // p = p + v * dt
            Vector2D dp = velocity.copy();
            dp.multiply(dt);
            position.add(dp);

            // reset acceleration for next frame
            this.acceleration = new Vector2D(0, 0);
        }
    }

    // resets the object's acceleration to zero
    public void resetAcceleration() {
        this.acceleration.x = 0;
        this.acceleration.y = 0;
    }

    // Calculates current speed of the object
    public double getSpeed() {
        return velocity.magnitude();
    }

    // Calculates kinetic energy of the object
    public double getKineticEnergy() {
        double speed = getSpeed();
        return 0.5 * mass * speed * speed;
    }

    // String representation of the object for debugging
    @Override
    public String toString() {
        return String.format(
            "KinematicObject[pos=%s, vel=%s, acc=%s, r=%.2f, m=%.2f]",
            position, velocity, acceleration, radius, mass
        );
    }

}
