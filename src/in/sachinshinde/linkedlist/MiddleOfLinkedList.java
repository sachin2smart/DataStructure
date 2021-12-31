/*
*		https://www.geeksforgeeks.org/write-a-c-function-to-print-the-middle-of-the-linked-list/
*/

package in.sachinshinde.linkedlist;

public class MiddleOfLinkedList {
	ListNode head;
	
	public void push(int data) {
		ListNode newNode = new ListNode(data);
		newNode.next = head;
		head = newNode;
	}
	
	public void printLinkedList() {
		ListNode tNode = head;
		System.out.println();
		while(tNode != null) {
			System.out.print(" "+tNode.data);
			tNode = tNode.next;
		}
		System.out.println();
	}
	
	public void printMiddle() {
		ListNode slow_ptr = head;
		ListNode fast_ptr = head;
		
		if(head != null) {
			while(fast_ptr != null && fast_ptr.next != null) {
				slow_ptr = slow_ptr.next;
				fast_ptr = fast_ptr.next.next;
			}
			System.out.println("Middle element is : "+slow_ptr.data);
		}
	}
	
	//	https://leetcode.com/problems/middle-of-the-linked-list/
	
	public ListNode middleNode(ListNode head) {
		ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
	
	public static void main(String args[]) {
		MiddleOfLinkedList llist = new MiddleOfLinkedList(); 
        for (int i=5; i>0; --i) 
        { 
            llist.push(i); 
            llist.printLinkedList(); 
            llist.printMiddle(); 
        } 
	}
}
