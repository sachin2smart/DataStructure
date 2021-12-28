package in.sachinshinde.bst;

//	https://leetcode.com/problems/range-sum-of-bst/

/*
 * 	Given the root node of a binary search tree and two integers low and high, 
 * return the sum of values of all nodes with a value in the inclusive range [low, high]
 */

public class RangeSumOfBST {
	
	public int rangeSumBST(Node root, int low, int high) {
        if (root == null) 
        	return 0;
        
        int sum = 0;
        
        if (root.key >= low && root.key <= high) 
        	sum += root.key;
        
        if (root.key > low) 
        	sum += rangeSumBST(root.left, low, high);
        
        if (root.key < high) 
        	sum += rangeSumBST(root.right, low, high);

        return sum;
    }
	
}
