package in.sachinshinde.bst.basics;

import in.sachinshinde.bst.Node;

public class SearchDataInBST {
	
	static Node root;

	public Node search(Node root, int data) {
		
		if(root == null || root.key == data) {
			return root;
		}
		
		if(root.key > data) {
			search(root.left, data);
		}
		
		return search(root.right, data);
	}
	
	public Node constructBST(int arr[]) {
		int numOfNode = arr.length;
		Node root = null;
		
		for(int i=0; i<numOfNode; i++) {
			root = LevelOrder(root, arr[i]);
		}
		
		return root;
	}

	public Node LevelOrder(Node root, int data) {
		
		if(root == null) {
			return new Node(data);
		}

		if(data < root.key) {
			root.left = LevelOrder(root.left, data);
		}
		else {
			root.right = LevelOrder(root.right, data);
		}
		
		return root;
	}
	
	public void inorder(Node root) {
		if(root == null) {
			return;
		}
		inorder(root.left);
		System.out.print(" "+ root.key);
		inorder(root.right);
	}

	public static void main(String[] args) {
		SearchDataInBST bst = new SearchDataInBST();
		int arr[] = {1,5,2,8,6,9};
		root = bst.constructBST(arr);
		bst.inorder(root);
		
		if(bst.search(root, 3) != null) {
			System.out.print("\n Found");
		}
		else {
			System.out.print("\n Not Found");
		}

		if(bst.searchBST(root, 3) != null) {
			System.out.print("\n Found");
		}
		else {
			System.out.print("\n Not Found");
		}
	}

	// https://leetcode.com/problems/search-in-a-binary-search-tree/
	
	public Node searchBST(Node root, int val) {
        if(root == null || root.key == val) {
			return root;
		}
        else {
			return val < root.key ? searchBST(root.left, val) : searchBST(root.right, val);
		}
    }
	
}
