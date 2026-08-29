package Day1.class_problems;

import java.util.Scanner;

public class ReverseCustomerName {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter customer name: ");
        String name = sc.nextLine();

        StringBuilder sb = new StringBuilder(name);

        System.out.println("Reversed name: " + sb.reverse());

        sc.close();
    }
}