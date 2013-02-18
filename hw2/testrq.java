import java.lang.*;
import java.util.*;
import java.io.*;

public class testrq {
    public static void main(String[] args) throws IOException {
        RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
        for(int i=0; i<10; i++){
            d.enqueue(i);
            System.out.println(d.size());
        }
        for(Integer i : d){
            System.out.println(i);
        }
    }
}