
package Day8.assignment_problems;

import java.util.*;

interface Capability {
    String getName();
    boolean apply(String value);
}

class PowerCapability implements Capability {
    private boolean on = false;

    public String getName() {
        return "Power";
    }

    public boolean apply(String value) {
        if (!value.equalsIgnoreCase("ON")
                && !value.equalsIgnoreCase("OFF")) {
            System.out.println("Rejected: Power must be ON or OFF.");
            return false;
        }

        on = value.equalsIgnoreCase("ON");
        System.out.println("Power: " + (on ? "ON" : "OFF"));
        return true;
    }
}

class BrightnessCapability implements Capability {
    private int brightness = 0;

    public String getName() {
        return "Brightness";
    }

    public boolean apply(String value) {
        try {
            int level = Integer.parseInt(value);

            if (level < 0 || level > 100) {
                System.out.println(
                        "Rejected: Brightness must be between 0 and 100%.");
                return false;
            }

            brightness = level;
            System.out.println("Brightness set to " + brightness + "%.");
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Rejected: Invalid brightness value.");
            return false;
        }
    }
}

class TemperatureCapability implements Capability {
    private int temperature = 24;

    public String getName() {
        return "Temperature";
    }

    public boolean apply(String value) {
        try {
            int temp = Integer.parseInt(value);

            if (temp < 16 || temp > 30) {
                System.out.println(
                        "Rejected: Temperature must be between 16°C and 30°C.");
                return false;
            }

            temperature = temp;
            System.out.println(
                    "Temperature set to " + temperature + "°C.");
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Rejected: Invalid temperature value.");
            return false;
        }
    }
}

class Device {
    private String name;
    private Map<String, Capability> capabilities = new LinkedHashMap<>();

    public Device(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addCapability(Capability capability) {
        capabilities.put(capability.getName().toLowerCase(), capability);
        System.out.println(name + ": " + capability.getName()
                + " capability added.");
    }

    public boolean supports(String capabilityName) {
        return capabilities.containsKey(capabilityName.toLowerCase());
    }

    public boolean apply(String capabilityName, String value) {
        Capability capability =
                capabilities.get(capabilityName.toLowerCase());

        if (capability == null) {
            return false;
        }

        System.out.print(name + ": ");
        return capability.apply(value);
    }
}

class SceneStep {
    private String capabilityName;
    private String value;

    public SceneStep(String capabilityName, String value) {
        this.capabilityName = capabilityName;
        this.value = value;
    }

    public boolean execute(List<Device> devices) {
        int actions = 0;

        for (Device device : devices) {
            if (device.supports(capabilityName)) {
                if (device.apply(capabilityName, value)) {
                    actions++;
                }
            }
        }

        return actions > 0;
    }
}

class Scene {
    private String name;
    private List<SceneStep> steps = new ArrayList<>();

    public Scene(String name) {
        this.name = name;
    }

    public void addStep(SceneStep step) {
        steps.add(step);
    }

    public void execute(List<Device> devices) {
        System.out.println("Scene '" + name + "' started.");

        int actions = 0;

        for (SceneStep step : steps) {
            // Each step applies to every device supporting that capability.
            if (step.execute(devices)) {
                actions++;
            }
        }

        System.out.println("Scene '" + name
                + "' completed: " + actions + " steps applied.");
    }
}

public class SmartLabControlPanel {
    public static void main(String[] args) {
        Device ac = new Device("Lab AC");
        ac.addCapability(new PowerCapability());
        ac.addCapability(new TemperatureCapability());

        Device lights = new Device("Ceiling Lights");
        lights.addCapability(new PowerCapability());
        lights.addCapability(new BrightnessCapability());

        Device projector = new Device("Projector");
        projector.addCapability(new PowerCapability());

        List<Device> devices = Arrays.asList(ac, lights, projector);

        Scene lectureMode = new Scene("Lecture Mode");
        lectureMode.addStep(new SceneStep("Power", "ON"));
        lectureMode.addStep(new SceneStep("Brightness", "40"));
        lectureMode.addStep(new SceneStep("Temperature", "24"));

        lectureMode.execute(devices);

        // Invalid temperature must not change the device's state.
        ac.apply("Temperature", "12");

        // Add a capability at runtime.
        projector.addCapability(new BrightnessCapability());
        projector.apply("Brightness", "70");
    }
}
