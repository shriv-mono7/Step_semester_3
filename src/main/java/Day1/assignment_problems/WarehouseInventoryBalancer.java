package Day1.assignment_problems;

import java.util.Scanner;

public class WarehouseInventoryBalancer {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] inventory = new int[n];

        System.out.println("Enter inventory quantities:");

        for (int i = 0; i < n; i++) {
            inventory[i] = sc.nextInt();
        }

        int totalInventory = 0;
        int maxQuantity = inventory[0];
        int maxIndex = 0;

        for (int i = 0; i < n; i++) {

            totalInventory += inventory[i];

            if (inventory[i] > maxQuantity) {
                maxQuantity = inventory[i];
                maxIndex = i;
            }
        }

        System.out.println("Total Inventory: " + totalInventory);
        System.out.println("Highest Quantity: " + maxQuantity);
        System.out.println("Index of Highest Quantity: " + maxIndex);

        sc.close();
    }
}