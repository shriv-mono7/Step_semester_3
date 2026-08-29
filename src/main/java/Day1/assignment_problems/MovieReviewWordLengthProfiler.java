package Day1.assignment_problems;

import java.util.Scanner;

public class MovieReviewWordLengthProfiler {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter movie review: ");
        String review = sc.nextLine();

        String[] words = review.split("\\s+");

        int shortWords = 0;
        int mediumWords = 0;
        int longWords = 0;

        for (String word : words) {

            int length = word.length();

            if (length <= 3) {
                shortWords++;
            } else if (length <= 6) {
                mediumWords++;
            } else {
                longWords++;
            }
        }

        System.out.println("Short Words (<=3): " + shortWords);
        System.out.println("Medium Words (4-6): " + mediumWords);
        System.out.println("Long Words (>6): " + longWords);

        sc.close();
    }
}