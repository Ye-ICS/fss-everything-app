package com.fss.everythingapp.simulations.momentum;
/**
 * A simple 2D vector class for physics and simulation calculations.
 * Supports vector arithmetic operations such as addition, scaling, dot product,
 * normalization, and utility functions like magnitude and distance.
 */
public class Vector2D {
    public double x; // The x component of the vector
    public double y; // The y component of the vector

    /**
     * Constructs a new 2D vector with the given x and y components.
     * @param x the x component
     * @param y the y component
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns a new vector which is the sum of this vector and another.
     * @param other the vector to add
     * @return a new Vector2D representing the sum
     */
    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    /**
     * Scales this vector in-place by a scalar multiplier.
     * @param scalar the value to scale both components by
     */
    public void scale(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    /**
     * Divides this vector in-place by a scalar value.
     * @param scalar the value to divide both components by
     * @throws ArithmeticException if scalar is zero
     */
    public void divide(double scalar) {
        if (scalar != 0) {
            this.x /= scalar;
            this.y /= scalar;
        } else {
            throw new ArithmeticException("Cannot divide vector by zero");
        }
    }

    /**
     * Returns a new vector that divides this vector by another vector's components.
     * @param other the vector whose components will divide this one
     * @return a new Vector2D with the result of the division
     * @throws ArithmeticException if any component of the divisor is zero
     */
    public Vector2D divideBack(Vector2D other) {
        if (other.x == 0 || other.y == 0) {
            throw new ArithmeticException("Cannot divide vector by zero vector");
        }
        return new Vector2D(this.x / other.x, this.y / other.y);
    }

    /**
     * Returns a new vector that is this vector multiplied by a scalar.
     * @param scalar the scalar value to multiply
     * @return a new scaled Vector2D
     */
    public Vector2D multiply(double scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    /**
     * Returns the angle (in radians) of this vector from the positive x-axis.
     * Uses Math.atan2(y, x), which returns angle in range [-π, π].
     * @return the angle in radians
     */
    public double angle() {
        return Math.atan2(y, x);
    }

    /**
     * Calculates the distance between this vector and another.
     * @param other the other vector
     * @return the Euclidean distance
     */
    public double distanceTo(Vector2D other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Sets this vector's components to the specified values.
     * @param x new x value
     * @param y new y value
     */
    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns a new vector with the same components as this one.
     * @return a copy of this Vector2D
     */
    public Vector2D copy() {
        return new Vector2D(this.x, this.y);
    }

    /**
     * Returns a new vector that is the result of subtracting vector b from vector a.
     * @param a the minuend vector
     * @param b the subtrahend vector
     * @return a new Vector2D representing the difference
     */
    public static Vector2D subtract(Vector2D a, Vector2D b) {
        return new Vector2D(a.x - b.x, a.y - b.y);
    }

    /**
     * Returns the squared magnitude (length) of this vector.
     * Avoids the cost of square root for performance in comparisons.
     * @return the squared magnitude
     */
    public double magnitudeSquared() {
        return x * x + y * y;
    }

    /**
     * Returns the actual magnitude (length) of this vector.
     * @return the magnitude
     */
    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Returns a normalized (unit length) version of this vector.
     * If the vector is zero, returns a zero vector.
     * @return a new normalized Vector2D
     */
    public Vector2D normalize() {
        double mag = magnitude();
        return (mag == 0) ? new Vector2D(0, 0) : new Vector2D(x / mag, y / mag);
    }

    /**
     * Calculates the dot product between this vector and another.
     * @param other the other vector
     * @return the dot product
     */
    public double dot(Vector2D other) {
        return this.x * other.x + this.y * other.y;
    }

    /**
     * Returns a string representation of the vector, formatted to 3 decimal places.
     * @return string in the form Vector2D(x.xxx, y.yyy)
     */
    @Override
    public String toString() {
        return String.format("Vector2D(%.3f, %.3f)", x, y);
    }
}
