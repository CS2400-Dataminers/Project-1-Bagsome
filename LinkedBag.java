public class LinkedBag<T> implements BagInterface<T> {
    private Node<T> firstNode;
    private int numberOfEntries;

    public LinkedBag() {
        firstNode = null;
        numberOfEntries = 0;
    }

    // ===== basic ops =====
    @Override
    public int getCurrentSize() { return numberOfEntries; }

    @Override
    public boolean isEmpty() { return numberOfEntries == 0; }

    @Override
    public boolean add(T newEntry) {
        Node<T> newNode = new Node<>(newEntry);
        newNode.setNext(firstNode);
        firstNode = newNode;
        numberOfEntries++;
        return true;
    }

    @Override
    public boolean remove(T anEntry) {
        // remove first occurrence
        Node<T> current = firstNode;
        Node<T> prev = null;
        while (current != null) {
            if (equals(current.getData(), anEntry)) {
                if (prev == null) firstNode = current.getNext();
                else prev.setNext(current.getNext());
                numberOfEntries--;
                return true;
            }
            prev = current;
            current = current.getNext();
        }
        return false;
    }

    @Override
    public void clear() {
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public int getFrequencyOf(T anEntry) {
        int count = 0;
        Node<T> cur = firstNode;
        while (cur != null) {
            if (equals(cur.getData(), anEntry)) count++;
            cur = cur.getNext();
        }
        return count;
    }

    @Override
    public boolean contains(T anEntry) {
        Node<T> cur = firstNode;
        while (cur != null) {
            if (equals(cur.getData(), anEntry)) return true;
            cur = cur.getNext();
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray() {
        Object[] arr = new Object[numberOfEntries];
        int i = 0;
        Node<T> cur = firstNode;
        while (cur != null) {
            arr[i++] = cur.getData();
            cur = cur.getNext();
        }
        return (T[]) arr;
    }

    // ===== union / intersection / difference =====

    @Override
    public BagInterface<T> union(BagInterface<T> other) {
        LinkedBag<T> result = new LinkedBag<>();
        for (T item : this.toArray()) result.add(item);
        for (T item : other.toArray()) result.add(item);
        return result;
    }

    @Override
    public BagInterface<T> intersection(BagInterface<T> other) {
        LinkedBag<T> result = new LinkedBag<>();
        // make a removable copy of "other" to respect multiplicity
        LinkedBag<T> copy = new LinkedBag<>();
        for (T item : other.toArray()) copy.add(item);

        for (T item : this.toArray()) {
            if (copy.remove(item)) {        // only if "other" still has a copy
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public BagInterface<T> difference(BagInterface<T> other) {
        LinkedBag<T> result = new LinkedBag<>();
        for (T item : this.toArray()) result.add(item);
        for (T item : other.toArray()) result.remove(item); // remove one occurrence if present
        return result;
    }

    // ===== util =====
    private boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
}
