package in.sachinshinde.design;

//	https://leetcode.com/problems/design-linked-list/

/*
 	Design your implementation of the linked list. 
 	You can choose to use a singly or doubly linked list.
	A node in a singly linked list should have two attributes: val and next. 
		val is the value of the current node, and 
		next is a pointer/reference to the next node.
	If you want to use the doubly linked list, 
		you will need one more attribute prev to indicate the previous node in the linked list. 
		Assume all nodes in the linked list are 0-indexed.

	Implement the MyLinkedList class:

	- MyLinkedList() 
		Initializes the MyLinkedList object.
	- int get(int index) 
		Get the value of the indexth node in the linked list. If the index is invalid, return -1.
	- void addAtHead(int val) 
		Add a node of value val before the first element of the linked list. 
		After the insertion, the new node will be the first node of the linked list.
	- void addAtTail(int val) 
		Append a node of value val as the last element of the linked list.
	- void addAtIndex(int index, int val) 
		Add a node of value val before the indexth node in the linked list. 
		If index equals the length of the linked list, 
			the node will be appended to the end of the linked list. 
		If index is greater than the length, 
			the node will not be inserted.
	- void deleteAtIndex(int index) 
		Delete the indexth node in the linked list, if the index is valid.
 

	Example 1:
	
	Input
	["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
	[[], [1], [3], [1, 2], [1], [1], [1]]
	Output
	[null, null, null, null, 2, null, 3]
	
	Explanation
	MyLinkedList myLinkedList = new MyLinkedList();
	myLinkedList.addAtHead(1);
	myLinkedList.addAtTail(3);
	myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
	myLinkedList.get(1);              // return 2
	myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
	myLinkedList.get(1);              // return 3

 */

public class DesignLinkedList {
	class Node {
        int key;
        Node next;
        
        public Node(int val) {
            this.key = val;
        }
    }
	
	private Node head;
	private int size;
	
	public DesignLinkedList() {
        
    }
	
    public int get(int index) {
        if(index >= size)
        	return -1;
        Node current = head;
        for(int i = 0; i < index; i++)
            current = current.next;
        
        if(current != null)
            return current.key;
        else
            return -1;
    }

    public void addAtHead(int val) {
    	Node prev = head;
    	head = new Node(val);
        head.next = prev;
        size++;
    }
    
    public void addAtTail(int val) {
        Node node = new Node(val);
        size++;
        
        if(head == null)
            head = node; 
        else {
            Node current = head;
            while (current.next != null)
                current = current.next;
            
            current.next = node;
        }
    }
    
    public void addAtIndex(int index, int val) {
        if(index > size) 
        	return;
        
        if(index == 0)
            addAtHead(val);
        else {
            size++;
            Node current = head;
            for(int i = 0; i < index - 1; i++)
                current = current.next;
            
            Node node = new Node(val);
            node.next = current.next;
            current.next = node;
        }
    }
    
    public void deleteAtIndex(int index) {
        if(index == 0) {
    		head = head.next;
            return;
        }
        
        if(index >= size) 
        	return;
        
        size--;
        Node current = head;
        for(int i = 0; i < index - 1; i++)
            current = current.next;
                
        current.next = current.next.next;
    }
    
    public static void main(String[] args) {
    	DesignLinkedList ll = new DesignLinkedList();
    	ll.addAtHead(1);
    	ll.addAtTail(3);
    	ll.addAtIndex(1,2);
    	System.out.println(ll.get(1));	//	2
    	ll.deleteAtIndex(1);
    	System.out.println(ll.get(1));	//	3
    	System.out.println(ll.get(3));	//	-1
    	ll.deleteAtIndex(3);
    	ll.deleteAtIndex(0);
    	System.out.println(ll.get(0));	//	3
    	ll.deleteAtIndex(0);
    	System.out.println(ll.get(0));	//	-1
	}
}