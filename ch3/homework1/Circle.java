package gr.aueb.cf.ch3.homework1;

public class Circle extends AbstractShape implements ITwoDimensional {
    private double radius;

    public Circle() {
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }


    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}
