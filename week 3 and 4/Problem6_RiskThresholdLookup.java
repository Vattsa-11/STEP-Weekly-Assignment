import java.util.*;

public class Problem6_RiskThresholdLookup {
    public static void linearSearch(int[] arr, int target) {
        int comps = 0;
        boolean found = false;
        for (int i = 0; i < arr.length; i++) {
            comps++;
            if (arr[i] == target) {
                System.out.println("Linear: threshold=" + target + " -> found at index " + i + " (" + comps + " comps)");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Linear: threshold=" + target + " -> not found (" + comps + " comps)");
        }
    }

    public static void binarySearchFloorCeil(int[] sortedArr, int target) {
        int low = 0, high = sortedArr.length - 1;
        int floor = -1, ceil = -1;
        int comps = 0;

        while (low <= high) {
            comps++;
            int mid = low + (high - low) / 2;
            if (sortedArr[mid] == target) {
                floor = sortedArr[mid];
                ceil = sortedArr[mid];
                break;
            } else if (sortedArr[mid] < target) {
                floor = sortedArr[mid];
                low = mid + 1;
            } else {
                ceil = sortedArr[mid];
                high = mid - 1;
            }
        }
        System.out.println("Binary floor(" + target + "): " + (floor == -1 ? "N/A" : floor) +
                ", ceiling: " + (ceil == -1 ? "N/A" : ceil) + " (" + comps + " comps)");
    }

    public static void main(String[] args) {
        int[] risks = {10, 25, 50, 100};
        System.out.println("Sorted risks: " + Arrays.toString(risks));
        
        linearSearch(risks, 30);
        binarySearchFloorCeil(risks, 30);
    }
}
