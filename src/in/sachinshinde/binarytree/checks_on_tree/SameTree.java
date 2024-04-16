package in.sachinshinde.binarytree.checks_on_tree;

//	https://leetcode.com/problems/same-tree/

/*
 *	Given the roots of two binary trees p and q, write a function to check if they are the same or not.
	Two binary trees are considered the same if they are structurally identical, and the nodes have the same value. 	
 */

import in.sachinshinde.binarytree.Node;

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
    
    public static void main(String[] args) {
    	SameTree sameTree = new SameTree();
		Node p = new Node(1);
		p.left = new Node(2);
		p.right = new Node(3);
		
		Node q = new Node(1);
		q.left = new Node(2);
		q.right = new Node(3);
		
		if(sameTree.isSameTree(p, q)) {
			System.out.println("Trees are same");
		}
		else {
			System.out.println("Trees are not same");
		}
	}
}
