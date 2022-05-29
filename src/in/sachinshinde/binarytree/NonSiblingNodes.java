/*
 *		https://www.geeksforgeeks.org/print-nodes-dont-sibling-binary-tree/ 
 */

package in.sachinshinde.binarytree;

import java.util.ArrayList;
import java.util.List;

public class NonSiblingNodes {

	static List<Integer> list = new ArrayList<>();
	
	static void printNonSiblingNodes(Node root) {
		
		if(root == null)
			return;
		
		if(root.left != null && root.right != null) {
			printNonSiblingNodes(root.left);
			printNonSiblingNodes(root.right);
		}
		else if(root.left != null) {
			System.out.print(root.left.key + " ");
			list.add(root.left.key);
			printNonSiblingNodes(root.left);
		}
		else if(root.right != null) {
			System.out.print(root.right.key + " ");
			list.add(root.right.key);
			printNonSiblingNodes(root.right);
		}
			
	}
	
	public static void main(String[] args) {
		
		Node root = new Node(5);
		
		root.left = new Node(4);
		root.left.left = new Node(3);
		root.left.left.left = new Node(2);
		
		root.right = new Node(7);
		root.right.right = new Node(8);
		root.right.right.right = new Node(9);
		
		printNonSiblingNodes(root);
		System.out.println("\n"+list);

	}

}
