package Day10.assignment_problems;

class PalindromeNode {
    int data;
    PalindromeNode next;

    PalindromeNode(int data) {
        this.data = data;
    }
}

public class PalindromeTrainCode {

    static boolean isPalindrome(PalindromeNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // Find the end of the first half.
        PalindromeNode slow = head;
        PalindromeNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse the second half.
        PalindromeNode secondHalf = reverse(slow.next);

        // Compare both halves.
        PalindromeNode first = head;
        PalindromeNode second = secondHalf;
        boolean result = true;

        while (second != null) {
            if (first.data != second.data) {
                result = false;
                break;
            }

            first = first.next;
            second = second.next;
        }

        // Restore the original list.
        slow.next = reverse(secondHalf);

        return result;
    }

    static PalindromeNode reverse(PalindromeNode head) {
        PalindromeNode previous = null;
        PalindromeNode current = head;

        while (current != null) {
            PalindromeNode nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }

        return previous;
    }

    static void display(PalindromeNode head) {
        PalindromeNode current = head;

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
        PalindromeNode head = new PalindromeNode(1);
        head.next = new PalindromeNode(2);
        head.next.next = new PalindromeNode(3);
        head.next.next.next = new PalindromeNode(2);
        head.next.next.next.next = new PalindromeNode(1);

        System.out.print("Original list: ");
        display(head);

        System.out.println("Is palindrome: " + isPalindrome(head));

        System.out.print("Restored list: ");
        display(head);
    }
}