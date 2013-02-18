import java.util.Iterator;
import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array;
    private int nitems;
    private int head;
    private int tail;
    public RandomizedQueue(){                      // construct an empty deque
        this.array = (Item[]) new Object[10];
        this.nitems = 0;
    }

    public boolean isEmpty() {           // is the deque empty?
        return size() == 0;
    }
    public int size(){ // return the number of items on the deque
        return this.nitems;
    }

    private void pretty(){
        System.out.print("nitems: ");
        System.out.println(nitems);
        System.out.print("array.length: ");
        System.out.println(array.length);
        for(Item i : this){
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println("");
        for(int i=0; i<nitems; i++){
            System.out.print(array[i]);
            System.out.print(" ");
        }
        System.out.println("");
    }

    private Item[] arrayCopy(){
        Item[] narr = (Item[]) new Object[nitems];
        for(int i=0; i<nitems; i++){
            narr[i] = array[i];
        }
        return narr;
    }
    
    private void resize(int newsize){
        Item[] oldarray = array;
        array = (Item[]) new Object[newsize];
        for(int i=0; i<nitems; i++){
            array[i] = oldarray[i];
        }
    }

    private void incsize(){
        if(this.nitems == this.array.length) {
            this.resize(2*this.array.length);
        }
        this.nitems++;
    }

    private void decsize(){
        if(this.nitems == this.array.length/4){
            this.resize(this.array.length/2);
        }
        nitems--;
    }

    public void enqueue(Item item) {     // insert the item at the end
        if(null == item) { throw new NullPointerException();}
        incsize(); 
        array[nitems-1] = item;
    }

    public Item dequeue(){          // delete and return the item at the front
        if(nitems == 0) { throw new NoSuchElementException(); }
        int ridx = StdRandom.uniform(nitems);
        Item item = array[ridx];
        if(ridx != nitems - 1){ // move last to fill hole
            // array[ridx] = removeLast(); // This doesn't work (why?) Mutating LHS when assigning to it?
            Item last = removeLast();
            array[ridx] = last;
        }
        else {
            removeLast();
        }
        return item;
    }

    public Item sample(){          // delete and return the item at the front
        if(nitems == 0) { throw new NoSuchElementException(); }
        int ridx = StdRandom.uniform(nitems);
        Item item = array[ridx];
        return item;
    }

    private Item removeLast(){           // delete and return the item at the end
        if(nitems == 0) { throw new NoSuchElementException(); }
        Item item = array[nitems - 1];
        array[nitems-1] = null;
        decsize();
        return item;
    }

    public Iterator<Item> iterator(){   // return an iterator over items in order from front to end
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        
        private Item[] ac;
        private int count;;

        public RandomizedQueueIterator() {
            ac = arrayCopy();
            StdRandom.shuffle(ac);
            count = nitems;
        }

        public void remove() { throw new UnsupportedOperationException(); };

        public boolean hasNext(){
            return count != 0;
        }

         public Item next(){
             if(count == 0) { throw new NoSuchElementException(); }
             return ac[--count];
         }
    }
}