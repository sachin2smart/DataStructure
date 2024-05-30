package in.sachinshinde.bst.construct_bst;

import in.sachinshinde.bst.Node;

public class SortedArrayToBST {

	static void preorder(Node root) {
		if(root == null)
			return;
		System.out.print(" "+ root.key);
		preorder(root.left);
		preorder(root.right);
	}
	
	
	static Node constructBST(int arr[]) {
		int start = 0;
		int end = arr.length-1;
		
		Node root = bstFromSortedArray(arr,start, end);
		
		return root;
	}

	static Node bstFromSortedArray(int arr[], int start, int end) {
		
		if(start > end) 
			return null;
		
		int mid  = (start + end) / 2;
		
		Node root = new Node(arr[mid]);
		root.left = bstFromSortedArray(arr, start, mid-1);
		root.right = bstFromSortedArray(arr, mid+1, end );
		
		return root;
	}
	
	public static void main(String args[]) {
		int arr[] = {1,2,3,4,5,6,7};
		
		Node root = constructBST(arr);
		
		preorder(root);
	}
}
