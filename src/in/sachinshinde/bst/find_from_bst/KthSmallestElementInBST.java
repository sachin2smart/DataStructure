package in.sachinshinde.bst.find_from_bst;

//	https://leetcode.com/problems/kth-smallest-element-in-a-bst/

/*
 *	 Given the root of a binary search tree, and an integer k, 
 *		return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 */

import in.sachinshinde.bst.Node;

public class KthSmallestElementInBST {
	
    public int kthSmallest(Node root, int k) {
	    int count = numOfNodes(root.left);
	    
	    if (k <= count)
	        return kthSmallest(root.left, k);
	    else if (k > count + 1)
	        return kthSmallest(root.right, k-(count+1));
	    
	    return root.key;
    }
    
    public int numOfNodes(Node n) {
        if (n == null) 
            return 0;
        
        return numOfNodes(n.left) + 1 + numOfNodes(n.right);
    }
    
    public static void main(String[] args) {
		KthSmallestElementInBST bst = new KthSmallestElementInBST();
		Node n = new Node(3);
		n.left = new Node(2);
		n.left.left = new Node(1);
		n.right = new Node(5);
		n.right.left = new Node(4);
		n.right.right = new Node(6);
		
		System.out.println(bst.kthSmallest(n, 2));
		System.out.println(bst.kthSmallest2(n, 2));
	}
    
    //	Method 2
    int curr = 0;
	
    public int kthSmallest2(Node root, int k) {
    	curr = k;
        Node n = helper(root);
        return n.key;
    }
    
    private Node helper(Node root) {
        if(root == null)
	        return null;
	
	    Node left = helper(root.left);
	
	    if(left != null)
	        return left;
	        
	    curr--;
	
	    if(curr == 0)
	        return root;
	
	    return helper(root.right);
    }

	public int kthSmallest_2(Node root, int k) {
		int numNodesInLeftTree = countNodes(root.left);

		if (numNodesInLeftTree == k - 1) {	//	early return
			return root.key;
		}

		if (numNodesInLeftTree >= k) {
			return kthSmallest(root.left, k);
		}

		return kthSmallest(root.right, k - 1 - numNodesInLeftTree); // if (leftCount < k) - Search in right subtree
	}

	private int countNodes(Node root) {
		if (root == null) {
			return 0;
		}
		return 1 + countNodes(root.left) + countNodes(root.right);
	}
}
