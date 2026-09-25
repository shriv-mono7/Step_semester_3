
package Day7.assignment_problems;

interface RemoteControllable {
    void connect(String appId);
}

interface EnergyTrackable {
    double getConsumptionWatts();
}

abstract class HomeDevice {

    private static int counter = 1000;
    private final String serialNumber;

    public HomeDevice() {
        serialNumber = "HOME-" + counter++;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public abstract void activate();
}

class WashingMachine extends HomeDevice
        implements RemoteControllable, EnergyTrackable {

    private double consumption;

    public WashingMachine(double consumption) {
        if (consumption <= 0) {
            throw new IllegalArgumentException(
                    "Consumption must be positive"
            );
        }

        this.consumption = consumption;
    }

    @Override
    public void activate() {
        System.out.println("Washing machine activated.");
    }

    @Override
    public void connect(String appId) {
        System.out.println(
                "Washing machine connected to app: " + appId
        );
    }

    @Override
    public double getConsumptionWatts() {
        return consumption;
    }
}

class Refrigerator extends HomeDevice
        implements EnergyTrackable {

    private double consumption;

    public Refrigerator(double consumption) {
        if (consumption <= 0) {
            throw new IllegalArgumentException(
                    "Consumption must be positive"
            );
        }

        this.consumption = consumption;
    }

    @Override
    public void activate() {
        System.out.println("Refrigerator activated.");
    }

    @Override
    public double getConsumptionWatts() {
        return consumption;
    }
}

class MobileApp implements RemoteControllable {

    private String appName;

    public MobileApp(String appName) {
        if (appName == null || appName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "App name cannot be blank"
            );
        }

        this.appName = appName;
    }

    @Override
    public void connect(String appId) {
        System.out.println(
                appName + " connected using app ID: " + appId
        );
    }
}

public class ConnectedHomeControlPanel {

    public static void connectAll(
            RemoteControllable[] items,
            String appId) {

        if (appId == null || appId.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "App ID cannot be blank"
            );
        }

        for (RemoteControllable item : items) {
            item.connect(appId);
        }
    }

    public static void getConsumptionIfTrackable(
            HomeDevice device) {

        if (device instanceof EnergyTrackable) {
            EnergyTrackable trackable =
                    (EnergyTrackable) device;

            System.out.println(
                    "Power Consumption: "
                            + trackable.getConsumptionWatts()
                            + " watts"
            );
        } else {
            System.out.println(
                    "Energy tracking unavailable."
            );
        }
    }

    public static void main(String[] args) {

        WashingMachine washingMachine =
                new WashingMachine(500);

        Refrigerator refrigerator =
                new Refrigerator(150);

        MobileApp mobileApp =
                new MobileApp("Smart Home App");

        washingMachine.activate();
        refrigerator.activate();

        System.out.println(
                "Washing Machine Serial: "
                        + washingMachine.getSerialNumber()
        );

        System.out.println(
                "Refrigerator Serial: "
                        + refrigerator.getSerialNumber()
        );

        RemoteControllable[] devices = {
                washingMachine,
                mobileApp
        };

        connectAll(devices, "APP-101");

        getConsumptionIfTrackable(washingMachine);
        getConsumptionIfTrackable(refrigerator);
    }
}