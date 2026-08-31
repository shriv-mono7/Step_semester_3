package Day3.assignment_problems;

public class Employee {

    private String empId;
    private String empName;
    private double salary;

    public Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public static void main(String[] args) {

        Employee plainEmployee =
                new Employee("E001", "Ravi", 40000);

        ManagerEmployee manager =
                new ManagerEmployee(
                        "E002",
                        "Divya",
                        70000,
                        8000
                );

        InternEmployee intern =
                new InternEmployee(
                        "E003",
                        "Meera",
                        12000,
                        10000
                );

        Employee[] employees = {
            plainEmployee,
            manager,
            intern
        };

        for (Employee employee : employees) {

            if (employee instanceof ManagerEmployee) {

                ManagerEmployee managerEmployee =
                        (ManagerEmployee) employee;

                System.out.println(
                    "Manager effective pay: Rs "
                    + managerEmployee.effectiveSalary()
                );

            } else if (employee instanceof InternEmployee) {

                InternEmployee internEmployee =
                        (InternEmployee) employee;

                System.out.println(
                    "Intern effective pay: Rs "
                    + internEmployee.effectiveSalary()
                );

            } else {

                System.out.println(
                    "Plain employee pay: Rs "
                    + employee.getSalary()
                );
            }
        }
    }
}


class ManagerEmployee extends Employee {

    private double teamBonus;

    public ManagerEmployee(
            String empId,
            String empName,
            double salary,
            double teamBonus) {

        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    public double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}


class InternEmployee extends Employee {

    private double stipendCap;

    public InternEmployee(
            String empId,
            String empName,
            double salary,
            double stipendCap) {

        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    public double effectiveSalary() {

        return Math.min(getSalary(), stipendCap);
    }
}