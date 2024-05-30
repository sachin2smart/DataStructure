package in.sachinshinde.bst.basics;

import in.sachinshinde.bst.Node;

public class ConstructBST {
	
	static void inorder(Node root) {
		if(root == null)
			return;
		inorder(root.left);
		System.out.print(" "+ root.key);
		inorder(root.right);
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
		int arr[] = {1,5,2,8,6,9};
		
		Node root = constructBST(arr);
		
		inorder(root);
	}
	
}
