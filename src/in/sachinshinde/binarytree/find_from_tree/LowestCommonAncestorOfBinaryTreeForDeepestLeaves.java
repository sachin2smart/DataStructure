package in.sachinshinde.binarytree.find_from_tree;

import in.sachinshinde.binarytree.Node;

import java.util.HashMap;

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
    
    //	2. Recursive approach : O(N^2)
    public int height(Node root){
        if(root == null)
        	return 0;
        
        return Math.max(
        		height(root.left), height(root.right)) + 1;
    }
    
    public Node lcaDeepestLeaves2(Node root) {
        if(root == null || 
        		height(root.right) == height(root.left))
        	return root;
        
        return lcaDeepestLeaves(
        		height(root.left) > height(root.right) ?
        				root.left	:	root.right);
    }
    
    
    //	3. Memoization : O(N)
    HashMap<Node, Integer> heights = new HashMap<Node, Integer>();
    public int heightMemorized(Node root){
        if(root == null)
        	return 0;
        
        if(heights.containsKey(root))
        	return heights.get(root);
        
        heights.put(root , 1 + Math.max(heightMemorized(root.left),
        								heightMemorized(root.right)));
        
        return heights.get(root);
    }
    
    public Node lcaDeepestLeaves3(Node root) {
        if(root == null || 
        		heightMemorized(root.right) == heightMemorized(root.left))
        	return root;
        
        return lcaDeepestLeaves3(
        		heightMemorized(root.left) > heightMemorized(root.right) ?
        				root.left	:	root.right);
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
		     
		     Answer :  8 4 12 9 2 10 13 5 11
	    	
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
		root.left.left.right.left = new Node(12);
		root.left.right.left.right = new Node(13);
		
		LowestCommonAncestorOfBinaryTreeForDeepestLeaves tree = 
				new LowestCommonAncestorOfBinaryTreeForDeepestLeaves();
		
		Node resultNode = tree.lcaDeepestLeaves3(root);
		tree.inOrder(resultNode);	//  8 4 12 9 2 10 13 5 11
    }
    
    private void inOrder(Node root) {
    	if(root == null)
    		return;
    	
    	inOrder(root.left);
    	System.out.print(" "+ root.key);
    	inOrder(root.right);
    }
    
}
