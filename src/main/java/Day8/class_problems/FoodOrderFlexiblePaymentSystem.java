
package Day8.class_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class FoodItem {
    private String name;
    private double price;

    public FoodItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

interface PaymentMethod {
    void pay(double amount);
}

class CashPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.printf("Paid Rs. %.2f using Cash.%n", amount);
    }
}

class CardPayment implements PaymentMethod {
    private String cardNumber;

    public CardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        String lastFour = cardNumber.substring(
            Math.max(0, cardNumber.length() - 4)
        );

        System.out.printf(
            "Paid Rs. %.2f using Card ending in %s.%n",
            amount, lastFour
        );
    }
}

class UPIPayment implements PaymentMethod {
    private String upiId;

    public UPIPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void pay(double amount) {
        System.out.printf(
            "Paid Rs. %.2f using UPI ID %s.%n",
            amount, upiId
        );
    }
}

class FoodOrder {
    private static int nextOrderId = 1001;

    private int orderId;
    private List<FoodItem> items;
    private boolean paid;

    public FoodOrder() {
        orderId = nextOrderId++;
        items = new ArrayList<>();
        paid = false;
    }

    public void addItem(FoodItem item) {
        if (paid) {
            System.out.println(
                "Cannot modify an order after payment."
            );
            return;
        }

        items.add(item);
        System.out.println(item.getName() + " added to order.");
    }

    public double calculateTotal() {
        double total = 0;

        for (FoodItem item : items) {
            total += item.getPrice();
        }

        return total;
    }

    public void displayOrder() {
        System.out.println("\n===== FOOD ORDER =====");
        System.out.println("Order ID: " + orderId);

        if (items.isEmpty()) {
            System.out.println("No items in the order.");
            return;
        }

        for (FoodItem item : items) {
            System.out.printf(
                "%s - Rs. %.2f%n",
                item.getName(), item.getPrice()
            );
        }

        System.out.printf(
            "Total: Rs. %.2f%n", calculateTotal()
        );

        System.out.println(
            "Payment Status: " + (paid ? "PAID" : "UNPAID")
        );
    }

    public void makePayment(PaymentMethod paymentMethod) {
        if (items.isEmpty()) {
            System.out.println(
                "Cannot pay for an empty order."
            );
            return;
        }

        if (paid) {
            System.out.println(
                "This order has already been paid."
            );
            return;
        }

        paymentMethod.pay(calculateTotal());
        paid = true;

        System.out.println("Payment successful!");
    }
}

public class FoodOrderFlexiblePaymentSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        FoodOrder order = new FoodOrder();

        FoodItem[] menu = {
            new FoodItem("Burger", 120),
            new FoodItem("Pizza", 250),
            new FoodItem("Sandwich", 100),
            new FoodItem("Fries", 80),
            new FoodItem("Juice", 60)
        };

        int choice = 0;

        do {
            System.out.println("\n===== FOOD ORDER SYSTEM =====");
            System.out.println("1. View menu");
            System.out.println("2. Add food item");
            System.out.println("3. View order");
            System.out.println("4. Make payment");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Enter a valid number.");
                sc.nextLine();
                continue;
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n===== MENU =====");

                    for (int i = 0; i < menu.length; i++) {
                        System.out.printf(
                            "%d. %s - Rs. %.2f%n",
                            i + 1,
                            menu[i].getName(),
                            menu[i].getPrice()
                        );
                    }
                    break;

                case 2:
                    System.out.print("Enter menu item number: ");

                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid item number.");
                        sc.nextLine();
                        break;
                    }

                    int itemNumber = sc.nextInt();
                    sc.nextLine();

                    if (itemNumber >= 1
                            && itemNumber <= menu.length) {
                        order.addItem(menu[itemNumber - 1]);
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;

                case 3:
                    order.displayOrder();
                    break;

                case 4:
                    System.out.println("1. Cash");
                    System.out.println("2. Card");
                    System.out.println("3. UPI");
                    System.out.print("Choose payment method: ");

                    if (!sc.hasNextInt()) {
                        System.out.println(
                            "Invalid payment choice."
                        );
                        sc.nextLine();
                        break;
                    }

                    int paymentChoice = sc.nextInt();
                    sc.nextLine();

                    switch (paymentChoice) {
                        case 1:
                            order.makePayment(new CashPayment());
                            break;

                        case 2:
                            System.out.print("Enter card number: ");
                            String cardNumber = sc.nextLine();

                            if (!cardNumber.trim().isEmpty()) {
                                order.makePayment(
                                    new CardPayment(cardNumber)
                                );
                            } else {
                                System.out.println(
                                    "Card number cannot be empty."
                                );
                            }
                            break;

                        case 3:
                            System.out.print("Enter UPI ID: ");
                            String upiId = sc.nextLine();

                            if (!upiId.trim().isEmpty()) {
                                order.makePayment(
                                    new UPIPayment(upiId)
                                );
                            } else {
                                System.out.println(
                                    "UPI ID cannot be empty."
                                );
                            }
                            break;

                        default:
                            System.out.println(
                                "Invalid payment method."
                            );
                    }
                    break;

                case 5:
                    System.out.println(
                        "Thank you for ordering!"
                    );
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);

        sc.close();
    }
}
