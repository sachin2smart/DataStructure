package in.sachinshinde.binarytree;

//	https://leetcode.com/problems/minimum-depth-of-binary-tree/

/*
 *	Given a binary tree, find its minimum depth.
	The minimum depth is the number of nodes along the shortest path 
		from the root node down to the nearest leaf node.
 */

public class MinimumDepthOfBinaryTree {
	
	public int getMinDepth(Node root) {
		 if(root == null) 
			 return 0;
	     
		 int left = getMinDepth(root.left);
	     int right = getMinDepth(root.right);
	        
	     return (left == 0 || right == 0) ? 
	    		 	left + right + 1: 
	    		 	Math.min(left,right) + 1;
	}

}
