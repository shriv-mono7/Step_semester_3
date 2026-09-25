package Day5.class_problems;

public class PatientProfileJavaBeanChainedConstructorsLockerPin {

    public static void main(String[] args) {

        PatientProfile p1 = new PatientProfile("Arjun Iyer");

        System.out.println(p1.getPatientId());
        System.out.println(p1.getName());

        PatientProfile p2 = new PatientProfile(
                "MT2026-0142",
                "Arjun Iyer"
        );

        System.out.println(p2.getPatientId());

        PatientProfile p3 = new PatientProfile();

        p3.setPatientId("MT2026-0142");
        p3.setPatientId("HACKED-0000");

        System.out.println(p3.getPatientId());

        p3.setDischarged(true);

        System.out.println(p3.isDischarged());

        p3.setLockerPin("1234");

        System.out.println("Locker PIN updated successfully.");
    }
}


class PatientProfile {

    private String patientId;
    private String name;
    private boolean discharged;

    private String lockerPinHash;
    private boolean patientIdSet;

    // No-argument constructor
    public PatientProfile() {
        this(null, null);
    }

    // Name-only constructor
    public PatientProfile(String name) {
        this(null, name);
    }

    // ID and name constructor
    public PatientProfile(String patientId, String name) {

        this.patientId = patientId;
        this.name = name;

        if (patientId != null) {
            patientIdSet = true;
        }
    }

    // Patient ID getter
    public String getPatientId() {
        return patientId;
    }

    // Patient ID setter - write once only
    public void setPatientId(String id) {

        if (!patientIdSet && id != null) {
            patientId = id;
            patientIdSet = true;
        }
    }

    // Name getter
    public String getName() {
        return name;
    }

    // Name setter
    public void setName(String name) {
        this.name = name;
    }

    // Discharged getter
    public boolean isDischarged() {
        return discharged;
    }

    // Discharged setter
    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    // Write-only locker PIN setter
    public void setLockerPin(String pin) {

        if (pin == null) {
            return;
        }

        if (!pin.matches("\\d{4,6}")) {
            return;
        }

        // Deterministic one-way transformation
        lockerPinHash = Integer.toHexString(pin.hashCode());
    }
}