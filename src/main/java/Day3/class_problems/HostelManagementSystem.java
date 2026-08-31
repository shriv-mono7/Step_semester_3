package Day3.class_problems;

public class HostelManagementSystem {

    // ---------- FeeAccount ----------

    static class FeeAccount {

        private double totalFee;
        private double amountPaid;

        public FeeAccount(double totalFee, double amountPaid) {
            this.totalFee = totalFee;
            this.amountPaid = amountPaid;
        }

        public void pay(double amount) {

            if (amount <= 0) {
                System.out.println(
                    "Payment rejected: amount must be positive"
                );
                return;
            }

            amountPaid += amount;

            if (amountPaid > totalFee) {
                amountPaid = totalFee;
            }
        }

        public double getDue() {
            return totalFee - amountPaid;
        }
    }

    // ---------- HostelFeeAccount ----------

    static class HostelFeeAccount extends FeeAccount {

        public HostelFeeAccount(double totalFee, double amountPaid) {
            super(totalFee, amountPaid);
        }

        public void payInTwoInstallments(double amount) {
            pay(amount / 2);
            pay(amount / 2);
        }
    }

    // ---------- HostelRoom ----------

    static class HostelRoom {

        String roomNo;
        int beds;
        int occupied;

        public HostelRoom(String roomNo, int beds, int occupied) {
            this.roomNo = roomNo;
            this.beds = beds;
            this.occupied = occupied;
        }

        public void allot(String studentName) {

            if (occupied < beds) {
                occupied++;

                System.out.println(
                    studentName + " allotted to room " + roomNo
                );
            }
        }

        static HostelRoom findAvailableRoom(HostelRoom[] rooms) {

            for (HostelRoom room : rooms) {

                if (room.occupied < room.beds) {
                    return room;
                }
            }

            return null;
        }

        static void safeAllot(
                HostelRoom[] rooms,
                String studentName) {

            HostelRoom room = findAvailableRoom(rooms);

            if (room != null) {
                room.allot(studentName);
            } else {
                System.out.println(
                    "No rooms available for " + studentName
                );
            }
        }
    }

    // ---------- SrmStudent ----------

    static class SrmStudent {

        String name;
        String regNo;
        HostelFeeAccount feeAccount;
        HostelRoom room;

        static int totalStudents = 0;

        public SrmStudent(
                String name,
                String regNo,
                HostelFeeAccount feeAccount) {

            this.name = name;
            this.regNo = regNo;
            this.feeAccount = feeAccount;
            this.room = null;

            totalStudents++;
        }

        public String fullStatus() {

            String roomStatus;

            if (room == null) {
                roomStatus = "unallotted";
            } else {
                roomStatus = room.roomNo;
            }

            return name
                    + " | Due: Rs "
                    + feeAccount.getDue()
                    + " | Room: "
                    + roomStatus;
        }
    }

    // ---------- Main ----------

    public static void main(String[] args) {

        SrmStudent ravi = new SrmStudent(
            "Ravi",
            "RA001",
            new HostelFeeAccount(150000, 100000)
        );

        SrmStudent anitha = new SrmStudent(
            "Anitha",
            "RA002",
            new HostelFeeAccount(200000, 100000)
        );

        SrmStudent karthik = new SrmStudent(
            "Karthik",
            "RA003",
            new HostelFeeAccount(200000, 0)
        );

        HostelRoom[] rooms = {
            new HostelRoom("C-214", 1, 0),
            new HostelRoom("C-507", 1, 0)
        };

        // Allot rooms to only two students.
        ravi.room = HostelRoom.findAvailableRoom(rooms);
        ravi.room.allot(ravi.name);

        anitha.room = HostelRoom.findAvailableRoom(rooms);
        anitha.room.allot(anitha.name);

        // Valid payment.
        ravi.feeAccount.pay(10000);

        // Rejected payment.
        anitha.feeAccount.pay(-5000);

        System.out.println();

        System.out.println(ravi.fullStatus());
        System.out.println(anitha.fullStatus());
        System.out.println(karthik.fullStatus());

        System.out.println(
            "Total students: " + SrmStudent.totalStudents
        );
    }
}