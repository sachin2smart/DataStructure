package in.sachinshinde.binarytree.traversals;

import in.sachinshinde.binarytree.Node;

//	https://www.geeksforgeeks.org/clockwise-spiral-traversal-of-binary-tree-set-2/
/*
 	Given a Binary Tree. The task is to print the circular clockwise spiral order traversal 
 	of the given binary tree.
 	
 	Examples:
 	--------
	Input : 
	           1
	         /  \
	        2    3
	       / \    \
	      4   5    6
	         /    / \
	        7    8   9
	Output :1 9 8 7 2 3 6 5 4
	
	Input :
	           20
	         /   \
	        8     22
	      /   \  /   \
	     5     3 4    25
	          / \
	         10  14
	Output :20 14 10 8 22 25 4 3 5

 */
public class ClockwiseSpiralTraversalOfABinaryTree {

	private int height(Node root) {
		if(root == null)
			return 0;
		
		int lheight = height(root.left);
		int rheight = height(root.right);
		
		return Math.max(lheight+1, rheight+1);
	}
	
	private void printLeftToRight(Node root, int level) {
		if(root == null)
			return;
		
		if(level ==1)
			System.out.print(" " + root.key);
		
		else if(level > 1) {
			printLeftToRight(root.left, level-1);
			printLeftToRight(root.right, level-1);
		}
	}
	
	private void printRightToLeft(Node root, int level) {
		if(root == null)
			return;
		
		if(level ==1)
			System.out.print(" " + root.key);
		
		else if(level > 1) {
			printRightToLeft(root.right, level-1);
			printRightToLeft(root.left, level-1);
		}
	}
	
	/*
	 	Approach: 
	 	--------
	 	- The idea is to use two variables i initialized to 1 and 
	 		j initialized to the height of tree and 
	 		run a while loop which wont break until i becomes greater than j. 
	 		
	 	- We will use another variable flag and initialize it to 0. 
	 	
	 	- Now in the while loop we will check a condition that if flag is equal to 0 
	 		we will traverse the tree from left to right and mark flag as 1 
	 		so that next time we traverse the tree from right to left and then 
	 		increment the value of i so that next time we visit the level 
	 		just below the current level. 
	 		
	 	- Also when we will traverse the level from bottom we will mark flag as 0 
	 		so that next time we traverse the tree from right to left and then 
	 		decrement the value of j so that next time we visit the level 
	 		just above the current level. 
	 		
	 	- Repeat the whole process until the binary tree is completely traversed.
	 */
	
	private void clockwiseSpiral(Node root) {
		int i = 1;
		int j = height(root);
		
		int flag = 0;
		
		while(i <= j) {
			if(flag == 0) {
				printLeftToRight(root, i);
				flag = 1;
				i++;
			}
			else {
				printRightToLeft(root, j);
				flag = 0;
				j--;
			}
		}
	}
	
	
	public static void main(String[] args) {
		/*
				   10
		         /   \
		        12   13
		           /   \
		          14   15
		          /\   / \
		        21 22 23 24
		        
		   Answer: 10 24 23 22 21 12 13 15 14
		 */
		Node root = new Node(10);
		 
	    root.left = new Node(12);
	    root.right = new Node(13);
	 
	    root.right.left = new Node(14);
	    root.right.right = new Node(15);
	 
	    root.right.left.left = new Node(21);
	    root.right.left.right = new Node(22);
	    root.right.right.left = new Node(23);
	    root.right.right.right = new Node(24);
	    
	    ClockwiseSpiralTraversalOfABinaryTree tree = 
	    		new ClockwiseSpiralTraversalOfABinaryTree();
	    
	    tree.clockwiseSpiral(root);
	}
}
