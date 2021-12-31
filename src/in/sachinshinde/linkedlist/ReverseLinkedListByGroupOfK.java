package in.sachinshinde.linkedlist;

//	https://leetcode.com/problems/reverse-nodes-in-k-group/

/*
 *		Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
 *		k is a positive integer and is less than or equal to the length of the linked list. 
 *		If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it. 
 * 
 */


// 	https://leetcode.com/submissions/detail/610502088/

public class ReverseLinkedListByGroupOfK {

	//	Method 1 - Recursive
	private static ListNode reverserListRecursivelyByGropOfK(ListNode head, int k) {
		ListNode curr = head;
	    int count = 0;
	    
	    while (curr != null && count != k) {
	        curr = curr.next;
	        count++;
	    }
	    
	    if (count == k) { 
	        curr = reverserListRecursivelyByGropOfK(curr, k);
	        
	        while (count-- > 0) { 
	            ListNode tmp = head.next; 
	            head.next = curr;  
	            curr = head; 
	            head = tmp; 
	        }
	        
	        head = curr;
	    }
	    return head;
    }
    
	//	Method 2 - Recursive
	private static ListNode reverseKGroup(ListNode head, int k) { 
	    ListNode node = head;
	    int count = 0;
	    
	    while (count < k) { 
	        if(node == null)
	        	return head;
	        node = node.next;
	        count++;
	    }
	    
        ListNode pre = reverseKGroup(node, k); 
        
        while (count > 0) {  
            ListNode next = head.next; 
            head.next = pre; 
            pre = head; 
            head = next;
            count = count - 1;
        }
        
        return pre;
	}
	
	//	Method 3 - Iterative
	private static ListNode reverseKGroupIterative(ListNode head, int k) {
        if(head == null || k == 1) 
        	return head;
        
        ListNode dummy = new ListNode(0);
        
        dummy.next = head;
        ListNode curr = dummy , next = dummy, prev = dummy;
        
        int count = 0;
        
        while(curr.next != null){
            curr = curr.next;
            count++;
        }
        
        while(count >= k){
            curr = prev.next;
            next = curr.next;
            for(int i = 1; i < k; i++){
                curr.next = next.next;
                next.next = prev.next;
                prev.next = next;
                next = curr.next;
            }
            prev = curr;
            count-=k;
        }
        return dummy.next;
    }
	
    private static ListNode head;
    
    private static void push(int new_data)
    {
    	ListNode new_node = new ListNode(new_data);
        new_node.next = head;
        head = new_node;
    }
    
    private static void printList(ListNode head)
    {
        ListNode tnode = head;
        while (tnode != null) {
            System.out.print(tnode.data + " ");
            tnode = tnode.next;
        }
    }
    
    public static void main(String[] args) {
    	
    	push(7);
        push(6);
        
        push(5);
        push(4);
        
        push(3);
        push(2);
        
        push(1);
        
        System.out.println("List before reverse operation : ");
        printList(head);
        
        ListNode lnReversed;
        
//        lnReversed = reverserListRecursivelyByGropOfK(head, 2);
//        System.out.println("\nList after reverse operation : " + "Group of 2");
//        printList(lnReversed);
    
//        lnReversed = reverseKGroup(head, 2);
//        System.out.println("\nList after reverse operation : " + "Group of 2");
//        printList(lnReversed);
        
        lnReversed = reverseKGroupIterative(head, 2);
        System.out.println("\nList after reverse operation : " + "Group of 2");
        printList(lnReversed);
        
    }
}
