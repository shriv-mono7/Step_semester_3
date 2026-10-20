
package Day9.class_problems;

import java.util.Scanner;
import java.util.Arrays;

public class PairSumSortedArray {

    public static String pairSumSorted(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];

            if (sum == target) {
                return "(" + nums[left] + ", " + nums[right] + ")";
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return "Not Found";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();

        if (n < 2) {
            System.out.println("Array must contain at least two elements.");
            sc.close();
            return;
        }

        int[] nums = new int[n];

        System.out.println("Enter sorted array elements:");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        // Check that the input is sorted in ascending order.
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                System.out.println("Please enter a sorted array.");
                sc.close();
                return;
            }
        }

        System.out.print("Enter target sum: ");
        int target = sc.nextInt();

        System.out.println("Result: " + pairSumSorted(nums, target));

        sc.close();
    }
}
