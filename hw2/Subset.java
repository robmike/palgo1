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
        int k = Integer.parseInt(args[0]);
        int nitems=0;
        String[] a = new String[k];

        String input = StdIn.readString();

        while(true){
            try{
                push(a, input, nitems++);
                input = StdIn.readString();
            }
            catch(NoSuchElementException e){
                break;
            }
        }
        System.out.print(pretty(a));
    }
}