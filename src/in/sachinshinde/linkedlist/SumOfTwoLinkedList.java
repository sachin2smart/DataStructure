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
	}


	private static void printList(LinkedListNode LL_Node) {
		while (LL_Node != null) { 
            System.out.print(LL_Node.data + " "); 
            LL_Node = LL_Node.next; 
        } 
        System.out.println("");
	}

}
