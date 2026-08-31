package Day4.assignment_problems;

public class FoodOrder {

    private String studentName;
    private String dishName;
    private boolean delivered;

    // Only parameterized constructor.
    public FoodOrder(String studentName, String dishName) {

        if (studentName == null
                || studentName.trim().isEmpty()) {

            throw new IllegalArgumentException(
                "Student name cannot be blank"
            );
        }

        if (dishName == null
                || dishName.trim().isEmpty()) {

            throw new IllegalArgumentException(
                "Dish name cannot be blank"
            );
        }

        this.studentName = studentName.trim();
        this.dishName = dishName.trim();
        this.delivered = false;
    }

    public void markDelivered() {

        if (!delivered) {

            delivered = true;

            System.out.println(
                "Order marked as delivered."
            );

        } else {

            System.out.println(
                "Order was already delivered."
            );
        }
    }

    public static void processBatch(String[][] rawOrders) {

        int valid = 0;
        int rejected = 0;

        for (String[] order : rawOrders) {

            if (order == null || order.length < 2) {
                rejected++;
                continue;
            }

            try {

                new FoodOrder(
                    order[0],
                    order[1]
                );

                valid++;

            } catch (IllegalArgumentException e) {

                rejected++;
            }
        }

        System.out.println(
            "Valid: " + valid
            + " | Rejected: " + rejected
        );
    }

    public static void main(String[] args) {

        String[][] rawOrders = {

            {"Ravi", "Paneer Butter Masala"},

            {"", "Chole Bhature"},

            {"Meera", " "},

            {"Divya", "Veg Biryani"}
        };

        processBatch(rawOrders);

        // Test markDelivered()
        FoodOrder order =
                new FoodOrder(
                    "Arun",
                    "Fried Rice"
                );

        order.markDelivered();
        order.markDelivered();
    }
}