import java.util.Arrays;

public class Brute {
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

        for(int i=0; i<n-3; i++) { // This is horrible
            for(int j=i+1; j<n-2; j++) {
                for(int k=j+1; k<n-1; k++) {
                    for(int l=k+1; l<n; l++) {
                        Point[] sp = new Point[4];
                        sp[0] = pts[i];
                        sp[1] = pts[j];
                        sp[2] = pts[k];
                        sp[3] = pts[l];
                        Arrays.sort(sp);
                        if((sp[0].slopeTo(sp[1])) == (sp[1].slopeTo(sp[2])) &&
                           (sp[2].slopeTo(sp[3])) == (sp[1].slopeTo(sp[2]))) {
                            System.out.println("found match");

                            System.out.println(sp[0]);
                            System.out.println(sp[1]);
                            System.out.println(sp[2]);
                            System.out.println(sp[3]);
                            System.out.println((sp[0].slopeTo(sp[1])));
                            System.out.println((sp[1].slopeTo(sp[2])));
                            System.out.println((sp[2].slopeTo(sp[3])));

                            Point first = sp[0];
                            Point last = sp[3];

                            first.drawTo(last);
                            StdDraw.show(0);
                        }
                    }
                }
            }
        } 
    }
}