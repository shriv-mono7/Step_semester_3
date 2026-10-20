
package Day9.assignment_problems;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class NetBalancePeriodCounter {

    public static int countPeriods(int[] transactions, int k) {
        Map<Integer, Integer> prefixCount = new HashMap<>();

        prefixCount.put(0, 1);

        int prefixSum = 0;
        int count = 0;

        for (int transaction : transactions) {
            prefixSum += transaction;

            int required = prefixSum - k;

            if (prefixCount.containsKey(required)) {
                count += prefixCount.get(required);
            }

            prefixCount.put(
                prefixSum,
                prefixCount.getOrDefault(prefixSum, 0) + 1
            );
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of transactions: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Number of transactions must be positive.");
            sc.close();
            return;
        }

        int[] transactions = new int[n];

        System.out.println("Enter daily net transactions:");
        for (int i = 0; i < n; i++) {
            transactions[i] = sc.nextInt();
        }

        System.out.print("Enter target amount k: ");
        int k = sc.nextInt();

        System.out.println("Count: " + countPeriods(transactions, k));

        sc.close();
    }
}
