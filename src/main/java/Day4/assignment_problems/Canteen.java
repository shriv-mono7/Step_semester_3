package Day4.assignment_problems;

public class Canteen implements Comparable<Canteen> {

    private String canteenCode;
    private String canteenName;
    private int trustScore;

    // Full constructor
    public Canteen(
            String canteenCode,
            String canteenName,
            int trustScore) {

        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    // Constructor chaining with default trust score = 3
    public Canteen(
            String canteenCode,
            String canteenName) {

        this(canteenCode, canteenName, 3);
    }

    @Override
    public int compareTo(Canteen other) {

        // 1. Higher trust score comes first
        if (this.trustScore != other.trustScore) {
            return Integer.compare(
                other.trustScore,
                this.trustScore
            );
        }

        // 2. Compare codes ignoring letter case
        int codeResult =
                this.canteenCode.compareToIgnoreCase(
                    other.canteenCode
                );

        if (codeResult != 0) {
            return codeResult;
        }

        // 3. If codes are equal ignoring case,
        // use name length as the final tie-breaker.
        return Integer.compare(
            this.canteenName.length(),
            other.canteenName.length()
        );
    }

    public static Canteen[] rankCanteens(
            Canteen[] canteens) {

        // Manual stable insertion sort
        for (int i = 1; i < canteens.length; i++) {

            Canteen current = canteens[i];

            int j = i - 1;

            while (j >= 0
                    && canteens[j].compareTo(current) > 0) {

                canteens[j + 1] = canteens[j];
                j--;
            }

            canteens[j + 1] = current;
        }

        return canteens;
    }

    public static void main(String[] args) {

        Canteen[] canteens = {

            new Canteen(
                "HB3-C",
                "Spice Junction",
                3
            ),

            new Canteen(
                "hb1-c",
                "Grand Mess",
                5
            ),

            new Canteen(
                "HB2-C",
                "Southern Treats"
            )
        };

        Canteen[] ranked =
                rankCanteens(canteens);

        for (Canteen canteen : ranked) {
            System.out.println(
                canteen.canteenCode
            );
        }
    }
}