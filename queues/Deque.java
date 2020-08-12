import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private Node beforeLast;
    private int length;

    private class Node {
        private Item item;
        private Node next;

        public Node(Item item) {
            this.item = item;
            this.next = null;
        }


    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item item = current.item;

            current = current.next;
            return item;
        }
    }


    // construct an empty deque
    public Deque() {
        length = 0;

    }

    // is the deque empty?
    public boolean isEmpty() {
        return length == 0;
    }

    // return the number of items on the deque
    public int size() {
        return length;
    }

    // add the item to the front
    public void addFirst(Item item) {
        Node temp = new Node(item);
        if (isEmpty()) {
            first = temp;
            last = temp;
        }

        temp.next = first;
        first = temp;
        length++;
    }

    // add the item to the back
    public void addLast(Item item) {
        Node temp = new Node(item);
        if (isEmpty()) {
            first = temp;
            last = temp;
        }
        Node oldLast = last;
        oldLast.next = temp;
        last = temp;
        beforeLast = oldLast;
        length++;

    }

    // remove and return the item from the front
    public Item removeFirst() {
        Item item = first.item;
        first = first.next;
        length--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        Item item = last.item;
        last = beforeLast;
        length--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {

        return new ListIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {

        Deque<String> d = new Deque<String>();
        System.out.println(d.isEmpty());
        d.addFirst("AddFirst");
        d.addFirst("AddSecond");
        System.out.println(d.isEmpty());
        System.out.println(d.length);
    }
}
