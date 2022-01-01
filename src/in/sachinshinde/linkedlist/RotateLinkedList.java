package in.sachinshinde.linkedlist;

//	https://leetcode.com/problems/rotate-list/

/*
 * 	Given the head of a linked list, rotate the list to the right by k places.
 * 
 * 	
 	Input: head = [1,2,3,4,5], k = 2
	Output: [4,5,1,2,3]
 * 	
 * 
 	Input: head = [1,2,3,4,5], k = 7
	Output: [4,5,1,2,3]
 *	
 */

public class RotateLinkedList {
	
	public ListNode rotateRight(ListNode head, int k) {
		if(head == null || head.next == null || k == 0)
			return head;
		
		ListNode curr = head;
		
		int len = 1;
		while(curr.next != null) {
			len++;
			curr = curr.next;
		}
		
		curr.next = head;
		
		k = k % len;
		k = len - k;
			
		while(k-- >0)
			curr = curr.next;
		
		head = curr.next;
		
		curr.next = null;
		
		return head;
    }

}
