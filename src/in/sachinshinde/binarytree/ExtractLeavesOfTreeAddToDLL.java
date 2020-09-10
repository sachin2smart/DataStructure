package in.sachinshinde.binarytree;

public class ExtractLeavesOfTreeAddToDLL {

	static Node head;
	static Node prev;
	
	static Node extractNode(Node root) {
		
		if(root == null)
			return null;
		
		if(root.left == null && root.right == null) {
			if(head ==null) {
				head = root;
				prev = root;
			}
			else {
				prev.right = root;
				root.left = prev;
				prev = root;
			}
			return null;
		}
		else {
			root.left = extractNode(root.left);
			root.right = extractNode(root.right);
		}
			
		return root;
		
	}
	
	public static void printTree(Node root) {
		
		if(root == null)
			return;
		
		System.out.print(" "+root.key);
		printTree(root.left);
		printTree(root.right);
	}
	
	public static void printDLL(Node root) {
		
	}
	
	public static void main(String[] args) {
		
		Node root = new Node(1);
		
		root.left = new Node(2);
		root.right = new Node(3);
		
		root.left.left = new Node(5);
		root.right.right = new Node(6);
		
		printTree(root);
		System.out.println();
		
		extractNode(root);
		
		printTree(root);
		System.out.println();
	}

}
