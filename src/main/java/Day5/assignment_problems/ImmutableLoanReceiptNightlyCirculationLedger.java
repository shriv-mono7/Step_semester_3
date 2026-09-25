
package Day5.assignment_problems;

public class ImmutableLoanReceiptNightlyCirculationLedger {

    /*
     * The base class is not final because
     * ReferenceOnlyLoanReceipt extends LoanReceipt.
     */

    static class LoanReceipt {

        private final String memberId;
        private final String[] bookIds;

        static {
            System.out.println("LoanReceipt class loaded");
        }

        public LoanReceipt(String memberId, String[] bookIds) {

            if (memberId == null || bookIds == null) {
                throw new IllegalArgumentException(
                        "Member ID and book IDs cannot be null"
                );
            }

            for (String bookId : bookIds) {

                if (!isValidBookId(bookId)) {
                    throw new IllegalArgumentException(
                            "Invalid book ID: " + bookId
                    );
                }
            }

            this.memberId = memberId;

            // Defensive copy
            this.bookIds = bookIds.clone();
        }

        private static boolean isValidBookId(String bookId) {

            return bookId != null
                    && bookId.matches("BK-\\d{3}");
        }

        public String[] getBookIds() {

            // Defensive copy
            return bookIds.clone();
        }

        public LoanReceipt withCorrectedBookId(
                int index,
                String newId) {

            if (index < 0 || index >= bookIds.length) {
                throw new IndexOutOfBoundsException(
                        "Invalid book ID index"
                );
            }

            if (!isValidBookId(newId)) {
                throw new IllegalArgumentException(
                        "Invalid corrected book ID"
                );
            }

            String[] correctedBookIds = bookIds.clone();

            correctedBookIds[index] = newId;

            // Return a new object instead of modifying this object
            return new LoanReceipt(
                    memberId,
                    correctedBookIds
            );
        }

        public String getMemberId() {
            return memberId;
        }
    }

    static class ReferenceOnlyLoanReceipt extends LoanReceipt {

        private final String roomNumber;

        public ReferenceOnlyLoanReceipt(
                String memberId,
                String[] bookIds,
                String roomNumber) {

            super(memberId, bookIds);

            if (roomNumber == null
                    || roomNumber.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Room number cannot be empty"
                );
            }

            this.roomNumber = roomNumber;
        }

        public String getRoomNumber() {
            return roomNumber;
        }
    }

    static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        if (receipts == null) {
            return "0 processed | 0 null skipped | "
                    + "0 reference-only | 0 regular";
        }

        for (LoanReceipt receipt : receipts) {

            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + referenceOnly + " reference-only | "
                + regular + " regular";
    }

    public static void main(String[] args) {

        System.out.println(
                "Immutable Loan Receipt & Nightly Ledger"
        );

        System.out.println("--------------------------------");

        String[] books = {
                "BK-101",
                "BK-102"
        };

        LoanReceipt regularReceipt =
                new LoanReceipt("MEM001", books);

        ReferenceOnlyLoanReceipt referenceReceipt =
                new ReferenceOnlyLoanReceipt(
                        "MEM002",
                        new String[]{"BK-201"},
                        "ROOM-A"
                );

        LoanReceipt correctedReceipt =
                regularReceipt.withCorrectedBookId(
                        0,
                        "BK-999"
                );

        System.out.println(
                "Original first book ID: "
                        + regularReceipt.getBookIds()[0]
        );

        System.out.println(
                "Corrected first book ID: "
                        + correctedReceipt.getBookIds()[0]
        );

        System.out.println(
                "Reference room: "
                        + referenceReceipt.getRoomNumber()
        );

        LoanReceipt[] receipts = {
                regularReceipt,
                referenceReceipt,
                null,
                correctedReceipt
        };

        System.out.println("\nNightly Circulation:");

        System.out.println(
                processNightlyCirculation(receipts)
        );

        System.out.println("\nDefensive Copy Test:");

        String[] returnedBookIds =
                regularReceipt.getBookIds();

        returnedBookIds[0] = "BK-000";

        System.out.println(
                "Stored first book ID: "
                        + regularReceipt.getBookIds()[0]
        );
    }
}