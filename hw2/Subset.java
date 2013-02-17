import java.lang.*;
import java.util.*;
import java.io.*;

public class Subset {
    private String[] a;
    public Subset(int k){
        String[] a = new String[k];
    }

    public static void main(String[] args) throws IOException {
        int k = Integer.parseInt(args[0]);
        Subset ss = Subset(k);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine(); // FIXME: Read 1 token at a time
        StringTokenizer tokenizer = new StringTokenizer(input, " ");
        while(tokenizer.hasMoreTokens()) {
            System.out.println(tokenizer.nextToken());
        } 
    }
}