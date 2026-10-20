
package Day8.assignment_problems;

import java.util.*;

interface CreditPolicy {
    int getCreditLimit();
    String getType();
}

class RegularPolicy implements CreditPolicy {
    public int getCreditLimit() {
        return 24;
    }

    public String getType() {
        return "Regular";
    }
}

class HonorsPolicy implements CreditPolicy {
    public int getCreditLimit() {
        return 28;
    }

    public String getType() {
        return "Honors";
    }
}

class ExchangePolicy implements CreditPolicy {
    public int getCreditLimit() {
        return 20;
    }

    public String getType() {
        return "Exchange";
    }
}

class Student {
    private String name;
    private int currentCredits;
    private CreditPolicy policy;

    public Student(String name, int currentCredits, CreditPolicy policy) {
        this.name = name;
        this.currentCredits = currentCredits;
        this.policy = policy;
    }

    public String getName() {
        return name;
    }

    public int getCurrentCredits() {
        return currentCredits;
    }

    public int getCreditLimit() {
        return policy.getCreditLimit();
    }

    public String getType() {
        return policy.getType();
    }

    public boolean canEnroll(int electiveCredits) {
        return currentCredits + electiveCredits <= getCreditLimit();
    }

    public void addCredits(int credits) {
        currentCredits += credits;
    }

    public void removeCredits(int credits) {
        currentCredits -= credits;
    }
}

class Elective {
    private String name;
    private int credits;
    private int capacity;
    private List<Student> enrolled = new ArrayList<>();
    private Queue<Student> waitlist = new LinkedList<>();

    public Elective(String name, int credits, int capacity) {
        this.name = name;
        this.credits = credits;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public boolean isEnrolled(Student student) {
        return enrolled.contains(student);
    }

    public boolean isWaiting(Student student) {
        return waitlist.contains(student);
    }

    public boolean hasSpace() {
        return enrolled.size() < capacity;
    }

    public void enroll(Student student) {
        enrolled.add(student);
        student.addCredits(credits);

        System.out.println(student.getName() + " enrolled in " + name
                + " (credits: " + student.getCurrentCredits()
                + "/" + student.getCreditLimit() + ").");
    }

    public void addToWaitlist(Student student) {
        waitlist.offer(student);

        System.out.println(student.getName()
                + " added to waitlist (position "
                + waitlist.size() + ").");
    }

    public void drop(Student student) {
        if (!enrolled.remove(student)) {
            System.out.println(student.getName()
                    + " is not enrolled in " + name + ".");
            return;
        }

        student.removeCredits(credits);

        System.out.println(student.getName() + " dropped " + name
                + " (credits: " + student.getCurrentCredits()
                + "/" + student.getCreditLimit() + ").");

        promoteNextStudent();
    }

    private void promoteNextStudent() {
        while (hasSpace() && !waitlist.isEmpty()) {
            Student next = waitlist.poll();

            // Recheck the credit limit when a waitlisted student is promoted.
            if (next.canEnroll(credits)) {
                enroll(next);
                System.out.println(next.getName()
                        + " promoted from waitlist and enrolled in "
                        + name + ".");
            } else {
                System.out.println("Promotion failed for "
                        + next.getName() + ": Credit limit exceeded.");
            }
        }
    }
}

class EnrollmentService {
    public void enroll(Student student, Elective elective) {
        if (elective.isEnrolled(student) || elective.isWaiting(student)) {
            System.out.println(student.getName()
                    + " is already enrolled or waitlisted.");
            return;
        }

        // Credit limit is checked before seat availability.
        if (!student.canEnroll(4)) {
            System.out.println("Enrollment failed: " + student.getName()
                    + " would exceed the " + student.getType()
                    + " credit limit (" + (student.getCurrentCredits() + 4)
                    + "/" + student.getCreditLimit() + ").");
            return;
        }

        if (elective.hasSpace()) {
            elective.enroll(student);
        } else {
            System.out.println(elective.getName() + " is full.");
            elective.addToWaitlist(student);
        }
    }

    public void drop(Student student, Elective elective) {
        elective.drop(student);
    }
}

public class ElectiveSeatRush {
    public static void main(String[] args) {
        Elective elective = new Elective("Cloud Computing", 4, 2);
        EnrollmentService service = new EnrollmentService();

        Student asha = new Student("Asha", 20, new RegularPolicy());
        Student ravi = new Student("Ravi", 22, new HonorsPolicy());
        Student neha = new Student("Neha", 12, new ExchangePolicy());
        Student kiran = new Student("Kiran", 22, new RegularPolicy());

        service.enroll(asha, elective);
        service.enroll(ravi, elective);
        service.enroll(neha, elective);
        service.enroll(kiran, elective);

        service.drop(asha, elective);
    }
}
