/*
 * 		https://www.geeksforgeeks.org/write-a-c-program-to-find-the-maximum-depth-or-height-of-a-tree/
 */

package in.sachinshinde.binarytree;

public class MaxDepthOfTree {
	
	static int getMaxDepth(Node root) {
	
		if(root == null)
			return 0;
		
		int leftDepth = getMaxDepth(root.left);
		int rightDepth = getMaxDepth(root.right);
		
		return Math.max(leftDepth, rightDepth) + 1;
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
		
		System.out.println("The max depth is : "+ getMaxDepth(root));	//	5
	}

}
