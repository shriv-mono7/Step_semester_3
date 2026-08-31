package Day3.class_problems;

public class SrmStudentStaticDemo {

    /*
     * BROKEN VERSION
     *
     * name, regNo and attendance are wrongly declared static.
     *
     * They are student-specific values, so they should NOT be static.
     * A static field is shared by every object of the class.
     */

    static class BrokenStudent {

        static String name;
        static String regNo;
        static int attendance;

        BrokenStudent(String name, String regNo, int attendance) {
            BrokenStudent.name = name;
            BrokenStudent.regNo = regNo;
            BrokenStudent.attendance = attendance;
        }
    }

    /*
     * FIXED VERSION
     *
     * name, regNo and attendance are instance fields because
     * every student must have independent values.
     *
     * university and admissionCount are static because they
     * belong to the college/class as a whole and are shared.
     */

    static class SrmStudent {

        String name;
        String regNo;
        int attendance;

        static String university = "SRM";
        static int admissionCount = 0;

        SrmStudent(String name, int attendance) {
            this.name = name;
            this.attendance = attendance;

            admissionCount++;
            this.regNo = "RA2311003010" + admissionCount;
        }

        void printIdCard() {
            System.out.println(
                name + " | " + regNo
            );
        }

        static void printTotalAdmissions() {
            System.out.println(
                "Students admitted so far: " + admissionCount
            );
        }
    }

    public static void main(String[] args) {

        // ---------- BROKEN VERSION ----------

        System.out.println("Broken version:");

        BrokenStudent student1 =
                new BrokenStudent(
                        "Ravi",
                        "RA231100301011",
                        85
                );

        BrokenStudent student2 =
                new BrokenStudent(
                        "Meera",
                        "RA231100301012",
                        90
                );

        System.out.println(BrokenStudent.name);
        System.out.println(BrokenStudent.name);

        /*
         * Both print Meera because name is static.
         * The second object overwrites the shared static field.
         */

        // ---------- FIXED VERSION ----------

        System.out.println("\nFixed version:");

        SrmStudent ravi =
                new SrmStudent("Ravi", 85);

        SrmStudent meera =
                new SrmStudent("Meera", 90);

        ravi.printIdCard();
        meera.printIdCard();

        SrmStudent.printTotalAdmissions();
    }
}