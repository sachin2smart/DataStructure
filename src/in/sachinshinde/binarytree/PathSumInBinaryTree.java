package in.sachinshinde.binarytree;

import java.util.ArrayList;
import java.util.List;

/*
 * https://leetcode.com/problems/path-sum/
 * https://leetcode.com/problems/path-sum-ii/
 */

public class PathSumInBinaryTree {
	 public List<List<Integer>> pathSum(Node root, int sum) {
	        List<List<Integer>> result = new ArrayList<>();
	        List<Integer> currentPath = new ArrayList<>();
	        pathSumUtil(root, sum, result, currentPath);
	        return result;
	    }

	    private void pathSumUtil(Node root, int sum, List<List<Integer>> result, List<Integer> currentPath) {
	        if (root == null) {
	            return;
	        }
	        if (root.left == null && root.right == null) {
	            if (root.key == sum) {
	                currentPath.add(root.key);
	                result.add(new ArrayList<>(currentPath));
	                currentPath.remove(currentPath.size() - 1);
	            }
	            return;
	        }
	        currentPath.add(root.key);
	        pathSumUtil(root.left, sum - root.key, result, currentPath);
	        pathSumUtil(root.right, sum - root.key, result, currentPath);
	        currentPath.remove(currentPath.size() - 1);
	    }

	    public boolean hasPathSum(Node root, int sum) {
	        if (root == null) 
	            return false;
	        
	        if (root.left == null && root.right == null) 
	            return root.key == sum;
	        
	        return hasPathSum(root.left, sum - root.key) || 
	        		hasPathSum(root.right, sum - root.key);
	    }
}
