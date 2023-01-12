package gr.aueb.cf.ch2.model;

/**
 * Defines a one-dimension point.
 */
public class Point {
    private double x;

    public Point() {}

    public Point(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "Point{" + "x= " + x + '}' + " distance from origin is: " + getDistanceFromOrigin();
    }

    public void movePlus10() {
        x += 10;
    }

    protected void movePlusOne() {
        x += 1;
    }

    protected void printTypeOf() {
        System.out.println(this.getClass().getSimpleName());
    }

    private void reset() {
        x = 0;
    }

    /**
     * A method that returns the distance from the origin of the point.
     *
     * @return The distance from the origin of the point.
     */
    public double getDistanceFromOrigin() {
        return Math.sqrt(Math.pow(x - 0, 2));
    }
}
