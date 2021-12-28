// https://www.geeksforgeeks.org/convert-bst-to-a-binary-tree/

package in.sachinshinde.bst;

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
	
	Node addGreater2(Node root) {
		int[] sum = new int[] {0};
		addGreaterUtil2(root, sum);
		return root;
	}

	void addGreaterUtil2(Node root, int[] sum) {
		if(root==null)
			return;
		addGreaterUtil2(root.right, sum);
		sum[0] = sum[0] + root.key;
		root.key = sum[0];
		addGreaterUtil2(root.left, sum);
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
		
		// Another way of passing value by array 
		BinarySumTree bSumT2 = new BinarySumTree();
		bSumT2.root = new Node(5);
		bSumT2.root.left = new Node(2);
		bSumT2.root.right = new Node(13);
		
		System.out.println("\n\n By passing a value with an array :");
		bSumT2.inorder(root);
		System.out.println("\n Result : ");
		bSumT2.addGreater2(bSumT2.root);
		bSumT2.inorder(bSumT2.root);
		
	}

}
