import java.util.Arrays;

public interface BagInterface<T> {
    // core ops
    int getCurrentSize();
    boolean isEmpty();
    boolean add(T newEntry);
    boolean remove(T anEntry);     // remove one occurrence if present
    void clear();
    int getFrequencyOf(T anEntry);
    boolean contains(T anEntry);
    T[] toArray();

    // the 3 new ones
    BagInterface<T> union(BagInterface<T> other);
    BagInterface<T> intersection(BagInterface<T> other);
    BagInterface<T> difference(BagInterface<T> other);

    // --- small default helper (not required but handy in drivers / debug) ---
    default String asMultisetString() {
        T[] arr = this.toArray();
        return Arrays.toString(arr);
    }
}
