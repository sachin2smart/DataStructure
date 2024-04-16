package in.sachinshinde.binarytree.checks_on_tree;

//	https://leetcode.com/problems/subtree-of-another-tree/

/*
 * 	Given the roots of two binary trees root and subRoot, 
 * 		return true if there is a subtree of root with the same structure and 
 * 		node values of subRoot and false otherwise.

	A subtree of a binary tree tree is a tree that consists of a node in tree and 
		all of this node's descendants. 
	The tree tree could also be considered as a subtree of itself.

 	Example-1:
 	--------
 	Input: root = [3,4,5,1,2], subRoot = [4,1,2]
	Output: true
	
	Example-2:
 	--------
 	Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
	Output: false
 */

import in.sachinshinde.binarytree.Node;

public class SubtreeOfAnotherTree {
    public boolean isSubtree(Node s, Node t) {
        if(s == null) 
            return false;
        
        if(isSame(s, t)) 
            return true;
        
        return isSubtree(s.left, t) || 
        	isSubtree(s.right, t);
    }
    
    private boolean isSame(Node s, Node t) {
        if(s == null && t == null) 
            return true;
        
        if(s == null || t == null) 
            return false;
        
        if(s.key != t.key) 
            return false;
        
        return isSame(s.left, t.left) && 
        	isSame(s.right, t.right);
    }
    
    public static void main(String[] args) {
	SubtreeOfAnotherTree tree = new SubtreeOfAnotherTree();
	
	Node s = new Node(3);
	s.left = new Node(4);
	s.right = new Node(5);
	s.left.left = new Node(1);
	s.left.right = new Node(2);
	
	Node t = new Node(4);
	t.left = new Node(1);
	t.right = new Node(2);
	
	System.out.println(tree.isSubtree(s, t));	//	true
	
	s.left.right.left = new Node(0);
	System.out.println(tree.isSubtree(s, t));	//	false
    }

}
