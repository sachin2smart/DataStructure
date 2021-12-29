package in.sachinshinde.bst;

//	https://practice.geeksforgeeks.org/problems/predecessor-and-successor/1
//	https://leetcode.ca/all/285.html
//	https://leetcode.ca/2016-09-10-285-Inorder-Successor-in-BST/

public class InorderPredecessorAndSuccessorInBST {
	
	public Node inorderSuccessor(Node root, Node s) {

        if (root == null) {
            return null;
        }

        if (root.key <= s.key) {
            return inorderSuccessor(root.right, s);
        }
        else {
            Node left = inorderSuccessor(root.left, s);
            return left == null ? root : left;
        }

    }
	
	public Node inorderPredecessor(Node root, Node p) {

        if (root == null) {
            return null;
        }

        if (root.key <= p.key) {
            Node right =  inorderPredecessor(root.right, p);
            return right == null ? root : right;
        }
        else {
            return inorderSuccessor(root.left, p);
        }

    }
	
}
