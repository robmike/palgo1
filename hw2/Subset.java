import java.lang.*;
import java.util.*;
import java.io.*;

public class Subset {

    public Subset(){
    }
    
    private static String pretty(String[] a) {
        StringBuilder sb = new StringBuilder(a.length*16);
        for(int i=0; i<a.length; i++){
            sb.append(a[i]);
            sb.append("\n");
        }
        // sb.append("\n");
        return sb.toString();
    }
    
    private static void push(String[] a, String s, int nitems) {
        if(nitems < a.length){
            a[nitems] = s;
        } 
        else {                  // reservoir sampling
            if(StdRandom.random() < (float) a.length/(nitems+1)){
                int ridx = StdRandom.uniform(a.length);
                a[ridx] = s;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        StdRandom.setSeed(0);
        int k = Integer.parseInt(args[0]);
        int nitems=0;
        String[] a = new String[k];

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine(); // FIXME: Read 1 token at a time
        StringTokenizer tokenizer = new StringTokenizer(input, " ");
        while(tokenizer.hasMoreTokens()) {
            push(a, tokenizer.nextToken(), nitems++);
        }
        System.out.print(pretty(a));
    }
}