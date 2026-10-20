
package Day8.class_problems;

import java.util.*;

abstract class Vehicle {
    protected String vehicleNumber;
    protected String model;
    protected double baseRate;

    public Vehicle(String vehicleNumber, String model, double baseRate) {
        this.vehicleNumber = vehicleNumber;
        this.model = model;
        this.baseRate = baseRate;
    }

    public abstract double calculateRentalCost(int days);

    public void displayDetails() {
        System.out.println("Vehicle Number: " + vehicleNumber);
        System.out.println("Model: " + model);
        System.out.println("Base Rate per Day: Rs. " + baseRate);
    }
}

class Car extends Vehicle {
    public Car(String vehicleNumber, String model, double baseRate) {
        super(vehicleNumber, model, baseRate);
    }

    @Override
    public double calculateRentalCost(int days) {
        return baseRate * days;
    }
}

class Bike extends Vehicle {
    public Bike(String vehicleNumber, String model, double baseRate) {
        super(vehicleNumber, model, baseRate);
    }

    @Override
    public double calculateRentalCost(int days) {
        return baseRate * days;
    }
}

class Rental {
    private Vehicle vehicle;
    private String customerName;
    private int days;
    private double demandMultiplier;

    public Rental(Vehicle vehicle, String customerName,
                  int days, double demandMultiplier) {
        this.vehicle = vehicle;
        this.customerName = customerName;
        this.days = days;
        this.demandMultiplier = demandMultiplier;
    }

    public double calculateTotal() {
        return vehicle.calculateRentalCost(days) * demandMultiplier;
    }

    public void displayBill() {
        System.out.println("\n===== VEHICLE RENTAL BILL =====");
        System.out.println("Customer: " + customerName);
        vehicle.displayDetails();
        System.out.println("Rental Days: " + days);
        System.out.println("Demand Multiplier: " + demandMultiplier);
        System.out.printf("Total Rental Cost: Rs. %.2f%n", calculateTotal());
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== VEHICLE RENTAL SYSTEM =====");
        System.out.print("Enter customer name: ");
        String name = sc.nextLine();

        System.out.println("1. Car");
        System.out.println("2. Bike");
        System.out.print("Choose vehicle type: ");
        int choice = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter vehicle number: ");
        String number = sc.nextLine();

        System.out.print("Enter vehicle model: ");
        String model = sc.nextLine();

        System.out.print("Enter base rental rate per day: Rs. ");
        double rate = sc.nextDouble();

        System.out.print("Enter number of rental days: ");
        int days = sc.nextInt();

        System.out.print("Enter demand multiplier (e.g. 1.0 or 1.5): ");
        double multiplier = sc.nextDouble();

        if (rate <= 0 || days <= 0 || multiplier <= 0) {
            System.out.println("Invalid input. Values must be positive.");
            sc.close();
            return;
        }

        Vehicle vehicle;

        if (choice == 1) {
            vehicle = new Car(number, model, rate);
        } else if (choice == 2) {
            vehicle = new Bike(number, model, rate);
        } else {
            System.out.println("Invalid vehicle choice.");
            sc.close();
            return;
        }

        Rental rental = new Rental(vehicle, name, days, multiplier);
        rental.displayBill();

        sc.close();
    }
}
