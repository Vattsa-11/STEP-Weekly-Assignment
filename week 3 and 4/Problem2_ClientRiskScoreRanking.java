import java.util.*;

class Client {
    String name;
    int riskScore;
    int accountBalance;

    public Client(String name, int riskScore, int accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return name.replace("client", "") + ":" + riskScore;
    }
}

public class Problem2_ClientRiskScoreRanking {
    public static void bubbleSortRiskAscending(Client[] clients) {
        int n = clients.length;
        int swaps = 0;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (clients[j].riskScore > clients[j + 1].riskScore) {
                    Client temp = clients[j];
                    clients[j] = clients[j + 1];
                    clients[j + 1] = temp;
                    swapped = true;
                    swaps++;
                }
            }
            if (!swapped) break;
        }
        System.out.println("Bubble (asc): " + Arrays.toString(clients) + " // Swaps: " + swaps);
    }

    public static void insertionSortRiskDescBalanceAsc(Client[] clients) {
        int n = clients.length;
        for (int i = 1; i < n; ++i) {
            Client key = clients[i];
            int j = i - 1;
            while (j >= 0 && (clients[j].riskScore < key.riskScore || 
                 (clients[j].riskScore == key.riskScore && clients[j].accountBalance > key.accountBalance))) {
                clients[j + 1] = clients[j];
                j = j - 1;
            }
            clients[j + 1] = key;
        }
        System.out.println("Insertion (desc): " + Arrays.toString(clients));
    }

    public static void getTopRisks(Client[] clients, int topN) {
        System.out.print("Top " + topN + " risks: ");
        for (int i = 0; i < Math.min(topN, clients.length); i++) {
            System.out.print(clients[i] + (i < topN - 1 && i < clients.length - 1 ? ", " : ""));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Client[] clients = {
            new Client("clientC", 80, 5000),
            new Client("clientA", 20, 10000),
            new Client("clientB", 50, 7500)
        };
        
        System.out.println("Input: [clientC:80, clientA:20, clientB:50]");
        
        Client[] clientsForBubble = Arrays.copyOf(clients, clients.length);
        bubbleSortRiskAscending(clientsForBubble);
        
        Client[] clientsForInsertion = Arrays.copyOf(clients, clients.length);
        insertionSortRiskDescBalanceAsc(clientsForInsertion);
        
        getTopRisks(clientsForInsertion, 3);
    }
}
