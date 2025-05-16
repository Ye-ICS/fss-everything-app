package main.java.com.fss.everythingapp.simulations;
/**
 * Simple 2d vector class for physics calculations.
 * encapsulates common vector operations : addition, subtraction, scaling, normalization, dot product and copying.
 */
public class Vector2D {
    public double x; // x component
    public double y; // y component


    // Constructs a new vector with the given x and y components
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //Adds another vector to this vector
    public void add(Vector2D other) {
        this.x += other.x;
        this.y += other.y;
    }

    //Subtracts another vector from this vector
    public void subtract(Vector2D other) {
        this.x -= other.x;
        this.y -= other.y;
    }

    //Multiplies this vector by a scalar
    public void scale(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    //Divides this vector by a scalar (usegul for computing acceleration F=ma)
    public void divide(double scalar) {
        if (scalar!=0){
            this.x/=scalar;
            this.y/=scalar;
        }
        else{
            System.out.println("Error: Division by zero");
            throw new ArithmeticException("Division by zero");
        }
    }

    //returns magnitude of this vector (length)
    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    //Normalizes this vector (makes it a unit vector)
    public void normalize() {
        double mag = magnitude();
        if (mag != 0) {
            this.x /= mag;
            this.y /= mag;
        } else {
            System.out.println("Error: Cannot normalize a zero vector");
            throw new ArithmeticException("Division by zero");
        }
    }

    //Returns the dot product of this vector and another vector
    public double dot(Vector2D other) {
        return this.x * other.x + this.y * other.y;
    }

    //Returns a copy of this vector 
    public Vector2D copy() {
        return new Vector2D(this.x, this.y);
    }

    @Override 
    public String toString() {
        return String.format ("Vector2D(%. 3f, %. 3f)", x, y);
    }

}