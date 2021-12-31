/*
 * 		https://www.geeksforgeeks.org/add-two-numbers-represented-by-linked-lists/
 */

package in.sachinshinde.linkedlist;

public class SumOfTwoLinkedList {

	static ListNode addTwoLinkedLists(ListNode first, ListNode second) {
		
		ListNode resultLinkedList = null;
		ListNode lastNodeOfCurrentSum = null;
		ListNode nextSumNode = null;
		
		int sum = 0, carry = 0;
				
		while(first != null || second != null) {
			
			sum = carry + (first != null ? first.data: 0) + (second != null ? second.data : 0);
			
			carry = (sum >= 10) ? 1 : 0;
			
			sum = sum % 10;
			
			nextSumNode = new ListNode(sum);
			
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
			lastNodeOfCurrentSum.next = new ListNode(carry);
						
		return resultLinkedList;
		
	}
	
	
	public static void main(String[] args) {
		
		ListNode LL_1_Node = new ListNode(7); 
		LL_1_Node.next = new ListNode(5); 
		LL_1_Node.next.next = new ListNode(9); 
		LL_1_Node.next.next.next = new ListNode(4); 
		LL_1_Node.next.next.next.next = new ListNode(6); 
		
        System.out.print("First List is "); 
        printList(LL_1_Node); 
  

        ListNode LL_2_Node = new ListNode(8); 
        LL_2_Node.next = new ListNode(4); 
        
        System.out.print("Second List is "); 
        printList(LL_2_Node); 
   
        ListNode resultList = addTwoLinkedLists(LL_1_Node, LL_2_Node);
        
        System.out.print("Resultant List is "); 
        printList(resultList); 
        
        resultList = addTwoNumberOfLinkedList(LL_1_Node, LL_2_Node);
        
        System.out.print("Resultant List is "); 
        printList(resultList); 
        
        resultList = addTwoNumbers(LL_1_Node, LL_2_Node);
        
        System.out.print("Resultant List is "); 
        printList(resultList); 
	}


	private static void printList(ListNode LL_Node) {
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
	
	private static ListNode addTwoNumberOfLinkedList(ListNode l1, ListNode l2) {
		ListNode c1 = l1;
		ListNode c2 = l2;
        
		ListNode sentinel = new ListNode(0);
		ListNode d = sentinel;
        
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
            d.next = new ListNode(sum % 10);
            d = d.next;
        }
        
        if (sum / 10 == 1)
            d.next = new ListNode(1);
        
        return sentinel.next;
	}
	
	private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode resultNode = new ListNode(0);
        ListNode currSumNode = resultNode;
        while(!(l1 == null && l2 == null && carry == 0)){
            int sum = (l1 != null ? l1.data : 0) + (l2 != null ? l2.data : 0) + carry;
            carry = sum / 10;
            ListNode newSumNode = new ListNode(sum % 10);
            currSumNode.next = newSumNode;
            currSumNode = newSumNode;
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }
        return resultNode.next;
    }
} 
