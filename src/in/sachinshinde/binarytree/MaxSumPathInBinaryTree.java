package in.sachinshinde.binarytree;

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
         
         System.out.println("The Max Path Sum for this tree is " + ans);

    }
    
}
