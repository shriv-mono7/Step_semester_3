
package Day8.assignment_problems;

import java.util.ArrayList;
import java.util.List;

interface ShippingType {
    double calculateCharge(double weight);
}

class StandardShipping implements ShippingType {
    public double calculateCharge(double weight) {
        return 40 + 10 * weight;
    }
}

class ExpressShipping implements ShippingType {
    public double calculateCharge(double weight) {
        return 80 + 15 * weight;
    }
}

class FragileShipping implements ShippingType {
    public double calculateCharge(double weight) {
        return new StandardShipping().calculateCharge(weight) + 50;
    }
}

interface NotificationChannel {
    void notifyStatus(String parcelId, String status);
}

class SmsChannel implements NotificationChannel {
    public void notifyStatus(String parcelId, String status) {
        System.out.println("[SMS] " + parcelId + " is now " + status + ".");
    }
}

class EmailChannel implements NotificationChannel {
    public void notifyStatus(String parcelId, String status) {
        System.out.println("[Email] " + parcelId + " is now " + status + ".");
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Parcel {
    private String parcelId;
    private double weight;
    private ShippingType shippingType;
    private String status = "BOOKED";
    private boolean cancelled = false;
    private List<NotificationChannel> channels = new ArrayList<>();

    public Parcel(String parcelId, double weight,
                  ShippingType shippingType) {
        if (weight <= 0) {
            throw new IllegalArgumentException(
                    "Weight must be positive.");
        }

        this.parcelId = parcelId;
        this.weight = weight;
        this.shippingType = shippingType;
    }

    public void subscribe(NotificationChannel channel) {
        channels.add(channel);
    }

    public double getCharge() {
        return shippingType.calculateCharge(weight);
    }

    private void notifyChannels() {
        for (NotificationChannel channel : channels) {
            channel.notifyStatus(parcelId, status);
        }
    }

    public void updateStatus(String newStatus) {
        if (cancelled) {
            System.out.println("Invalid transition: Parcel is cancelled.");
            return;
        }

        String nextStatus;

        switch (status) {
            case "BOOKED":
                nextStatus = "PICKED_UP";
                break;
            case "PICKED_UP":
                nextStatus = "IN_TRANSIT";
                break;
            case "IN_TRANSIT":
                nextStatus = "OUT_FOR_DELIVERY";
                break;
            case "OUT_FOR_DELIVERY":
                nextStatus = "DELIVERED";
                break;
            default:
                System.out.println(
                        "Invalid transition: " + status
                                + " → " + newStatus
                                + " is not allowed.");
                return;
        }

        if (!nextStatus.equals(newStatus)) {
            System.out.println(
                    "Invalid transition: " + status
                            + " → " + newStatus
                            + " is not allowed.");
            return;
        }

        status = newStatus;
        notifyChannels();
    }

    public void cancel() {
        if (!status.equals("BOOKED") || cancelled) {
            System.out.println("Cancellation failed: "
                    + parcelId + " can be cancelled only while BOOKED.");
            return;
        }

        cancelled = true;
        System.out.println("Parcel " + parcelId + " cancelled.");
    }

    public String getStatus() {
        return status;
    }

    public String getParcelId() {
        return parcelId;
    }
}

class ParcelService {
    public Parcel bookParcel(String id, double weight,
                             ShippingType shippingType) {
        Parcel parcel = new Parcel(id, weight, shippingType);

        System.out.println("Parcel " + id + " booked ("
                + getShippingName(shippingType) + ", "
                + weight + " kg).");

        System.out.printf(java.util.Locale.US,
                "Charge: ₹%.2f%n", parcel.getCharge());

        return parcel;
    }

    private String getShippingName(ShippingType type) {
        if (type instanceof ExpressShipping) return "Express";
        if (type instanceof FragileShipping) return "Fragile";
        return "Standard";
    }
}

public class SwiftShipParcelTracker {
    public static void main(String[] args) {
        ParcelService service = new ParcelService();

        Customer customer = new Customer("Asha");

        Parcel parcel = service.bookParcel(
                "P101", 2, new ExpressShipping());

        parcel.subscribe(new SmsChannel());
        parcel.subscribe(new EmailChannel());

        // Notify subscribers of the initial BOOKED status.
        // Both channels are subscribed after booking, so send the
        // initial status by advancing through the normal workflow below.
        parcel.updateStatus("PICKED_UP");

        parcel.cancel();

        parcel.updateStatus("IN_TRANSIT");

        // Skipping OUT_FOR_DELIVERY is not allowed.
        parcel.updateStatus("DELIVERED");

        System.out.println("Current status: " + parcel.getStatus());
    }
}
