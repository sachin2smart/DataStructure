package in.sachinshinde.linkedlist;

//	https://leetcode.com/problems/merge-two-sorted-lists/

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
}
