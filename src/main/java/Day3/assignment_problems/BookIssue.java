package Day3.assignment_problems;

public class BookIssue {

    String title;
    String borrowerName;
    int daysOverdue;

    public BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    public double fineAmount() {
        if (daysOverdue > 0) {
            return daysOverdue * 5;
        }

        return 0;
    }

    public boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    /*
     * fineAmount() is an instance method because the fine belongs
     * to one particular book issue.
     *
     * totalFineCollected() is static because it calculates the
     * total fine across multiple BookIssue objects.
     */
    public static double totalFineCollected(BookIssue[] issues) {

        double total = 0;

        for (BookIssue issue : issues) {
            total += issue.fineAmount();
        }

        return total;
    }

    public static void main(String[] args) {

        BookIssue[] issues = {
            new BookIssue("Clean Code", "Ravi", 18),
            new BookIssue("Effective Java", "Anitha", 5),
            new BookIssue("Refactoring", "Karthik", 0),
            new BookIssue("DSA Handbook", "Meera", 21),
            new BookIssue("Design Patterns", "Suresh", 9)
        };

        for (BookIssue issue : issues) {

            String status;

            if (issue.isSeverelyOverdue()) {
                status = "Severely overdue";
            } else {
                status = "OK";
            }

            System.out.println(
                issue.title
                + " - "
                + issue.daysOverdue
                + " days - "
                + status
            );
        }

        System.out.println(
            "Total fine collected: Rs "
            + totalFineCollected(issues)
        );
    }
}