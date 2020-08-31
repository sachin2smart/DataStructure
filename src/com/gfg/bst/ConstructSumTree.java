package com.gfg.bst;

public class ConstructSumTree {

	Node root;
	
	int toSumTree(Node root) {
		
		if(root == null)
			return 0;
		
		int oldData = root.key;
		root.key = toSumTree(root.left) + toSumTree(root.right);
		
		return oldData + root.key;
		
	}
	
	public void inorder(Node root) {
		if(root!=null) {
			inorder(root.left);
			System.out.print(" "+ root.key);
			inorder(root.right);
		}
	}
	
	public static void main(String[] args) {
		ConstructSumTree btree = new ConstructSumTree();
		btree.root = new Node(10);
		btree.root.left = new Node(5);
		btree.root.right = new Node(15);
		
		btree.inorder(btree.root);
		btree.toSumTree(btree.root);
		System.out.println();
		btree.inorder(btree.root);
		
	}

}
