package in.sachinshinde.binarytree;

import org.junit.Assert;
import org.junit.Test;

import in.sachinshinde.binarytree.checks_on_tree.CheckBinaryTreeIsBST;

public class CheckBinaryTreeIsBSTTest {

	@Test
	public void testNullTreeIsBST() {
		Node root = null;
		Assert.assertTrue(CheckBinaryTreeIsBST.isBST(root, null, null));
	}
	
	@Test
	public void testSingleNodeBinaryTreeIsBST() {
		Node root = new Node(5);
		Assert.assertTrue(CheckBinaryTreeIsBST.isBST(root, null, null));
	}
	
	@Test
	public void testValidBinaryTreeIsBST_1() {
		
		/*
				 5
				/ \
			   4   6
		*/
		
		Node root = new Node(5);
		root.left = new Node(4);
		root.right = new Node(6);
		Assert.assertTrue(CheckBinaryTreeIsBST.isBST(root, null, null));
	}
	
	@Test
	public void testValidBinaryTreeIsBST_2() {
		
		/*
				  5
				 / \
				/   \
			   3     8
			  /\    / \
			 2  4  6  10
			   
		*/
		
		Node root = new Node(5);
		root.left = new Node(3);
		root.left.left = new Node(2);
		root.left.right = new Node(4);
		root.right = new Node(8);
		root.right.left = new Node(6);
		root.right.right = new Node(10);
		Assert.assertTrue(CheckBinaryTreeIsBST.isBST(root, null, null));
	}
	
	@Test
	public void testInvalidBinaryTreeIsBST_1() {
		
		/*
				 5
				/ \
			   4   2
		*/
	
		Node root = new Node(5);
		root.left = new Node(4);
		root.right = new Node(2);
		Assert.assertFalse(CheckBinaryTreeIsBST.isBST(root, null, null));
	}

	@Test
	public void testInvalidBinaryTreeIsBST_2() {
		
		/*
				  5
				 / \
				/   \
			   3     8
			  /\    / \
			 2  1  6   4
			   
		*/
		Node root = new Node(5);
		root.left = new Node(3);
		root.left.left = new Node(2);
		root.left.right = new Node(1);
		root.right = new Node(8);
		root.right.left = new Node(6);
		root.right.right = new Node(4);
		Assert.assertFalse(CheckBinaryTreeIsBST.isBST(root, null, null));
	}
	
}
