
package Day6.assignment_problems;

public class RaceEntryFoundationBatchValidator {

    static class RaceEntry {
        private String runnerId;
        private double entryFee;
        private double balanceDue;

        public RaceEntry(String runnerId, double entryFee) {

            if (runnerId == null ||
                    runnerId.trim().length() < 4) {
                throw new IllegalArgumentException("Invalid runner ID");
            }

            if (entryFee <= 0) {
                throw new IllegalArgumentException("Fee must be positive");
            }

            this.runnerId = runnerId;
            this.entryFee = entryFee;
            this.balanceDue = entryFee;
        }

        public void pay(double amount) {
            if (amount > 0) {
                balanceDue -= amount;

                if (balanceDue < 0) {
                    balanceDue = 0;
                }
            }
        }

        public double getBalanceDue() {
            return balanceDue;
        }

        public static String registerBatch(
                String[] runnerIds, double entryFee) {

            int registered = 0;
            int rejected = 0;

            for (String id : runnerIds) {
                try {
                    new RaceEntry(id, entryFee);
                    registered++;
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }

            return "Registered: " + registered
                    + " | Rejected: " + rejected;
        }
    }

    static class MarathonEntry extends RaceEntry {
        private String category;

        public MarathonEntry(String id, double fee,
                             String category) {
            super(id, fee);
            this.category = category;
        }

        public String getCategory() {
            return category;
        }
    }

    public static void main(String[] args) {

        MarathonEntry runner =
                new MarathonEntry("RUN1", 1500, "10K");

        runner.pay(500);

        System.out.println("Balance: "
                + runner.getBalanceDue());

        String[] runnerIds = {
                "RUN1", "R1", "RUN2", " ", "RUN3"
        };

        System.out.println(
                RaceEntry.registerBatch(runnerIds, 500));
    }
}