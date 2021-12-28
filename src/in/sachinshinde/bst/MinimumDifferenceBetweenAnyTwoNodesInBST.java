package in.sachinshinde.bst;

public class MinimumDifferenceBetweenAnyTwoNodesInBST {

	int min = Integer.MAX_VALUE;
	Integer prev = null;
	
	private int getMinimumDifferenceBetweenAnyTwoNodesInBST(Node root) {
		
		if(root == null)
			return min;
		
		getMinimumDifferenceBetweenAnyTwoNodesInBST(root.left);
		
		if(prev != null)
			min = Math.min(min, root.key - prev);
		
		prev = root.key;
		
		getMinimumDifferenceBetweenAnyTwoNodesInBST(root.right);
		
		return min;
	}
}
