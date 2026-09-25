
package Day6.class_problems;

public class NightlyTicketAnnouncer {

    static class EventTicket {
        protected String attendeeId;
        protected double balanceDue;

        public EventTicket(String attendeeId, double price) {
            this.attendeeId = attendeeId;
            this.balanceDue = price;
        }

        public String getTicketDetails() {
            return "Event Ticket | ID: " + attendeeId
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
        public String getTicketDetails() {
            return "Workshop Ticket | ID: " + attendeeId
                    + " | Track: " + track
                    + " | Balance: " + balanceDue;
        }

        public String getTrack() {
            return track;
        }
    }

    static class HackathonTicket extends EventTicket {
        private String teamName;

        public HackathonTicket(String id, double price, String teamName) {
            super(id, price);
            this.teamName = teamName;
        }

        @Override
        public String getTicketDetails() {
            return "Hackathon Ticket | ID: " + attendeeId
                    + " | Team: " + teamName
                    + " | Balance: " + balanceDue;
        }
    }

    static String batchPrint(EventTicket[] tickets) {

        StringBuilder result = new StringBuilder();

        for (EventTicket ticket : tickets) {
            result.append(ticket.getTicketDetails())
                    .append("\n");

            if (ticket instanceof WorkshopTicket) {
                WorkshopTicket workshop =
                        (WorkshopTicket) ticket;

                result.append("Track Verified: ")
                        .append(workshop.getTrack())
                        .append("\n");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {

        EventTicket[] tickets = {
                new EventTicket("STU1", 500),
                new WorkshopTicket("STU2", 1200, "AI/ML"),
                new HackathonTicket("STU3", 800, "Byte Force")
        };

        System.out.println("NIGHTLY TICKET ANNOUNCER");
        System.out.println(batchPrint(tickets));
    }
}