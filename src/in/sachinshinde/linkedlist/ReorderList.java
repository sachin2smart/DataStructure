package in.sachinshinde.linkedlist;

//	https://leetcode.com/problems/reorder-list/

/*
 * 	Reorder List
 * --------------
 *	You are given the head of a singly linked-list. The list can be represented as:

		L0 → L1 → … → Ln - 1 → Ln
		Reorder the list to be on the following form:
		
		L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
		You may not modify the values in the list's nodes. Only nodes themselves may be changed.
	
 */

/*
 * 	
	Input: head = [1,2,3,4]
	Output: [1,4,2,3]
	
	Input: head = [1,2,3,4,5]
	Output: [1,5,2,4,3]
 */


public class ReorderList {
	
	// Using recursion :  create two pointer left and right 
	//	if we can move left-node-to-forward and right-node-to-backward
	//	TC: O(n), SC: O(n)
	
	public static void reorderList(ListNode head) {
		ListNode[] left = new ListNode[1];
		left[0] = head;
		reorder(left, head);
    }

	private static void reorder(ListNode[] left, ListNode right) {
		if(right == null)
			return;
		
		reorder(left, right.next);
		
		if(left[0].next != null) {
			ListNode leftNext = left[0].next;
			left[0].next = right;
			right.next = leftNext;
			left[0] = leftNext; 
		}
		
		if(left[0].next == right)
			left[0].next = null;
	}
	
	//	Line 44: using a recursive way, reach to right node - that time head is still pointing to left node
	//	Line 47-50: rearrange the pointers
	//	Line 53: check if swapping is done, left node should not cross to right node
	//				if it is crossing then it means that we have reached the end of list
	
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		print(head);	//	1,2,3,4
		
		System.out.println();
		reorderList(head);
		print(head);	//	1,4,2,3
		System.out.println();
		
		head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		System.out.println();
		print(head);	//	1,2,3,4
		
		System.out.println();
		reorderList2(head);
		print(head);	//	1,4,2,3
	}
	
	private static void print(ListNode head) {
		while(head!=null) {
			System.out.print(" "+head.data);
			head = head.next;
		}
	}
	
	//	Method 2 : Split linked list, TC = O(n), SC = O(1)
	/*
	 * split linkedlist into two halves
			-	find mid and split
					and reverse second half
			-	merge these two halves
	 */
	
	public static void reorderList2(ListNode head) {
        
        ListNode midNode =  midNode(head);
        ListNode nextToMid =  midNode.next;
        midNode.next = null;	// this is for the last node 
        
        ListNode p2 =  reverse(nextToMid);
        ListNode p1 =  head;
        
        ListNode p1Next;
        
        while(p1!=null && p2!=null){
            p1Next = p1.next;                                    
			p1.next = p2;
			           
            p1 = p2;		//	interchanging p1 & p2 with each other 
            p2 = p1Next;                        
        }                
    }

	private static ListNode reverse(ListNode head) {
		ListNode curr =  head, prev=  null, next = null;
        while(curr!=null){
            next  =  curr.next;
            curr.next =  prev ;
            prev =  curr;
            curr  =  next;
        }
        return prev;
	}

	private static ListNode midNode(ListNode head) {
		ListNode fast =  head, slow  =  head;
        while(fast.next!=null && fast.next.next!=null){
            fast =  fast.next.next;
            slow =  slow.next;
        }
        return slow;
	}     
	
}
