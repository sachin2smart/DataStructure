package in.sachinshinde.binarytree.traversals;

import in.sachinshinde.binarytree.Node;

import java.util.ArrayList;
import java.util.List;

/*

Binary Tree:
-----------

           1
        /     \
       2       3
      / \    /   \
     4   5  6     7
        /       /   \
       8       9     10


	 Inorder Traversal   : [4, 2, 8, 5, 1, 6, 3, 9, 7, 10]

	 Preorder Traversal  : [1, 2, 4, 5, 8, 3, 6, 7, 9, 10]

	 Postorder Traversal : [4, 8, 5, 2, 6, 9, 10, 7, 3, 1]
    --------------------------------------------------------------------------------------

    Given: Root of a Binary Tree
    Return: List of node values with Inorder, Preorder, Postorder traversals

 */

public class BinaryTreeTraversals {

	public static void main(String[] args) {
		BinaryTreeTraversals tree = new BinaryTreeTraversals();

		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.left.right.left = new Node(8);
		root.right.left = new Node(6);
		root.right.right = new Node(7);
		root.right.right.left = new Node(9);
		root.right.right.right = new Node(10);

		List<Integer> resultInorder = tree.inorderTraversalUtil(root);
		System.out.println("\n Inorder Traversal : " + resultInorder);

		List<Integer> resultPreorder = tree.preorderTraversalUtil(root);
		System.out.println("\n Preorder Traversal : " + resultPreorder);

		List<Integer> resultPostorder = tree.postorderTraversalUtil(root);
		System.out.println("\n Postorder Traversal : " + resultPostorder);
	}

	public List<Integer> inorderTraversalUtil(Node root){
		List<Integer> result = new ArrayList<>();
		inorder(root, result);
		return result;
	}
	
	private static void inorder(Node root, List<Integer> result) {
		if(root == null) {
			return;
		}

		inorder(root.left, result);
		result.add(root.key);
		inorder(root.right, result);
	}

	public List<Integer> preorderTraversalUtil(Node root){
		List<Integer> result = new ArrayList<>();
		preorder(root, result);
		return result;
	}

	private static void preorder(Node root, List<Integer> result) {
		if(root == null) {
			return;
		}

		result.add(root.key);
		preorder(root.left, result);
		preorder(root.right, result);
	}

	public List<Integer> postorderTraversalUtil(Node root){
		List<Integer> result = new ArrayList<>();
		postorder(root, result);
		return result;
	}

	private static void postorder(Node root, List<Integer> result) {
		if(root == null) {
			return;
		}

		postorder(root.left, result);
		postorder(root.right, result);
		result.add(root.key);
	}
}
