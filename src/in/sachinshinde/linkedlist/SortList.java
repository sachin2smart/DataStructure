package in.sachinshinde.linkedlist;

import java.util.PriorityQueue;

//	https://leetcode.com/problems/sort-list/

/*
 	Given the head of a linked list, return the list after sorting it in ascending order.

        Example 1:        

        Input: head = [4,2,1,3]
        Output: [1,2,3,4]
        Example 2:
        
        
        Input: head = [-1,5,3,4,0]
        Output: [-1,0,3,4,5]
        Example 3:
        
        Input: head = []
        Output: []
         
        
        Constraints:
        
        The number of nodes in the list is in the range [0, 5 * 104].
        -105 <= Node.val <= 105
         
        
        Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 */

public class SortList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        ListNode temp = head;
        while (temp.next != null) {
            queue.add(temp.data);
            temp = temp.next;
        }
        queue.add(temp.data);
        temp = head;
        while (!queue.isEmpty()) {
            temp.data = queue.poll();
            temp = temp.next;
        }
        return head;
    }
    
    public ListNode getMid(ListNode head) {
        ListNode slow=head, fast=head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    
    public ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (left != null && right != null) {
            if(left.data < right.data) {
                tail.next = left;
                left = left.next;
            }
            else {
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }
        
        if(left != null) {
            tail.next = left;
        }
        
        if(right != null) {
            tail.next = right;
        }
        
        return dummy.next;
    }
    
    public ListNode sortList2(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode left = head;
        ListNode right = getMid(head);
        ListNode tmp = right.next;
        right.next = null;
        right = tmp;

        left = sortList(left);
        right = sortList(right);
        return merge(left, right);        
    }
    
    public ListNode sortList3(ListNode head) {
        return sortList(head, null);
    }
    
    private ListNode sortList(ListNode start, ListNode end) {
        if (start == null || start.next == null || start == end) {
            return start;
        }
        
        int pivot = start.data;
        ListNode left = start;
        ListNode right = start;
        ListNode curr = start.next;
        boolean sorted = true;
        
        while (curr != null && curr != end) {
            ListNode temp = curr.next;
            if (curr.data < pivot) {
                sorted = false;
                curr.next = left;
                left = curr;
                right.next = temp;
            } 
            else if (curr.data < right.data) {
                sorted = false;
                right = curr;
            }
            else
                right = curr;
            
            curr = temp;
        }
        if (sorted)
            return start;
        
        start.next = sortList(start.next, end);
        left = sortList(left, start);
        return left;
    }
    
    public void printList(ListNode head) {
        System.out.println();
        while(head.next != null) {
            System.out.print(" "+ head.data + " -> ");
            head = head.next;
        }
         System.out.print(" -> "+ head.data);
    }
    
    public static void main(String[] args) {
        SortList sortList = new SortList();
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        sortList.printList(head);	//	4 ->  2 ->  1 ->  -> 3
        head = sortList.sortList(head);
        sortList.printList(head);	//	 1 ->  2 ->  3 ->  -> 4

        head = new ListNode(-1);
        head.next = new ListNode(5);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(0);

        sortList.printList(head);		//	 -1 ->  5 ->  3 ->  4 ->  -> 0
        head = sortList.sortList(head);
        sortList.printList(head);		//	 -1 ->  0 ->  3 ->  4 ->  -> 5
    }
}