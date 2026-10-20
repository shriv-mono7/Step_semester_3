package Day10.class_problems;

public class StudentMarksGrid {
    public static void main(String[] args) {
        String[] students = {"Anu", "Ravi", "Meena"};
        String[] subjects = {"Math", "Sci", "Eng"};

        // -1 represents a missing mark.
        int[][] marks = {
            {80, 90, 70},
            {60, 85, -1},
            {95, 75, 88}
        };

        int n = students.length;
        int m = subjects.length;

        System.out.println("Student Totals:");

        for (int i = 0; i < n; i++) {
            int total = 0;

            for (int j = 0; j < m; j++) {
                if (marks[i][j] != -1) {
                    total += marks[i][j];
                }
            }

            System.out.println(students[i] + " " + total);
        }

        System.out.println("\nSubject Toppers:");

        for (int j = 0; j < m; j++) {
            int highest = -1;
            int topperIndex = -1;

            for (int i = 0; i < n; i++) {
                if (marks[i][j] != -1 && marks[i][j] > highest) {
                    highest = marks[i][j];
                    topperIndex = i;
                }
            }

            if (topperIndex != -1) {
                System.out.println(
                    subjects[j] + " " + students[topperIndex]
                    + " " + highest
                );
            }
        }
    }
}