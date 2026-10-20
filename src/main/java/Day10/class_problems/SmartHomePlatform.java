package Day10.class_problems;

import java.util.HashMap;
import java.util.Map;

class Device {
    String id;
    boolean isOn;

    Device(String id) {
        this.id = id;
        this.isOn = false;
    }

    void turnOn() {
        isOn = true;
        System.out.println(id + " is ON");
    }

    void turnOff() {
        isOn = false;
        System.out.println(id + " is OFF");
    }

    void dim(int level) {
        System.out.println(id + " rejected: DIM unsupported");
    }

    void schedule(String time) {
        System.out.println(id + " rejected: SCHEDULE unsupported");
    }

    void energy() {
        System.out.println(id + " rejected: ENERGY unsupported");
    }
}

class Light extends Device {
    Light(String id) {
        super(id);
    }

    @Override
    void dim(int level) {
        System.out.println(id + " dimmed to " + level);
    }

    @Override
    void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Fan extends Device {
    Fan(String id) {
        super(id);
    }

    @Override
    void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Plug extends Device {
    int energyUsage;

    Plug(String id, int energyUsage) {
        super(id);
        this.energyUsage = energyUsage;
    }

    @Override
    void energy() {
        System.out.println(id + " energy " + energyUsage + " kWh");
    }
}

public class SmartHomePlatform {
    public static void main(String[] args) {
        Map<String, Device> devices = new HashMap<>();

        devices.put("L1", new Light("L1"));
        devices.put("F1", new Fan("F1"));
        devices.put("P1", new Plug("P1", 12));

        String[] commands = {
            "ON L1",
            "DIM L1 40",
            "DIM F1 30",
            "SCHEDULE F1 22:00",
            "ENERGY P1",
            "ENERGY L1"
        };

        for (String command : commands) {
            String[] parts = command.split(" ");
            String operation = parts[0];
            Device device = devices.get(parts[1]);

            if (device == null) {
                System.out.println(
                    parts[1] + " rejected: device not found"
                );
                continue;
            }

            switch (operation) {
                case "ON":
                    device.turnOn();
                    break;

                case "OFF":
                    device.turnOff();
                    break;

                case "DIM":
                    device.dim(Integer.parseInt(parts[2]));
                    break;

                case "SCHEDULE":
                    device.schedule(parts[2]);
                    break;

                case "ENERGY":
                    device.energy();
                    break;

                default:
                    System.out.println("Invalid command");
            }
        }
    }
}