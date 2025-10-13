import java.util.Arrays;

public class ResizableArrayBag<T> implements BagInterface<T> {
    private T[] bag;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public ResizableArrayBag() {
        bag = (T[]) new Object[DEFAULT_CAPACITY];
        numberOfEntries = 0;
    }

    @SuppressWarnings("unchecked")
    public ResizableArrayBag(int initialCapacity) {
        if (initialCapacity < 1) initialCapacity = DEFAULT_CAPACITY;
        bag = (T[]) new Object[initialCapacity];
        numberOfEntries = 0;
    }

    // ===== basic ops =====
    @Override
    public int getCurrentSize() { return numberOfEntries; }

    @Override
    public boolean isEmpty() { return numberOfEntries == 0; }

    @Override
    public boolean add(T newEntry) {
        ensureCapacity(numberOfEntries + 1);
        bag[numberOfEntries++] = newEntry;
        return true;
    }

    @Override
    public boolean remove(T anEntry) {
        int idx = indexOf(anEntry);
        if (idx == -1) return false;
        // swap-with-last then null-out (O(1)) OR shift-left (stable order).
        // For bag, order doesn't matter → swap-with-last.
        bag[idx] = bag[numberOfEntries - 1];
        bag[numberOfEntries - 1] = null;
        numberOfEntries--;
        return true;
    }

    @Override
    public void clear() {
        Arrays.fill(bag, 0, numberOfEntries, null);
        numberOfEntries = 0;
    }

    @Override
    public int getFrequencyOf(T anEntry) {
        int c = 0;
        for (int i = 0; i < numberOfEntries; i++) {
            if (equals(bag[i], anEntry)) c++;
        }
        return c;
    }

    @Override
    public boolean contains(T anEntry) {
        return indexOf(anEntry) >= 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray() {
        Object[] copy = new Object[numberOfEntries];
        System.arraycopy(bag, 0, copy, 0, numberOfEntries);
        return (T[]) copy;
    }

    // ===== union / intersection / difference =====

    @Override
    public BagInterface<T> union(BagInterface<T> other) {
        ResizableArrayBag<T> result = new ResizableArrayBag<>(this.numberOfEntries + other.getCurrentSize());
        for (T item : this.toArray()) result.add(item);
        for (T item : other.toArray()) result.add(item);
        return result;
    }

    @Override
    public BagInterface<T> intersection(BagInterface<T> other) {
        ResizableArrayBag<T> result = new ResizableArrayBag<>();
        // make a removable copy of "other"
        ResizableArrayBag<T> copy = new ResizableArrayBag<>(other.getCurrentSize());
        for (T item : other.toArray()) copy.add(item);

        for (T item : this.toArray()) {
            if (copy.remove(item)) {  // only if still available in other
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public BagInterface<T> difference(BagInterface<T> other) {
        ResizableArrayBag<T> result = new ResizableArrayBag<>(this.numberOfEntries);
        for (T item : this.toArray()) result.add(item);
        for (T item : other.toArray()) result.remove(item);
        return result;
    }

    // ===== internal helpers =====
    private void ensureCapacity(int minCapacity) {
        if (minCapacity <= bag.length) return;
        int newCap = Math.max(bag.length * 2, minCapacity);
        bag = Arrays.copyOf(bag, newCap);
    }

    private int indexOf(T target) {
        for (int i = 0; i < numberOfEntries; i++) {
            if (equals(bag[i], target)) return i;
        }
        return -1;
    }

    private boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
}
