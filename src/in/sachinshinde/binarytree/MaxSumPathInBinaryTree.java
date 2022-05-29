package in.sachinshinde.binarytree;

//	https://www.geeksforgeeks.org/find-maximum-path-sum-in-a-binary-tree/

/*
  	Input: Root of below tree
       1
      / \
     2   3
     
	Output: 6
	
	See below diagram for another example.
	1+2+3
 */

public class MaxSumPathInBinaryTree {

	static int max = Integer.MIN_VALUE;

	public static int maxPathSum(Node root) {
	    helper(root);
	    return max;
	}

	public static int helper(Node root) {
	    if(root == null)
	        return 0;
	    
	    int left = Math.max(0, helper(root.left));
	    int right = Math.max(0, helper(root.right));
	    
	    max = Math.max(max, root.key + left + right);
	    return root.key + Math.max(left, right);
	}
    
    public static void main(String[] args) {
    	 Node root = new Node(-10);
         root.left = new Node(9);
         root.right = new Node(20);
         root.right.left = new Node(15);
         root.right.right = new Node(7);

         int ans =  maxPathSum(root);
         
         System.out.println("The Max Path Sum for this tree is " + ans);	//	42
         
         
         Node root2 = new Node(10);
         root2.left = new Node(2);
         root2.right = new Node(10);
         root2.left.left = new Node(20);
         root2.left.right = new Node(1);
         root2.right.right = new Node(-25);
         root2.right.right.left = new Node(3);
         root2.right.right.right = new Node(4);
         
         int ans2 =  maxPathSum(root2);
         
         System.out.println("The Max Path Sum for this tree is " + ans2);	//	42
         

    }
    
}
