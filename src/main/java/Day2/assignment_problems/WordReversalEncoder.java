package Day2.assignment_problems;

import java.util.Scanner;

public class WordReversalEncoder {

    public static String reverseEachWord(String sentence) {

        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {

            StringBuilder sb = new StringBuilder(words[i]);
            result.append(sb.reverse());

            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter sentence: ");
        String sentence = sc.nextLine();

        System.out.println("Reversed sentence: " + reverseEachWord(sentence));

        sc.close();
    }
}