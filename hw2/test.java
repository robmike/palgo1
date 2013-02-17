import java.lang.*;
import java.util.*;
import java.io.*;

public class test {
    public static void main(String[] args) throws IOException {
        Deque<Integer> d = new Deque<Integer>();
        for(int i=0; i<10; i++){
            d.addLast(i);
            System.out.println(i);
        }
        for(int i=0; i<5; i++){
            System.out.println(d.removeLast());
        }
        for(int i=100; i<105; i++){
            d.addLast(i);
        }
        for(Integer i : d){
            System.out.println(i);
        }
    }
}