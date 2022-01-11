package in.sachinshinde.bst;

//	https://leetcode.com/problems/validate-binary-search-tree/

/*
 *	Given the root of a binary tree, determine if it is a valid binary search tree (BST).

	A valid BST is defined as follows:
		- The left subtree of a node contains only nodes with keys less than the node's key.
		- The right subtree of a node contains only nodes with keys greater than the node's key.
		- Both the left and right subtrees must also be binary search trees. 
 */

public class ValidateBST {
	
	public boolean isValidBST(Node root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
     }
    
    private boolean isValidBST(Node root, long minVal, long maxVal) {
        if (root == null) 
        	return true;
        
        if (root.key >= maxVal || root.key <= minVal) 
        	return false;
        
        // update minVal and maxVal in recursive call 
        return isValidBST(root.left, minVal, root.key) && 
        		isValidBST(root.right, root.key, maxVal);
        
    }
}
