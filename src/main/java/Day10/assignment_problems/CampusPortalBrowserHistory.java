package Day10.assignment_problems;

class HistoryNode {
    String url;
    HistoryNode prev;
    HistoryNode next;

    HistoryNode(String url) {
        this.url = url;
    }
}

class BrowserHistory {
    private HistoryNode current;

    BrowserHistory(String homepage) {
        current = new HistoryNode(homepage);
    }

    void visit(String url) {
        HistoryNode newNode = new HistoryNode(url);

        // Discard all forward history.
        current.next = null;

        // Link the new page.
        newNode.prev = current;
        current.next = newNode;
        current = newNode;
    }

    String back(int steps) {
        while (steps > 0 && current.prev != null) {
            current = current.prev;
            steps--;
        }

        return current.url;
    }

    String forward(int steps) {
        while (steps > 0 && current.next != null) {
            current = current.next;
            steps--;
        }

        return current.url;
    }
}

public class CampusPortalBrowserHistory {
    public static void main(String[] args) {
        BrowserHistory browser = new BrowserHistory("srm.edu");

        browser.visit("a.com");
        browser.visit("b.com");
        browser.visit("c.com");

        System.out.println(browser.back(1));
        System.out.println(browser.back(1));
        System.out.println(browser.forward(1));

        browser.visit("d.com");

        System.out.println(browser.forward(2));
        System.out.println(browser.back(2));
        System.out.println(browser.back(7));
    }
}