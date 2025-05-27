import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Puck extends Circle {
    private Vector2D position;
    private Vector2D velocity;
    private double mass;
    private Image image;

    public Puck(double x, double y, double mass) {
        super();
        this.mass = mass;
        this.position = new Vector2D(x, y);
        this.velocity = new Vector2D(0, 0);

        setMass(mass);
        setCenterX(x);
        setCenterY(y);
        setFill(Color.BLUE); // Temporary color
    }

    public void setMass(double mass) {
        this.mass = mass;
        double radius = 10 + Math.sqrt(mass) * 2;
        setRadius(radius);
    }

    public void setVelocity(Vector2D v) {
        this.velocity = v;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void updatePosition(double dt) {
        position.add(velocity.multiply(dt));
        setCenterX(position.x);
        setCenterY(position.y);
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D pos) {
        this.position = pos;
        setCenterX(pos.x);
        setCenterY(pos.y);
    }

    public double getMass() {
        return mass;
    }

    public void setImage(Image image) {
        this.image = image;
        setFill(new ImagePattern(image));
    }

    public Image getImage() {
        return image;
    }
}
