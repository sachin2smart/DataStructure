package in.sachinshinde.binarytree.traversals;

//	https://www.geeksforgeeks.org/given-a-binary-tree-print-all-root-to-leaf-paths/

import in.sachinshinde.binarytree.Node;

public class PrintAllRootToLeafNodes {

	private static void getPaths(Node root) {
		int a[] = new int[1000];
		int len = 0;
		getRootToLeafPaths(root, a, len);
	}
	
	private static void getRootToLeafPaths(Node root, int a[], int len) {
		
		if(root == null)
			return;
		
		a[len] = root.key;
		len++;
		
		if(root.left==null && root.right==null) {
			printPathFound(a, len);
		}
		else {
			getRootToLeafPaths(root.left, a, len);
			getRootToLeafPaths(root.right, a, len);
		}
	}
	
	private static void printPathFound(int[] a, int len) {
		for(int i=0; i<len; i++) {
			System.out.print(a[i]+ " -> ");
		}
		System.out.print("NULL");
		System.out.println();
	}

	public static void main(String[] args) {
		Node root = new Node(10);
		root.left = new Node(8);
		root.left.left = new Node(3);
		root.left.right = new Node(5);
		root.right = new Node(2);
		
		getPaths(root);
	}

}
