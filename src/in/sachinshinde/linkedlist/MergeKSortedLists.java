package in.sachinshinde.linkedlist;

import java.util.PriorityQueue;

//	https://leetcode.com/problems/merge-k-sorted-lists/
//	Video Link: https://youtu.be/kpCesr9VXDA

/*
  	You are given an array of k linked-lists lists, 
  	each linked-list is sorted in ascending order.
	
	Merge all the linked-lists into one sorted linked-list 
	and return it.
*/

/*
    Example 1:
    ---------
    Input: lists = [[1,4,5],[1,3,4],[2,6]]
    Output: [1,1,2,3,4,4,5,6]
    Explanation: The linked-lists are:
                    [
                      1->4->5,
                      1->3->4,
                      2->6
                    ]
                  merging them into one sorted list:
                    1->1->2->3->4->4->5->6
    
    Example 2:
    ---------
    Input: lists = []
    Output: []
    
    Example 3:
    ---------
    Input: lists = [[]]
    Output: []	
 */

public class MergeKSortedLists {
    private class ListNode {
	int val;
	ListNode next;
	
	ListNode() {
	    
	}
	
	ListNode(int val) {
	    this.val = val; 
	}
	
	ListNode(int val, ListNode next) { 
	    this.val = val; 
	    this.next = next; 
	}
    }
    
    public static void main(String[] args) {
	MergeKSortedLists lists = new MergeKSortedLists();
	//	[[1,4,5],[1,3,4],[2,6]]
	ListNode L2 = lists.new ListNode(5);
	ListNode L1 = lists.new ListNode(4,L2);
	ListNode L1Head = lists.new ListNode(1, L1);

	ListNode L22 = lists.new ListNode(4);
	ListNode L12 = lists.new ListNode(3,L22);
	ListNode L12Head = lists.new ListNode(1, L12);
	
	ListNode L13 = lists.new ListNode(6);
	ListNode L13Head = lists.new ListNode(2, L13);

	ListNode resultHead = lists.mergeKLists(new ListNode[]{L1Head, L12Head, L13Head});
	lists.printListNode(resultHead);
	System.out.println();
	
	ListNode resultHead12 = lists.mergeKLists2(new ListNode[]{L1Head, L12Head, L13Head});
	lists.printListNode(resultHead12);
	
	// []
	ListNode resultHead2 = lists.mergeKLists(new ListNode[]{});
	lists.printListNode(resultHead2);
	
	// [[]]
	ListNode L14Head = lists.new ListNode();
	ListNode resultHead3 = lists.mergeKLists(new ListNode[]{L14Head});
	lists.printListNode(resultHead3);
    }
    
    private void printListNode(ListNode head) {
	while(head != null) {
	    System.out.print(head.val + " -> ");
	    head = head.next;
	}
    }
    
    //	Method 1: Using PriorityQueue
   
    public ListNode mergeKLists(ListNode[] lists) {
	if(lists == null || lists.length == 0)
	    return null;
	
	PriorityQueue<ListNode> q = new PriorityQueue<ListNode>(
		lists.length, (a,b) -> a.val - b.val);
	
	ListNode head = new ListNode(0);
	ListNode tail = head;
	
	for(ListNode node: lists)
	    if(node != null)
		q.add(node);
	
	while(!q.isEmpty()) {
	    tail.next = q.poll();
	    tail = tail.next;
	    
	    if(tail.next != null)
		q.add(tail.next);
	}
	
	return head.next;
    }
    
    // Method 2: Divide and Conquer
    
    public ListNode mergeKLists2(ListNode[] lists) {
        if(lists == null || lists.length == 0)
            return null;
        return kMerge(lists, 0, lists.length - 1);
    }
    
    private ListNode kMerge(ListNode[]lists,int start,int end) {
        if(start == end)
            return lists[start];
        
        int mid = start + (end - start) / 2;
        ListNode l1 =  kMerge(lists, start, mid);
        ListNode l2 =  kMerge(lists, mid + 1, end);
        
        return mergeTwoLists(l1,l2);
    }
    
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null)
            return l1 != null ? l1 : l2;
        
        ListNode c1 = l1;
        ListNode c2 = l2;
        ListNode head = new ListNode(-1);
        ListNode prev = head;
        
        while(c1 != null & c2 != null) {
            if(c1.val < c2.val) {
                prev.next = c1;
                c1 = c1.next;
            }
            else {
                prev.next = c2;
                c2 = c2.next;
            }
            prev = prev.next;
        }
        
        prev.next = c1 != null ? c1 : c2;
        
        return head.next;
    }
    
}