
package Day9.assignment_problems;

import java.util.Scanner;

public class LongestBudgetFriendlyStreak {

    public static int[] longestStreak(int[] costs, int budget) {
        int left = 0;
        int windowSum = 0;

        int maxLength = 0;
        int startIndex = -1;

        for (int right = 0; right < costs.length; right++) {
            windowSum += costs[right];

            while (windowSum > budget && left <= right) {
                windowSum -= costs[left];
                left++;
            }

            int currentLength = right - left + 1;

            // Update only for a strictly longer streak.
            // This keeps the earliest start in a tie.
            if (currentLength > maxLength) {
                maxLength = currentLength;
                startIndex = left;
            }
        }

        return new int[]{maxLength, startIndex};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of days: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Number of days must be positive.");
            sc.close();
            return;
        }

        int[] costs = new int[n];

        System.out.println("Enter daily costs (non-negative):");
        for (int i = 0; i < n; i++) {
            costs[i] = sc.nextInt();

            if (costs[i] < 0) {
                System.out.println("Costs cannot be negative.");
                sc.close();
                return;
            }
        }

        System.out.print("Enter budget: ");
        int budget = sc.nextInt();

        if (budget < 0) {
            System.out.println("Budget cannot be negative.");
            sc.close();
            return;
        }

        int[] result = longestStreak(costs, budget);

        System.out.println(
            "Result: (" + result[0] + ", " + result[1] + ")"
        );

        sc.close();
    }
}
