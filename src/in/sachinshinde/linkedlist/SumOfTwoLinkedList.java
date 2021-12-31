/*
 * 		https://www.geeksforgeeks.org/add-two-numbers-represented-by-linked-lists/
 */

package in.sachinshinde.linkedlist;

public class SumOfTwoLinkedList {

	static LinkedListNode addTwoLinkedLists(LinkedListNode first, LinkedListNode second) {
		
		LinkedListNode resultLinkedList = null;
		LinkedListNode lastNodeOfCurrentSum = null;
		LinkedListNode nextSumNode = null;
		
		int sum = 0, carry = 0;
				
		while(first != null || second != null) {
			
			sum = carry + (first != null ? first.data: 0) + (second != null ? second.data : 0);
			
			carry = (sum >= 10) ? 1 : 0;
			
			sum = sum % 10;
			
			nextSumNode = new LinkedListNode(sum);
			
			if(resultLinkedList == null)
				resultLinkedList = nextSumNode;
			else
				lastNodeOfCurrentSum.next = nextSumNode;
			
			lastNodeOfCurrentSum = nextSumNode;
			
			if(first != null)
				first = first.next;
			
			if(second != null)
				second = second.next;
			
		}
		
		if(carry > 0)
			lastNodeOfCurrentSum.next = new LinkedListNode(carry);
						
		return resultLinkedList;
		
	}
	
	
	public static void main(String[] args) {
		
		LinkedListNode LL_1_Node = new LinkedListNode(7); 
		LL_1_Node.next = new LinkedListNode(5); 
		LL_1_Node.next.next = new LinkedListNode(9); 
		LL_1_Node.next.next.next = new LinkedListNode(4); 
		LL_1_Node.next.next.next.next = new LinkedListNode(6); 
		
        System.out.print("First List is "); 
        printList(LL_1_Node); 
  

        LinkedListNode LL_2_Node = new LinkedListNode(8); 
        LL_2_Node.next = new LinkedListNode(4); 
        
        System.out.print("Second List is "); 
        printList(LL_2_Node); 
   
        LinkedListNode resultList = addTwoLinkedLists(LL_1_Node, LL_2_Node);
        
        System.out.print("Resultant List is "); 
        printList(resultList); 
        
        resultList = addTwoNumberOfLinkedList(LL_1_Node, LL_2_Node);
        
        System.out.print("Resultant List is "); 
        printList(resultList); 
        
        resultList = addTwoNumbers(LL_1_Node, LL_2_Node);
        
        System.out.print("Resultant List is "); 
        printList(resultList); 
	}


	private static void printList(LinkedListNode LL_Node) {
		while (LL_Node != null) { 
            System.out.print(LL_Node.data + " "); 
            LL_Node = LL_Node.next; 
        } 
        System.out.println("");
	}

	//	https://leetcode.com/problems/add-two-numbers/
	
	/*
	 * 	GIVEN :: You are given two non-empty linked lists representing two non-negative integers. 
	 * 	         The digits are stored in reverse order, and each of their nodes contains a single digit. 
	 * 	TASK ::  Add the two numbers and return the sum as a linked list.
	 * 	NOTE ::  You may assume the two numbers do not contain any leading zero, except the number 0 itself.
	 */
	
	private static LinkedListNode addTwoNumberOfLinkedList(LinkedListNode l1, LinkedListNode l2) {
		LinkedListNode c1 = l1;
		LinkedListNode c2 = l2;
        
		LinkedListNode sentinel = new LinkedListNode(0);
		LinkedListNode d = sentinel;
        
        int sum = 0;
        
        while (c1 != null || c2 != null) {
            sum /= 10;
            if (c1 != null) {
                sum += c1.data;
                c1 = c1.next;
            }
            if (c2 != null) {
                sum += c2.data;
                c2 = c2.next;
            }
            d.next = new LinkedListNode(sum % 10);
            d = d.next;
        }
        
        if (sum / 10 == 1)
            d.next = new LinkedListNode(1);
        
        return sentinel.next;
	}
	
	private static LinkedListNode addTwoNumbers(LinkedListNode l1, LinkedListNode l2) {
        int carry = 0;
        LinkedListNode resultNode = new LinkedListNode(0);
        LinkedListNode currSumNode = resultNode;
        while(!(l1 == null && l2 == null && carry == 0)){
            int sum = (l1 != null ? l1.data : 0) + (l2 != null ? l2.data : 0) + carry;
            carry = sum / 10;
            LinkedListNode newSumNode = new LinkedListNode(sum % 10);
            currSumNode.next = newSumNode;
            currSumNode = newSumNode;
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }
        return resultNode.next;
    }
} 
