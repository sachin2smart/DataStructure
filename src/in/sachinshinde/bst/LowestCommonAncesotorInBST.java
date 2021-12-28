package in.sachinshinde.bst;

/*
 * 	https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * 
 * 	Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * 	“The lowest common ancestor is defined between two nodes p and q as the lowest node in T 
 * 		that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 */

public class LowestCommonAncesotorInBST {

	public Node getLowestCommonAncestor(Node root, Node p, Node q) {
        if(root.key > p.key && root.key > q.key)
            return getLowestCommonAncestor(root.left, p, q);
        else if(root.key < p.key && root.key < q.key)
            return getLowestCommonAncestor(root.right, p, q);
        else
            return root;
	 }
}