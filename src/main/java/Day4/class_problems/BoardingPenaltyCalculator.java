package Day4.class_problems;

public final class BoardingPenaltyCalculator {

    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {

        if (minimumPenaltyPercent < 0) {
            throw new IllegalArgumentException(
                "Minimum penalty percent cannot be negative"
            );
        }

        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    public final double calculatePenalty(
            double ticketFare,
            int minutesLate) {

        if (ticketFare < 0) {
            throw new IllegalArgumentException(
                "Ticket fare cannot be negative"
            );
        }

        if (minutesLate < 0) {
            throw new IllegalArgumentException(
                "Minutes late cannot be negative"
            );
        }

        // On-time boarding means absolutely no penalty.
        if (minutesLate == 0) {
            return 0.0;
        }

        // Tier 1: minutes 1-5 at 0.5% per minute
        int firstTierMinutes = Math.min(minutesLate, 5);

        // Tier 2: minutes 6-15 at 1% per minute
        int secondTierMinutes =
                Math.min(Math.max(minutesLate - 5, 0), 10);

        // Tier 3: minute 16 onward at 2% per minute
        int thirdTierMinutes =
                Math.max(minutesLate - 15, 0);

        double tieredPenalty =
                ticketFare * 0.005 * firstTierMinutes
                + ticketFare * 0.01 * secondTierMinutes
                + ticketFare * 0.02 * thirdTierMinutes;

        // Minimum flat-fee floor
        double minimumPenalty =
                ticketFare * minimumPenaltyPercent / 100.0;

        return Math.max(tieredPenalty, minimumPenalty);
    }

    public static void main(String[] args) {

        // The examples use a 1% minimum penalty floor.
        BoardingPenaltyCalculator calculator =
                new BoardingPenaltyCalculator(1.0);

        System.out.println(
            "0 minutes: Rs "
            + calculator.calculatePenalty(1000, 0)
        );

        System.out.println(
            "1 minute: Rs "
            + calculator.calculatePenalty(1000, 1)
        );

        System.out.println(
            "16 minutes: Rs "
            + calculator.calculatePenalty(1000, 16)
        );
    }
}