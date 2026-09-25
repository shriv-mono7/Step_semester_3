
package Day7.assignment_problems;

interface Exportable {
    String exportData();
}

class ReportGenerator implements Exportable {
    private String reportName;

    public ReportGenerator(String reportName) {
        if (reportName == null || reportName.trim().isEmpty()) {
            throw new IllegalArgumentException("Report name cannot be blank");
        }

        this.reportName = reportName;
    }

    @Override
    public String exportData() {
        OneClickDataExport.incrementCounter();

        return "Exported Report: " + reportName;
    }
}

class UserProfile implements Exportable {
    private String username;

    public UserProfile(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be blank");
        }

        this.username = username;
    }

    @Override
    public String exportData() {
        OneClickDataExport.incrementCounter();

        return "Exported User Profile: " + username;
    }
}

public class OneClickDataExport {

    private static int exportCounter = 0;

    public static void incrementCounter() {
        exportCounter++;
    }

    public static int getExportCounter() {
        return exportCounter;
    }

    public static void exportAll(Exportable[] items) {
        for (Exportable item : items) {
            System.out.println(item.exportData());
        }
    }

    public static void main(String[] args) {

        ReportGenerator report = new ReportGenerator("Annual Report");
        UserProfile user = new UserProfile("Shrivalli");

        Exportable[] items = {report, user};

        exportAll(items);

        System.out.println(
                "Total Exports: " + getExportCounter()
        );

        // Demonstrating polymorphism
        Exportable anotherReport = new ReportGenerator("Monthly Report");

        System.out.println(anotherReport.exportData());

        System.out.println(
                "Total Exports: " + getExportCounter()
        );
    }
}