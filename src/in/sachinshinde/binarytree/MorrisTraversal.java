package in.sachinshinde.binarytree;

public class MorrisTraversal {
	public static void main(String args[]) {
		Node root = new Node(10);
		root.left = new Node(8);
		root.left.left = new Node(3);
		root.left.right = new Node(5);
		root.right = new Node(2);
		root.right.left = new Node(7);
		
		System.out.println("\n Inorder Traversal : ");
		inOrderTraversal(root);
		
		System.out.println("\n Preorder Traversal : ");
		preOrderTraversal(root);
		
	}
	
	private static void inOrderTraversal(Node root) {
		Node curr = root;
		while(curr != null) {
			if(curr.left == null) {
				System.out.print(" "+ curr.key);
				curr = curr.right;
			}
			else {
				Node predecessor = curr.left;
				while(predecessor.right != null && predecessor.right != curr) {
					predecessor = predecessor.right;
				}
				if(predecessor.right == null) {
					predecessor.right = curr;
					curr = curr.left;
				}
				else {
					predecessor.right = null;
					System.out.print(" "+ curr.key);
					curr = curr.right;
				}
			}
		}
	}
	
	private static void preOrderTraversal(Node root) {
		Node curr = root;
		while(curr != null) {
			if(curr.left == null) {
				System.out.print(" "+ curr.key);
				curr = curr.right;
			}
			else {
				Node predecessor = curr.left;
				while(predecessor.right != null && predecessor.right != curr) {
					predecessor = predecessor.right;
				}
				if(predecessor.right == null) {
					predecessor.right = curr;
					System.out.print(" "+ curr.key);
					curr = curr.left;
				}
				else {
					predecessor.right = null;
					curr = curr.right;
				}
			}
		}
	}
}
