
package Day9.class_problems;

import java.util.Scanner;

public class WarehouseBinGridScan {

    public static void warehouseSummary(int[][] grid) {
        int total = 0;
        int max = grid[0][0];
        int maxRow = 0;
        int maxCol = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                total += grid[i][j];

                // Update only when a strictly larger value is found.
                // This preserves the first maximum in row-by-row order.
                if (grid[i][j] > max) {
                    max = grid[i][j];
                    maxRow = i;
                    maxCol = j;
                }
            }
        }

        System.out.println("Total items: " + total);
        System.out.println(
            "Maximum bin coordinates: (" + maxRow + ", " + maxCol + ")"
        );
        System.out.println("Maximum items in one bin: " + max);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int rows = sc.nextInt();

        System.out.print("Enter number of columns: ");
        int cols = sc.nextInt();

        if (rows <= 0 || cols <= 0) {
            System.out.println("Rows and columns must be positive.");
            sc.close();
            return;
        }

        int[][] grid = new int[rows][cols];

        System.out.println("Enter bin item counts row by row:");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = sc.nextInt();

                if (grid[i][j] < 0) {
                    System.out.println("Item counts cannot be negative.");
                    sc.close();
                    return;
                }
            }
        }

        warehouseSummary(grid);
        sc.close();
    }
}
