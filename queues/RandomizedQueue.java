import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private ResizingArray resizingArray;

    private class ResizingArray {
        private Item[] array;
        private int length;
        private int popCount;

        public ResizingArray() {
            popCount = 0;
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
            System.out.println(Arrays.toString(array));
            array[rand] = array[length - 1];
            array[length - 1] = null;
            System.out.println(Arrays.toString(array));
            length--;
            popCount++;
            if (length > 0 && length == array.length / 4) resize(array.length / 2);
            return item;

        }
    }

    private class RandomIterator implements Iterator<Item> {
        private int count;
        private Item[] shuffled;

        public RandomIterator() {
            count = 0;
            shuffled = resizingArray.array;
            StdRandom.shuffle(shuffled, 0, resizingArray.length - resizingArray.popCount);
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
            int rand = StdRandom.uniform(0, resizingArray.length + 1);
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
        RandomizedQueue<String> rQ = new RandomizedQueue<>();

        System.out.println(rQ.isEmpty());
        rQ.enqueue("first");
        rQ.enqueue("second");
        rQ.enqueue("third");
        rQ.enqueue("fourth");
        rQ.dequeue();
        rQ.dequeue();

        Iterator<String> iterator = rQ.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


    }

}

