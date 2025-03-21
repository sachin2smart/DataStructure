package in.sachinshinde.linkedlist;

//	https://leetcode.com/problems/merge-two-sorted-lists/

/*
		You are given the heads of two sorted linked lists list1 and list2.

		Merge the two lists into one sorted list.
		The list should be made by splicing together the nodes of the first two lists.

		Return the head of the merged linked list.


		Example 1:
		---------
		Input: list1 = [1,2,4], list2 = [1,3,4]
		Output: [1,1,2,3,4,4]

		Example 2:
		---------
		Input: list1 = [], list2 = []
		Output: []

		Example 3:
		---------
		Input: list1 = [], list2 = [0]
		Output: [0]

		Constraints:
		-----------
			The number of nodes in both lists is in the range [0, 50].
			-100 <= Node.val <= 100
			Both list1 and list2 are sorted in non-decreasing order.
 */
public class MergeTwoSortedLists {

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if(l1 == null)
			return l2;
		
		if(l2 == null)
			return l1;
		
		if(l1.data < l2.data) {
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		}
		else {
			l2.next = mergeTwoLists(l2.next, l1);
			return l2;
		}
	}

	public static void main(String[] args) {
		MergeTwoSortedLists mergeTwoSortedLists = new MergeTwoSortedLists();
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(4);

		ListNode l2 = new ListNode(1);
		l2.next = new ListNode(3);
		l2.next.next = new ListNode(4);

		ListNode resultNode = mergeTwoSortedLists.mergeTwoLists(l1, l2);
		mergeTwoSortedLists.print(resultNode);	//	1 -> 1 -> 2 -> 3 -> 4 -> 4

		l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(4);

		l2 = new ListNode(1);
		l2.next = new ListNode(3);
		l2.next.next = new ListNode(4);
		resultNode = mergeTwoSortedLists.mergeTwoLists2(l1, l2);
		mergeTwoSortedLists.print(resultNode);	//	1 -> 1 -> 2 -> 3 -> 4 -> 4

		l1 = null;
		l2 = null;
		resultNode = mergeTwoSortedLists.mergeTwoLists(l1, l2);
		mergeTwoSortedLists.print(resultNode);	//	[]
		resultNode = mergeTwoSortedLists.mergeTwoLists2(l1, l2);
		mergeTwoSortedLists.print(resultNode);	//	[]

		l1 = null;
		l2 = new ListNode(0);
		resultNode = mergeTwoSortedLists.mergeTwoLists(l1, l2);
		mergeTwoSortedLists.print(resultNode);	//	0
		resultNode = mergeTwoSortedLists.mergeTwoLists2(l1, l2);
		mergeTwoSortedLists.print(resultNode);	//	0
	}

	public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
		if(list1 != null && list2 != null) {
			if(list1.data < list2.data) {
				list1.next = mergeTwoLists2(list1.next, list2);
				return list1;
			}
			else {
				list2.next = mergeTwoLists2(list1, list2.next);
				return list2;
			}
		}

		if(list1 == null) {
			return list2;
		}

		return list1;
	}

	public void print(ListNode node) {
		while(node != null) {
			if(node.next != null) {
				System.out.print(node.data + " -> ");
			}
			else {
				System.out.print(node.data);
			}
			node = node.next;
		}
		System.out.println();
	}
}
