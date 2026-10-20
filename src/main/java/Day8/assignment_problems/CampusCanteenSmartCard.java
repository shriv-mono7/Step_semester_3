
package Day8.assignment_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

interface PricingPlan {
    double getDiscount();
    String getPlanName();
}

class DayScholarPlan implements PricingPlan {
    public double getDiscount() {
        return 0.0;
    }

    public String getPlanName() {
        return "Day Scholar";
    }
}

class HostellerPlan implements PricingPlan {
    public double getDiscount() {
        return 0.10;
    }

    public String getPlanName() {
        return "Hosteller";
    }
}

class StaffPlan implements PricingPlan {
    public double getDiscount() {
        return 0.20;
    }

    public String getPlanName() {
        return "Staff";
    }
}

class Transaction {
    private String description;
    private double amount;
    private boolean refunded = false;

    public Transaction(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isRefunded() {
        return refunded;
    }

    public void markRefunded() {
        refunded = true;
    }
}

class SmartCard {
    private String cardId;
    private double balance = 0;
    private PricingPlan plan;
    private boolean blocked = false;
    private List<Transaction> transactions = new ArrayList<>();

    public SmartCard(String cardId, PricingPlan plan) {
        this.cardId = cardId;
        this.plan = plan;
    }

    public double getBalance() {
        return balance;
    }

    public void topUp(double amount) {
        if (blocked) {
            System.out.println("Top-up failed: Card is blocked.");
            return;
        }

        if (amount < 100) {
            System.out.println("Top-up failed: Minimum top-up is ₹100.");
            return;
        }

        if (balance + amount > 5000) {
            System.out.println("Top-up failed: Maximum balance is ₹5000.");
            return;
        }

        balance += amount;
        transactions.add(new Transaction("Top-up", amount));

        System.out.printf(Locale.US,
                "%s topped up with ₹%.2f. Balance: ₹%.2f%n",
                cardId, amount, balance);
    }

    public Transaction purchase(String item, double price) {
        if (blocked) {
            System.out.println("Purchase failed: Card is blocked.");
            return null;
        }

        if (price <= 0) {
            System.out.println("Purchase failed: Price must be positive.");
            return null;
        }

        double charged = price * (1 - plan.getDiscount());

        if (balance < charged) {
            System.out.printf(Locale.US,
                    "Purchase failed: Insufficient balance "
                            + "(required ₹%.2f, available ₹%.2f).%n",
                    charged, balance);
            return null;
        }

        balance -= charged;

        Transaction transaction =
                new Transaction(item, -charged);

        transactions.add(transaction);

        System.out.printf(Locale.US,
                "%s purchased for ₹%.2f. Balance: ₹%.2f%n",
                item, charged, balance);

        return transaction;
    }

    public void refund(Transaction transaction) {
        if (transaction == null || !transactions.contains(transaction)
                || transaction.getAmount() >= 0) {
            System.out.println("Refund rejected: Invalid purchase.");
            return;
        }

        if (transaction.isRefunded()) {
            System.out.println("Refund rejected: "
                    + transaction.getDescription()
                    + " has already been refunded.");
            return;
        }

        double refundAmount = -transaction.getAmount();

        if (balance + refundAmount > 5000) {
            System.out.println("Refund rejected: Maximum balance exceeded.");
            return;
        }

        transaction.markRefunded();
        balance += refundAmount;

        transactions.add(new Transaction(
                "Refund: " + transaction.getDescription(),
                refundAmount));

        System.out.printf(Locale.US,
                "Refund of ₹%.2f for %s processed. Balance: ₹%.2f%n",
                refundAmount, transaction.getDescription(), balance);
    }

    public void block() {
        blocked = true;
        System.out.println(cardId + " is blocked.");
    }

    public void unblock() {
        blocked = false;
        System.out.println(cardId + " is unblocked.");
    }

    public void miniStatement() {
        System.out.print("Mini-statement for " + cardId + ": ");

        double total = 0;

        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            double amount = transaction.getAmount();
            total += amount;

            System.out.printf(Locale.US,
                    "%s%.2f",
                    amount >= 0 ? "+" : "-",
                    Math.abs(amount));

            if (i < transactions.size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.printf(Locale.US,
                " = ₹%.2f%n", total);
    }
}

public class CampusCanteenSmartCard {
    public static void main(String[] args) {
        SmartCard card = new SmartCard(
                "C-2045", new HostellerPlan());

        card.topUp(500);

        Transaction vegThali =
                card.purchase("Veg Thali", 120);

        card.purchase("Cold Coffee", 60);

        card.purchase("Other Items", 400);

        card.refund(vegThali);

        card.refund(vegThali);

        card.miniStatement();

        // Demonstrate blocked-card behavior.
        card.block();
        card.topUp(200);
        card.purchase("Sandwich", 80);
        card.unblock();
    }
}
