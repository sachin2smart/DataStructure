package in.sachinshinde.binarytree;

import java.util.HashMap;
import java.util.Map;

//	https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
//	https://www.youtube.com/watch?v=aZNaLrVebKQ

public class ConstructBinaryTreeFromInorderPreorderTraversal {
	
	public Node buildTree(int[] inorder, int[] preorder) {
		if (inorder == null || preorder == null || 
				inorder.length != preorder.length)
            return null;
		
        Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();
        
        //  Storing the i as the i'th index value from inorder list 
        //		into the hashmap [nodeVal = index] 
        for(int i = 0; i < inorder.length; i++)
            inMap.put(inorder[i], i);
        
        return buildTree(inorder, 0, inorder.length - 1, 
        					preorder, 0, preorder.length - 1, 
        					inMap);
    }

    public Node buildTree(int[] inorder, int inStart, int inEnd,
    						int[] preorder, int preStart, int preEnd,
    						Map<Integer, Integer> inMap) {
        if(inStart > inEnd || preStart > preEnd) 
        	return null;

        // first node from the preOrder list is the root node
        Node root = new Node(preorder[preStart]);
        
        // Get the index position from the inorder list
        int inRoot = inMap.get(root.key);

        int deltaIndex = preStart + inRoot - inStart;
        
        //  inorder  = inStart.......inRoot-1......inRoot+1.......inEnd
        //	preorder = preStart+1....deltaIndex....deltaIndex+1...preEnd
        
        root.left = buildTree(inorder, inStart, inRoot - 1, 
        						preorder, preStart + 1, deltaIndex, 
        						inMap);
        
        root.right = buildTree(inorder, inRoot + 1, inEnd,
        						preorder, deltaIndex + 1, preEnd, 
        						inMap);

        return root;
    }
	
}
