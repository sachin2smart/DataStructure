package com.gfg.bst;

public class BSTTraversals {
	static void inorder(Node root) {
		if(root == null)
			return;
		inorder(root.left);
		System.out.print(" "+ root.key);
		inorder(root.right);
	}
	
	static void postorder(Node root) {
		if(root == null)
			return;
		postorder(root.left);
		postorder(root.right);
		System.out.print(" "+ root.key);
	}
	
	static void preorder(Node root) {
		if(root == null)
			return;
		
		System.out.print(" "+ root.key);
		preorder(root.left);
		preorder(root.right);
	}
	
	static Node constructBST(int arr[]) {
		int numOfNode = arr.length;
		Node root = null;
		
		for(int i=0; i<numOfNode; i++) {
			root = LevelOrder(root, arr[i]);
		}
		
		return root;
	}

	static Node LevelOrder(Node root, int data) {
		
		if(root == null) 
			return new Node(data);
		
		if(data < root.key)
			root.left = LevelOrder(root.left, data);
		else
			root.right = LevelOrder(root.right, data);
		
		return root;
	}
	
	
	public static void main(String args[]) {
		int arr[] = {8, 5, 10, 3, 4, 9, 7};
		
		Node root = constructBST(arr);

		preorder(root);
		System.out.println();
		
		inorder(root);
		System.out.println();
		
		postorder(root);
		System.out.println();
		

	}

}
