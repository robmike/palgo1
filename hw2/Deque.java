import java.util.Iterator;
import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int nitems;
    private Node head;
    private Node tail;
    private Node pre;
    private Node post;

    public Deque(){                      // construct an empty deque
        pre = new Node();
        post = new Node();
        pre.prev = pre;
        pre.next = post;
        post.prev = pre;
        post.next = post;
        head = post; 
        tail = pre;
        this.nitems = 0;
    }

    private class Node {
        Node next;
        Node prev;
        Item item;
    }

    public boolean isEmpty() {           // is the deque empty?
        return size() == 0;
    }
    public int size(){ // return the number of items on the deque
        return this.nitems;
    }

    private void insertBefore(Item item, Node h) {
        Node node = new Node();
        h.prev.next = node;
        node.prev = h.prev;
        node.next = h;
        node.item = item;
        h.prev = node;
        head = pre.next;
        tail = post.prev;
        nitems++;
    }

    private void insertAfter(Item item, Node h) {
        Node node = new Node();
        h.next.prev = node;
        node.prev = h;
        node.next = h.next;
        node.item = item;
        h.next = node;
        head = pre.next;
        tail = post.prev;
        nitems++;
    }

    public void addFirst(Item item){    // insert the item at the front
        insertBefore(item, head);
    }

    public void addLast(Item item) {     // insert the item at the end
        insertAfter(item, tail);
    }

    private Item remove(Node n){
        if(nitems == 0) {
            throw new NoSuchElementException();
        }
        n.prev.next = n.next;
        n.next.prev = n.prev;
        head = pre.next;
        tail = post.prev;
        Item item = n.item;
        return item;
    }

    public Item removeFirst(){          // delete and return the item at the front
        return remove(head);
    }

    public Item removeLast(){           // delete and return the item at the end 
        return remove(tail);
    }

    public Iterator<Item> iterator(){   // return an iterator over items in order from front to end
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node idx;
        public DequeIterator(){
            idx = head;
        }

        public void remove() { throw new UnsupportedOperationException(); };

        public boolean hasNext(){
            return nitems > 0 && idx != tail;
        }

        public Item next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            Item item = idx.item;
            idx = idx.next;
            return item;
        }
    }
}