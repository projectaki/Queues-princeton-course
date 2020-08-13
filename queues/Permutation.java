import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        String string = StdIn.readString();

        Deque<String> deq = new Deque<>();

        System.out.println(k);
        System.out.println(string);
        System.out.println(deq);

    }
}
