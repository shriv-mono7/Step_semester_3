
package Day7.assignment_problems;

interface Insurable {
    String getInsuranceInfo();
}

abstract class ServiceableVehicle {

    private double mileage;

    public ServiceableVehicle(double mileage) {
        if (mileage < 0) {
            throw new IllegalArgumentException("Mileage cannot be negative");
        }

        this.mileage = mileage;
    }

    public double getMileage() {
        return mileage;
    }

    public void addMileage(double km) {
        if (km < 0) {
            throw new IllegalArgumentException("Added mileage cannot be negative");
        }

        mileage += km;
    }

    public abstract void performMaintenance();
}

class Forklift extends ServiceableVehicle implements Insurable {

    private String model;

    public Forklift(String model, double mileage) {
        super(mileage);
        this.model = model;
    }

    @Override
    public void performMaintenance() {
        System.out.println("Performing basic forklift maintenance.");
    }

    @Override
    public String getInsuranceInfo() {
        return "Insurance available for forklift model: " + model;
    }
}

class HeavyDutyForklift extends Forklift {

    public HeavyDutyForklift(String model, double mileage) {
        super(model, mileage);
    }

    @Override
    public void performMaintenance() {
        super.performMaintenance();
        System.out.println("Checking hydraulic pressure.");
    }
}

public class FleetMaintenanceTracker {

    public static void getInsuranceIfApplicable(ServiceableVehicle vehicle) {

        if (vehicle instanceof Insurable) {
            Insurable insurableVehicle = (Insurable) vehicle;
            System.out.println(insurableVehicle.getInsuranceInfo());
        } else {
            System.out.println("Insurance information unavailable.");
        }
    }

    public static void main(String[] args) {

        Forklift forklift = new Forklift("FL-100", 1500);
        HeavyDutyForklift heavyForklift =
                new HeavyDutyForklift("HD-200", 2500);

        forklift.performMaintenance();
        heavyForklift.performMaintenance();

        forklift.addMileage(100);

        System.out.println("Forklift Mileage: " + forklift.getMileage());

        getInsuranceIfApplicable(forklift);
        getInsuranceIfApplicable(heavyForklift);
    }
}