package Day3.class_problems;

import java.util.Scanner;

public class SrmStudent {

    String name;
    String regNo;
    int attendance;

    public SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    public void addAttendanceUpdate(int newAttendance) {
        attendance = newAttendance;
    }

    public boolean isEligible() {
        return attendance >= 75;
    }

    // Static because it calculates the average for the whole array.
    // isEligible() is not static because it checks one particular student.
    public static double classAverage(SrmStudent[] students) {

        int total = 0;

        for (SrmStudent student : students) {
            total += student.attendance;
        }

        return (double) total / students.length;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        SrmStudent[] students = new SrmStudent[5];

        for (int i = 0; i < 5; i++) {

            System.out.print("Enter name: ");
            String name = sc.nextLine();

            System.out.print("Enter register number: ");
            String regNo = sc.nextLine();

            System.out.print("Enter attendance: ");
            int attendance = sc.nextInt();
            sc.nextLine();

            students[i] = new SrmStudent(name, regNo, attendance);
        }

        System.out.println("\nStudent Eligibility:");

        for (SrmStudent student : students) {

            String status;

            if (student.isEligible()) {
                status = "Eligible";
            } else {
                status = "Detained";
            }

            System.out.println(
                student.name + " - " +
                student.attendance + "% - " +
                status
            );
        }

        System.out.println(
            "Class average: " + classAverage(students) + "%"
        );

        sc.close();
    }
}