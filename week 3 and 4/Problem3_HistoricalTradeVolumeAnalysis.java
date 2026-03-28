import java.util.*;

class Trade {
    String id;
    int volume;

    public Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return id.replace("trade", "") + ":" + volume;
    }
}

public class Problem3_HistoricalTradeVolumeAnalysis {
    public static void mergeSortByVolume(Trade[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSortByVolume(arr, left, mid);
            mergeSortByVolume(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(Trade[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        Trade[] L = new Trade[n1];
        Trade[] R = new Trade[n2];
        for (int i = 0; i < n1; ++i) L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].volume <= R[j].volume) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    public static void quickSortByVolumeDesc(Trade[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSortByVolumeDesc(arr, low, pi - 1);
            quickSortByVolumeDesc(arr, pi + 1, high);
        }
    }

    private static int partition(Trade[] arr, int low, int high) {
        int pivotIndex = low + (high - low) / 2; // median approach conceptually simple
        Trade pivotElement = arr[pivotIndex];
        arr[pivotIndex] = arr[high];
        arr[high] = pivotElement;
        
        int pivot = arr[high].volume;
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j].volume >= pivot) { // Sort Desc
                i++;
                Trade temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Trade temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        Trade[] morningTrades = {
            new Trade("trade3", 500),
            new Trade("trade1", 100),
            new Trade("trade2", 300)
        };
        
        System.out.println("Input: [trade3:500, trade1:100, trade2:300]");
        
        Trade[] forMerge = Arrays.copyOf(morningTrades, morningTrades.length);
        mergeSortByVolume(forMerge, 0, forMerge.length - 1);
        System.out.println("MergeSort: " + Arrays.toString(forMerge) + " // Stable");
        
        Trade[] afternoonTrades = {
            new Trade("trade3", 500),
            new Trade("trade1", 100),
            new Trade("trade2", 300)
        };
        Trade[] forQuick = Arrays.copyOf(afternoonTrades, afternoonTrades.length);
        quickSortByVolumeDesc(forQuick, 0, forQuick.length - 1);
        System.out.println("QuickSort (desc): " + Arrays.toString(forQuick) + " // Pivot: median");
        
        int totalVolume = 0;
        for (Trade t : morningTrades) totalVolume += t.volume;
        for (Trade t : afternoonTrades) totalVolume += t.volume;
        System.out.println("Merged morning+afternoon total: " + totalVolume);
    }
}
