package in.sachinshinde.binarytree;

//	https://leetcode.com/problems/invert-binary-tree/

//	Given the root of a binary tree, invert the tree, 
//	and return its root.
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
	
	public Node invert(Node node) { 
		if (node == null) 
			return node; 
		
		Node left = invert(node.left); 
		Node right = invert(node.right); 

		node.left = right; 
		node.right = left; 

		return node; 
	}
	
	public static void main(String[] args) {
		InvertBinaryTree binaryTree = new InvertBinaryTree();
		Node n = new Node(2);
		n.left = new Node(1);
		n.right = new Node(4);
		n.right.left = new Node(3);
		n.right.right = new Node(5);
		
		binaryTree.inOrder(n);	//	1 2 3 4 5
		
		Node root = binaryTree.invert(n);
		
		System.out.println();
		binaryTree.inOrder(root);	// 5 4 3 2 1
	}
	
	private void inOrder(Node root) {
		if(root == null)
			return;
		
		inOrder(root.left);
		System.out.print(" " + root.key);
		inOrder(root.right);
	}
}
