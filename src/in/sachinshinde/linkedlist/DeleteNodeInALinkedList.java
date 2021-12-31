package in.sachinshinde.linkedlist;

//	https://leetcode.com/problems/delete-node-in-a-linked-list/

/*
 * 	TASK   :: Write a function to delete a node in a singly-linked list. 
 *  GIVEN  :: You will not be given access to the head of the list, 
 *            instead you will be given access to the node to be deleted directly.
 * 
	Constraints:-
		The number of the nodes in the given list is in the range [2, 1000].
		The value of each node in the list is unique.
T		The node to be deleted is in the list and is not a tail node

 */


public class DeleteNodeInALinkedList {
	 
	public void deleteNode(ListNode node) {
		node.data = node.next.data;
	    node.next = node.next.next;
	}
}
