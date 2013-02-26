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

        for(int i=0; i<n-3; i++) {
            Point[] p = new Point[n-i-1];
            for(int j = i+1; j<n; j++) {
                p[j-i-1] = pts[j];
            }
            Arrays.sort(p, pts[i].SLOPE_ORDER);
            int count = 0;
            double prev = pts[i].slopeTo(p[0]);
            double slope;
            // System.out.println("point i");
            // System.out.println(pts[i]);
            // for(int k=0; k<n; k++) {
            //     System.out.print(p[k]);
            //     System.out.println(pts[i].slopeTo(p[k]));
            // }

            for(int j=1; j<n-i-1; j++) {
                slope = pts[i].slopeTo(p[j]);
                // System.out.println("prev slope");
                // System.out.println(prev);
                // System.out.println(slope);
                if (slope == prev){
                    count++;
                    if (count == 2) {
                    // System.out.println("count == 2");
                    // System.out.println(pts[i]);
                    // System.out.print(p[j]);System.out.println(pts[i].slopeTo(p[j]));
                    // System.out.print(p[j-1]);System.out.println(pts[i].slopeTo(p[j-1]));
                    // System.out.print(p[j-2]);System.out.println(pts[i].slopeTo(p[j-2]));

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
                        System.out.print(sp[0]); System.out.print(" -> ");
                        System.out.print(sp[1]); System.out.print(" -> ");
                        System.out.print(sp[2]); System.out.print(" -> ");
                        System.out.print(sp[3]); System.out.println();
                        //StdDraw.show(0);
                    }
                } else {
                    count = 0;
                }
                prev = slope;
            }
        }
        StdDraw.show(0);
    }
}