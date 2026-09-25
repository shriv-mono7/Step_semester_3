
package Day6.class_problems;

public class TicketHierarchyFoundationBatchValidator {

    static class EventTicket {

        private String attendeeId;
        private double basePrice;
        private double balanceDue;

        public EventTicket(String attendeeId, double basePrice) {

            if (attendeeId == null ||
                attendeeId.trim().length() < 4) {
                throw new IllegalArgumentException(
                    "Invalid attendee ID"
                );
            }

            if (basePrice <= 0) {
                throw new IllegalArgumentException(
                    "Price must be positive"
                );
            }

            this.attendeeId = attendeeId;
            this.basePrice = basePrice;
            this.balanceDue = basePrice;
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
                String[] attendeeIds,
                double basePrice) {

            int registered = 0;
            int rejected = 0;

            for (String id : attendeeIds) {

                try {
                    new EventTicket(id, basePrice);
                    registered++;

                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }

            return "Registered: " + registered +
                   " | Rejected: " + rejected;
        }
    }

    static class WorkshopTicket extends EventTicket {

        private String track;

        public WorkshopTicket(
                String attendeeId,
                double basePrice,
                String track) {

            super(attendeeId, basePrice);
            this.track = track;
        }

        public String getTrack() {
            return track;
        }
    }

    public static void main(String[] args) {

        WorkshopTicket workshop =
                new WorkshopTicket("STU2", 1200, "AI/ML");

        workshop.pay(500);

        System.out.println(
                "Balance: " + workshop.getBalanceDue()
        );

        String[] ids = {
            "STU1", "ST1", "STU2", " ", "STU3"
        };

        System.out.println(
                EventTicket.registerBatch(ids, 500)
        );
    }
}