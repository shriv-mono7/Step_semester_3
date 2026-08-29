package Day3.class_problems;

public class HostelRoom {

    String roomNo;
    int beds;
    int occupied;

    public HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    public void allot(String name) {

        if (occupied < beds) {
            occupied++;

            System.out.println(
                name + " allotted to room " + roomNo
            );
        }
    }

    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {

        for (HostelRoom room : rooms) {

            if (room.occupied < room.beds) {
                return room;
            }
        }

        return null;
    }

    static void safeAllot(
            HostelRoom[] rooms,
            String studentName) {

        HostelRoom room = findAvailableRoom(rooms);

        if (room != null) {
            room.allot(studentName);
        } else {
            System.out.println(
                "No rooms available for " + studentName
            );
        }
    }

    /*
     * The array contains references to HostelRoom objects.
     * Passing the array does not create copies of those objects.
     * Therefore, changes made through a room reference affect
     * the original HostelRoom object.
     */

    public static void main(String[] args) {

        HostelRoom[] rooms = {
            new HostelRoom("C-214", 3, 2),
            new HostelRoom("C-507", 2, 2)
        };

        safeAllot(rooms, "Divya");

        safeAllot(rooms, "Divya");
    }
}