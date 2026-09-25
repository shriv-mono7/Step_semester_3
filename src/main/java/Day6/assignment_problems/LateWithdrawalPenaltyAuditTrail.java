
package Day6.assignment_problems;

import java.util.Arrays;

public class LateWithdrawalPenaltyAuditTrail {

    static class RaceEntry {
        protected double balanceDue;
        private double[] penaltyHistory = new double[10];
        private int penaltyCount = 0;

        public RaceEntry(String runnerId, double entryFee) {
            if (runnerId == null || runnerId.trim().length() < 4
                    || entryFee <= 0) {
                throw new IllegalArgumentException("Invalid entry details");
            }

            balanceDue = entryFee;
        }

        public void pay(double amount) {
            if (amount > 0) {
                balanceDue -= amount;

                if (balanceDue < 0) {
                    balanceDue = 0;
                }
            }
        }

        protected void applyLateFee(double amount) {
            if (amount > 0 && penaltyCount < 10) {
                balanceDue += amount;
                penaltyHistory[penaltyCount] = amount;
                penaltyCount++;
            }
        }

        public double[] getPenaltyHistory() {
            return Arrays.copyOf(penaltyHistory, penaltyCount);
        }

        public double getBalanceDue() {
            return balanceDue;
        }
    }

    static class MarathonEntry extends RaceEntry {

        public MarathonEntry(String runnerId, double entryFee) {
            super(runnerId, entryFee);
        }

        @Override
        protected void applyLateFee(double amount) {
            super.applyLateFee(amount * 2);
        }
    }

    public static void main(String[] args) {

        MarathonEntry entry =
                new MarathonEntry("RUN1", 1200);

        entry.pay(1200);
        entry.applyLateFee(100);

        System.out.println("Balance Due: "
                + entry.getBalanceDue());

        double[] history = entry.getPenaltyHistory();

        System.out.println("Penalty History: "
                + Arrays.toString(history));

        history[0] = 999;

        System.out.println("After External Modification: "
                + Arrays.toString(entry.getPenaltyHistory()));
    }
}