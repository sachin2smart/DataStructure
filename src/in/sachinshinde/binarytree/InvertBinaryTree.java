package in.sachinshinde.binarytree;

//	https://leetcode.com/problems/invert-binary-tree/

//	Given the root of a binary tree, invert the tree, and return its root.
//	Input: root = [4,2,7,1,3,6,9]
//	Output:root = [4,7,2,9,6,3,1]

public class InvertBinaryTree {
	
	 public Node invertTree(Node root) {
        if(root == null) 
            return null;
        
        Node leftNode = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(leftNode);
        
        return root;
    }
}
