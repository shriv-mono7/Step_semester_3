package Day10.assignment_problems;

class PolynomialNode {
    int coefficient;
    int exponent;
    PolynomialNode next;

    PolynomialNode(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
        this.next = null;
    }
}

public class PolynomialCalculator {

    static PolynomialNode addPolynomials(
            PolynomialNode p, PolynomialNode q) {

        PolynomialNode dummy = new PolynomialNode(0, 0);
        PolynomialNode tail = dummy;

        while (p != null && q != null) {
            if (p.exponent > q.exponent) {
                tail.next = new PolynomialNode(
                    p.coefficient, p.exponent
                );
                p = p.next;
            } else if (p.exponent < q.exponent) {
                tail.next = new PolynomialNode(
                    q.coefficient, q.exponent
                );
                q = q.next;
            } else {
                int sum = p.coefficient + q.coefficient;

                if (sum != 0) {
                    tail.next = new PolynomialNode(
                        sum, p.exponent
                    );
                }

                p = p.next;
                q = q.next;

                if (sum == 0) {
                    continue;
                }
            }

            tail = tail.next;
        }

        // Copy any remaining terms from p.
        while (p != null) {
            tail.next = new PolynomialNode(
                p.coefficient, p.exponent
            );
            tail = tail.next;
            p = p.next;
        }

        // Copy any remaining terms from q.
        while (q != null) {
            tail.next = new PolynomialNode(
                q.coefficient, q.exponent
            );
            tail = tail.next;
            q = q.next;
        }

        return dummy.next;
    }

    static void printPolynomial(PolynomialNode head) {
        if (head == null) {
            System.out.println("0");
            return;
        }

        PolynomialNode current = head;
        boolean first = true;

        while (current != null) {
            int coefficient = current.coefficient;
            int exponent = current.exponent;

            if (!first) {
                System.out.print(
                    coefficient > 0 ? " + " : " - "
                );
            } else if (coefficient < 0) {
                System.out.print("-");
            }

            int absoluteCoefficient = Math.abs(coefficient);

            if (exponent == 0 || absoluteCoefficient != 1) {
                System.out.print(absoluteCoefficient);
            }

            if (exponent > 0) {
                System.out.print("x");

                if (exponent > 1) {
                    System.out.print("^" + exponent);
                }
            }

            first = false;
            current = current.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        // p = 5x^3 + 4x^2 + 2
        PolynomialNode p = new PolynomialNode(5, 3);
        p.next = new PolynomialNode(4, 2);
        p.next.next = new PolynomialNode(2, 0);

        // q = 5x^2 - 3x + 1
        PolynomialNode q = new PolynomialNode(5, 2);
        q.next = new PolynomialNode(-3, 1);
        q.next.next = new PolynomialNode(1, 0);

        System.out.print("Polynomial P: ");
        printPolynomial(p);

        System.out.print("Polynomial Q: ");
        printPolynomial(q);

        PolynomialNode result = addPolynomials(p, q);

        System.out.print("Sum: ");
        printPolynomial(result);
    }
}