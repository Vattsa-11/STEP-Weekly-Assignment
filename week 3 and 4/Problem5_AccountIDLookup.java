import java.util.*;

public class Problem5_AccountIDLookup {
    public static void linearSearch(String[] arr, String target) {
        int firstIndex = -1;
        int comps = 0;
        for (int i = 0; i < arr.length; i++) {
            comps++;
            if (arr[i].equals(target)) {
                firstIndex = i;
                break;
            }
        }
        System.out.println("Linear first " + target + ": index " + firstIndex + " (" + comps + " comparisons)");
    }

    public static void binarySearchCount(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int first = -1;
        int comps = 0;

        while (low <= high) {
            comps++;
            int mid = low + (high - low) / 2;
            int cmp = arr[mid].compareTo(target);
            if (cmp == 0) {
                first = mid;
                high = mid - 1; // look for earlier occurrence
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (first == -1) {
            System.out.println("Binary " + target + ": not found (" + comps + " comparisons)");
            return;
        }

        int count = 0;
        for (int i = first; i < arr.length && arr[i].equals(target); i++) {
            count++;
        }
        
        System.out.println("Binary " + target + ": index " + first + " (" + comps + " comparisons), count=" + count);
    }

    public static void main(String[] args) {
        String[] logs = {"accB", "accA", "accB", "accC"};
        System.out.println("Input logs: " + Arrays.toString(logs));
        
        Arrays.sort(logs);
        System.out.println("Sorted logs: " + Arrays.toString(logs));
        
        // Linear search for accB on sorted logs (as per sample output)
        linearSearch(logs, "accB");
        binarySearchCount(logs, "accB");
    }
}
