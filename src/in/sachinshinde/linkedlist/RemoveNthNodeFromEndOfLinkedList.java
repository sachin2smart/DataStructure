package in.sachinshinde.linkedlist;

//	https://leetcode.com/problems/remove-nth-node-from-end-of-list/

public class RemoveNthNodeFromEndOfLinkedList {
	
	public LinkedListNode removeNthFromEnd(LinkedListNode head, int n) {
		
		LinkedListNode leadingPointer = head, secondaryPointer = head;
		
		//	Move the leadingPointer pointer to the n'th node from start of the linked list
        for (int i = 0; i < n; i++) 
        	leadingPointer = leadingPointer.next;
        
        // Case : when n = size of linkedList, n'th node from right is the first node of the linked list
        //			So, to remove n'th node in this case - simply move head pointer to it's next node
        if (leadingPointer == null) 
        	return head.next;	// *** IMP : return from here itself 
        
        //	At this point the leadingPointer pointer will point to the n'th node from start of linked list 
        while (leadingPointer.next != null) {
            leadingPointer = leadingPointer.next;
            secondaryPointer = secondaryPointer.next;
        }
        
        //	At this point, the secondaryPointer pointer points to the one less than n'th node from right 
        secondaryPointer.next = secondaryPointer.next.next;
        
        return head;	// ** Reminder to return head only, NOT the secondaryPointer pointer 
    }

}
