package in.sachinshinde.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

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
