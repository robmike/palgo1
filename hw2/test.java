import java.lang.*;
import java.util.*;
import java.io.*;

public class test {
    public static void main(String[] args) throws IOException {
        Deque<Integer> d = new Deque<Integer>();
        for(int i=0; i<10; i++){
            d.addFirst(i);
            System.out.println(d.nitems);
        }
        for(int i=0; i<10; i++){
            System.out.println(d.removeLast());
        }
    }
}