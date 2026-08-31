package Day4.assignment_problems;

public class DeliveryAccount {

    private String studentId;
    private double orderValue;

    // One-time class-level setup
    private static String systemStatus;

    static {
        systemStatus = "RECONCILIATION READY";
    }

    // Full constructor
    public DeliveryAccount(String studentId, double orderValue) {

        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Student ID cannot be blank"
            );
        }

        if (orderValue < 0) {
            throw new IllegalArgumentException(
                "Order value cannot be negative"
            );
        }

        this.studentId = studentId.trim();
        this.orderValue = orderValue;
    }

    // Provisional constructor
    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    // Reusing F4's tiered surge-fee logic.
    public final double calculateSurgeFee(int delayMinutes) {

        if (delayMinutes < 0) {
            throw new IllegalArgumentException(
                "Delay minutes cannot be negative"
            );
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        int firstTier = Math.min(delayMinutes, 5);

        int secondTier =
                Math.min(
                    Math.max(delayMinutes - 5, 0),
                    10
                );

        int thirdTier =
                Math.max(delayMinutes - 15, 0);

        return orderValue * 0.005 * firstTier
                + orderValue * 0.01 * secondTier
                + orderValue * 0.02 * thirdTier;
    }

    public void processAccount(
            DeliveryAccount account,
            double amount,
            int delayMinutes) {

        if (account == null) {
            return;
        }

        double surgeFee =
                account.calculateSurgeFee(delayMinutes);

        if (account instanceof PremiumDeliveryAccount) {

            // Premium accounts pay 50% of surge fee.
            surgeFee = surgeFee * 0.50;

            System.out.println(
                account.studentId
                + " | Premium | Surge fee: Rs "
                + surgeFee
            );

        } else {

            System.out.println(
                account.studentId
                + " | Regular | Surge fee: Rs "
                + surgeFee
            );
        }
    }

    public static void processBatch(
            DeliveryAccount[] accounts,
            double[] amounts,
            int[] delayMinutesArray) {

        if (accounts == null
                || amounts == null
                || delayMinutesArray == null) {

            System.out.println(
                "Invalid batch: null array"
            );
            return;
        }

        // Process only the common length.
        // This prevents mismatching an account with
        // the wrong amount or delay.
        int count = Math.min(
            accounts.length,
            Math.min(
                amounts.length,
                delayMinutesArray.length
            )
        );

        int processed = 0;
        int nullSkipped = 0;
        int premium = 0;
        int regular = 0;
        double grandTotal = 0.0;

        DeliveryAccount processor =
                new DeliveryAccount("PROCESSOR", 0);

        for (int i = 0; i < count; i++) {

            DeliveryAccount account = accounts[i];

            if (account == null) {
                nullSkipped++;
                continue;
            }

            try {

                double fee =
                        account.calculateSurgeFee(
                            delayMinutesArray[i]
                        );

                if (account instanceof PremiumDeliveryAccount) {

                    premium++;
                    fee = fee * 0.50;

                } else {

                    regular++;
                }

                processor.processAccount(
                    account,
                    amounts[i],
                    delayMinutesArray[i]
                );

                grandTotal += fee;
                processed++;

            } catch (IllegalArgumentException e) {

                System.out.println(
                    "Skipped invalid account at index "
                    + i
                );
            }
        }

        System.out.println();
        System.out.println(
            processed
            + " processed | "
            + nullSkipped
            + " null skipped | "
            + premium
            + " premium | "
            + regular
            + " regular | grand total surge fees = Rs "
            + grandTotal
        );
    }

    public static void main(String[] args) {

        DeliveryAccount[] accounts = {

            new PremiumDeliveryAccount(
                "STU001",
                500
            ),

            null,

            new DeliveryAccount(
                "STU002",
                300
            )
        };

        double[] amounts = {
            500,
            400,
            300
        };

        int[] delayMinutesArray = {
            10,
            5,
            0
        };

        processBatch(
            accounts,
            amounts,
            delayMinutesArray
        );
    }
}


// Premium account
class PremiumDeliveryAccount extends DeliveryAccount {

    public PremiumDeliveryAccount(
            String studentId,
            double orderValue) {

        super(studentId, orderValue);
    }

    public PremiumDeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }
}