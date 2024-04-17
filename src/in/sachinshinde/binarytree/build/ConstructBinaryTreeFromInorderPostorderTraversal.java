package in.sachinshinde.binarytree.build;

import in.sachinshinde.binarytree.Node;

import java.util.HashMap;
import java.util.Map;

//	http://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/

public class ConstructBinaryTreeFromInorderPostorderTraversal {

	public Node buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length) {
            return null;
        }

        Map<Integer, Integer> inMap = new HashMap<>();

        //  Storing the i as the i'th index value from inorder list into the hashmap [nodeVal = index]
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        return buildTree(0, postorder.length - 1, postorder, 0, inorder.length - 1, inMap);
    }

    public Node buildTree(int postStart, int postEnd, int[] postorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if(inStart > inEnd || postStart > postEnd) {
            return null;
        }
        // Last node from the postorder list is the root node
        Node root = new Node(postorder[postEnd]);
        // Get the index position from the inorder list
        int inRoot = inMap.get(root.key);
        // Determine number of nodes on left side for left subtree
        int numsLeft = (postStart - inStart) + inRoot;

        //  inorder  = inStart.......inRoot-1......inRoot+1.......inEnd
        //	preorder = postStart+1....numsLeft....numsLeft+1...postEnd

        root.left = buildTree(postStart, numsLeft - 1, postorder, inStart, inRoot - 1, inMap);
        root.right = buildTree(numsLeft, postEnd - 1, postorder, inRoot + 1, inEnd, inMap);

        return root;
    }

    public static void main(String[] args) {
        ConstructBinaryTreeFromInorderPostorderTraversal tree = new ConstructBinaryTreeFromInorderPostorderTraversal();
        int[] postorder = new int[] {40,50,20,60,30,10};
        int[] inorder = new int[] {40,20,50,10,60,30};

        Node root = tree.buildTree(inorder, postorder);
        tree.postOrder(root); // 40 50 20 60 30 10

    }

    private void postOrder(Node root) {
        if( root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(" "+ root.key);
    }
}