package in.sachinshinde.bst;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//	https://leetcode.ca/2016-08-28-272-Closest-Binary-Search-Tree-Value-II/

/*
 * 	 Given a non-empty binary search tree and a target value,
 	 Find k values in the BST that are closest to the target.

 Example:

 Input: root = [4,2,5,1,3], target = 3.714286, and k = 2

    4
   / \
  2   5
 / \
1   3

 Output: [4,3]

 Note:

 Given target value is a floating point.
 You may assume k is always valid, that is: k <= total nodes.
 You are guaranteed to have only one unique set of k values in the BST that are closest to the target.

 Follow up:
 Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

 */

public class ClosestNodesInBST {
	
	private static Deque<Integer> result = new ArrayDeque<Integer>();

    public static List<Integer> closestKValues(Node root, double target, int k) {
        inOrderTraversal(root, target, k);
        return new LinkedList<Integer>(result);
    }
    
    private static void inOrderTraversal(Node root, double target, int k) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left, target, k);
        if (result.size() < k) {
            result.add(root.key);
        } else if(result.size() == k) { 
            if (Math.abs(result.getFirst() - target) > (Math.abs(root.key - target))) {
                result.removeFirst();
                result.addLast(root.key);
            } else {
                return; // diff is larger, so skip, as trim
            }
        }
        inOrderTraversal(root.right, target, k);
    }
    
	public static void main(String[] args) {
	    Node root = new Node(4);
	    root.left = new Node(2);
	    root.right = new Node(5);

	    root.left.left = new Node(1);
	    root.left.right = new Node(3);

	    System.out.println(closestKValues(root, 3.114286, 2));
	}
}
