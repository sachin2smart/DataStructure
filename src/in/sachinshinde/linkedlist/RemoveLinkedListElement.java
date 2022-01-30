package in.sachinshinde.linkedlist;

//	https://leetcode.com/problems/remove-linked-list-elements/

/*
 * 	Remove Linked List Elements
 * 	Given the head of a linked list and an integer val, 
 * 	remove all the nodes of the linked list that has Node.val == val, 
 * 	and return the new head.
 */

public class RemoveLinkedListElement {

	public ListNode removeElements(ListNode head, int val) {
       if(head == null) 
           return null;
        
        ListNode next = removeElements(head.next, val);
        
        if(head.data == val) 
            return next;
        
        head.next = next;
        
        return head; 
    }
	
}
