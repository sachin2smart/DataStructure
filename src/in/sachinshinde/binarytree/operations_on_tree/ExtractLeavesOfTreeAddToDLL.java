package in.sachinshinde.binarytree.operations_on_tree;

//	https://www.geeksforgeeks.org/connect-leaves-doubly-linked-list/
//	Given a Binary Tree, extract all leaves of it in a Doubly Linked List (DLL).
//	left, right === prev, next

import in.sachinshinde.binarytree.Node;

public class ExtractLeavesOfTreeAddToDLL {

	Node root;
	Node head;
	Node prev;
	
	Node extractNode(Node root) {
		
		if(root == null)
			return null;
		
		if(root.left == null && root.right == null) {
			if(head == null) {
				head = root;
				prev = root;
			}
			else {
				prev.right = root;
				root.left = prev;
				prev = root;
			}
			return null;	// Once we found the leaf node, we should return a NULL value 
		}
		
		root.left = extractNode(root.left);
		root.right = extractNode(root.right);
			
		return root;
		
	}
	
	public void printTree(Node root) {
		
		if(root == null)
			return;
		
		System.out.print(" "+root.key);
		printTree(root.left);
		printTree(root.right);
	}
	
	
	public static void main(String[] args) {
		
//		ExtractLeavesOfTreeAddToDLL tree = new ExtractLeavesOfTreeAddToDLL();
//		tree.root = new Node(1);
//		tree.root.left = new Node(2);
//		tree.root.right = new Node(3);
//		tree.root.left.left = new Node(5);
//		tree.root.right.right = new Node(6);
//		
//		tree.printTree(tree.root);
//		System.out.println();
//		
//		tree.extractNode(tree.root);
//		
//		tree.printTree(tree.root);
//		System.out.println();
		
		// Test 2 
		ExtractLeavesOfTreeAddToDLL tree2 = new ExtractLeavesOfTreeAddToDLL();
		tree2.root = new Node(1);
//		tree2.root.left = new Node(2);
		tree2.extractNode(tree2.root);
		
		tree2.printTree(tree2.root);
		System.out.println();
		
	}

}
