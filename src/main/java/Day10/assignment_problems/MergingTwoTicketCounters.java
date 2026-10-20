package Day10.assignment_problems;

class TicketNode {
    int data;
    TicketNode next;

    TicketNode(int data) {
        this.data = data;
    }
}

public class MergingTwoTicketCounters {

    static TicketNode mergeSorted(TicketNode a, TicketNode b) {
        TicketNode dummy = new TicketNode(0);
        TicketNode tail = dummy;

        while (a != null && b != null) {
            if (a.data <= b.data) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }

            tail = tail.next;
        }

        // Attach whichever list still has nodes.
        if (a != null) {
            tail.next = a;
        } else {
            tail.next = b;
        }

        return dummy.next;
    }

    static void display(TicketNode head) {
        TicketNode current = head;

        while (current != null) {
            System.out.print(current.data);

            if (current.next != null) {
                System.out.print(" -> ");
            }

            current = current.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        TicketNode a = new TicketNode(3);
        a.next = new TicketNode(8);
        a.next.next = new TicketNode(15);

        TicketNode b = new TicketNode(5);
        b.next = new TicketNode(8);
        b.next.next = new TicketNode(12);
        b.next.next.next = new TicketNode(20);

        System.out.print("Counter A: ");
        display(a);

        System.out.print("Counter B: ");
        display(b);

        TicketNode merged = mergeSorted(a, b);

        System.out.print("Merged tickets: ");
        display(merged);
    }
}