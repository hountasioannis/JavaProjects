package gr.aueb.cf.ch2.model;

import java.util.Locale;

/**
 * Defines a three-dimension point.
 */
public class Point3D extends Point2D {
    private double z;

    public Point3D() {}

    public Point3D(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "Point3D{" + "x= " + getX() + ", " + "y= " + getY() +", " + "z= " + z + '}' + " distance from origin is: " + String.format(Locale.US,"%.2f", getDistanceFromOrigin());
    }

    @Override
    public void movePlus10() {
        super.movePlus10();
        z += 10;
    }

    @Override
    protected void movePlusOne() {
        super.movePlusOne();
        z += 1;
    }

    /**
     * A method that returns the distance from the origin of the point3D.
     *
     *
     * @return The distance from the origin of the point3D.
     */
    @Override
    public double getDistanceFromOrigin() {
        return Math.sqrt(Math.pow(getX() - 0, 2) + Math.pow(getY() - 0, 2) + Math.pow(z - 0, 2));
    }
}
