import java.util.Arrays;

public class BagDriver {
    public static void main(String[] args) {
        demo("LinkedBag", new LinkedBag<String>(), new LinkedBag<String>());
        demo("ResizableArrayBag", new ResizableArrayBag<String>(), new ResizableArrayBag<String>());
    }

    private static void demo(String label, BagInterface<String> bag1, BagInterface<String> bag2) {
        bag1.add("apple");
        bag1.add("banana");
        bag1.add("banana");
        bag1.add("cherry");

        bag2.add("banana");
        bag2.add("cherry");
        bag2.add("date");

        System.out.println("=== " + label + " ===");
        System.out.println("Bag1: " + Arrays.toString(bag1.toArray()));
        System.out.println("Bag2: " + Arrays.toString(bag2.toArray()));
        System.out.println("Union: " + Arrays.toString(bag1.union(bag2).toArray()));
        System.out.println("Intersection: " + Arrays.toString(bag1.intersection(bag2).toArray()));
        System.out.println("Difference (bag1 - bag2): " + Arrays.toString(bag1.difference(bag2).toArray()));
        System.out.println();
    }
}
