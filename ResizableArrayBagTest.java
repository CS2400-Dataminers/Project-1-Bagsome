import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResizableArrayBagTest {
    @Test
    public void testAddAndSize() {
        BagInterface<Integer> bag = new ResizableArrayBag<>();
        assertTrue(bag.isEmpty());
        bag.add(1); bag.add(2); bag.add(2);
        assertEquals(3, bag.getCurrentSize());
        assertEquals(2, bag.getFrequencyOf(2));
    }

    @Test
    public void testUnionIntersectionDifference() {
        BagInterface<String> a = new ResizableArrayBag<>();
        BagInterface<String> b = new ResizableArrayBag<>();
        a.add("a"); a.add("b"); a.add("b"); a.add("c");
        b.add("b"); b.add("c"); b.add("d");

        BagInterface<String> u = a.union(b);
        BagInterface<String> i = a.intersection(b);
        BagInterface<String> d = a.difference(b);

        // union should have sizes added
        assertEquals(a.getCurrentSize() + b.getCurrentSize(), u.getCurrentSize());

        // intersection should have min counts
        assertEquals(1, i.getFrequencyOf("c"));
        assertEquals(1, i.getFrequencyOf("b"));
        assertEquals(0, i.getFrequencyOf("a"));

        // difference should subtract multiplicities
        assertEquals(1, d.getFrequencyOf("b"));
        assertEquals(1, d.getFrequencyOf("a"));
        assertEquals(0, d.getFrequencyOf("c"));
        assertEquals(0, d.getFrequencyOf("d"));
    }

    @Test
    public void testRemoveOneOccurrence() {
        BagInterface<Integer> bag = new ResizableArrayBag<>();
        bag.add(5); bag.add(5); bag.add(6);
        assertTrue(bag.remove(5));
        assertEquals(1, bag.getFrequencyOf(5));
        assertTrue(bag.contains(6));
    }
}
