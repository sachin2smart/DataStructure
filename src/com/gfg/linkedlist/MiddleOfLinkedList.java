/*
*		https://www.geeksforgeeks.org/write-a-c-function-to-print-the-middle-of-the-linked-list/
*/

package com.gfg.linkedlist;

public class MiddleOfLinkedList {
	LinkedListNode head;
	
	public void push(int data) {
		LinkedListNode newNode = new LinkedListNode(data);
		newNode.next = head;
		head = newNode;
	}
	
	public void printLinkedList() {
		LinkedListNode tNode = head;
		System.out.println();
		while(tNode != null) {
			System.out.print(" "+tNode.data);
			tNode = tNode.next;
		}
		System.out.println();
	}
	
	public void printMiddle() {
		LinkedListNode slow_ptr = head;
		LinkedListNode fast_ptr = head;
		
		if(head != null) {
			while(fast_ptr != null && fast_ptr.next != null) {
				slow_ptr = slow_ptr.next;
				fast_ptr = fast_ptr.next.next;
			}
			System.out.println("Middle element is : "+slow_ptr.data);
		}
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
