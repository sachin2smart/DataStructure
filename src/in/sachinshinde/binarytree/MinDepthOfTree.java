package in.sachinshinde.binarytree;

//	https://leetcode.com/problems/minimum-depth-of-binary-tree/

/*
 *	Given a binary tree, find its minimum depth.
	The minimum depth is the number of nodes along the shortest path 
		from the root node down to the nearest leaf node.
 */

public class MinDepthOfTree {
	
	static int getMinDepth(Node root) {
		 if(root == null) 
			 return 0;
	     
		 int left = getMinDepth(root.left);
	     int right = getMinDepth(root.right);
	     
	     if(left == 0)
	    	 return right + 1;
	     
	     if(right == 0)
	    	 return left + 1;
	     
	     return Math.min(left, right) + 1;
	     
//	     return (left == 0 || right == 0) ? 
//	    		 	left + right + 1: 
//	    		 	Math.min(left,right) + 1;
	}

	public static void main(String[] args) {
		Node root = new Node(5);	// depth = 1
		
		root.left = new Node(4);	// depth = 2
		root.left.left = new Node(3);	// depth = 3
		root.left.left.left = new Node(2);	// depth = 4
		root.left.left.left.left = new Node(1);	// depth = 5
		
		root.right = new Node(7);	// depth = 2
		root.right.right = new Node(8);	// depth = 3
		root.right.right.right = new Node(9);	// depth = 4
		
		System.out.println("The min depth is : "+ getMinDepth(root));	//	4
		
		MinDepthOfTree tree = new MinDepthOfTree();
		System.out.println("The min depth is : "+ tree.minDepth2(root));	//	4
		System.out.println("The min depth is : "+ tree.minDepth3(root));	//	4
		System.out.println("The min depth is : "+ tree.minDepth4(root));	//	4
	}
	
	public int minDepth2(Node root) {
	    if (root == null) 
	    	return 0;
	    
	    int L = minDepth2(root.left), 
	    	R = minDepth2(root.right);
	    
	    return 1 + (Math.min(L, R) > 0 ? 
	    				Math.min(L, R) : Math.max(L, R));
	}

	public int minDepth3(Node root) {
	    if (root == null) 
	    	return 0;
	    
	    int L = minDepth3(root.left), 
	    	R = minDepth3(root.right), 
	    	m = Math.min(L, R);
	    
	    return 1 + (m > 0 ? m : Math.max(L, R));
	}

	public int minDepth4(Node root) {
	    if (root == null) 
	    	return 0;
	    
	    int L = minDepth4(root.left), 
	    	R = minDepth4(root.right);
	    
	    return L<R && L>0 || R<1 ? 1+L : 1+R;
	}
}
