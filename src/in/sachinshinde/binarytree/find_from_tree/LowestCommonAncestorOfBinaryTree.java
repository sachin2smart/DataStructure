package in.sachinshinde.binarytree.find_from_tree;

//	https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

/*
 *	Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 *	The lowest common ancestor is defined between two nodes p and q as the lowest node in T 
 *	that has both p and q as descendants (where a node can be considered as to be a descendant of itself)
 */

import in.sachinshinde.binarytree.Node;

public class LowestCommonAncestorOfBinaryTree {
	
	public Node lowestCommonAncestor(Node root, Node p, Node q) {
		//	if the node found
        if (root == null || root == p || root == q) 
            return root;
        
        Node left = lowestCommonAncestor(root.left, p, q);
        Node right = lowestCommonAncestor(root.right, p, q);
        
        //	Return the sibling, if it's null
        if(left == null)	
        	return right;
        else 
        	if(right == null)	
        		return left;
        
        return root;
        
    }
	
	public static void main(String args[]) {
		/*
		        	1
				 /      \
			    2        3
			  /   \     / \
			 4     5   6   7
			/ \   / \
			8   9 10  11
		      /     \
		     12     13
		     
		     
			LCA(8,13) = 2 
	    	LCA(9,6) = 1
	    	LCA(11,13) = 5
	    	
		 */
		
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);
		root.left.left.left = new Node(8);
		root.left.left.right = new Node(9);
		root.left.right.left = new Node(10);
		root.left.right.right = new Node(11);
		root.left.right.left.left = new Node(12);
		root.left.right.left.right = new Node(13);
		
		LowestCommonAncestorOfBinaryTree tree = 
				new LowestCommonAncestorOfBinaryTree();
		
		Node resultNode = tree.lowestCommonAncestor(root, 
				root.left.left.left, root.left.right.right.right);
		
		System.out.println(resultNode.key);	//	2
		
		resultNode = tree.lowestCommonAncestor(root, 
				root.left.left.right, root.right.left);
		
		System.out.println(resultNode.key);	//	1
		
		resultNode = tree.lowestCommonAncestor(root, 
				root.left.right.left.right, root.left.right.right);
		
		System.out.println(resultNode.key);	//	5
	}
}
