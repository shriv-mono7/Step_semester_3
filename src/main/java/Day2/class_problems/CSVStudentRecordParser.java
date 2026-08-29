package Day2.class_problems;

import java.util.Scanner;

public class CSVStudentRecordParser {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter student record: ");
        String record = sc.nextLine();

        String[] fields = record.split(",");

        if (fields.length != 3) {
            System.out.println("Invalid Record");
        } else {
            String studentId = fields[0];
            String name = fields[1];
            int marks = Integer.parseInt(fields[2]);

            System.out.println("ID: " + studentId);
            System.out.println("Name: " + name);
            System.out.println("Marks: " + marks);
        }

        sc.close();
    }
}