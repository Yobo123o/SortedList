import java.util.ArrayList;

/**
 * Custom sorted list of strings using binary search insertion.
 */
public class SortedList {
    private ArrayList<String> list;

    /** Constructor initializes an empty list. */
    public SortedList() {
        list = new ArrayList<>();
    }

    /**
     * Inserts an item into the list in sorted order using binary search.
     * @param value the string to insert
     */
    public void insert(String value) {
        int index = findInsertionPoint(value);
        list.add(index, value);
    }

    /**
     * Searches for a string and returns its index, or -1 if not found.
     * @param value the string to search for
     * @return index of the string or -1 if not found
     */
    public int search(String value) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = list.get(mid).compareTo(value);
            if (cmp == 0) return mid;
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    /**
     * Finds the correct insertion point for a value using binary search.
     */
    private int findInsertionPoint(String value) {
        int low = 0;
        int high = list.size();

        while (low < high) {
            int mid = (low + high) / 2;
            if (list.get(mid).compareTo(value) < 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    /** Returns the string representation of the list. */
    public String toString() {
        return list.toString();
    }

    /** Returns the internal list (for display). */
    public ArrayList<String> getList() {
        return list;
    }
}
