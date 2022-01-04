package in.sachinshinde.binarytree;

import java.util.HashMap;

//	httpostStart://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/

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

public class ConstructBinaryTreeFromInorderPostorderTraversal {

	//	Method 1 : by Striver
	
	public Node buildTree1(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length)
            return null;
        HashMap<Integer, Integer> hm = new HashMap<Integer,Integer>();
        for (int i=0;i<inorder.length;++i)
            hm.put(inorder[i], i);
        return buildTreePostIn(inorder, 0, inorder.length-1, postorder, 0, 
                              postorder.length-1,hm);
    }

    private Node buildTreePostIn(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd, 
                                     HashMap<Integer,Integer> hm){
        if (postStart > postEnd || inStart > inEnd) 
        	return null;
        
        Node root = new Node(postorder[postEnd]);

        int inRoot = hm.get(postorder[postEnd]);
        
        Node leftchild = buildTreePostIn(inorder, inStart, inRoot-1, postorder, postStart, postStart+inRoot-inStart-1, hm);
        Node rightchild = buildTreePostIn(inorder,inRoot+1, inEnd, postorder, postStart+inRoot-inStart, postEnd-1, hm);
        
        root.left = leftchild;
        root.right = rightchild;
        
        return root;
    }
	
	// Method 2 : by Tushar Roy
	public Node buildTree2(int[] inorder, int[] postorder) {
        return buildTree(inorder, postorder, 0, inorder.length - 1, postorder.length - 1);
    }

    public Node buildTree(int[] inorder, int[] postorder, int start, int end, int index) {
        if (start > end) {
            return null;
        }

        int i;
        for (i = start; i <= end; i++) {
            if (postorder[index] == inorder[i]) {
                break;
            }
        }

        Node tn = new Node(postorder[index]);
        tn.left = buildTree(inorder, postorder, start, i - 1, index - (end - i + 1));
        tn.right = buildTree(inorder, postorder, i + 1, end, index - 1);
        return tn;
    }
}
