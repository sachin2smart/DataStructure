package in.sachinshinde.bst;

//	https://leetcode.com/problems/kth-smallest-element-in-a-bst/

/*
 *	 Given the root of a binary search tree, and an integer k, 
 *		return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 */

public class KthSmallestElementInBST {
	
    public int kthSmallest(Node root, int k) {
	    int count = numOfNodes(root.left);
	    
	    if (k <= count)
	        return kthSmallest(root.left, k);
	    else if (k > count + 1)
	        return kthSmallest(root.right, k-(count+1));
	    
	    return root.key;
    }
    
    public int numOfNodes(Node n) {
        if (n == null) 
            return 0;
        
        return numOfNodes(n.left) + 1 + numOfNodes(n.right);
    }
}
