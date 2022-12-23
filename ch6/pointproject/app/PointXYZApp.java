package gr.aueb.cf.ch6.pointproject.app;

import gr.aueb.cf.ch6.pointproject.model.PointXYZ;

/**
 * creates 3D points
 */
public class PointXYZApp {

    public static void main(String[] args) {
        PointXYZ p1 = new PointXYZ();
        PointXYZ p2 = new PointXYZ(3, 4, 5);

        p1.setX(6);
        p1.setY(7);
        p1.setZ(8);

        System.out.println(p1.convertToString());
        System.out.println(p2.convertToString());
    }
}
