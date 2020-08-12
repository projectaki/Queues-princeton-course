import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int length;

    private class Node {
        private Item item;
        private Node next;
        private Node previous;

        public Node() {
            item = null;
            next = null;
            previous = null;
        }
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            else {
                Item item = current.item;

                current = current.next;
                return item;
            }

        }

        public void remove() {
            throw new UnsupportedOperationException();
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
        if (item == null) {
            throw new IllegalArgumentException();
        }
        else {
            Node temp = new Node();
            temp.item = item;

            if (isEmpty()) {
                temp.next = null;
                temp.previous = null;
                last = temp;
                first = last;
            }
            else {
                temp.next = first;
                first.previous = temp;
                first = temp;
            }

            length++;
        }

    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        else {
            Node temp = new Node();
            temp.item = item;
            if (isEmpty()) {
                temp.next = null;
                temp.previous = null;
                last = temp;
                first = last;
            }
            else {
                Node oldLast = last;
                oldLast.next = temp;
                temp.previous = oldLast;
                last = temp;

            }

            length++;
        }


    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (length == 0) {
            throw new NoSuchElementException();
        }
        else {
            Item item = first.item;
            first = first.next;
            length--;
            return item;
        }


    }

    // remove and return the item from the back
    public Item removeLast() {
        if (length == 0) {
            throw new NoSuchElementException();
        }
        else {
            Item item = last.item;
            if (length == 1) {
                removeFirst();

            }
            else {
                last.previous.next = null;
                last = last.previous;
                length--;
            }


            return item;
        }

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
        d.addFirst("AddThird");
        d.addLast("AddFourth");
        d.removeLast();
        d.removeLast();
        d.removeLast();
        d.removeLast();
        d.removeLast();
        System.out.println(d.isEmpty());
        System.out.println(d.length);

        for (String s : d) {
            System.out.println(s);
        }


    }
}
