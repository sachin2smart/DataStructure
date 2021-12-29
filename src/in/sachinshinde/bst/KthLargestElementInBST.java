package in.sachinshinde.bst;

//	https://practice.geeksforgeeks.org/problems/kth-largest-element-in-bst/1#
//	https://takeuforward.org/data-structure/kth-largest-smallest-element-in-binary-search-tree/

public class KthLargestElementInBST {
	
    public int kthLargest(Node root, int k)
    {
        int[] k1 = new int[]{k};
        Node n = helper(root, k1);
        return n.key;
    }
    
    private Node helper(Node root, int[] k){
        if(root == null)
	        return null;
	
	    Node right = helper(root.right, k);
	
	    if(right != null)
	        return right;
	        
	    k[0]--;
	
	    if(k[0] == 0)
	        return root;
	
	    return helper(root.left, k);
    }
}
