package in.sachinshinde.binarytree;

public class SymmetricTreeCheck {
	
    public boolean isSymmetric(Node root) {
        return root == null || areChildrenSymmetric(root.left, root.right);   // OR Condition
    }
    
    private boolean areChildrenSymmetric(Node left, Node right){
        
        if(left == null || right == null)	// OR Condition
            return left == right; // important
        
        if(left.key != right.key)
            return false;
        
        return areChildrenSymmetric(left.left, right.right) && areChildrenSymmetric (left.right, right.left);   // AND Condition
    }
    
    public static void main(String[] args) {
		SymmetricTreeCheck symmetricTreeCheck = new SymmetricTreeCheck();
		
		Node root = null;
		System.out.println(symmetricTreeCheck.isSymmetric(root));	//	true
		
		root = new Node(1);
		System.out.println(symmetricTreeCheck.isSymmetric(root));	//	true
		
		root.left = new Node(2);
		root.right = new Node(2);
		System.out.println(symmetricTreeCheck.isSymmetric(root));	//	true
		
		root.left.left = new Node(3);
		System.out.println(symmetricTreeCheck.isSymmetric(root));	//	false
		
		root.right.right = new Node(3);
		System.out.println(symmetricTreeCheck.isSymmetric(root));	//	true
		
	}
}
