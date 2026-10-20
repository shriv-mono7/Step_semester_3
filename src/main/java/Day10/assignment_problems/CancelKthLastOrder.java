package Day10.assignment_problems;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class CancelKthLastOrder {

    static Node removeKthFromEnd(Node head, int k) {
        Node dummy = new Node(0);
        dummy.next = head;

        Node lead = dummy;
        Node trail = dummy;

        // Move lead k + 1 steps ahead.
        for (int i = 0; i <= k; i++) {
            lead = lead.next;
        }

        // Move both pointers until lead reaches the end.
        while (lead != null) {
            lead = lead.next;
            trail = trail.next;
        }

        // Remove the target node.
        trail.next = trail.next.next;

        return dummy.next;
    }

    static void display(Node head) {
        Node current = head;

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
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);
        head.next.next.next.next = new Node(50);

        int k = 2;

        System.out.print("Original orders: ");
        display(head);

        head = removeKthFromEnd(head, k);

        System.out.print("After cancellation: ");
        display(head);
    }
}