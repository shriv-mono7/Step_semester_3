package Day3.assignment_problems;

public class CompanyEmployeeRecord {

    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;

    static int totalRecords = 0;

    public CompanyEmployeeRecord(
            String name,
            String empId,
            Employee employee) {

        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = null;

        totalRecords++;
    }

    public String fullProfile() {

        String slotStatus;

        if (slot == null) {
            slotStatus = "no parking assigned";
        } else {
            slotStatus = slot.slotNo;
        }

        double pay;

        if (employee instanceof ManagerEmployee) {
            ManagerEmployee manager =
                    (ManagerEmployee) employee;

            pay = manager.effectiveSalary();
        } else {
            pay = employee.getSalary();
        }

        return name
                + " | Pay: Rs "
                + pay
                + " | Slot: "
                + slotStatus;
    }

    public static void main(String[] args) {

        Employee plainEmployee =
                new Employee("E001", "Karan", 40000);

        ManagerEmployee manager =
                new ManagerEmployee(
                        "E002",
                        "Divya",
                        70000,
                        8000
                );

        InternEmployee intern =
                new InternEmployee(
                        "E003",
                        "Meera",
                        12000,
                        10000
                );

        CompanyEmployeeRecord divya =
                new CompanyEmployeeRecord(
                        "Divya",
                        "E002",
                        manager
                );

        CompanyEmployeeRecord karan =
                new CompanyEmployeeRecord(
                        "Karan",
                        "E001",
                        plainEmployee
                );

        CompanyEmployeeRecord meera =
                new CompanyEmployeeRecord(
                        "Meera",
                        "E003",
                        intern
                );

        ParkingSlot[] slots = {
            new ParkingSlot("A1", 1, 0),
            new ParkingSlot("A2", 1, 0)
        };

        divya.slot = ParkingSlot.findAvailableSlot(slots);
        divya.slot.allot("E002");

        karan.slot = ParkingSlot.findAvailableSlot(slots);
        karan.slot.allot("E001");

        // Meera is intentionally left without parking.

        System.out.println(divya.fullProfile());
        System.out.println(karan.fullProfile());
        System.out.println(meera.fullProfile());

        System.out.println(
            "Total records: " + CompanyEmployeeRecord.totalRecords
        );
    }
}