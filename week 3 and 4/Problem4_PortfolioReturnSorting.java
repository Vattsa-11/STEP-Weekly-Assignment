import java.util.*;

class Asset {
    String ticker;
    int returnRate;
    int volatility;

    public Asset(String ticker, int returnRate, int volatility) {
        this.ticker = ticker;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return ticker + ":" + returnRate + "%";
    }
}

public class Problem4_PortfolioReturnSorting {
    public static void mergeSortByReturnRate(Asset[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSortByReturnRate(arr, left, mid);
            mergeSortByReturnRate(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(Asset[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        Asset[] L = new Asset[n1];
        Asset[] R = new Asset[n2];
        for (int i = 0; i < n1; ++i) L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].returnRate <= R[j].returnRate) { // Ascending order
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

    public static void quickSortByReturnRateDescVolatilityAsc(Asset[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSortByReturnRateDescVolatilityAsc(arr, low, pi - 1);
            quickSortByReturnRateDescVolatilityAsc(arr, pi + 1, high);
        }
    }

    private static int partition(Asset[] arr, int low, int high) {
        Asset pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j].returnRate > pivot.returnRate || 
               (arr[j].returnRate == pivot.returnRate && arr[j].volatility < pivot.volatility)) {
                i++;
                Asset temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Asset temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        Asset[] portfolio = {
            new Asset("AAPL", 12, 10),
            new Asset("TSLA", 8, 20),
            new Asset("GOOG", 15, 12)
        };
        
        System.out.println("Input: [AAPL:12%, TSLA:8%, GOOG:15%]");

        Asset[] forMerge = Arrays.copyOf(portfolio, portfolio.length);
        mergeSortByReturnRate(forMerge, 0, forMerge.length - 1);
        System.out.println("Merge: " + Arrays.toString(forMerge));

        Asset[] forQuick = Arrays.copyOf(portfolio, portfolio.length);
        quickSortByReturnRateDescVolatilityAsc(forQuick, 0, forQuick.length - 1);
        System.out.println("Quick (desc): " + Arrays.toString(forQuick));
    }
}
