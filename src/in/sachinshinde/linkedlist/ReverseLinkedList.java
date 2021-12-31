package in.sachinshinde.linkedlist;

public class ReverseLinkedList {
	
	public ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        
        while(head != null){
            ListNode nextNode = head.next;
            head.next = newHead;
            newHead = head;
            head = nextNode;
        }
        
        return newHead;
        
    }
	
	public ListNode reverserListRecursively(ListNode head) {
		 return reverseListIterative(head, null);
	}
    
     private ListNode reverseListIterative(ListNode head, ListNode prevNode) {
         if(head == null)
             return prevNode;
        
         ListNode nextNode = head.next;
         head.next = prevNode;
        
         return reverseListIterative(nextNode, head);
     }
	
}
