package in.sachinshinde.bst.find_from_bst;

import in.sachinshinde.bst.Node;

import java.util.LinkedList;
import java.util.List;

//	https://leetcode.com/problems/unique-binary-search-trees-ii/

/*
 	Given an integer n, return all the structurally unique BST's (binary search trees), 
 	which has exactly n nodes of unique values from 1 to n. Return the answer in any order.

        Example 1:
        ---------
        Input: n = 3
        Output: [[1,null,2,null,3],
        	[1,null,3,2],
        	[2,1,3],
        	[3,1,null,null,2],
        	[3,2,null,1]]

        Example 2:
        ---------
        Input: n = 1
        Output: [[1]]
        
        Constraints:
        -----------
        	1 <= n <= 8
 */

//	Video Link : https://youtu.be/m907FlQa2Yc

public class UniqueBSTs2 {
    public List<Node> generateTrees(int n) {
		return generateSubtrees(1, n);
    }

    private List<Node> generateSubtrees(int start, int end) {
		List<Node> res = new LinkedList<>();

		if (start > end) {
			res.add(null);
			return res;
		}

		for (int i = start; i <= end; ++i) {
			List<Node> leftSubtrees = generateSubtrees(start, i - 1);
			List<Node> rightSubtrees = generateSubtrees(i + 1, end);

			for (Node left : leftSubtrees) {
				for (Node right : rightSubtrees) {
					Node root = new Node(i);
					root.left = left;
					root.right = right;
					res.add(root);
				}
			}
		}

		return res;
    }
    
    public void print(Node root) {
		if(root == null) {
			System.out.print(" "+ null);
			return;
		}

		System.out.print(" " + root.key);

		if(root.left != null || root.right != null) {
			print(root.left);
			print(root.right);
		}
    }
    
    public static void main(String[] args) {
		UniqueBSTs2 trees = new UniqueBSTs2();
		List<Node> nodes = trees.generateTrees(3);

		for(Node root: nodes) {
			trees.print(root);
			System.out.println();
		}
	/*
		Result:
	 	 		 1 null 2 null 3
                 1 null 3 2 null
                 2 1 3
                 3 1 null 2 null
                 3 2 1 null null
	 */
    }
}