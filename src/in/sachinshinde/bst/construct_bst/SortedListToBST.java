package in.sachinshinde.bst.construct_bst;

//	https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/

/*
 *	Given the head of a singly linked list where elements are sorted in ascending order,
 *	Convert that list to a height balanced BST.

	## Height-balanced binary tree is defined as 
		A binary tree in which the depth of the two subtrees of every node never differ by more than 1. 
 */

import in.sachinshinde.bst.Node;

public class SortedListToBST {
	
	public Node sortedListToBST(ListNode head) {
        if(head==null) 
            return null;
        
        return toBST(head,null);
    }
    
    private Node toBST(ListNode head, ListNode tail){
        //  terminate the process when head reaches to tail
        if(head==tail) 
            return null;

        ListNode slow = head;
        ListNode fast = head;
        
        // Get the mid node 
        // use slow-fast pointer mechanism 
        while(fast!=tail && fast.next!=tail) { // fast either reaches to tail-node, or prev to tail node  
            fast = fast.next.next;
            slow = slow.next;
        }
        
        // Node, slow is at mid (head----slow----tail)
        Node thead = new Node(slow.data);
        thead.left = toBST(head,slow);
        thead.right = toBST(slow.next,tail);
        
        return thead;
    }
    
}
