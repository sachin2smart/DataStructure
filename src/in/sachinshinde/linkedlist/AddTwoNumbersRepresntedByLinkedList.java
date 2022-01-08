package in.sachinshinde.linkedlist;

//	https://leetcode.com/problems/add-two-numbers/

/*
 *	You are given two non-empty linked lists representing two non-negative integers. 
 *  The digits are stored in reverse order, and each of their nodes contains a single digit. 
 *  Add the two numbers and return the sum as a linked list.
 *  You may assume the two numbers do not contain any leading zero, except the number 0 itself. 
 */

public class AddTwoNumbersRepresntedByLinkedList {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode resultNode = new ListNode(0);
        ListNode currSumNode = resultNode;
        while(!(l1 == null && l2 == null && carry == 0)){
            int sum = (l1 != null ? l1.data : 0) + (l2 != null ? l2.data : 0) + carry;
            carry = sum / 10;
            ListNode newSumNode = new ListNode(sum % 10);
            currSumNode.next = newSumNode;
            currSumNode = newSumNode;
            if(l1 != null) 
                l1 = l1.next;
            if(l2 != null) 
                l2 = l2.next;
        }
        return resultNode.next;
    }
}
