package com.gfg.bst;

// https://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/

public class CheckBinaryTreeIsBST {
	
	Node prev;
	Node root;
	
	public boolean isBST(Node root) {
		if(root!=null)
		{
			if(!isBST(root.left))
				return false;
			
			if(prev!=null && prev.key >= root.key)
				return false;
			
			prev = root;
			
			return isBST(root.right);
		}
		return true;
	}
	
	boolean isBSTUtil() {
		return isBST(root);
	}
	
	public static void main(String[] args) {
		CheckBinaryTreeIsBST checkBinaryTreeIsBST = new CheckBinaryTreeIsBST();
		checkBinaryTreeIsBST.root = null;
	    System.out.println(checkBinaryTreeIsBST.isBSTUtil());

	}

}
