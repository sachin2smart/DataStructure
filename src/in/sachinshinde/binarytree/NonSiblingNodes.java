package in.sachinshinde.binarytree;

/*
	https://www.geeksforgeeks.org/print-nodes-dont-sibling-binary-tree/ 
 */

/*
 	Given a Binary Tree, print all nodes that don’t have a sibling 
 	(a sibling is a node that has same parent. 
 		In a Binary Tree, there can be at most one sibling). 
 	Root should not be printed as root cannot have a sibling.
 */

import java.util.ArrayList;
import java.util.List;

public class NonSiblingNodes {
	
	private void getNonSiblingNodes(Node root, List<Integer> list) {
		if(root == null) {
			return;
		}
		else if(root.left != null && root.right != null) {
			getNonSiblingNodes(root.left, list);
			getNonSiblingNodes(root.right, list);
		}
		else if(root.left != null) {
			list.add(root.left.key);
			getNonSiblingNodes(root.left, list);
		}
		else if(root.right != null) {
			list.add(root.right.key);
			getNonSiblingNodes(root.right, list);
		}
	}
	
	public static void main(String[] args) {
		/*
		 			5
		 		   / \
		 		  4   7
		 		 /     \
		 		3       8
		       /         \
		      2           9
		 */
		Node root = new Node(5);
		root.left = new Node(4);
		root.left.left = new Node(3);
		root.left.left.left = new Node(2);
		root.right = new Node(7);
		root.right.right = new Node(8);
		root.right.right.right = new Node(9);
		
		List<Integer> list = new ArrayList<>();
		NonSiblingNodes tree = new NonSiblingNodes();
		tree.getNonSiblingNodes(root, list);
		System.out.println(list);
	}
}
