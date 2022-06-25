package in.sachinshinde.bst;

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
	    System.out.println(checkBinaryTreeIsBST.isBSTUtil());	//	true
	    
	    CheckBinaryTreeIsBST bst = new CheckBinaryTreeIsBST();
	    Node root = new Node(4);
	    root.left = new Node(2);
	    root.left.right = new Node(3);
	    root.right = new Node(6);
	    root.right.left = new Node(5);
	    System.out.println(bst.isValidBST(root));	//	true
	    root.right.right =  new Node(1);
	    System.out.println(bst.isValidBST(root));	//	false
	}
	
	 public boolean isValidBST(Node root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
     }
    
    private boolean isValidBST(Node root, long minVal, long maxVal) {
        if (root == null) 
        	return true;
        
        if (root.key >= maxVal || root.key <= minVal) 
        	return false;
        
        // update minVal and maxVal in recursive call 
        return isValidBST(root.left, minVal, root.key) && 
        		isValidBST(root.right, root.key, maxVal);
        
    }

}
