package in.sachinshinde.binarytree;

import java.util.HashMap;

//	http://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/

public class ConstructBinaryTreeFromInorderPostorderTraversal {

	public Node buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || 
        		inorder.length != postorder.length)
            return null;
        
        HashMap<Integer, Integer> inMap = new HashMap<Integer,Integer>();
        
        //  Storing the i as the i'th index value from inorder list 
        //		into the hashmap [nodeVal = index] 
        for (int i=0;i<inorder.length;++i)
        	inMap.put(inorder[i], i);
        
        return buildTree(inorder, 0, inorder.length-1, 
        					postorder, 0, postorder.length-1,
        					inMap);
    }

    private Node buildTree(int[] inorder, int inStart, int inEnd, 
    						int[] postorder, int postStart, int postEnd, 
    						HashMap<Integer,Integer> inMap) {
        if(inStart > inEnd || postStart > postEnd) 
        	return null;
        
        //	last node from the postOrder list is the root node
        Node root = new Node(postorder[postEnd]);
        
        //  Get the index position from the inorder list
        int inRoot = inMap.get(root.key);	
        
        int deltaIndex = postStart + inRoot - inStart;
        
        //	inorder = inStart....inRoot-1....inRoot+1....inEnd
        //	postorder = postStart....deltaIndex-1....deltaIndex...postEnd-1
        
        root.left = buildTree(inorder, inStart, inRoot - 1, 
        						postorder, postStart, deltaIndex - 1, 
        						inMap);
        
        root.right = buildTree(inorder, inRoot + 1, inEnd, 
        						postorder, deltaIndex, postEnd - 1, 
        						inMap);
        
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