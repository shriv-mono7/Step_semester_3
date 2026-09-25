package Day5.class_problems;
public class FieldVisibilityIntakeValidator {

    public static void main(String[] args) {

        System.out.println(
                AccessRuleEngine.classifyAccess("private", "SAME_CLASS")
        );

        System.out.println(
                AccessRuleEngine.classifyAccess("default", "DIFFERENT_PACKAGE")
        );

        String[][] attempts = {
                {"protected", "SAME_PACKAGE"},
                {"protected", "DIFFERENT_PACKAGE"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
                AccessRuleEngine.summarizeBatch(attempts)
        );

        PatientRecord patient = new PatientRecord(
                "MT94", "W3", 98.2, "MediTrack Central"
        );

        System.out.println("Patient record created successfully.");
    }
}


class AccessRuleEngine {

    static String classifyAccess(
            String fieldModifier,
            String accessorContext) {

        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        switch (fieldModifier) {

            case "private":
                return accessorContext.equals("SAME_CLASS")
                        ? "ALLOWED" : "DENIED";

            case "default":
            case "protected":
                return accessorContext.equals("SAME_CLASS")
                        || accessorContext.equals("SAME_PACKAGE")
                        ? "ALLOWED" : "DENIED";

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }


    static String summarizeBatch(String[][] attempts) {

        int allowed = 0;
        int denied = 0;

        for (String[] attempt : attempts) {

            String result = classifyAccess(
                    attempt[0], attempt[1]
            );

            if (result.equals("ALLOWED")) {
                allowed++;
            } else {
                denied++;
            }
        }

        return "Allowed: " + allowed
                + " | Denied: " + denied;
    }
}


class PatientRecord {

    private String patientId;
    String wardCode;
    protected double vitalsScore;
    public String facilityName;


    public PatientRecord(
            String patientId,
            String wardCode,
            double vitalsScore,
            String facilityName) {

        if (patientId == null
                || patientId.trim().length() < 4) {

            throw new IllegalArgumentException(
                    "Invalid patient ID"
            );
        }

        this.patientId = patientId.trim();
        this.wardCode = wardCode;
        this.vitalsScore = vitalsScore;
        this.facilityName = facilityName;
    }
}