package in.sachinshinde.binarytree;

//	https://leetcode.com/problems/same-tree/

/*
 *	Given the roots of two binary trees p and q, write a function to check if they are the same or not.
	Two binary trees are considered the same if they are structurally identical, and the nodes have the same value. 	
 */

public class SameTree {
	
    public boolean isSameTree(Node p, Node q) {
        if(p == null && q == null) 
            return true;
        
        if(p == null || q == null) 
            return false;
        
        if(p.key == q.key)
            return isSameTree(p.left, q.left) && 
                    isSameTree(p.right, q.right);
        
        return false;
    }
}