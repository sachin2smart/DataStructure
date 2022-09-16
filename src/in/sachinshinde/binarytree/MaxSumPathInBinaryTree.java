package in.sachinshinde.binarytree;

//	https://www.geeksforgeeks.org/find-maximum-path-sum-in-a-binary-tree/
	
/*
	  	Input: Root of a tree
	  	Output: Max sum from any path in tree
	  	
	  	See below diagram for another example.
	       1
	      / \
	     2   3
	     
		Output: 6 [1+2+3]
 */

public class MaxSumPathInBinaryTree {

	int max;

	public int maxPathSum(Node root) {
		max = Integer.MIN_VALUE;
		pathSum(root);
	    return max;
	}

	public int pathSum(Node root) {
	    if(root == null)
	        return 0;
	    
	    // Have used Math.max() 4 times
	    int leftSum = Math.max(0, pathSum(root.left));
	    int rightSum = Math.max(0, pathSum(root.right));
	    int siblingMaxSum = Math.max(leftSum, rightSum);
	    
	    max = Math.max(max, root.key + leftSum + rightSum);
	    
	    return root.key + siblingMaxSum;
	}
    
    public static void main(String[] args) {
    	MaxSumPathInBinaryTree tree = 
    			new MaxSumPathInBinaryTree();
    	
    	/*
    	  			-10
    	  			/  \
    	  		   9   20
    	  		       / \
    	  		      15  7
    	  		      
    	  		  Answer : 15+20+7 = 42
    	 */
    	 Node root = new Node(-10);
         root.left = new Node(9);
         root.right = new Node(20);
         root.right.left = new Node(15);
         root.right.right = new Node(7);

         int ans =  tree.maxPathSum(root);
         System.out.println("The Max Path Sum for this tree is: " + ans);	//	42
         
         
         /*
          				10
          			   /  \
          			  2   10
          			 / \    \ 
          			20  1   -25
          			        /  \
          			       3    4
          			       
          			Answer : 20+2+10+10 = 42
          */
         
         Node root2 = new Node(10);
         root2.left = new Node(2);
         root2.right = new Node(10);
         root2.left.left = new Node(20);
         root2.left.right = new Node(1);
         root2.right.right = new Node(-25);
         root2.right.right.left = new Node(3);
         root2.right.right.right = new Node(4);
         
         int ans2 =  tree.maxPathSum(root2);
         System.out.println("The Max Path Sum for this tree is: " + ans2);	//	42
         
         Node root3 = new Node(10);
         root3.left = new Node(2);
         root3.right = new Node(10);
         
         int ans3 =  tree.maxPathSum(root3);
         System.out.println("The Max Path Sum for this tree is: " + ans3);	//	22
    }
    
}
