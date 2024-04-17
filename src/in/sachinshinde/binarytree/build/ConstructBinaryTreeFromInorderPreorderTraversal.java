package in.sachinshinde.binarytree.build;

import in.sachinshinde.binarytree.Node;

import java.util.HashMap;
import java.util.Map;

//	https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
//	https://www.youtube.com/watch?v=aZNaLrVebKQ

public class ConstructBinaryTreeFromInorderPreorderTraversal {
	
	public Node buildTree(int[] inorder, int[] preorder) {
        if (inorder == null || preorder == null || inorder.length != preorder.length) {
            return null;
        }

        Map<Integer, Integer> inMap = new HashMap<>();

        //  Storing the i as the i'th index value from inorder list into the hashmap [nodeVal = index]
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        return buildTree(0, preorder.length - 1, preorder, 0, inorder.length - 1, inMap);
    }

    public Node buildTree(int preStart, int preEnd, int[] preorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if(inStart > inEnd || preStart > preEnd) {
            return null;
        }
        // first node from the preOrder list is the root node
        Node root = new Node(preorder[preStart]);
        // Get the index position from the inorder list
        int inRoot = inMap.get(root.key);
        // Determine number of nodes on left side for left subtree
        int numsLeft = (preStart - inStart) + inRoot;
        
        //  inorder  = inStart.......inRoot-1......inRoot+1.......inEnd
        //	preorder = preStart+1....numsLeft....numsLeft+1...preEnd
        
        root.left = buildTree(preStart + 1, numsLeft, preorder, inStart, inRoot - 1, inMap);
        root.right = buildTree(numsLeft + 1, preEnd, preorder, inRoot + 1, inEnd, inMap);

        return root;
    }

    public static void main(String[] args) {
        ConstructBinaryTreeFromInorderPreorderTraversal tree = new ConstructBinaryTreeFromInorderPreorderTraversal();
        int[] preorder = new int[] {10,20,40,50,30,60};
        int[] inorder = new int[] {40,20,50,10,60,30};

        Node root = tree.buildTree(inorder, preorder);
        tree.preOrder(root); // 10 20 40 50 30 60
    }

    private void preOrder(Node root) {
        if( root == null) {
            return;
        }

        System.out.print(" "+ root.key);
        preOrder(root.left);
        preOrder(root.right);
    }
}
