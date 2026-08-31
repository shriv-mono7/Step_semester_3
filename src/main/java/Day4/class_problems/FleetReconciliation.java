package Day4.class_problems;

public class FleetReconciliation {

    // ---------- BusTicketAccount ----------

    static class BusTicketAccount {

        protected String bookingId;
        protected double ticketFare;

        static double penaltyRate;

        // One-time class-level setup
        static {
            penaltyRate = 1.0;
        }

        // Full constructor
        public BusTicketAccount(
                String bookingId,
                double ticketFare) {

            if (ticketFare < 0) {
                throw new IllegalArgumentException(
                    "Ticket fare cannot be negative"
                );
            }

            this.bookingId = bookingId;
            this.ticketFare = ticketFare;
        }

        // Provisional constructor using this(...)
        public BusTicketAccount(String bookingId) {
            this(bookingId, 0.0);
        }

        public final double calculatePenalty(int minutesLate) {

            if (minutesLate < 0) {
                throw new IllegalArgumentException(
                    "Minutes late cannot be negative"
                );
            }

            if (minutesLate == 0) {
                return 0.0;
            }

            return ticketFare * penaltyRate / 100.0;
        }

        public void processAccount(
                double amount,
                int minutesLate) {

            double penalty = calculatePenalty(minutesLate);

            System.out.println(
                bookingId
                + " processed | Amount: Rs "
                + amount
                + " | Penalty: Rs "
                + penalty
            );
        }
    }


    // ---------- SleeperAccount ----------

    static class SleeperAccount extends BusTicketAccount {

        public SleeperAccount(
                String bookingId,
                double ticketFare) {

            super(bookingId, ticketFare);
        }

        @Override
        public void processAccount(
                double amount,
                int minutesLate) {

            double penalty =
                    calculatePenalty(minutesLate) * 0.5;

            System.out.println(
                bookingId
                + " sleeper processed | Amount: Rs "
                + amount
                + " | Penalty: Rs "
                + penalty
            );
        }
    }


    // ---------- Batch Processing ----------

    public static void processBatch(
            BusTicketAccount[] accounts,
            double[] amounts,
            int[] minutesLateArray) {

        if (accounts == null
                || amounts == null
                || minutesLateArray == null) {

            System.out.println(
                "Invalid batch: null array"
            );
            return;
        }

        // Process only the common valid length.
        int limit = Math.min(
            accounts.length,
            Math.min(
                amounts.length,
                minutesLateArray.length
            )
        );

        int processed = 0;
        int nullSkipped = 0;
        int sleeperCount = 0;
        int regularCount = 0;

        double grandTotalPenalties = 0.0;

        for (int i = 0; i < limit; i++) {

            BusTicketAccount account = accounts[i];

            if (account == null) {
                nullSkipped++;
                continue;
            }

            try {

                double beforePenalty =
                        account.calculatePenalty(
                                minutesLateArray[i]
                        );

                if (account instanceof SleeperAccount) {
                    sleeperCount++;
                } else {
                    regularCount++;
                }

                account.processAccount(
                        amounts[i],
                        minutesLateArray[i]
                );

                /*
                 * Sleeper accounts settle differently, so their
                 * actual penalty is half the normal penalty.
                 */
                if (account instanceof SleeperAccount) {
                    grandTotalPenalties +=
                            beforePenalty * 0.5;
                } else {
                    grandTotalPenalties +=
                            beforePenalty;
                }

                processed++;

            } catch (IllegalArgumentException e) {

                System.out.println(
                    account.bookingId
                    + " skipped: "
                    + e.getMessage()
                );
            }
        }

        System.out.println();

        System.out.println(
            processed
            + " processed | "
            + nullSkipped
            + " null skipped | "
            + sleeperCount
            + " sleeper | "
            + regularCount
            + " regular | grand total penalties = Rs "
            + grandTotalPenalties
        );
    }


    // ---------- Main ----------

    public static void main(String[] args) {

        BusTicketAccount[] accounts = {

            new SleeperAccount(
                "BK001",
                2000
            ),

            null,

            new BusTicketAccount(
                "BK002",
                1200
            )
        };

        double[] amounts = {
            1200,
            900,
            700
        };

        int[] minutesLateArray = {
            10,
            5,
            0
        };

        processBatch(
            accounts,
            amounts,
            minutesLateArray
        );
    }
}