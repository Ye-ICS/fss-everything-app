/**
 * Simple 2D vector class for physics calculations.
 * Encapsulates common vector operations: addition, subtraction, scaling, normalization, dot product, and copying.
 */
public class Vector2D {
    public double x; // x component
    public double y; // y component

    // Constructs a new vector with the given x and y components
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Adds another vector to this vector (in-place)
    public void add(Vector2D other) {
        this.x += other.x;
        this.y += other.y;
    }

    // Multiplies this vector by a scalar (in-place)
    public void scale(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    // Divides this vector by a scalar (in-place)
    public void divide(double scalar) {
        if (scalar != 0) {
            this.x /= scalar;
            this.y /= scalar;
        } else {
            throw new ArithmeticException("Cannot divide vector by zero");
        }
    }

    // Returns a new vector that is this vector multiplied by a scalar (non-mutating)
    public Vector2D multiply(double scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    // Returns the angle (in radians) between this vector and the positive x-axis
    public double angle() {
        return Math.atan2(y, x); // range is [-π, π]
    }

    // Returns the distance between this vector and another vector
    public double distanceTo(Vector2D other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Sets this vector's components to the given values
    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Returns a copy of this vector
    public Vector2D copy() {
        return new Vector2D(this.x, this.y);
    }

    // Static method: returns a new vector that is the sum of two vectors
    public static Vector2D add(Vector2D a, Vector2D b) {
        return new Vector2D(a.x + b.x, a.y + b.y);
    }

    // Static method: returns a new vector that is the difference of two vectors
    public static Vector2D subtract(Vector2D a, Vector2D b) {
        return new Vector2D(a.x - b.x, a.y - b.y);
    }
    public double magnitudeSquared() {
        return x * x + y * y;
    }
    @Override
    public String toString() {
        return String.format("Vector2D(%.3f, %.3f)", x, y);
    }

    public double magnitude() {
    return Math.sqrt(x * x + y * y);
}

public Vector2D normalize() {
    double mag = magnitude();
    return (mag == 0) ? new Vector2D(0, 0) : new Vector2D(x / mag, y / mag);
}

public double dot(Vector2D other) {
    return this.x * other.x + this.y * other.y;
}



}
