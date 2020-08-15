import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private final ResizingArray resizingArray;

    private class ResizingArray {
        private Item[] array;
        private int length;

        public ResizingArray() {
            length = 0;
            array = (Item[]) new Object[1];
        }

        public void push(Item item) {
            if (length == array.length) resize(2 * array.length);
            array[length++] = item;

        }

        public void resize(int cap) {
            Item[] copy = (Item[]) new Object[cap];
            for (int i = 0; i < length; i++) {
                copy[i] = array[i];

            }
            array = copy;
        }

        /*
                public Item pop() {
                    Item item = array[--length];
                    array[length] = null;
                    if (length > 0 && length == array.length / 4) resize(array.length / 2);
                    return item;
                }
        */
        public Item popRandom() {
            int rand = StdRandom.uniform(length);
            Item item = array[rand];

            array[rand] = array[length - 1];
            array[length - 1] = null;

            length--;

            if (length > 0 && length == array.length / 4) resize(array.length / 2);
            return item;

        }
    }

    private class RandomIterator implements Iterator<Item> {
        private int count;
        private final Item[] shuffled;

        public RandomIterator() {
            count = 0;
            shuffled = resizingArray.array;
            StdRandom.shuffle(shuffled, 0, resizingArray.array.length - (resizingArray.array.length
                    - resizingArray.length));
        }

        public boolean hasNext() {

            return count != resizingArray.length;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            else {
                Item item = shuffled[count++];
                return item;
            }

        }


        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        resizingArray = new ResizingArray();

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return resizingArray.length == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return resizingArray.length;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        else resizingArray.push(item);
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        else {
            Item item = resizingArray.popRandom();
            return item;
        }

    }


    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        else {
            int rand = StdRandom.uniform(resizingArray.length);
            Item item = resizingArray.array[rand];
            return item;
        }

    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> r = new RandomizedQueue<>();
        r.enqueue(1);
        r.enqueue(2);
        r.enqueue(3);
        r.enqueue(4);

        for (int a : r) {
            System.out.println("outer" + a);

        }
        System.out.println();
        for (int a : r) {
            System.out.println("inner" + a);
        }


    }

}

