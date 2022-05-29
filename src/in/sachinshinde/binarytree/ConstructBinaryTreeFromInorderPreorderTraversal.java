package in.sachinshinde.binarytree;

import java.util.HashMap;
import java.util.Map;

//	https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
//	https://www.youtube.com/watch?v=aZNaLrVebKQ

public class ConstructBinaryTreeFromInorderPreorderTraversal {
	
	public Node buildTree(int[] preorder, int[] inorder) {
		if (inorder == null || preorder == null || inorder.length != preorder.length)
            return null;
        Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();
        for(int i = 0; i < inorder.length; i++)
            inMap.put(inorder[i], i);	//	Storing the i as the i'th index value from inorder list into the hashmap [nodeVal = index] 
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    public Node buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if(preStart > preEnd || inStart > inEnd) return null;

        Node root = new Node(preorder[preStart]);	// first node from the preOrder list is the root node
        int inRoot = inMap.get(root.key);	// Get the index position from the inorder list

        root.left = buildTree(preorder, preStart + 1, preStart + inRoot - inStart, inorder, inStart, inRoot - 1, inMap);
        root.right = buildTree(preorder, preStart + inRoot - inStart + 1, preEnd, inorder, inRoot + 1, inEnd, inMap);

        return root;
    }
	
}
