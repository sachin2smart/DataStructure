// https://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/

package in.sachinshinde.binarytree;

public class CheckBinaryTreeIsBST {
	
	static boolean isBST(Node root, Node leftNode, Node rightNode) {
		if(root ==null)
			return true;
		
		if(leftNode != null  && leftNode.key >= root.key)
			return false;
		
		if(rightNode != null && rightNode.key <= root.key)
			return false;
		
		return isBST(root.left, leftNode, root) && 
				isBST(root.right, root, rightNode);
	}
	
	public static void main(String[] args) {
		Node root = new Node(3);  
		root.left = new Node(2);  
		root.right = new Node(5);  
		root.left.left = new Node(1);  
		root.left.right = new Node(4);  
	  
	    if(isBST(root, null, null))  
	        System.out.print("Is BST");  
	    else
	        System.out.print("Not a BST");
	}
}
