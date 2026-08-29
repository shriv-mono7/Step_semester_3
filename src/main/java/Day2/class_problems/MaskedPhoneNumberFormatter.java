package Day2.class_problems;

import java.util.Scanner;

public class MaskedPhoneNumberFormatter {

    public static String maskPhoneNumber(String phoneNumber) {

        String digits = phoneNumber.replaceAll("\\D", "");

        if (digits.length() < 4) {
            return "Invalid Phone Number";
        }

        String lastFour = digits.substring(digits.length() - 4);

        return "XXXXXX" + lastFour;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter phone number: ");
        String phoneNumber = sc.nextLine();

        System.out.println("Masked Phone: " + maskPhoneNumber(phoneNumber));

        sc.close();
    }
}