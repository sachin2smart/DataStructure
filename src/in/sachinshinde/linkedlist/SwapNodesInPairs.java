package in.sachinshinde.linkedlist;

//	https://leetcode.com/problems/swap-nodes-in-pairs/
//	Swap Nodes in Pairs

/*
 * 	Given a linked list, swap every two adjacent nodes and return its head. 
 * 	You must solve the problem without modifying the values in the list's nodes 
 * 		(i.e., only nodes themselves may be changed.)
 */

public class SwapNodesInPairs {
	
	public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode n = head.next;
        head.next = swapPairs(head.next.next);
        n.next = head;
        return n;
    }
	
}
