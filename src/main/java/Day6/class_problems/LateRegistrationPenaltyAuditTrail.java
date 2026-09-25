
package Day6.class_problems;

import java.util.Arrays;

public class LateRegistrationPenaltyAuditTrail {

    static class EventTicket {
        protected double balanceDue;
        private double[] lateFeeHistory = new double[10];
        private int feeCount = 0;

        public EventTicket(String attendeeId, double basePrice) {
            if (attendeeId == null || attendeeId.trim().length() < 4
                    || basePrice <= 0) {
                throw new IllegalArgumentException("Invalid ticket details");
            }

            balanceDue = basePrice;
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
            if (amount > 0 && feeCount < 10) {
                balanceDue += amount;
                lateFeeHistory[feeCount] = amount;
                feeCount++;
            }
        }

        public double[] getLateFeeHistory() {
            return Arrays.copyOf(lateFeeHistory, feeCount);
        }

        public double getBalanceDue() {
            return balanceDue;
        }
    }

    static class WorkshopTicket extends EventTicket {

        public WorkshopTicket(String attendeeId, double basePrice) {
            super(attendeeId, basePrice);
        }

        @Override
        protected void applyLateFee(double amount) {
            super.applyLateFee(amount * 2);
        }
    }

    public static void main(String[] args) {

        WorkshopTicket ticket =
                new WorkshopTicket("STU1", 1200);

        ticket.pay(1200);
        ticket.applyLateFee(100);

        System.out.println("Balance Due: "
                + ticket.getBalanceDue());

        double[] history = ticket.getLateFeeHistory();

        System.out.println("Late Fee History: "
                + Arrays.toString(history));

        history[0] = 999;

        System.out.println("After External Modification: "
                + Arrays.toString(ticket.getLateFeeHistory()));
    }
}