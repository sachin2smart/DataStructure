package in.sachinshinde.binarytree;

/*
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. 
 * This path may or may not pass through the root.

   https://leetcode.com/problems/diameter-of-binary-tree/
 
 */

public class DiameterOfBinaryTree {

	private static int max;
	
	private static int diameterOfBinaryTree(Node root) {
		
		if(root == null)
			return 0;
		
		int height = 0;
		
		int l_height = diameterOfBinaryTree(root.left);
		int r_height = diameterOfBinaryTree(root.right);
		
		height = Math.max(l_height, r_height);
		height++;
		
		max = Math.max(max, l_height + r_height + 1);
		return height;
	}
	
	private static int getDiameterOfBinaryTree(Node root) {
		max = Integer.MIN_VALUE;
		diameterOfBinaryTree(root);
		return max-1;
	}
	
	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.left.left = new Node(3);
		root.left.left.left = new Node(4);
		root.left.left.right = new Node(5);
		root.left.left.right.right = new Node(6);
		root.left.left.right.right.right = new Node(7);
		root.left.right = new Node(8);
		root.left.right.right = new Node(9);
		root.left.right.right.right = new Node(10);
		root.left.right.right.right.right = new Node(11);
		root.right = new Node(12);
		
		System.out.println("The diameter of a binary tree is : " + getDiameterOfBinaryTree(root)); //8
		
		Node n = new Node(1);
		n.left = new Node(2);
		n.right = new Node(3);
		
		System.out.println("\nThe diameter of a binary tree is : " + getDiameterOfBinaryTree(n)); //2
		
		max = 0 ;
		System.out.println("The diameter of a binary tree is : " + getDiameterOfBinaryTree2(root)); //8
		max = 0;
		System.out.println("The diameter of a binary tree is : " + getDiameterOfBinaryTree2(n)); //2
		
	}
	
		//	Method 2 
	    private static int getDiameterOfBinaryTree2(Node root) {
	        maxDepth(root);
	        return max;
	    }
	    
	    private static int maxDepth(Node root) {
	        if(root == null)
	            return 0;
	        int left = maxDepth(root.left);
	        int right = maxDepth(root.right);
	        max = Math.max(max,left+right);	//	calculate max; but,
	        return Math.max(left,right)+1;	//	return the height
	    }
}
