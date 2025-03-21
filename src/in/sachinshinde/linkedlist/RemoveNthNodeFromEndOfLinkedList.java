package in.sachinshinde.linkedlist;

//	https://leetcode.com/problems/remove-nth-node-from-end-of-list/

/*
        Given the head of a linked list, remove the nth node from the end of the list and return its head.

        Example 1:
        ---------
            Input: head = [1,2,3,4,5], n = 2
            Output: [1,2,3,5]

        Example 2:
        ---------
            Input: head = [1], n = 1
            Output: []

        Example 3:
        ---------
            Input: head = [1,2], n = 1
            Output: [1]

        Constraints:
        -----------
            The number of nodes in the list is sz.
            1 <= sz <= 30
            0 <= Node.val <= 100
            1 <= n <= sz

        Follow up: Could you do this in one pass?
 */

public class RemoveNthNodeFromEndOfLinkedList {

    //  Using fast and slow pointers: leadingPointer = fast;  secondaryPointer = slow;
	public ListNode removeNthFromEnd(ListNode head, int n) {
		
		ListNode leadingPointer = head, secondaryPointer = head;
		
		//	Move the leadingPointer pointer to the n'th node from start of the linked list
        for (int i = 0; i < n; i++) {
            leadingPointer = leadingPointer.next;
        }
        
        // Case : when n = size of linkedList, n'th node from right is the first node of the linked list
        //			So, to remove n'th node in this case - simply move head pointer to it's next node
        if (leadingPointer == null) {
            return head == null ? null : head.next;    // *** IMP : return from here itself
        }
        
        //	At this point the leadingPointer pointer will point to the n'th node from start of linked list 
        while (leadingPointer.next != null) {
            leadingPointer = leadingPointer.next;
            secondaryPointer = secondaryPointer.next;
        }
        
        //	At this point, the secondaryPointer pointer points to the one less than n'th node from right 
        secondaryPointer.next = secondaryPointer.next.next;
        return head;	// ** Reminder to return head only, NOT the secondaryPointer pointer 
    }

    public static void main(String[] args) {
        RemoveNthNodeFromEndOfLinkedList linkedList = new RemoveNthNodeFromEndOfLinkedList();

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);

        System.out.println("Before : ");
        linkedList.print(l1);   //  1 --> 2 --> 3 --> 4 --> 5
        ListNode modifiedListHeadNode = linkedList.removeNthFromEnd(l1, 2);
        System.out.println("\nAfter : ");
        linkedList.print(modifiedListHeadNode); //  1 --> 2 --> 3 --> 5
    }

    private void print(ListNode head) {
        while(head.next != null) {
            System.out.print(head.data + " --> ");
            head = head.next;
        }
        System.out.print(head.data);
    }

}
