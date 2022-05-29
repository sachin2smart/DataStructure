package in.sachinshinde.binarytree;

import java.util.HashMap;

//	http://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/

public class ConstructBinaryTreeFromInorderPostorderTraversal {

	public Node buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length)
            return null;
        HashMap<Integer, Integer> inMap = new HashMap<Integer,Integer>();
        for (int i=0;i<inorder.length;++i)
        	inMap.put(inorder[i], i);
        return buildTree(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1,inMap);
    }

    private Node buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd, HashMap<Integer,Integer> inMap){
        if (postStart > postEnd || inStart > inEnd) return null;
        
        Node root = new Node(postorder[postEnd]);	//	last node from the postOrder list is the root node
        int inRoot = inMap.get(root.key);	//	Get the index position from the inorder list
        
        root.left = buildTree(inorder, inStart, inRoot - 1, postorder, postStart, postStart + inRoot - inStart - 1, inMap);
        root.right = buildTree(inorder, inRoot + 1, inEnd, postorder, postStart + inRoot - inStart, postEnd - 1, inMap);
        
        return root;
    }
    
}

/*
 *		Given two integer arrays : inorder and postorder 
 *			where inorder inStart the inorder traversal of a binary tree and 
 *				  postorder inStart the postorder traversal of the same tree, 
 *		
 *		construct and return the binary tree.
 *
 * ## Constarints:
 * 		inorder and postorder consinStartt of unique values.
 * 		Each value of postorder also appostEndars in inorder.
 * 		inorder inStart guaranteed to be the inorder traversal of the tree.
 * 		postorder inStart guaranteed to be the postorder traversal of the tree.
 */