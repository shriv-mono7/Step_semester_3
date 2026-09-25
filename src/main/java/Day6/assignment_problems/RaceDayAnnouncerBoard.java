
package Day6.assignment_problems;

public class RaceDayAnnouncerBoard {

    static class RaceEntry {
        protected String runnerId;
        protected double balanceDue;

        public RaceEntry(String runnerId, double fee) {
            this.runnerId = runnerId;
            this.balanceDue = fee;
        }

        public String getEntryDetails() {
            return "Race Entry | ID: " + runnerId
                    + " | Balance: " + balanceDue;
        }
    }

    static class MarathonEntry extends RaceEntry {
        private String category;

        public MarathonEntry(String id, double fee, String category) {
            super(id, fee);
            this.category = category;
        }

        @Override
        public String getEntryDetails() {
            return "Marathon Entry | ID: " + runnerId
                    + " | Category: " + category
                    + " | Balance: " + balanceDue;
        }

        public String getCategory() {
            return category;
        }
    }

    static class RelayEntry extends RaceEntry {
        private String teamName;

        public RelayEntry(String id, double fee, String teamName) {
            super(id, fee);
            this.teamName = teamName;
        }

        @Override
        public String getEntryDetails() {
            return "Relay Entry | ID: " + runnerId
                    + " | Team: " + teamName
                    + " | Balance: " + balanceDue;
        }
    }

    static String batchPrint(RaceEntry[] entries) {

        StringBuilder result = new StringBuilder();

        for (RaceEntry entry : entries) {

            result.append(entry.getEntryDetails())
                    .append("\n");

            if (entry instanceof MarathonEntry) {
                MarathonEntry marathon =
                        (MarathonEntry) entry;

                result.append("Category Verified: ")
                        .append(marathon.getCategory())
                        .append("\n");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {

        RaceEntry[] entries = {
                new RaceEntry("RUN1", 500),
                new MarathonEntry("RUN2", 1200, "10K"),
                new RelayEntry("RUN3", 800, "Fast Runners")
        };

        System.out.println("RACE-DAY ANNOUNCER BOARD");
        System.out.println(batchPrint(entries));
    }
}