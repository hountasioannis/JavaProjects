package gr.aueb.cf.ch4.homework2;

import java.io.Serializable;

public class Line extends AbstractShape implements ILine, Cloneable, Serializable {
    private static final long serialVersionUID = 2L;
    private double length;

    public Line() {
    }

    public Line(double length) {
        this.length = length;
    }

    //Copy Constructor
    public Line(Line line) {
        this.length = line.length;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Line{" +
                "length=" + length +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        return Double.compare(line.getLength(), getLength()) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(getLength());
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    protected Line clone() throws CloneNotSupportedException {
        return (Line) super.clone();
    }
}
