package Day1.assignment_problems;

import java.util.Scanner;

public class TypingSpeedTestAccuracyChecker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter original sentence: ");
        String original = sc.nextLine();

        System.out.print("Enter typed sentence: ");
        String typed = sc.nextLine();

        int minLength = Math.min(original.length(), typed.length());
        int correctCharacters = 0;

        for (int i = 0; i < minLength; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                correctCharacters++;
            }
        }

        int totalCharacters = Math.max(original.length(), typed.length());

        double accuracy = (correctCharacters * 100.0) / totalCharacters;

        System.out.println("Correct Characters: " + correctCharacters);
        System.out.println("Accuracy: " + accuracy + "%");

        sc.close();
    }
}