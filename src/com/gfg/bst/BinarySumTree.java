
package com.gfg.bst;

public class BinarySumTree {

	static Node root;
	Sum sum_to_carry = new Sum();
	
	Node addGreater(Node root) {
		addGreaterUtil(root, sum_to_carry);
		return root;
	}

	void addGreaterUtil(Node root, Sum sum_to_carry) {
		if(root==null)
			return;
		addGreaterUtil(root.right, sum_to_carry);
		sum_to_carry.sum = sum_to_carry.sum + root.key;
		root.key = sum_to_carry.sum;
		addGreaterUtil(root.left, sum_to_carry);
	}
	
	void inorder(Node root) {
		if(root == null)
			return;
		inorder(root.left);
		System.out.print(" "+ root.key);
		inorder(root.right);
	}
	
	public static void main(String[] args) {
		BinarySumTree bSumT = new BinarySumTree();
		bSumT.root = new Node(5);
		bSumT.root.left = new Node(2);
		bSumT.root.right = new Node(13);
		
		bSumT.inorder(root);
		System.out.println();
		
		bSumT.addGreater(root);
		
		bSumT.inorder(root);
		
	}

}
