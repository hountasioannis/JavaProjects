package gr.aueb.cf.ch2.model;

import java.util.Locale;

/**
 * Defines a two-dimension point.
 */
public class Point2D extends Point {
    private double y;

    public Point2D() {
    }

    public Point2D(double x, double y) {
        super(x);
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point2D{" + "x= " + getX() + ", " + "y= " + y + '}' + " distance from origin is: " + String.format(Locale.US,"%.2f",getDistanceFromOrigin());
    }

    @Override
    public void movePlus10() {
        super.movePlus10();
        y += 10;
    }

    @Override
    protected void movePlusOne() {
        super.movePlusOne();
        y += 1;
    }

    /**
     * A method that returns the distance from the origin of the point2D.
     *
     *
     * @return The distance from the origin of the point2D.
     */
    @Override
    public double getDistanceFromOrigin() {
        return Math.sqrt(Math.pow(getX() - 0, 2) + Math.pow(y - 0, 2));
    }
}
