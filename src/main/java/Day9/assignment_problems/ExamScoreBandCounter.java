
package Day9.assignment_problems;

import java.util.Scanner;

public class ExamScoreBandCounter {

    public static int countInBand(int[] scores, int low, int high) {
        int first = lowerBound(scores, low);
        int last = upperBound(scores, high);

        return last - first;
    }

    // First index where score >= target
    private static int lowerBound(int[] scores, int target) {
        int left = 0;
        int right = scores.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (scores[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    // First index where score > target
    private static int upperBound(int[] scores, int target) {
        int left = 0;
        int right = scores.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (scores[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Number of students must be positive.");
            sc.close();
            return;
        }

        int[] scores = new int[n];

        System.out.println("Enter scores in ascending order (0-100):");

        for (int i = 0; i < n; i++) {
            scores[i] = sc.nextInt();

            if (scores[i] < 0 || scores[i] > 100) {
                System.out.println("Scores must be between 0 and 100.");
                sc.close();
                return;
            }

            if (i > 0 && scores[i] < scores[i - 1]) {
                System.out.println("Scores must be sorted.");
                sc.close();
                return;
            }
        }

        System.out.print("Enter low and high: ");
        int low = sc.nextInt();
        int high = sc.nextInt();

        if (low < 0 || high > 100 || low > high) {
            System.out.println("Invalid score band.");
            sc.close();
            return;
        }

        System.out.println(
            "Count: " + countInBand(scores, low, high)
        );

        sc.close();
    }
}
