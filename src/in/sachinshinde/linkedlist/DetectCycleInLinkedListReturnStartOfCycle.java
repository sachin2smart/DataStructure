package in.sachinshinde.linkedlist;

//	https://leetcode.com/problems/linked-list-cycle-ii/

/*
 *	Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *	There is a cycle in a linked list if there is some node in the list that can be reached again 
 *	by continuously following the next pointer. 
 *	Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). 
 *	It is -1 if there is no cycle. Note that pos is not passed as a parameter.

	Do not modify the linked list. 
 */

//	https://leetcode.com/problems/linked-list-cycle-ii/discuss/44774/Java-O(1)-space-solution-with-detailed-explanation.

public class DetectCycleInLinkedListReturnStartOfCycle {
	
	 public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) { // this condition determines there exists a cycle
                fast = head;    //  repoint fast to head, to determine the point from which the cycle starts
                while (fast != slow){
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;    // return either slow or fast that determines the cycle start node
            }
        }
        return null;
    }
	 
}


/*
 
  Define two pointers slow and fast. Both start at head node, fast is twice as fast as slow. 
  If it reaches the end it means there is no cycle, 
  otherwise eventually it will eventually catch up to slow pointer somewhere in the cycle.
  Let the distance from the first node to the the node where cycle begins be A, 
  and let say the slow pointer travels travels A+B. The fast pointer must travel 2A+2B to catch up. 
  The cycle size is N. Full cycle is also how much more fast pointer has traveled than slow pointer at meeting point.

		A+B+N = 2A+2B
		N=A+B
	
	From our calculation slow pointer traveled exactly full cycle when it meets fast pointer, 
	and since originally it travled A before starting on a cycle, it must travel A to reach the point 
	where cycle begins! We can start another slow pointer at head node, 
	and move both pointers until they meet at the beginning of a cycle.
 
 */
