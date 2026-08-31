package Day4.class_problems;

import java.util.HashSet;
import java.util.Set;

public class BusTicket {

    private String passengerName;
    private String destination;
    private boolean checkedIn;

    // Only parameterized constructor.
    // Invalid data is rejected immediately.
    public BusTicket(String passengerName, String destination) {

        if (!isMeaningfulName(passengerName)) {
            throw new IllegalArgumentException(
                "Invalid passenger name"
            );
        }

        if (!isMeaningfulDestination(destination)) {
            throw new IllegalArgumentException(
                "Invalid destination"
            );
        }

        this.passengerName = passengerName.trim();
        this.destination = destination.trim();
        this.checkedIn = false;
    }

    private static boolean isMeaningfulName(String name) {

        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        return name.trim().matches("[A-Za-z ]+");
    }

    private static boolean isMeaningfulDestination(String destination) {

        if (destination == null || destination.trim().isEmpty()) {
            return false;
        }

        return destination.trim().matches("[A-Za-z ]+");
    }

    public void markCheckedIn() {

        // Idempotent: calling it again does not change the state.
        if (!checkedIn) {
            checkedIn = true;
        }
    }

    public static void processBatch(String[][] rawBookings) {

        int valid = 0;
        int rejected = 0;
        int duplicates = 0;

        Set<String> acceptedBookings = new HashSet<>();

        for (String[] booking : rawBookings) {

            if (booking == null || booking.length < 2) {
                rejected++;
                continue;
            }

            try {

                BusTicket ticket =
                    new BusTicket(booking[0], booking[1]);

                String key =
                    ticket.passengerName.toLowerCase()
                    + "|"
                    + ticket.destination.toLowerCase();

                if (acceptedBookings.contains(key)) {
                    duplicates++;
                } else {
                    acceptedBookings.add(key);
                    valid++;
                }

            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println(
            "Valid: " + valid
            + " | Rejected: " + rejected
            + " | Duplicates skipped: " + duplicates
        );
    }

    public static void main(String[] args) {

        String[][] rawBookings = {
            {"Divya", "Chennai"},
            {"", "Bangalore"},
            {"Ravi123", "Pune"},
            {"Divya", "Chennai"},
            {" ", " "}
        };

        processBatch(rawBookings);

        // Testing idempotent check-in
        BusTicket ticket =
            new BusTicket("Arun", "Mumbai");

        ticket.markCheckedIn();
        ticket.markCheckedIn();

        System.out.println("Check-in completed safely.");
    }
}