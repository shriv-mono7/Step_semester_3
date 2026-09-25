
package Day6.class_problems;

import java.util.ArrayList;

public class FestWideTicketIssuanceSettlement {

    static class EventTicket {
        protected String attendeeId;
        protected double balanceDue;

        private static int nextTicketId = 1000;
        protected int ticketId;

        public EventTicket(String attendeeId, double price) {
            this.attendeeId = attendeeId;
            this.balanceDue = price;
            this.ticketId = nextTicketId++;
        }

        public void pay(double amount) {
            if (amount > 0) {
                balanceDue -= amount;

                if (balanceDue < 0) {
                    balanceDue = 0;
                }
            }
        }

        public void pay(double amount, String promoCode) {
            if (promoCode != null && promoCode.startsWith("F")) {
                amount += 100;
            }

            pay(amount);
        }

        public double getBalanceDue() {
            return balanceDue;
        }

        public String getDetails() {
            return "Ticket ID: " + ticketId
                    + " | Attendee: " + attendeeId
                    + " | Balance: " + balanceDue;
        }
    }

    static class WorkshopTicket extends EventTicket {
        private String track;

        public WorkshopTicket(String id, double price, String track) {
            super(id, price);
            this.track = track;
        }

        @Override
        public String getDetails() {
            return super.getDetails()
                    + " | Track: " + track;
        }
    }

    static class GroupTicket extends EventTicket {
        private int groupSize;

        public GroupTicket(String id, double price, int groupSize) {
            super(id, price);
            this.groupSize = groupSize;
        }

        @Override
        public String getDetails() {
            return super.getDetails()
                    + " | Group Size: " + groupSize;
        }
    }

    static void processNightlySettlement(EventTicket[] tickets) {

        double totalCollected = 0;

        for (EventTicket ticket : tickets) {
            double collected = 1000 - ticket.getBalanceDue();

            if (collected > 0) {
                totalCollected += collected;
            }

            System.out.println(ticket.getDetails());
        }

        System.out.println("Nightly Settlement: "
                + totalCollected);
    }

    public static void main(String[] args) {

        EventTicket standard =
                new EventTicket("STU1", 1000);

        WorkshopTicket workshop =
                new WorkshopTicket("STU2", 1500, "AI/ML");

        GroupTicket group =
                new GroupTicket("GRP1", 2000, 5);

        standard.pay(500);
        workshop.pay(600, "F123A");
        group.pay(1000);

        EventTicket[] tickets = {
                standard, workshop, group
        };

        processNightlySettlement(tickets);
    }
}