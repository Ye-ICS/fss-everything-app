public class simulation {
    private double initialVelocity;
    private double initialMass;
    private double finalVelocity;
    private double finalMass;

    public simulation() {
        // Constructor
        this.initialVelocity = 0.0;
        this.initialMass = 0.0;
        this.finalVelocity = 0.0;
        this.finalMass = 0.0;
    }

    public void setInitialConditions(double mass, double velocity) {
        this.initialMass = mass;
        this.initialVelocity = velocity;
    }

    public void run() {
        // Simulate momentum conservation
        this.finalMass = this.initialMass; // Assuming no mass loss
        this.finalVelocity = (this.initialMass * this.initialVelocity) / this.finalMass;
    }

    public void displayResults() {
        System.out.println("Initial Mass: " + this.initialMass);
        System.out.println("Initial Velocity: " + this.initialVelocity);
        System.out.println("Final Mass: " + this.finalMass);
        System.out.println("Final Velocity: " + this.finalVelocity);
    }
}
