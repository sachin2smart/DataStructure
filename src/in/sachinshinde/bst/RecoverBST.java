package in.sachinshinde.bst;

//	https://leetcode.com/problems/recover-binary-search-tree/

/*
 * 	Given the root of a binary search tree (BST), 
 * 		where the keyues of exactly two nodes of the tree were swapped by mistake. 
 * 	Recover the tree without changing its structure.
 */

public class RecoverBST {
	
	// 	First Solution 
	
    Node firstElement = null;
    Node secondElement = null;
    Node prevElement = new Node(Integer.MIN_VALUE);
    
    public void recoverTree1(Node root) {
        traverse(root);
        int temp = firstElement.key;
        firstElement.key = secondElement.key;
        secondElement.key = temp;
    }
    
    private void traverse(Node root) {
        
        if (root == null)
            return;
            
        traverse(root.left);

        if (firstElement == null && prevElement.key >= root.key) {
            firstElement = prevElement;
        }
        if (firstElement != null && prevElement.key >= root.key) {
            secondElement = root;
        }        
        prevElement = root;

        traverse(root.right);
    }
    
    // Another Solution 
    
    private Node first = null;
    private Node second = null;
    private Node pre = null;
    
    public void recoverTree2(Node root) {
        if(root==null) 
            return;
        
        inorder(root);
        int temp = first.key;
        first.key = second.key;
        second.key = temp;
    }
    
    private void inorder(Node root){
        if(root==null) 
            return;
        
        inorder(root.left);
        
        if(first==null && (pre==null ||pre.key>=root.key)){
            first = pre;
        }
        
        if(first!=null && pre.key>=root.key){
            second = root;
        }
        
        pre = root;
        
        inorder(root.right);
    }
}
