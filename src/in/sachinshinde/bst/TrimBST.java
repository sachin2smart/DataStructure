package in.sachinshinde.bst;

/*
 * 	* Problem : https://leetcode.com/problems/trim-a-binary-search-tree/
 * 
 * 	* Trim the tree so that all its elements lies in [low, high]
 * 	
 *  * Trimming the tree should not change the relative structure of the elements 
 * 		that will remain in the tree 
 *   (i.e., any node's descendant should remain a descendant)
 *  
 *  	 1
 *  	/ \
 *     0   2
 *  
 *  low = 1, high = 2
 *  
 *  result :
 *  			1
 *  			 \ 
 *  			  2
 *  
 *  (Here, node with value 0 removed, since it does not inclusively falls inside 1..2)
 *  
 */

public class TrimBST {
	public Node trimBST(Node root, int low, int high) {
        if (root == null) 
            return root;
        
        // When node.val > high, the trimmed binary tree must occur to the left of the node
        if (root.key > high) 
            return trimBST(root.left, low, high);
        
        // When node.val < low, the trimmed binary tree must occur to the right of the node
        if (root.key < low) 
            return trimBST(root.right, low, high);

        // Otherwise, trim both sides of a tree
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        
        return root;    
    }
}


//	Time : O(n)
//  Space: O(n)
