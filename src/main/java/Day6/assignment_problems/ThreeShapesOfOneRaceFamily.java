
package Day6.assignment_problems;

public class ThreeShapesOfOneRaceFamily {

    static class RaceEntry {
        protected double entryFee;
        protected double balanceDue;

        public RaceEntry(String runnerId, double fee) {
            if (runnerId == null || runnerId.trim().length() < 4
                    || fee <= 0) {
                throw new IllegalArgumentException("Invalid details");
            }

            entryFee = fee;
            balanceDue = fee;
        }

        public double getBalanceDue() {
            return balanceDue;
        }

        public void printEntry() {
            System.out.println(
                    "Standard Race Entry | Balance: " + balanceDue);
        }
    }

    static class MarathonEntry extends RaceEntry {
        protected String category;

        public MarathonEntry(String id, double fee, String category) {
            super(id, fee);
            this.category = category;
        }

        @Override
        public void printEntry() {
            System.out.println(
                    "Marathon Entry | Category: " + category
                    + " | Balance: " + balanceDue);
        }
    }

    static class PremiumMarathonEntry extends MarathonEntry {
        private double kitFee;

        public PremiumMarathonEntry(String id, double fee,
                                    String category, double kitFee) {
            super(id, fee, category);
            this.kitFee = kitFee;
        }

        @Override
        public void printEntry() {
            System.out.println(
                    "Premium Marathon Entry | Category: " + category
                    + " | Kit Fee: " + kitFee
                    + " | Balance: " + balanceDue);
        }
    }

    static class RelayEntry extends RaceEntry {
        private String teamName;

        public RelayEntry(String id, double fee, String teamName) {
            super(id, fee);
            this.teamName = teamName;
        }

        @Override
        public void printEntry() {
            System.out.println(
                    "Relay Entry | Team: " + teamName
                    + " | Balance: " + balanceDue);
        }
    }

    static String classifyGeneration(RaceEntry entry) {

        if (entry instanceof PremiumMarathonEntry) {
            return "Multilevel descendant";
        }

        if (entry instanceof RelayEntry) {
            return "Hierarchical sibling";
        }

        return "Base or direct child";
    }

    static double getTotalBalanceDue(RaceEntry[] entries) {

        double total = 0;

        for (RaceEntry entry : entries) {
            total += entry.getBalanceDue();
        }

        return total;
    }

    public static void main(String[] args) {

        RaceEntry standard =
                new RaceEntry("RUN1", 500);

        MarathonEntry marathon =
                new MarathonEntry("RUN2", 1200, "10K");

        PremiumMarathonEntry premium =
                new PremiumMarathonEntry(
                        "RUN3", 2000, "Full Marathon", 300);

        RelayEntry relay =
                new RelayEntry("RUN4", 800, "Fast Runners");

        standard.printEntry();
        marathon.printEntry();
        premium.printEntry();
        relay.printEntry();

        System.out.println(classifyGeneration(premium));
        System.out.println(classifyGeneration(relay));

        RaceEntry[] entries = {
                standard, marathon, premium, relay
        };

        System.out.println(
                "Total Balance: " + getTotalBalanceDue(entries));
    }
}