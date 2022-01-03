package in.sachinshinde.binarytree;

//	https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/

/*
 *	
	Given the root of a binary tree, return the lowest common ancestor of its deepest leaves.

	Recall that:

	(1) The node of a binary tree is a leaf if and only if it has no children
	(2) The depth of the root of the tree is 0. if the depth of a node is d, the depth of each of its children is d + 1.
	(3) The lowest common ancestor of a set S of nodes, is the node A with the largest depth 
		such that every node in S is in the subtree with root A. 
 * 
 */

public class LowestCommonAncestorOfBinaryTreeForDeepestLeaves {

	int deepest = 0;
    Node lca;
    
    public Node lcaDeepestLeaves(Node root) {
    	getDepth(root, 0);
        return lca;
    }
    
    // Since at each level we need to determine depth, getDepth() serves that
    // update lca node pointer - once the depth of left & right subtree are equal
    private int getDepth(Node node, int depth) {
        deepest = Math.max(deepest, depth);
        
        if (node == null) {	// base condition - upon reaching to leaf nodes's left/right child node-  
            return depth;	//return the calculated depth
        }
        
        int leftTreeDepth = getDepth(node.left, depth + 1);		// going deep in each level
        int rightTreeDepth = getDepth(node.right, depth + 1);	// depth increased by 1
        
        if (leftTreeDepth == deepest && rightTreeDepth == deepest) {   
            lca = node;	// if the left and right child are deepest node of a tree then their parent node will be
        }
        
        return Math.max(leftTreeDepth, rightTreeDepth); // return the maximum depth traversed so far
    }
    
}
