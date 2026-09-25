
package Day5.assignment_problems;

public class BookCopyCirculationGuard {

    static class BookInventory {

        private int copiesTotal;
        private int copiesAvailable;

        public BookInventory(int copiesTotal) {

            if (copiesTotal <= 0) {
                throw new IllegalArgumentException(
                        "Total copies must be positive"
                );
            }

            this.copiesTotal = copiesTotal;
            this.copiesAvailable = copiesTotal;
        }

        public void checkOut() {

            if (copiesAvailable > 0) {
                copiesAvailable--;
            }
        }

        public void checkIn() {

            if (copiesAvailable < copiesTotal) {
                copiesAvailable++;
            }
        }

        public int getCopiesAvailable() {
            return copiesAvailable;
        }
    }

    public static void main(String[] args) {

        System.out.println("Book Copy Circulation Guard");
        System.out.println("--------------------------------");

        try {

            BookInventory invalidInventory =
                    new BookInventory(0);

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Invalid inventory rejected"
            );
        }

        BookInventory inventory =
                new BookInventory(3);

        System.out.println(
                "Initial available copies: "
                        + inventory.getCopiesAvailable()
        );

        inventory.checkOut();

        System.out.println(
                "After 1 checkout: "
                        + inventory.getCopiesAvailable()
        );

        inventory.checkOut();

        System.out.println(
                "After 2 checkouts: "
                        + inventory.getCopiesAvailable()
        );

        inventory.checkOut();

        System.out.println(
                "After 3 checkouts: "
                        + inventory.getCopiesAvailable()
        );

        inventory.checkOut();

        System.out.println(
                "After 4 checkouts: "
                        + inventory.getCopiesAvailable()
        );

        inventory.checkIn();

        System.out.println(
                "After 1 check-in: "
                        + inventory.getCopiesAvailable()
        );

        inventory.checkIn();

        inventory.checkIn();

        inventory.checkIn();

        System.out.println(
                "After 4 total check-ins: "
                        + inventory.getCopiesAvailable()
        );
    }
}