package in.sachinshinde.binarytree;

//	https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

/*
 *	Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 *	The lowest common ancestor is defined between two nodes p and q as the lowest node in T 
 *	that has both p and q as descendants (where a node can be considered as to be a descendant of itself)
 */

public class LowestCommonAncestorOfBinaryTree {
	
	public Node lowestCommonAncestor(Node root, Node p, Node q) {
        if (root == null || root == p || root == q) 
            return root;
        
        Node left = lowestCommonAncestor(root.left, p, q);
        Node right = lowestCommonAncestor(root.right, p, q);
        
        if(left == null)
            return right;
        
        else if(right == null)
            return left;
        
        return root;
        
    }
}
