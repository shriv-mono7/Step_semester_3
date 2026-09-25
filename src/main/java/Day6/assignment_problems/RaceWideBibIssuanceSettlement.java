
package Day6.assignment_problems;

public class RaceWideBibIssuanceSettlement {

    static class RaceEntry {
        protected String runnerId;
        protected double balanceDue;
        protected int bibNumber;

        private static int nextBibNumber = 1000;

        public RaceEntry(String runnerId, double fee) {
            this.runnerId = runnerId;
            this.balanceDue = fee;
            this.bibNumber = nextBibNumber++;
        }

        public void pay(double amount) {
            if (amount > 0) {
                balanceDue -= amount;

                if (balanceDue < 0) {
                    balanceDue = 0;
                }
            }
        }

        public void pay(double amount, String discountCode) {
            if (discountCode != null
                    && discountCode.startsWith("R")) {
                amount += 100;
            }

            pay(amount);
        }

        public double getBalanceDue() {
            return balanceDue;
        }

        public String getDetails() {
            return "Bib: " + bibNumber
                    + " | Runner: " + runnerId
                    + " | Balance: " + balanceDue;
        }
    }

    static class MarathonEntry extends RaceEntry {
        private String category;

        public MarathonEntry(String id, double fee,
                             String category) {
            super(id, fee);
            this.category = category;
        }

        @Override
        public String getDetails() {
            return super.getDetails()
                    + " | Category: " + category;
        }
    }

    static class GroupRaceEntry extends RaceEntry {
        private int groupSize;

        public GroupRaceEntry(String id, double fee,
                              int groupSize) {
            super(id, fee);
            this.groupSize = groupSize;
        }

        @Override
        public String getDetails() {
            return super.getDetails()
                    + " | Group Size: " + groupSize;
        }
    }

    static void processNightlySettlement(RaceEntry[] entries) {

        double totalCollected = 0;

        for (RaceEntry entry : entries) {

            double collected = 1000 - entry.getBalanceDue();

            if (collected > 0) {
                totalCollected += collected;
            }

            System.out.println(entry.getDetails());
        }

        System.out.println("Nightly Settlement: "
                + totalCollected);
    }

    public static void main(String[] args) {

        RaceEntry standard =
                new RaceEntry("RUN1", 1000);

        MarathonEntry marathon =
                new MarathonEntry("RUN2", 1500, "10K");

        GroupRaceEntry group =
                new GroupRaceEntry("GRP1", 2000, 5);

        standard.pay(500);
        marathon.pay(600, "R123A");
        group.pay(1000);

        RaceEntry[] entries = {
                standard, marathon, group
        };

        processNightlySettlement(entries);
    }
}