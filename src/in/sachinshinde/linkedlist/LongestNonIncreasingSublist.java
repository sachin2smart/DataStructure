package in.sachinshinde.linkedlist;

// Another flavour of -
    // https://www.geeksforgeeks.org/longest-increasing-sublist-linked-list/

class SinglyLinkedListNode {
    int data;
    SinglyLinkedListNode next;

    public SinglyLinkedListNode(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LongestNonIncreasingSublist {
    public SinglyLinkedListNode locateLongestList(SinglyLinkedListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        SinglyLinkedListNode maxSublistStart = null;
        SinglyLinkedListNode maxSublistEnd = null;
        SinglyLinkedListNode currentStart = head;
        SinglyLinkedListNode prevNode = head;

        int maxLength = 1;
        int currentLength = 1;

        while (head.next != null) {
            if (head.next.data <= head.data) {
                currentLength++;
            }
            else {
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    maxSublistStart = currentStart;
                    maxSublistEnd = prevNode;
                }
                currentLength = 1;
                currentStart = head.next;
            }
            prevNode = head.next;
            head = head.next;
        }

        if (currentLength > maxLength) {
            maxSublistStart = currentStart;
            maxSublistEnd = prevNode;
        }

        // Ensure the sublist ends at the last node
        if (maxSublistEnd != null) {
            maxSublistEnd.next = null;
        }

        return maxSublistStart;
    }

    public static void main(String[] args) {
        LongestNonIncreasingSublist list = new LongestNonIncreasingSublist();

        SinglyLinkedListNode head = new SinglyLinkedListNode(1);
        head.next = new SinglyLinkedListNode(2);
        head.next.next = new SinglyLinkedListNode(4);
        head.next.next.next = new SinglyLinkedListNode(5);
        head.next.next.next.next = new SinglyLinkedListNode(4);
        head.next.next.next.next.next = new SinglyLinkedListNode(4);
        head.next.next.next.next.next.next = new SinglyLinkedListNode(2);
        head.next.next.next.next.next.next.next = new SinglyLinkedListNode(9);

        head = list.locateLongestList(head);
        list.printList(head);   //  5 4 4 2
    }

    private void printList(SinglyLinkedListNode head) {
        while(head != null) {
            System.out.print(" "+ head.data);
            head = head.next;
        }
    }
}
