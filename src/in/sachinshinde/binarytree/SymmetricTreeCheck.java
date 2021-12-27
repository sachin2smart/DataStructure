package in.sachinshinde.binarytree;

public class SymmetricTreeCheck {
	
    public boolean isSymmetric(Node root) {
        return root == null || helper(root.left, root.right);   // OR Condition
    }
    
    private boolean helper(Node left, Node right){
        
        if(left == null || right == null)	// OR Condition
            return left == right; // important
        
        if(left.key != right.key)
            return false;
        
        return helper(left.left, right.right) && helper (left.right, right.left);   // AND Condition
    }
}
