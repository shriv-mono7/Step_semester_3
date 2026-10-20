
package Day8.class_problems;

import java.util.*;

class Room {
    private int roomNumber;
    private String roomType;
    private double pricePerNight;
    private boolean available;

    public Room(int roomNumber, String roomType, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.available = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void displayRoom() {
        System.out.println("Room " + roomNumber + " | " + roomType
                + " | Rs. " + pricePerNight + " per night"
                + " | " + (available ? "Available" : "Booked"));
    }
}

class Booking {
    private static int nextBookingId = 1001;

    private int bookingId;
    private String customerName;
    private Room room;
    private int nights;
    private boolean cancelled;

    public Booking(String customerName, Room room, int nights) {
        this.bookingId = nextBookingId++;
        this.customerName = customerName;
        this.room = room;
        this.nights = nights;
        this.cancelled = false;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Room getRoom() {
        return room;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public double calculateCost() {
        return room.getPricePerNight() * nights;
    }

    public void cancel() {
        if (cancelled) {
            System.out.println("Booking already cancelled.");
            return;
        }

        cancelled = true;
        room.setAvailable(true);
        System.out.println("Booking " + bookingId + " cancelled successfully.");
        System.out.println("Room " + room.getRoomNumber() + " is available again.");
    }

    public void displayBooking() {
        System.out.println("\nBooking ID: " + bookingId);
        System.out.println("Customer: " + customerName);
        System.out.println("Room: " + room.getRoomNumber()
                + " (" + room.getRoomType() + ")");
        System.out.println("Nights: " + nights);
        System.out.printf("Total Cost: Rs. %.2f%n", calculateCost());
        System.out.println("Status: " + (cancelled ? "Cancelled" : "Confirmed"));
    }
}

class Hotel {
    private List<Room> rooms = new ArrayList<>();
    private Map<Integer, Booking> bookings = new HashMap<>();

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void displayAvailableRooms() {
        System.out.println("\n===== AVAILABLE ROOMS =====");
        boolean found = false;

        for (Room room : rooms) {
            if (room.isAvailable()) {
                room.displayRoom();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No rooms available.");
        }
    }

    public void bookRoom(String customerName, int roomNumber, int nights) {
        if (nights <= 0) {
            System.out.println("Number of nights must be positive.");
            return;
        }

        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                Booking booking = new Booking(customerName, room, nights);
                room.setAvailable(false);
                bookings.put(booking.getBookingId(), booking);

                System.out.println("Room booked successfully!");
                booking.displayBooking();
                return;
            }
        }

        System.out.println("Room not found or already booked.");
    }

    public void cancelBooking(int bookingId) {
        Booking booking = bookings.get(bookingId);

        if (booking == null) {
            System.out.println("Invalid booking ID.");
        } else {
            booking.cancel();
        }
    }

    public void displayBooking(int bookingId) {
        Booking booking = bookings.get(bookingId);

        if (booking == null) {
            System.out.println("Booking not found.");
        } else {
            booking.displayBooking();
        }
    }
}

public class HotelBookingCancellationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel();

        hotel.addRoom(new Room(101, "Standard", 1500));
        hotel.addRoom(new Room(102, "Deluxe", 2500));
        hotel.addRoom(new Room(103, "Suite", 4000));

        int choice = 0;

        do {
            System.out.println("\n===== HOTEL BOOKING SYSTEM =====");
            System.out.println("1. View available rooms");
            System.out.println("2. Book a room");
            System.out.println("3. Cancel a booking");
            System.out.println("4. View booking details");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                sc.nextLine();
                continue;
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    hotel.displayAvailableRooms();
                    break;

                case 2:
                    System.out.print("Enter customer name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter room number: ");
                    int roomNumber = sc.nextInt();

                    System.out.print("Enter number of nights: ");
                    int nights = sc.nextInt();
                    sc.nextLine();

                    hotel.bookRoom(name, roomNumber, nights);
                    break;

                case 3:
                    System.out.print("Enter booking ID: ");
                    int cancelId = sc.nextInt();
                    sc.nextLine();

                    hotel.cancelBooking(cancelId);
                    break;

                case 4:
                    System.out.print("Enter booking ID: ");
                    int viewId = sc.nextInt();
                    sc.nextLine();

                    hotel.displayBooking(viewId);
                    break;

                case 5:
                    System.out.println("Thank you for using the system!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);

        sc.close();
    }
}
