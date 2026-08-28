package Day1.class_problems;

import java.util.Scanner;

public class PalindromeChecker {

    static boolean isPalindromeIterative(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
    static boolean isPalindromeRecursive(String s, int left, int right) {

    if (left >= right) {
        return true;
    }

    if (s.charAt(left) != s.charAt(right)) {
        return false;
    }

    return isPalindromeRecursive(s, left + 1, right - 1);
    }

    static boolean isPalindromeArray(String s) {

    char[] original = s.toCharArray();
    char[] reversed = new char[original.length];

    for (int i = 0; i < original.length; i++) {
        reversed[i] = original[original.length - 1 - i];
    }

    for (int i = 0; i < original.length; i++) {
        if (original[i] != reversed[i]) {
            return false;
        }
    }

    return true;
}

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        if (isPalindromeIterative(input)) {
            System.out.println("Palindrome");
        } else {
            System.out.println("Not a Palindrome");
        }

        sc.close();
    }
}