package Day2.class_problems;

import java.util.Scanner;

public class ProductInventorySummary {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of products: ");
        int n = sc.nextInt();
        sc.nextLine();

        String highestProduct = "";
        int highestQuantity = 0;
        int totalQuantity = 0;

        for (int i = 0; i < n; i++) {

            System.out.print("Enter product record: ");
            String record = sc.nextLine();

            String[] fields = record.split(",");

            String productName = fields[0];
            int quantity = Integer.parseInt(fields[2]);

            totalQuantity += quantity;

            if (quantity > highestQuantity) {
                highestQuantity = quantity;
                highestProduct = productName;
            }
        }

        System.out.println("Total Quantity: " + totalQuantity);
        System.out.println("Highest Quantity Product: " + highestProduct);
        System.out.println("Highest Quantity: " + highestQuantity);

        sc.close();
    }
}