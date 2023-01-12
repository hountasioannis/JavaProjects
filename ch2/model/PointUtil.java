package gr.aueb.cf.ch2.model;

/**
 * A utility class that a method which includes
 * a method which calculates the distance from the origin of a point.
 */
public class PointUtil {

    private PointUtil() {
    }

    /**
     * It is a polymorphic method that returns the distance of a point for th e origin.
     *
     * @param point A point instance or any type of instance in the inheritance hierarchy of Point.
     *
     * @return The distance from the origin.
     */
    public static double distanceFromOrigin(Point point) {
        return point.getDistanceFromOrigin();
    }
}
