package Day5.class_problems;

import java.util.Arrays;

public class ImmutableDischargeSummaryNightlyLedger {

    public static void main(String[] args) {

        try {
            DischargeSummary invalid = new DischargeSummary(
                    "MT2026-0142",
                    new String[]{"MED-A", "bad"}
            );

        } catch (IllegalArgumentException e) {
            System.out.println("Construction rejected");
        }

        DischargeSummary d = new DischargeSummary(
                "MT2026-0142",
                new String[]{"MED-A", "MED-B"}
        );

        String[] codes = d.getMedicationCodes();
        codes[0] = "TAMPERED";

        System.out.println(d.getMedicationCodes()[0]);

        DischargeSummary corrected = d.withCorrectedMedication(
                1,
                "MED-C"
        );

        System.out.println(
                Arrays.toString(corrected.getMedicationCodes())
        );

        DischargeSummary[] summaries = {
                new CriticalCareDischargeSummary(
                        "MT001",
                        new String[]{"MED-X"},
                        4
                ),
                null,
                new DischargeSummary(
                        "MT002",
                        new String[]{"MED-Y"}
                )
        };

        System.out.println(
                DischargeSummary.processNightlyBatch(summaries)
        );
    }
}


// Base discharge summary class
class DischargeSummary {

    private final String patientId;
    private final String[] medicationCodes;

    private static String ledgerName;

    // Static block for one-time shared setup
    static {
        ledgerName = "MediTrack Nightly Ledger";
    }

    public DischargeSummary(
            String patientId,
            String[] medicationCodes
    ) {

        if (!areValidMedicationCodes(medicationCodes)) {
            throw new IllegalArgumentException(
                    "Invalid medication code"
            );
        }

        this.patientId = patientId;
        this.medicationCodes = medicationCodes.clone();
    }

    private static boolean areValidMedicationCodes(
            String[] medicationCodes
    ) {

        if (medicationCodes == null) {
            return false;
        }

        for (String code : medicationCodes) {

            if (code == null
                    || !code.matches("MED-[A-Z]")) {
                return false;
            }
        }

        return true;
    }

    public String[] getMedicationCodes() {

        return medicationCodes.clone();
    }

    public DischargeSummary withCorrectedMedication(
            int index,
            String newCode
    ) {

        if (index < 0 || index >= medicationCodes.length) {
            return this;
        }

        if (newCode == null
                || !newCode.matches("MED-[A-Z]")) {
            return this;
        }

        String[] updatedCodes = medicationCodes.clone();
        updatedCodes[index] = newCode;

        return new DischargeSummary(
                patientId,
                updatedCodes
        );
    }

    public static String processNightlyBatch(
            DischargeSummary[] summaries
    ) {

        int processed = 0;
        int nullSkipped = 0;
        int criticalCare = 0;
        int routine = 0;

        if (summaries == null) {
            return "0 processed | 0 null skipped | "
                    + "0 critical-care | 0 routine";
        }

        for (DischargeSummary summary : summaries) {

            if (summary == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (summary instanceof CriticalCareDischargeSummary) {
                criticalCare++;
            } else {
                routine++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + criticalCare + " critical-care | "
                + routine + " routine";
    }
}


// Critical-care discharge summary
class CriticalCareDischargeSummary extends DischargeSummary {

    private final int icuDays;

    public CriticalCareDischargeSummary(
            String patientId,
            String[] medicationCodes,
            int icuDays
    ) {

        super(patientId, medicationCodes);
        this.icuDays = icuDays;
    }

    public int getIcuDays() {
        return icuDays;
    }
}