package in.sachinshinde.bst.find_from_bst;

import in.sachinshinde.bst.Node;

public class FindPreSuccOfkeyInBST {
	static Node pre;
	static Node succ;
	
	static void inorder(Node root) {
		if(root == null)
			return;
		inorder(root.left);
		System.out.print(" "+ root.key);
		inorder(root.right);
	}
	
	static Node constructBST(int arr[]) {
		int numOfNode = arr.length;
		Node root = null;
		
		for(int i=0; i<numOfNode; i++) {
			root = LevelOrder(root, arr[i]);
		}
		
		return root;
	}

	static Node LevelOrder(Node root, int data) {
		
		if(root == null) 
			return new Node(data);
		
		if(data < root.key)
			root.left = LevelOrder(root.left, data);
		else
			root.right = LevelOrder(root.right, data);
		
		return root;
	}
	
	static void findPreSuccNodes(Node root, int key) {
		if(root == null)
			return;
		
		while(root != null) {
			if(root.key == key) {
				if(root.right != null) {
					succ = root.right;
					while(succ.left != null)
						succ = succ.left;
				}
				
				if(root.left != null) {
					pre = root.left;
					while(pre.right != null)
						pre = pre.right;
				}
				return;
			}
			else if(root.key < key) {
				pre = root;
				root = root.right;
			}
			else {
				succ = root;
				root = root.left;
			}
		}
	}
	
	public static void main(String args[]) {
		int arr[] = {8, 5, 10, 3, 4, 9, 7};
		
		Node root = constructBST(arr);
	
		inorder(root);
		System.out.println();
			
		findPreSuccNodes(root, 6); 
	    if (pre != null) 
	        System.out.println("Predecessor is " +  
	                                     pre.key); 
	    else
	        System.out.println("-1"); 
	  
	    if (succ != null) 
	        System.out.println("Successor is " + succ.key); 
	    else
	        System.out.print("-1"); 
	   

	}
}
