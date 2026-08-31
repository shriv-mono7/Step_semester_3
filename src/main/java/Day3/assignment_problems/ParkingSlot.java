package Day3.assignment_problems;

public class ParkingSlot {

    String slotNo;
    int capacity;
    int occupiedCount;

    public ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    public void allot(String vehicleNo) {

        if (occupiedCount < capacity) {
            occupiedCount++;

            System.out.println(
                vehicleNo + " allotted to slot " + slotNo
            );
        }
    }

    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {

        for (ParkingSlot slot : slots) {

            if (slot.occupiedCount < slot.capacity) {
                return slot;
            }
        }

        return null;
    }

    static void safeAllot(
            ParkingSlot[] slots,
            String vehicleNo) {

        ParkingSlot slot = findAvailableSlot(slots);

        if (slot != null) {
            slot.allot(vehicleNo);
        } else {
            System.out.println(
                "No slots available for " + vehicleNo
            );
        }
    }

    /*
     * The array contains references to ParkingSlot objects.
     * Passing the array does not create copies of the objects.
     * Therefore, changes made through a reference affect the
     * original ParkingSlot object.
     */

    public static void main(String[] args) {

        // First case: A1 has an available spot.
        ParkingSlot[] slots1 = {
            new ParkingSlot("A1", 4, 3),
            new ParkingSlot("A2", 5, 5)
        };

        safeAllot(slots1, "TN09AB1234");

        // Second case: every slot is full.
        ParkingSlot[] slots2 = {
            new ParkingSlot("A1", 4, 4),
            new ParkingSlot("A2", 5, 5)
        };

        safeAllot(slots2, "TN09AB1234");
    }
}