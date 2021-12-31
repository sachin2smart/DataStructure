package in.sachinshinde.linkedlist;

//	https://leetcode.com/problems/intersection-of-two-linked-lists/

/*
 * 	GIVEN:: The heads of two singly linked-lists headA and headB, 
 * 	TASK :: Return the node at which the two lists intersect. 
 * 	CONSTRAINT:: If the two linked lists have no intersection at all, return null.
 */

public class IntersectionOfTwoLinkedList {

	private ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if(headA == null || headB == null) return null;
	    ListNode a = headA;
	    ListNode b = headB;
	    while( a != b){
	        a = a == null? headB : a.next;
	        b = b == null? headA : b.next;    
	    }
	    return a;
	}
	
}


/*
 * 
 *	headA = [a1, a2, c1* ,c2, c3] 
 *	headB = [b1, b2, b3, c1* ,c2, c3] 
 * 
 *
 * (1)	a =	a1
 * 		b = b1
 * 
 * (2)	a =	a2
 * 		b = b2
 * 
 * (3)	a =	c1
 * 		b = b3
 * 
 * (4)	a =	c2
 * 		b = c1
 * 
 * (5)	a =	c3
 * 		b = c2
 * 
 * (6)	a =	b1
 * 		b = c3
 * 
 * (7)	a =	b2
 * 		b = a1
 * 
 * (8)	a =	b3
 * 		b = a2
 * 
 * (9)	a =	c1
 * 		b = c1
 * 
 * 
 */