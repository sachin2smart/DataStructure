package in.sachinshinde.bst.find_from_bst;

//	https://practice.geeksforgeeks.org/problems/kth-largest-element-in-bst/1#
//	https://takeuforward.org/data-structure/kth-largest-smallest-element-in-binary-search-tree/

import in.sachinshinde.bst.Node;

public class KthLargestElementInBST {
	
	int curr = 0;
	
    public int kthLargest(Node root, int k) {
    	curr = k;
        Node n = helper(root);
        return n.key;
    }
    
    private Node helper(Node root) {
        if(root == null)
	        return null;
	
	    Node right = helper(root.right);
	
	    if(right != null)
	        return right;
	        
	    curr--;
	
	    if(curr == 0)
	        return root;
	
	    return helper(root.left);
    }
    
    public static void main(String[] args) {
		KthLargestElementInBST bst = new KthLargestElementInBST();
		Node n = new Node(3);
		n.left = new Node(2);
		n.left.left = new Node(1);
		n.right = new Node(5);
		n.right.left = new Node(4);
		n.right.right = new Node(6);
		
		System.out.println(bst.kthLargest(n, 2));
	}
}
