
package Day9.class_problems;

import java.util.Scanner;

public class MaximumSumSubarrayFixedSizeK {

    public static int maxSumSubarray(int[] sales, int k) {
        if (sales == null || k <= 0 || k > sales.length) {
            throw new IllegalArgumentException(
                "Invalid array or window size."
            );
        }

        int windowSum = 0;

        // Calculate the sum of the first k elements.
        for (int i = 0; i < k; i++) {
            windowSum += sales[i];
        }

        int maxSum = windowSum;

        // Slide the window one position at a time.
        for (int i = k; i < sales.length; i++) {
            windowSum = windowSum + sales[i] - sales[i - k];

            if (windowSum > maxSum) {
                maxSum = windowSum;
            }
        }

        return maxSum;
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

        int[] sales = new int[n];

        System.out.println("Enter daily sales values:");
        for (int i = 0; i < n; i++) {
            sales[i] = sc.nextInt();
        }

        System.out.print("Enter window size k: ");
        int k = sc.nextInt();

        if (k <= 0 || k > n) {
            System.out.println("Invalid window size.");
        } else {
            System.out.println(
                "Maximum sum of " + k + " consecutive days: "
                + maxSumSubarray(sales, k)
            );
        }

        sc.close();
    }
}
