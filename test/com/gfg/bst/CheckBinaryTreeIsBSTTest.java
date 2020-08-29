package com.gfg.bst;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.gfg.bst.Node;

public class CheckBinaryTreeIsBSTTest {

	CheckBinaryTreeIsBST checkBinaryTreeIsBST = new CheckBinaryTreeIsBST();
	
	@Test
	public void checkEmptyTree() {
		checkBinaryTreeIsBST.root = null;
		assertTrue(checkBinaryTreeIsBST.isBST(checkBinaryTreeIsBST.root));
	}
	
	@Test
	public void testWithSingleNode() {
		checkBinaryTreeIsBST.root = new Node(5);
		assertTrue(checkBinaryTreeIsBST.isBST(checkBinaryTreeIsBST.root));
	}
	
	@Test
	public void testWithCorrectNodeStructureOfBST_1() {
		
		/*
			      500
			      /  \
			    400  700
		   
		 */
		
		checkBinaryTreeIsBST.root = new Node(500);
		checkBinaryTreeIsBST.root.left = new Node(400);
		checkBinaryTreeIsBST.root.right = new Node(700);
		assertTrue(checkBinaryTreeIsBST.isBST(checkBinaryTreeIsBST.root));
	}
	
	@Test
	public void testWithCorrectNodeStructureOfBST_2() {
		
		/*
		           25
		         /   \
		        /     \
		       9       30 
		      / \      / \
		     6   11   27 35
		      
		    
		*/
		
		checkBinaryTreeIsBST.root = new Node(25);
		checkBinaryTreeIsBST.root.left = new Node(9);
		checkBinaryTreeIsBST.root.left.left = new Node(6);
		checkBinaryTreeIsBST.root.left.right = new Node(11); 
		checkBinaryTreeIsBST.root.right = new Node(30);
		checkBinaryTreeIsBST.root.right.left = new Node(27);
		checkBinaryTreeIsBST.root.right.right = new Node(35);
		
		assertTrue(checkBinaryTreeIsBST.isBST(checkBinaryTreeIsBST.root));
	}
	
	@Test
	public void testWithIncorrectNodeStructureOfBST() {
		
		/*
			      700
			      /  \
			    400  500
		   
		 */
		
		checkBinaryTreeIsBST.root = new Node(700);
		checkBinaryTreeIsBST.root.left = new Node(400);
		checkBinaryTreeIsBST.root.right = new Node(500);
		assertFalse(checkBinaryTreeIsBST.isBST(checkBinaryTreeIsBST.root));
	}
	

}
