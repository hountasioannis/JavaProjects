package gr.aueb.cf.ch6.pointproject.model;

/**
 * represents points in a three-dimensional space
 */
public final class PointXYZ {
    private int x;
    private int y;
    private int z;

    //default constructor
    public PointXYZ() {
    }

    //overloaded constructor
    public PointXYZ(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // getters and setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    /**
     * returns the state of the point
     * @return (x, y, z)
     */
    public String convertToString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
