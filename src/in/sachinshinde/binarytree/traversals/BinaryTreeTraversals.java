package in.sachinshinde.binarytree.traversals;

import in.sachinshinde.binarytree.Node;

public class BinaryTreeTraversals {

	public static void main(String args[]) {
		Node root = new Node(10);
		root.left = new Node(8);
		root.left.left = new Node(3);
		root.left.right = new Node(5);
		root.right = new Node(2);
		root.right.left = new Node(7);
		
		System.out.println("\n Inorder Traversal : ");
		inOrderTraversal(root);
		
		System.out.println("\n Preorder Traversal : ");
		preOrderTraversal(root);
		
		System.out.println("\n Postorder Traversal : ");
		postOrderTraversal(root);
	}
	
	private static void inOrderTraversal(Node root) {
		if(root == null)
			return;
		inOrderTraversal(root.left);
		System.out.print(" "+root.key);
		inOrderTraversal(root.right);
	}
	
	private static void preOrderTraversal(Node root) {
		if(root == null)
			return;
		
		System.out.print(" "+root.key);
		preOrderTraversal(root.left);
		preOrderTraversal(root.right);
	}
	
	private static void postOrderTraversal(Node root) {
		if(root == null)
			return;
		postOrderTraversal(root.left);
		postOrderTraversal(root.right);
		System.out.print(" "+root.key);
	}
}
