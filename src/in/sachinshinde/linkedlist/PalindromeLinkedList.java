package in.sachinshinde.linkedlist;

//	https://leetcode.com/problems/palindrome-linked-list/

/*
 * 		Given the head of a singly linked list, return true if it is a palindrome.
 */

/*
 * 	1. Reach to the middle of a linked list, use slow-fast pointers. slow should points to middle of the list
 * 	2. Now from slow to the end of the list - reverse that list 
 * 	3. Now, we have 2 lists 
 * 			: one from head to the middle-1 nodes
 * 			: another from end node to the middle node (in reverse manner)
 * 	4. Compare nodes one by one  
 * 
 */

public class PalindromeLinkedList {
	
	public boolean isPalindrome(ListNode head) {
	    ListNode fast = head, slow = head;
	    while (fast != null && fast.next != null) {
	        fast = fast.next.next;
	        slow = slow.next;
	    }
	    
	    // If the number of nodes are in odd length: fast - does not points to last node, 
		// 	slow points to one node less than the middle of linked list, so move slow to it's next node  
	    if (fast != null) 
	    	slow = slow.next;
	    
	    slow = reverse(slow);
	    fast = head;
	    
	    while (slow != null) {
	        if (fast.data != slow.data) 
	            return false;
	        
	        fast = fast.next;
	        slow = slow.next;
	    }
	    
	    return true;
	}

	private ListNode reverse(ListNode head) {
	    ListNode prev = null;
	    while (head != null) {
	        ListNode next = head.next;
	        head.next = prev;
	        prev = head;
	        head = next;
	    }
	    return prev;
	}
	
}
