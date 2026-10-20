
package Day8.class_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Employee {
    private int employeeId;
    private String name;

    public Employee(int employeeId, String name) {
        this.employeeId = employeeId;
        this.name = name;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }
}

class LeaveRequest {
    private int requestId;
    private Employee employee;
    private String leaveType;
    private int days;
    private String reason;
    private String status;

    public LeaveRequest(int requestId, Employee employee,
                        String leaveType, int days, String reason) {
        this.requestId = requestId;
        this.employee = employee;
        this.leaveType = leaveType;
        this.days = days;
        this.reason = reason;
        this.status = "PENDING";
    }

    public int getRequestId() {
        return requestId;
    }

    public String getStatus() {
        return status;
    }

    public void approve() {
        if (status.equals("PENDING")) {
            status = "APPROVED";
            System.out.println("Leave request approved.");
        } else {
            System.out.println("Request has already been processed.");
        }
    }

    public void reject() {
        if (status.equals("PENDING")) {
            status = "REJECTED";
            System.out.println("Leave request rejected.");
        } else {
            System.out.println("Request has already been processed.");
        }
    }

    public void display() {
        System.out.println("\nRequest ID: " + requestId);
        System.out.println("Employee ID: " + employee.getEmployeeId());
        System.out.println("Employee Name: " + employee.getName());
        System.out.println("Leave Type: " + leaveType);
        System.out.println("Number of Days: " + days);
        System.out.println("Reason: " + reason);
        System.out.println("Status: " + status);
    }
}

class LeaveManagementSystem {
    private List<LeaveRequest> requests = new ArrayList<>();
    private int nextRequestId = 1;

    public void submitRequest(Employee employee, String leaveType,
                              int days, String reason) {
        if (days <= 0) {
            System.out.println("Number of leave days must be positive.");
            return;
        }

        LeaveRequest request = new LeaveRequest(
                nextRequestId++, employee, leaveType, days, reason);

        requests.add(request);

        System.out.println("Leave request submitted successfully.");
        System.out.println("Your request ID is: " + request.getRequestId());
    }

    public void displayAllRequests() {
        if (requests.isEmpty()) {
            System.out.println("No leave requests found.");
            return;
        }

        for (LeaveRequest request : requests) {
            request.display();
        }
    }

    public void displayPendingRequests() {
        boolean found = false;

        for (LeaveRequest request : requests) {
            if (request.getStatus().equals("PENDING")) {
                request.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No pending leave requests.");
        }
    }

    public void processRequest(int requestId, boolean approve) {
        for (LeaveRequest request : requests) {
            if (request.getRequestId() == requestId) {
                if (approve) {
                    request.approve();
                } else {
                    request.reject();
                }
                return;
            }
        }

        System.out.println("Request ID not found.");
    }
}

public class EmployeeLeaveRequestManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LeaveManagementSystem system = new LeaveManagementSystem();

        int choice;

        do {
            System.out.println("\n===== EMPLOYEE LEAVE MANAGEMENT =====");
            System.out.println("1. Submit leave request");
            System.out.println("2. View all requests");
            System.out.println("3. View pending requests");
            System.out.println("4. Approve a request");
            System.out.println("5. Reject a request");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                sc.nextLine();
                choice = 0;
                continue;
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter employee ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter employee name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter leave type: ");
                    String type = sc.nextLine();

                    System.out.print("Enter number of days: ");
                    int days = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter reason: ");
                    String reason = sc.nextLine();

                    Employee employee = new Employee(id, name);
                    system.submitRequest(employee, type, days, reason);
                    break;

                case 2:
                    system.displayAllRequests();
                    break;

                case 3:
                    system.displayPendingRequests();
                    break;

                case 4:
                    System.out.print("Enter request ID to approve: ");
                    int approveId = sc.nextInt();
                    sc.nextLine();
                    system.processRequest(approveId, true);
                    break;

                case 5:
                    System.out.print("Enter request ID to reject: ");
                    int rejectId = sc.nextInt();
                    sc.nextLine();
                    system.processRequest(rejectId, false);
                    break;

                case 6:
                    System.out.println("Exiting Leave Management System.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 6);

        sc.close();
    }
}
