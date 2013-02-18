import java.lang.*;
import java.util.*;
import java.io.*;

public class testrq {
    public static void main(String[] args) throws IOException {
        RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
        ArrayList<Integer> x = new ArrayList<Integer>();
        ArrayList<Integer> a = new ArrayList<Integer>();

        for(int i=0; i<10; i++){
            int j = i;// StdRandom.uniform(500);
            x.add(j);
            d.enqueue(j);
            //d.pretty();
        }
        for(int i=0; i<10; i++){
            int k = d.dequeue();
            a.add(k);
            System.out.print("Dequeued: ");
            System.out.println(k);
            d.pretty();
        }

        for(int i=10; i<20; i++){
            int j = i;// StdRandom.uniform(500);
            x.add(j);
            d.enqueue(j);
            //d.pretty();
        }
        for(int i=0; i<10; i++){

            int k = d.dequeue();
            a.add(k);
            System.out.print("Dequeued: ");
            System.out.println(k);
            d.pretty();
        }


        // for(Integer i : d){
        //     //System.out.println(i);
        //     int k = d.dequeue();
        //     a.add(k);
        //     System.out.print("Dequeued: ");
        //     System.out.println(k);
        //     d.pretty();
        // }
        Collections.sort(a);
        Collections.sort(x);

        for(int i = 0; i<a.size(); i++){
            System.out.print(a.get(i));
            System.out.print(" ");
            System.out.print(x.get(i));
            System.out.print(" ");
            System.out.print(x.get(i).equals(a.get(i)));
            System.out.println();
        }

        System.out.println(Arrays.deepEquals(a.toArray(), x.toArray()));

        // for(int i = 0; i<a.size(); i++){
        //     System.out.print(a.get(i));
        //     System.out.print(a.get(i) == i);
        //     System.out.print(" ");
        // }

        // System.out.println(d.dequeue());
        // d.pretty();
        // System.out.println(d.dequeue());
        // d.pretty();
        // d.enqueue(100);
        // d.pretty();
        // System.out.println(d.dequeue());
        // d.pretty();
        // for(int i=0; i<9; i++){
        //     d.dequeue();
        //     d.pretty();
        // }
    }
}