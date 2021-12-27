package in.sachinshinde.binarytree;

import java.util.Stack;

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
		
		if (root == null) 
			return;
        
		Stack<Node> st = new Stack<Node>();
        st.push(root);
        
        while (!st.isEmpty()){
            
        	Node curr = st.pop();
            
            if (curr.right != null)  
                 st.push(curr.right);
            
            if (curr.left!=null)  
                 st.push(curr.left);
            
            if (!st.isEmpty()) 
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
		root.right.right = new Node(6);
		
		System.out.println("\n Pre-Order: ");
		preOrder(root);
		
		flatten4(root);
		
		System.out.println("\n\n Flatten: ");
		preOrder(root);
		
	}
}
