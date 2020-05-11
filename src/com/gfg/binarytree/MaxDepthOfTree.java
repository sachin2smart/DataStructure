/*
 * 		https://www.geeksforgeeks.org/write-a-c-program-to-find-the-maximum-depth-or-height-of-a-tree/
 */

package com.gfg.binarytree;

public class MaxDepthOfTree {
	
	static int getMaxDepth(Node root) {
	
		if(root == null)
			return 0;
		else {
			int leftDepth = getMaxDepth(root.left);
			int rightDepth = getMaxDepth(root.right);
			
			if(leftDepth > rightDepth)
				return 1 + leftDepth;
			else
				return 1 + rightDepth;
		}
	}
	
	public static void main(String[] args) {
		
		Node root = new Node(5);
		
		root.left = new Node(4);
		root.left.left = new Node(3);
		root.left.left.left = new Node(2);
		root.left.left.left.left = new Node(1);
		
		root.right = new Node(7);
		root.right.right = new Node(8);
		root.right.right.right = new Node(9);
		
		System.out.println("The max depth is : "+ getMaxDepth(root));
	}

}
