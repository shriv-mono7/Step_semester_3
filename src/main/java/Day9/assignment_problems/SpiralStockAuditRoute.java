
package Day9.assignment_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpiralStockAuditRoute {

    public static List<Integer> auditRoute(int[][] grid) {
        List<Integer> result = new ArrayList<>();

        int top = 0;
        int bottom = grid.length - 1;
        int left = 0;
        int right = grid[0].length - 1;

        while (top <= bottom && left <= right) {

            // 1. Traverse the top row from left to right
            for (int col = left; col <= right; col++) {
                result.add(grid[top][col]);
            }
            top++;

            // 2. Traverse the right column from top to bottom
            for (int row = top; row <= bottom; row++) {
                result.add(grid[row][right]);
            }
            right--;

            // 3. Traverse the bottom row from right to left
            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    result.add(grid[bottom][col]);
                }
                bottom--;
            }

            // 4. Traverse the left column from bottom to top
            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    result.add(grid[row][left]);
                }
                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int rows = sc.nextInt();

        System.out.print("Enter number of columns: ");
        int cols = sc.nextInt();

        if (rows <= 0 || cols <= 0 || rows > 100 || cols > 100) {
            System.out.println("Rows and columns must be between 1 and 100.");
            sc.close();
            return;
        }

        int[][] grid = new int[rows][cols];

        System.out.println("Enter bin IDs row by row:");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        List<Integer> route = auditRoute(grid);

        System.out.println("Audit Route: " + route);

        sc.close();
    }
}
