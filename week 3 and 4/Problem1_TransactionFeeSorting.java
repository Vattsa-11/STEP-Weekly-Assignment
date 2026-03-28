import java.util.*;

class Transaction {
    String id;
    double fee;
    String timestamp;

    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

public class Problem1_TransactionFeeSorting {
    public static void bubbleSortByFee(List<Transaction> list) {
        int n = list.size();
        int passes = 0;
        int swaps = 0;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                    swaps++;
                }
            }
            if (!swapped) break;
        }
        System.out.println("BubbleSort (fees): " + list + " // " + passes + " passes, " + swaps + " swaps");
    }

    public static void insertionSortByFeeAndTimestamp(List<Transaction> list) {
        int n = list.size();
        for (int i = 1; i < n; ++i) {
            Transaction key = list.get(i);
            int j = i - 1;
            while (j >= 0 && (list.get(j).fee > key.fee || 
                 (list.get(j).fee == key.fee && list.get(j).timestamp.compareTo(key.timestamp) > 0))) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }
            list.set(j + 1, key);
        }
        System.out.println("InsertionSort (fee+ts): " + list);
    }

    public static void flagHighFeeOutliers(List<Transaction> list) {
        System.out.print("High-fee outliers: ");
        boolean found = false;
        for (Transaction t : list) {
            if (t.fee > 50.0) {
                System.out.print(t + " ");
                found = true;
            }
        }
        if (!found) System.out.print("none");
        System.out.println();
    }

    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>(Arrays.asList(
            new Transaction("id1", 10.5, "10:00"),
            new Transaction("id2", 25.0, "09:30"),
            new Transaction("id3", 5.0, "10:15")
        ));
        
        List<Transaction> forBubble = new ArrayList<>(transactions);
        bubbleSortByFee(forBubble);
        
        List<Transaction> forInsertion = new ArrayList<>(transactions);
        insertionSortByFeeAndTimestamp(forInsertion);
        
        flagHighFeeOutliers(transactions);
    }
}
