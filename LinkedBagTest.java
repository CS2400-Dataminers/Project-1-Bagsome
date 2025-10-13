import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedBagTest {
    @Test
    public void testAddAndSize() {
        BagInterface<Integer> bag = new LinkedBag<>();
        assertTrue(bag.isEmpty());
        bag.add(1); bag.add(2); bag.add(2);
        assertEquals(3, bag.getCurrentSize());
        assertEquals(2, bag.getFrequencyOf(2));
    }

    @Test
    public void testUnionIntersectionDifference() {
        BagInterface<String> a = new LinkedBag<>();
        BagInterface<String> b = new LinkedBag<>();
        a.add("a"); a.add("b"); a.add("b"); a.add("c");
        b.add("b"); b.add("c"); b.add("d");

        BagInterface<String> u = a.union(b);
        BagInterface<String> i = a.intersection(b);
        BagInterface<String> d = a.difference(b);

        assertEquals(a.getCurrentSize() + b.getCurrentSize(), u.getCurrentSize());
        assertEquals(1, i.getFrequencyOf("c"));
        assertEquals(1, i.getFrequencyOf("b"));
        assertEquals(0, i.getFrequencyOf("a"));

        assertEquals(1, d.getFrequencyOf("b"));
        assertEquals(1, d.getFrequencyOf("a"));
        assertEquals(0, d.getFrequencyOf("c"));
        assertEquals(0, d.getFrequencyOf("d"));
    }

    @Test
    public void testRemoveOneOccurrence() {
        BagInterface<Integer> bag = new LinkedBag<>();
        bag.add(5); bag.add(5); bag.add(6);
        assertTrue(bag.remove(5));
        assertEquals(1, bag.getFrequencyOf(5));
        assertTrue(bag.contains(6));
    }
}
