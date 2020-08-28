package com.gfg.binarytree;

// Size of a tree is the number of elements present in the tree

public class SizeOfTree {
	
	static int size(Node root){
		if(root == null)
			return 0;
		else 
			return (size(root.left)+1+size(root.right));
	}
	
	public static void main(String[] args) {
		
		Node root = new Node(5);
		
		root.left = new Node(4);
		root.left.left = new Node(3);
		root.left.left.left = new Node(2);
		
		root.right = new Node(7);
		root.right.right = new Node(8);
		root.right.right.right = new Node(9);
		
		System.out.println("The Size of a Binary Tree is : "+ size(root));
		// Since there are 7 elements in the tree, the size of binary tree will be 7
	}

}
