package gr.aueb.cf.ch2.app;

import gr.aueb.cf.ch2.model.Point;
import gr.aueb.cf.ch2.model.Point2D;
import gr.aueb.cf.ch2.model.Point3D;
import gr.aueb.cf.ch2.model.PointUtil;

/**
 * Creation of instances of Point
 * and calculation of the distance from th origin.
 */
public class Main {

    public static void main(String[] args) {
        Point p1 = new Point(56);
        Point p2 = new Point2D(18,9);
        Point p3 = new Point3D(5, 27, 11);

        PointUtil.distanceFromOrigin(p1);
        PointUtil.distanceFromOrigin(p2);
        PointUtil.distanceFromOrigin(p3);

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
    }
}
