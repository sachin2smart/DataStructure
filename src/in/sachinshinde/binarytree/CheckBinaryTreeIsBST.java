// https://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/

package in.sachinshinde.binarytree;

public class CheckBinaryTreeIsBST {
	
	static boolean isBST(Node root, Node leftNode, Node rightNode) {
		if(root ==null)
			return true;
		
		// if left node exist then check it has correct data or not 
		// i.e. left node's data should be less than root's data
		if(leftNode != null  && leftNode.key >= root.key)
			return false;
		
		// if right node exist then check it has correct data or not 
		// i.e. right node's data should be greater than root's data
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
	    
	    //	Method : 2
	    System.out.print("\n");
	    if(isValidBST(root))
	    	System.out.print("Is BST");
	    else
	    	System.out.print("Not a BST");
	}
	
	//	Method 2
	public static boolean isValidBST(Node root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
     }
    
    public static boolean isValidBST(Node root, long minVal, long maxVal) {
        if (root == null) 
        	return true;
        
        if (root.key >= maxVal || root.key <= minVal) 
        	return false;
        
        // update minVal and maxVal in recursive call 
        return isValidBST(root.left, minVal, root.key) && 
        		isValidBST(root.right, root.key, maxVal);
        
    }
}
