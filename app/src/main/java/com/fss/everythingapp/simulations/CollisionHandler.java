package com.fss.everythingapp.simulations;

/**
 * CollisionHandler manages collision detection and resolution
 * for circular objects (circle-circle) and for circles against
 * rectangular boundary walls (circle-wall). Supports both elastic
 * (bounce) and inelastic (stick) collisions.
 */
public class CollisionHandler {

    /**
     * Detects whether two circles are colliding.
     * @param a first kinematic object
     * @param b second kinematic object
     * @return true if distance between centers <= sum of radii
     */
    public static boolean areCirclesColliding(KinematicObject a, KinematicObject b) {
        double dx = a.position.x - b.position.x;
        double dy = a.position.y - b.position.y;
        double distanceSq = dx * dx + dy * dy;
        double radiusSum = a.radius + b.radius;
        return distanceSq <= radiusSum * radiusSum;
    }

    /**
     * Resolves a collision between two circles.
     * @param a first object
     * @param b second object
     * @param elasticity between 0 (perfectly inelastic) and 1 (perfectly elastic)
     *                    e.g., 1.0 for bounce, 0.0 for stick
     */
    public static void resolveCircleCollision(KinematicObject a, KinematicObject b, double elasticity) {
        // Skip static objects or non-colliding pairs
        if (a.isStatic && b.isStatic) return;
        if (!areCirclesColliding(a, b)) return;

        // Compute collision normal (unit vector from a to b)
        Vector2D normal = new Vector2D(b.position.x - a.position.x,
                                       b.position.y - a.position.y);
        double dist = normal.magnitude();
        if (dist == 0) {
            // Perfect overlap: nudge b slightly
            normal = new Vector2D(1, 0);
            dist = 1;
        }
        normal.divide(dist);

        // Relative velocity along the normal
        Vector2D relVel = a.velocity.copy();
        relVel.subtract(b.velocity);
        double velAlongNormal = relVel.dot(normal);
        if (velAlongNormal > 0) {
            // Objects moving apart
            return;
        }

        // Compute impulse scalar j
        double invMassA = a.isStatic ? 0 : 1 / a.mass;
        double invMassB = b.isStatic ? 0 : 1 / b.mass;
        double j = -(1 + elasticity) * velAlongNormal;
        j /= invMassA + invMassB;

        // Apply impulse
        Vector2D impulse = normal.copy();
        impulse.multiply(j);
        if (!a.isStatic) {
            a.velocity.add(impulse.copy().multiply(invMassA));
        }
        if (!b.isStatic) {
            b.velocity.subtract(impulse.copy().multiply(invMassB));
        }

        // For inelastic (elasticity == 0), optionally merge velocities or stick
        if (elasticity == 0) {
            // Simple stick: set both velocities to center-of-mass velocity
            double totalMass = a.mass + b.mass;
            Vector2D combinedVel = a.velocity.copy();
            combinedVel.multiply(a.mass);
            Vector2D tmp = b.velocity.copy();
            tmp.multiply(b.mass);
            combinedVel.add(tmp);
            combinedVel.divide(totalMass);
            if (!a.isStatic) a.velocity = combinedVel.copy();
            if (!b.isStatic) b.velocity = combinedVel.copy();
        }
    }

    /**
     * Handles collisions between a circle and rectangular walls.
     * @param obj the kinematic object (circle)
     * @param minX left boundary in simulation coords
     * @param maxX right boundary
     * @param minY bottom boundary
     * @param maxY top boundary
     * @param elasticity bounce factor (1=perfect, 0=stick)
     */
    public static void handleWallCollision(KinematicObject obj,
                                           double minX, double maxX,
                                           double minY, double maxY,
                                           double elasticity) {
        if (obj.isStatic) return;

        // Left wall
        if (obj.position.x - obj.radius < minX) {
            obj.position.x = minX + obj.radius;
            obj.velocity.x = -obj.velocity.x * elasticity;
        }
        // Right wall
        if (obj.position.x + obj.radius > maxX) {
            obj.position.x = maxX - obj.radius;
            obj.velocity.x = -obj.velocity.x * elasticity;
        }
        // Bottom wall
        if (obj.position.y - obj.radius < minY) {
            obj.position.y = minY + obj.radius;
            obj.velocity.y = -obj.velocity.y * elasticity;
        }
        // Top wall
        if (obj.position.y + obj.radius > maxY) {
            obj.position.y = maxY - obj.radius;
            obj.velocity.y = -obj.velocity.y * elasticity;
        }
    }

    /**
     * Debug string for collision handler (not commonly used).
     */
    @Override
    public String toString() {
        return "CollisionHandler[]";
    }
}
