package in.sachinshinde.binarytree.checks_on_tree;

//	https://leetcode.com/problems/balanced-binary-tree/

/*
 * 	Given a binary tree, determine if it is height-balanced.
	## A height-balanced binary tree is defined as:
			a binary tree in which the left and right subtrees 
			of every node differ in height by no more than 1.
 */

import in.sachinshinde.binarytree.Node;

public class BalancedBinaryTree {
	
	public boolean isBalanced(Node root) {
      if(root == null) {
    	 return true;
	  }
      
	  return helper(root) != -1;
    }
    
    private int helper(Node root) {
    	//	Base Condition
        if(root == null) {
            return 0;
        }
        
        int left = helper(root.left);
        int right = helper(root.right);
        
        /*
        	## If leftNode of currentNode is null, return -1
        	## If rightNode of currentNode is null, return -1
        	## If the difference between height of left and right node 
        			is greater than 1, return -1
        */
   
        if(left == -1 || right == -1 || Math.abs(left - right) > 1){
            return -1;	
        }
        
        // Always return the maximum height of the current node
        return Math.max(left, right) + 1;
    }
    
    public static void main(String[] args) {
		BalancedBinaryTree tree = new BalancedBinaryTree();
		
		Node n = new Node(1);
		n.left = new Node(2);
		n.right = new Node(3);
		
		System.out.println(tree.isBalanced(n));	//	true
		
		n.left.left = new Node(4);
		System.out.println(tree.isBalanced(n));	// true
		
		n.left.left.left = new Node(5);
		System.out.println(tree.isBalanced(n));	// false
	}
}
