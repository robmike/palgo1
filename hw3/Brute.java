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

        for(int i=0; i<n-3; i++) { // This is horrible
            for(int j=i+1; j<n-2; j++) {
                for(int k=j+1; k<n-1; k++) {
                    for(int l=k+1; l<n; l++) {
                        if(Math.abs(pts[i].slopeTo(pts[j])) == Math.abs(pts[j].slopeTo(pts[k])) &&
                           Math.abs(pts[k].slopeTo(pts[l])) == Math.abs(pts[j].slopeTo(pts[k]))) {
                            System.out.println("found match");
                            System.out.println(i);
                            System.out.println(j);
                            System.out.println(k);
                            System.out.println(l);
                            System.out.println(Math.abs(pts[i].slopeTo(pts[j])));
                            System.out.println(Math.abs(pts[j].slopeTo(pts[k])));
                            Point first = pts[i];
                            Point last = pts[l];
                            for(int m=0; m<n; m++) {
                                if(pts[firem].slopeTo(pts[i]) >= 0 &&
                                   pts[m].slopeTo(pts[j]) >= 0 &&
                                   pts[m].slopeTo(pts[k]) >= 0 &&
                                   pts[m].slopeTo(pts[l]) >= 0) {
                                    first = pts[m];
                                    System.out.println("first");
                                    System.out.println(first);
                                    break;
                                }
                            }
                            for(int m=0; m<n; m++) {
                                if(pts[i].slopeTo(pts[m]) <= 0 &&
                                   pts[j].slopeTo(pts[m]) <= 0 &&
                                   pts[k].slopeTo(pts[m]) <= 0 &&
                                   pts[l].slopeTo(pts[m]) <= 0) {
                                    last = pts[m];
                                    System.out.println("last");
                                    System.out.println(last);
                                    break;
                                }
                            }
                            first.drawTo(last);
                            StdDraw.show(0);
                        }
                    }
                }
            }
        } 
    }
}