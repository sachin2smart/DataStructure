package in.sachinshinde.linkedlist;

//	https://leetcode.com/problems/add-two-numbers/

/*
 *	You are given two non-empty linked lists representing two non-negative integers. 
 *  The digits are stored in reverse order, and each of their nodes contains a single digit. 
 *  Add the two numbers and return the sum as a linked list.
 *  You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 *
 *      Example 1:


        Input: l1 = [2,4,3], l2 = [5,6,4]
        Output: [7,0,8]
        Explanation: 342 + 465 = 807.

        * Example 2:

        Input: l1 = [0], l2 = [0]
        Output: [0]

        * Example 3:

        Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
        Output: [8,9,9,9,0,0,0,1]


        * Constraints:

            The number of nodes in each linked list is in the range [1, 100].
            0 <= Node.val <= 9
            It is guaranteed that the list represents a number that does not have leading zeros.
 */

public class AddTwoNumbersRepresentedByALinkedList {
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

    public ListNode addTwoNumbers_2(ListNode l1, ListNode l2) {
        ListNode currNode = new ListNode();
        ListNode res = currNode;
        int total, carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            total = carry;

            if (l1 != null) {
                total += l1.data;
                l1 = l1.next;
            }
            if (l2 != null) {
                total += l2.data;
                l2 = l2.next;
            }

            int num = total % 10;
            carry = total / 10;

            currNode.next = new ListNode(num);
            currNode = currNode.next;
        }

        return res.next;
    }

    public static void main(String[] args) {
        AddTwoNumbersRepresentedByALinkedList addNums = new AddTwoNumbersRepresentedByALinkedList();
        //---------------------------------------------------
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode lResult = addNums.addTwoNumbers(l1, l2);
        addNums.print(lResult); //  7 -> 0 -> 8

        System.out.println();

        lResult = addNums.addTwoNumbers_2(l1, l2);
        addNums.print(lResult);     //  7 -> 0 -> 8

        //---------------------------------------------------
        l1 = new ListNode(0);
        l2 = new ListNode(0);

        System.out.println();

        lResult = addNums.addTwoNumbers(l1, l2);
        addNums.print(lResult);     //  0

        System.out.println();

        lResult = addNums.addTwoNumbers_2(l1, l2);
        addNums.print(lResult);     //  0
        //---------------------------------------------------
        l1 = new ListNode(9);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);
        l1.next.next.next = new ListNode(9);
        l1.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next = new ListNode(9);

        l2 = new ListNode(9);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(9);
        l2.next.next.next = new ListNode(9);

        System.out.println();

        lResult = addNums.addTwoNumbers(l1, l2);
        addNums.print(lResult);     //  8 -> 9 -> 9 -> 9 -> 0 -> 0 -> 1

        System.out.println();

        lResult = addNums.addTwoNumbers_2(l1, l2);
        addNums.print(lResult);     //  8 -> 9 -> 9 -> 9 -> 0 -> 0 -> 1
        //---------------------------------------------------
    }

    private void print(ListNode l) {
        while(l != null) {
            if(l.next != null) {
                System.out.print(l.data + " -> ");
            }
            else {
                System.out.print(l.data);
            }
            l = l.next;
        }
    }
}
