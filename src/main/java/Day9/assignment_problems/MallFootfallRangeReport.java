
package Day9.assignment_problems;

import java.util.Scanner;

public class MallFootfallRangeReport {

    public static int[] footfallReport(int[] visitors, int[][] queries) {
        int n = visitors.length;

        // Build the prefix sum array.
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + visitors[i];
        }

        // Answer each query using two prefix sums.
        int[] results = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0];
            int end = queries[i][1];

            results[i] = prefix[end + 1] - prefix[start];
        }

        return results;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of hours: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Number of hours must be positive.");
            sc.close();
            return;
        }

        int[] visitors = new int[n];

        System.out.println("Enter visitor counts for each hour:");
        for (int i = 0; i < n; i++) {
            visitors[i] = sc.nextInt();

            if (visitors[i] < 0) {
                System.out.println("Visitor counts cannot be negative.");
                sc.close();
                return;
            }
        }

        System.out.print("Enter number of queries: ");
        int q = sc.nextInt();

        if (q <= 0) {
            System.out.println("Number of queries must be positive.");
            sc.close();
            return;
        }

        int[][] queries = new int[q][2];

        System.out.println("Enter start and end indices for each query:");

        for (int i = 0; i < q; i++) {
            queries[i][0] = sc.nextInt();
            queries[i][1] = sc.nextInt();

            if (queries[i][0] < 0
                    || queries[i][1] < queries[i][0]
                    || queries[i][1] >= n) {
                System.out.println("Invalid query range.");
                sc.close();
                return;
            }
        }

        int[] results = footfallReport(visitors, queries);

        System.out.println("Range sums:");
        for (int result : results) {
            System.out.print(result + " ");
        }
        System.out.println();

        sc.close();
    }
}
