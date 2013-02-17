public class RandomizedQueue<Item> extends Deque<Item> {
    private Node[] array;
    private int arhead;
    private int artail;

    public RandomizedQueue(){                      // construct an empty deque
        super();
        this.array = new Node[10];
        arhead = 0;
        artail = 0;
    }

    private void resize(int newsize){
        Node[] oldarray = array;
        array = new Node[newsize];
        int i = 0;
        while(arhead != artail){
            array[i] = oldarray[arhead];
            i++;
            arhead = (arhead + 1) % array.length;
        }
        array[i] = oldarray[tail];
        arhead = 0;
        artail = nitems-1;
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

    public Item removeFirst(){          // delete and return the item at the front
        if(nitems == 0) { return new NoSuchElementException(); }
        Item item = this.array[head];
        this.array[head] = null;
        head = (head + 1) % array.length;
        decsize();
        return item;
    }
    public Item removeLast(){           // delete and return the item at the end
        if(nitems == 0) { return new NoSuchElementException(); }
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

        public void remove() { throw new UnsupportedOperationException(); };

        public Item next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            Item item = array[idx];
            idx = (idx + 1) % array.length;
            return item;
        }
    }
}