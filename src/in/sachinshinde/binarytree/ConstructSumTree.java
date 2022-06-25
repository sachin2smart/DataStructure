package in.sachinshinde.binarytree;

//	https://www.geeksforgeeks.org/convert-a-given-tree-to-sum-tree/

/*
	Given a Binary Tree where each node has positive and negative values. 
	Convert this to a tree where 
		each node contains the sum of the left and right sub trees in the original tree. 
	The values of leaf nodes are changed to 0.
 */

public class ConstructSumTree {

	Node root;
	
	int toSumTree(Node root) {
		
		if(root == null)
			return 0;
		
		int oldData = root.key;
		root.key = toSumTree(root.left) + toSumTree(root.right);
		
		return oldData + root.key;
		
	}
	
	public void inorder(Node root) {
		if(root!=null) {
			inorder(root.left);
			System.out.print(" "+ root.key);
			inorder(root.right);
		}
	}
	
	public static void main(String[] args) {
		ConstructSumTree btree = new ConstructSumTree();
		btree.root = new Node(10);
		btree.root.left = new Node(5);
		btree.root.right = new Node(15);
		
		btree.inorder(btree.root);
		btree.toSumTree(btree.root);
		System.out.println();
		btree.inorder(btree.root);
		
		Node n = new Node(10);
		n.left = new Node(-2);
		n.left.left = new Node(8);
		n.left.right = new Node(-4);
		n.right = new Node(6);
		n.right.left = new Node(7);
		n.right.right = new Node(5);
		
		System.out.println("\n");
		btree.inorder(n);	 //	8 -2 -4 10 7 6 5
		 
		btree.toSumTree(n);
		System.out.println();
		btree.inorder(n);	//	 0 4 0 20 0 12 0
		
	}
	
	//	Time Complexity: The solution involves a simple traversal of the given tree. 
	//	So the time complexity is O(n) where n is the number of nodes in the given Binary Tree.
	
	/*
	   
	Example : 1    
		 			10
			 		/\
			 	   5  15
	 	   
	should be changed to  	   
			 	   20
			 	   /\
			 	  0  0
		 	  
		 	  
	Example : 2
		 	  
		 	      10
               /      \
             -2        6
           /   \      /  \ 
         8     -4    7    5
         
         
	should be changed to 

                 20(4-2+12+6)
               /      \
         4(8-4)      12(7+5)
           /   \      /  \ 
         0      0    0    0
	 
	 */

}
