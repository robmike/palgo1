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
        this.head = 0;
        this.tail = 0;
        this.nitems = 0;
    }

    public boolean isEmpty() {           // is the deque empty?
        return size() == 0;
    }
    public int size(){ // return the number of items on the deque
        return this.nitems;
    }
    
    private void resize(int newsize){
        Item[] oldarray = array;
        array = (Item[]) new Object[newsize];
        int i = 0;
        while(head != tail){
            array[i] = oldarray[head];
            i++;
            head = (head + 1) % array.length;
        }
        array[i] = oldarray[tail];
        head = 0;
        tail = nitems-1;
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
    }

    public void enqueue(Item item) {     // insert the item at the end
        incsize();
        this.tail = (this.tail + 1) % this.array.length;
        this.array[this.tail] = item;
    }

    public Item dequeue(){          // delete and return the item at the front
        if(nitems == 0) { throw new NoSuchElementException(); }
        int ridx = StdRandom.uniform(nitems);
        Item item = array[ridx];
        array[ridx] = removeLast();
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
        Item item = array[tail];
        array[tail] = null;
        tail = (tail - 1) % array.length;
        decsize();
        return item;
    }

    public Iterator<Item> iterator(){   // return an iterator over items in order from front to end
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        public void remove() { throw new UnsupportedOperationException(); };

        public boolean hasNext(){
            return !isEmpty();
        }

         public Item next(){
            return sample();
        }
    }
}