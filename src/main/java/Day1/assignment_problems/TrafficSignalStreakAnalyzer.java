package Day1.assignment_problems;

import java.util.Scanner;

public class TrafficSignalStreakAnalyzer {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter traffic signal sequence: ");
        String signal = sc.nextLine();

        int maxStreak = 1;
        int currentStreak = 1;
        char streakCharacter = signal.charAt(0);

        for (int i = 1; i < signal.length(); i++) {

            if (signal.charAt(i) == signal.charAt(i - 1)) {
                currentStreak++;
            } else {
                currentStreak = 1;
            }

            if (currentStreak > maxStreak) {
                maxStreak = currentStreak;
                streakCharacter = signal.charAt(i);
            }
        }

        System.out.println("Longest Streak: " + streakCharacter);
        System.out.println("Streak Length: " + maxStreak);

        sc.close();
    }
}