package in.sachinshinde.bst.custom;

/*
 * 	https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 */


public class NextRightPointerForEachNodeInPerfectBST {
	
	public TreeLinkNode connect(TreeLinkNode root) {
	   
		// In perfect Binary Tree - Left node should not be a NULL
       if(root == null || root.left == null) 
	       return root;

       TreeLinkNode curr = root;

       while(curr != null && curr.left != null) { // check for left node NULL
    	   TreeLinkNode nextLevelLeftmostNode = curr.left;

            while(curr != null){
                curr.left.next = curr.right;    // next pointer for left node
                if(curr.next != null) 
                     curr.right.next = curr.next.left; // next pointer for right node 
                curr = curr.next;
            }

            curr = nextLevelLeftmostNode;
        }
        
        return root;
    }
}
