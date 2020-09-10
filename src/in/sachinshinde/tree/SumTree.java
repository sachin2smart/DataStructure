package in.sachinshinde.tree;

public class SumTree {

	Node root;
	
	static boolean isLeafNode(Node node) {
		if(node == null)
			return false;
		
		if(node.left != null && node.right != null)
			return true;
		
		return false;
	}
	
	static boolean isSumTree(Node node) {
		
		if(node == null || isLeafNode(node))
			return true;
		
		int ls, rs;
		
		if(isSumTree(node.left) && isSumTree(node.right)) {
			if(node.left == null)
				ls = 0;
			else if (isLeafNode(node.left))
				ls = node.left.key;
			else 
				ls = 2 * node.left.key;
			
			if(node.right == null)
				rs = 0;
			else if(isLeafNode(node.right))
				rs = node.right.key;
			else
				rs = 2 * node.right.key;
			
			if(node.key == ls+rs)
				return true;
			else
				return false;
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		SumTree tree = new SumTree(); 
        tree.root = new Node(26); 
        tree.root.left = new Node(10); 
        tree.root.right = new Node(3); 
        tree.root.left.left = new Node(4); 
        tree.root.left.right = new Node(6); 
        tree.root.right.right = new Node(3);
        
        if(isSumTree(tree.root)) {
        	System.out.println("It is a Sum Tree.");
        }
        else {
        	System.out.println("Not a Sum Tree.");
        }
	}

}
