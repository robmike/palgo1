import java.lang.*;
import java.util.*;
import java.io.*;

public class Subset {
    private String[] a;
    private int nitems;

    public Subset(int k){
        a = new String[k];
        nitems = 0;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder(a.length*16);
        for(int i=0; i<a.length; i++){
            sb.append(a[i]);
            sb.append(" ");
        }
        sb.append("\n");
        return sb.toString();
    }
    
    public void push(String s) {
        if(nitems < a.length){
            a[nitems] = s;
        } 
        else {                  // reservoir sampling
            if(StdRandom.random() < (float) a.length/nitems){
                int ridx = StdRandom.uniform(a.length);
                a[ridx] = s;
            }
        }
        nitems++;
    }

    public static void main(String[] args) throws IOException {
        StdRandom.setSeed(0);
        int k = Integer.parseInt(args[0]);
        Subset ss = new Subset(k);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine(); // FIXME: Read 1 token at a time
        StringTokenizer tokenizer = new StringTokenizer(input, " ");
        while(tokenizer.hasMoreTokens()) {
            ss.push(tokenizer.nextToken());
        }
        System.out.println(ss);
    }
}