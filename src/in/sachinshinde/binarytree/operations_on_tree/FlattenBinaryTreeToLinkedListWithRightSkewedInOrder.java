package in.sachinshinde.binarytree.operations_on_tree;

import in.sachinshinde.binarytree.Node;

import java.util.Stack;

// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
/*
 	Given the root of a binary tree, flatten the tree into a "linked list":

	The "linked list" should use the same TreeNode class 
		where the right child pointer points to the next node in the list 
		and the left child pointer is always null.
	The "linked list" should be in the same order 
		as a pre-order traversal of the binary tree.
 */

//	Follow up: Can you flatten the tree in-place (with O(1) extra space)?


public class FlattenBinaryTreeToLinkedListWithRightSkewedInOrder {
	
	private static void flatten(Node root) {
		if(root == null)
			return;
		
		Node left = root.left;
		Node right = root.right;
		
		root.left = null;
		
		flatten(left);
		flatten(right);
		
		root.right = left;
		
		Node curr = root;
		while(curr.right != null)
			curr = curr.right;
		
		curr.right = right;
	}
	
	private static Node prev = null;
	
	private static void flatten2(Node root) {
	    if (root == null)
	        return;
	    flatten(root.right);
	    flatten(root.left);
	    root.right = prev;
	    root.left = null;
	    prev = root;
	}
	
	private static void flatten3(Node root) {
		
		if(root == null)
			return;
        
		Stack<Node> st = new Stack<Node>();
        st.push(root);
        
        while(!st.isEmpty()) {
            
        	Node curr = st.pop();
            
            if(curr.right != null)
                 st.push(curr.right);
            
            if(curr.left != null)
                 st.push(curr.left);
            
            if(!st.isEmpty())
                 curr.right = st.peek();
            
            curr.left = null;
        }
	}
	
	private static void flatten4(Node root) {
		if(root == null)
		 	return;
		 	
		flatten(root.left);
		flatten(root.right);
		 
		Node right = root.right;
		 
		if(root.left != null) {
			
			root.right = root.left;
		 	root.left = null;
		
		 	while(root.right != null)
		 		root = root.right;
			
		 	root.right = right;
		 }
	}
	
	private static void preOrder(Node root) {
		if(root == null ) {
			System.out.print(" NULL ");
			return;
		}
		
		System.out.print(" "+ root.key);
		
		if(root.left == null && root.right == null)
			return;
		
		preOrder(root.left);
		preOrder(root.right);
	}
	
	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.left.left = new Node(3);
		root.left.right = new Node(4);
		root.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);
		
		System.out.println("\n Pre-Order: ");
		preOrder(root);
		
		flatten3(root);
		
		System.out.println("\n\n Flatten: ");
		preOrder(root);
		
	}
}
