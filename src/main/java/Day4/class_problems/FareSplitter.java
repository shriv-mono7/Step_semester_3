package Day4.class_problems;

import java.util.Arrays;

public class FareSplitter {

    private String tripId;
    private double totalFare;
    private int passengerCount;

    // Full constructor
    public FareSplitter(
            String tripId,
            double totalFare,
            int passengerCount) {

        if (totalFare < 0) {
            throw new IllegalArgumentException(
                "Fare cannot be negative"
            );
        }

        if (passengerCount <= 0) {
            throw new IllegalArgumentException(
                "Passenger count must be positive"
            );
        }

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    // Constructor chaining
    public FareSplitter(
            String tripId,
            double totalFare) {

        this(tripId, totalFare, 2);
    }

    // Constructor chaining
    public FareSplitter(String tripId) {

        this(tripId, 0.0, 2);
    }

    public double[] fareBreakdown() {

        double[] shares = new double[passengerCount];

        if (passengerCount == 0) {
            return shares;
        }

        // Work in paise to avoid floating-point rounding problems.
        long totalPaise =
                Math.round(totalFare * 100);

        long basePaise =
                totalPaise / passengerCount;

        long remainder =
                totalPaise % passengerCount;

        for (int i = 0; i < passengerCount; i++) {

            shares[i] = basePaise / 100.0;

            // Extra paise goes to the LAST shares.
            if (i >= passengerCount - remainder) {
                shares[i] += 0.01;
            }
        }

        return shares;
    }

    public boolean isConfirmationOverdue(
            int confirmed,
            int expected) {

        return confirmed < expected;
    }

    public static void main(String[] args) {

        FareSplitter trip1 =
                new FareSplitter(
                        "TRIP001",
                        100000,
                        3
                );

        System.out.println(
            Arrays.toString(trip1.fareBreakdown())
        );

        FareSplitter trip2 =
                new FareSplitter("TRIP003");

        System.out.println(
            Arrays.toString(trip2.fareBreakdown())
        );

        System.out.println(
            "Confirmation overdue: "
            + trip1.isConfirmationOverdue(2, 3)
        );
    }
}