import java.util.Arrays;
import java.util.Collections;

public class Fast {
    public static void main(String[] args) {
        String fname = args[0];
        In f = new In(fname);
        int n = f.readInt();
        Point[] pts = new Point[n];
        for(int i=0; i<n; i++) {
            int x = f.readInt();
            int y = f.readInt();
            pts[i] = new Point(x, y);
        }

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);

        for(int i=0; i<n; i++){
            pts[i].draw();
        }
        StdDraw.show(0);

        for(int i=0; i<n; i++) {
            Point[] p = (Point[])pts.clone();
            Arrays.sort(p, pts[i].SLOPE_ORDER);
            int count = 0;
            double prev = pts[i].slopeTo(p[1]);
            double slope;
            // System.out.println("point i");
            // System.out.println(pts[i]);
            // for(int k=0; k<n; k++) {
            //     System.out.print(p[k]);
            //     System.out.println(pts[i].slopeTo(p[k]));
            // }

            for(int j=2; j<n; j++) {
                slope = pts[i].slopeTo(p[j]);
                System.out.println("prev slope");
                System.out.println(prev);
                System.out.println(slope);
                if (slope == prev){
                    count++;
                    if (count == 2) {
                    System.out.println("count == 2");
                    System.out.println(pts[i]);
                    System.out.print(p[j]);System.out.println(pts[i].slopeTo(p[j]));
                    System.out.print(p[j-1]);System.out.println(pts[i].slopeTo(p[j-1]));
                    System.out.print(p[j-2]);System.out.println(pts[i].slopeTo(p[j-2]));

                        count = 0;
                        Point[] sp = new Point[4];
                        sp[0] = pts[i];
                        sp[1] = p[j];
                        sp[2] = p[j-1];
                        sp[3] = p[j-2];
                        Arrays.sort(sp);

                        Point first = sp[0];
                        Point last = sp[3];

                        first.drawTo(last);
                        StdDraw.show(0);
                    }
                } else {
                    count = 0;
                }
                prev = slope;
            }
        }
    }
}