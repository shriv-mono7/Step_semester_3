package Day3.class_problems;

public class FeeAccount {

    private String regNo;
    private double totalFee;
    private double amountPaid;

    public FeeAccount(String regNo, double totalFee, double amountPaid) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
    }

    public void pay(double amount) {
        amountPaid += amount;

        if (amountPaid > totalFee) {
            amountPaid = totalFee;
        }
    }

    public double getDue() {
        return totalFee - amountPaid;
    }

    public static void main(String[] args) {

        FeeAccount plain =
                new FeeAccount("RA001", 150000, 0);

        HostelFeeAccount hostel =
                new HostelFeeAccount("RA002", 200000, 0);

        ScholarshipFeeAccount scholarship =
                new ScholarshipFeeAccount("RA003", 180000, 0, 20);

        plain.pay(50000);
        hostel.payInTwoInstallments(60000);
        scholarship.pay(30000);

        FeeAccount[] accounts = {
            plain, hostel, scholarship
        };

        for (FeeAccount account : accounts) {

            if (account instanceof HostelFeeAccount) {

                HostelFeeAccount hostelAccount =
                        (HostelFeeAccount) account;

                System.out.println(
                    "Hostel account due: Rs "
                    + hostelAccount.getDue()
                );

            } else if (account instanceof ScholarshipFeeAccount) {

                ScholarshipFeeAccount scholarshipAccount =
                        (ScholarshipFeeAccount) account;

                System.out.println(
                    "Scholarship account effective due: Rs "
                    + scholarshipAccount.effectiveDue()
                );

            } else {

                System.out.println(
                    "Plain account due: Rs "
                    + account.getDue()
                );
            }
        }
    }
}


class HostelFeeAccount extends FeeAccount {

    public HostelFeeAccount(
            String regNo,
            double totalFee,
            double amountPaid) {

        super(regNo, totalFee, amountPaid);
    }

    public void payInTwoInstallments(double amount) {

        pay(amount / 2);
        pay(amount / 2);
    }
}


class ScholarshipFeeAccount extends FeeAccount {

    private double scholarshipPercent;

    public ScholarshipFeeAccount(
            String regNo,
            double totalFee,
            double amountPaid,
            double scholarshipPercent) {

        super(regNo, totalFee, amountPaid);
        this.scholarshipPercent = scholarshipPercent;
    }

    public double effectiveDue() {

        return getDue()
                * (1 - scholarshipPercent / 100);
    }
}