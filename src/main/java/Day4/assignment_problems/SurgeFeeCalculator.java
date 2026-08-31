package Day4.assignment_problems;

public final class SurgeFeeCalculator {

    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {

        if (minimumSurgePercent < 0) {
            throw new IllegalArgumentException(
                "Minimum surge percent cannot be negative"
            );
        }

        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(
            double orderValue,
            int delayMinutes) {

        // Validation must happen at calculation time.
        if (orderValue < 0) {
            throw new IllegalArgumentException(
                "Order value cannot be negative"
            );
        }

        if (delayMinutes < 0) {
            throw new IllegalArgumentException(
                "Delay minutes cannot be negative"
            );
        }

        // No delay = absolutely no surge floor.
        if (delayMinutes == 0) {
            return 0.0;
        }

        // Minutes 1-5
        int firstTier =
                Math.min(delayMinutes, 5);

        // Minutes 6-15
        int secondTier =
                Math.min(
                    Math.max(delayMinutes - 5, 0),
                    10
                );

        // Minute 16 onward
        int thirdTier =
                Math.max(delayMinutes - 15, 0);

        double tieredFee =
                orderValue * 0.005 * firstTier
                + orderValue * 0.01 * secondTier
                + orderValue * 0.02 * thirdTier;

        // Minimum surge floor
        double minimumFee =
                orderValue * minimumSurgePercent / 100.0;

        return Math.max(tieredFee, minimumFee);
    }

    public static void main(String[] args) {

        SurgeFeeCalculator calculator =
                new SurgeFeeCalculator(1.0);

        System.out.println(
            "0 minutes: Rs "
            + calculator.calculateSurgeFee(500, 0)
        );

        System.out.println(
            "1 minute: Rs "
            + calculator.calculateSurgeFee(500, 1)
        );

        System.out.println(
            "16 minutes: Rs "
            + calculator.calculateSurgeFee(500, 16)
        );
    }
}