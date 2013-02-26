/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;
import java.lang.NullPointerException;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new RelativeSlope();
    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    private class RelativeSlope implements Comparator<Point> {
        public int compare(Point u, Point v) {
            if(v == null) { throw new NullPointerException();}
            if(u == null) { throw new NullPointerException();}
            if( u.x == v.x && u.y == v.y ) return 0;
            double ds = slopeTo(u) - slopeTo(v); // fixme: is ordering preserved?
            if(ds == 0) return 0;
            if(ds < 0) return -1;
            return 1;
            //return (int) Math.round(slopeTo(v) - slopeTo(u)); // fixme: is ordering preserved?
        }
    }

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        if(that == null) { throw new NullPointerException();}
        if(this == that) return Double.NEGATIVE_INFINITY;
        if(this.x == that.x) return Double.POSITIVE_INFINITY;
        if(this.y == that.y) return 0.0;
        return ((float)(that.y - this.y))/(that.x - this.x);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        int dy = this.y - that.y;
        if(dy != 0) return dy;
        int dx = this.x - that.x;
        return dx;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}
