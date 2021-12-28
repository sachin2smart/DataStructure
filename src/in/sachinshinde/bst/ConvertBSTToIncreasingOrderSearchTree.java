package in.sachinshinde.bst;

// https://leetcode.com/problems/increasing-order-search-tree/

public class ConvertBSTToIncreasingOrderSearchTree {
	
	Node prev = null; 
	Node head = null;
	
    public Node increasingBST(Node root) {
        if(root==null) 
        	return null;
        
        increasingBST(root.left);
        
        if(prev!=null) { 
        	root.left=null; // we no  longer needs the left  side of the node, so set it to null
        	prev.right=root; 
        }
        
        if(head==null) 
        	head=root; // record the most left node as it will be our root
        
        prev=root; //keep track of the prev node
        
        increasingBST(root.right); 
        
        return head;
    }
}
