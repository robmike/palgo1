import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Item[] array;
    private int nitems;
    private int head;
    private int tail;
    public Deque(){                      // construct an empty deque
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

    public void addFirst(Item item){    // insert the item at the front
        incsize();
        this.head = (this.head - 1) % this.array.length;
        this.array[this.head] = item;
    }

    public void addLast(Item item) {     // insert the item at the end
        incsize();
        this.tail = (this.tail + 1) % this.array.length;
        this.array[this.tail] = item;
    }

    public Item removeFirst(){          // delete and return the item at the front
        Item item = this.array[head];
        this.array[head] = null;
        head = (head + 1) % array.length;
        decsize();
        return item;
    }
    public Item removeLast(){           // delete and return the item at the end
        Item item = this.array[tail];
        this.array[tail] = null;
        tail = (tail - 1) % array.length;
        decsize();
        return item;
    }

    public Iterator<Item> iterator(){   // return an iterator over items in order from front to end
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private int idx;
        public DequeIterator(){
            idx = head;
        }

        public void remove() {};

        public boolean hasNext(){
            return idx != tail;
        }
        public Item next(){
            Item item = array[idx];
            idx = (idx + 1) % array.length;
            return item;
        }
    }
}