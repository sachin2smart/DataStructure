package in.sachinshinde.binarytree;

import java.util.ArrayList;
import java.util.List;

/*
 * https://leetcode.com/problems/path-sum/
 * https://leetcode.com/problems/path-sum-ii/
 */

public class PathSumInBinaryTree {
    //	Get all path node keys from root to leaf node having sum = targetSum
    public List<List<Integer>> pathSum(Node root, int sum) {
	List<List<Integer>> result = new ArrayList<>();
	List<Integer> currentPath = new ArrayList<>();
	pathSumUtil(root, sum, result, currentPath);
	return result;
    }

    private void pathSumUtil(Node root, int sum, List<List<Integer>> result, List<Integer> currentPath) {
        //	base condition
    	if (root == null)
            return;
        
    	//	termination condition
        if (root.left == null && root.right == null) {
        	//	check
            if (root.key == sum) {
                currentPath.add(root.key);
                result.add(new ArrayList<>(currentPath));	// since we found the sum, add as a new list to result 
                currentPath.remove(currentPath.size() - 1);	// go to the one level up
            }
            return;
        }
        currentPath.add(root.key);
        pathSumUtil(root.left, sum - root.key, result, currentPath);	// reduce sum by node value and go one level down left
        pathSumUtil(root.right, sum - root.key, result, currentPath);	// reduce sum by node value and go one level down right
        currentPath.remove(currentPath.size() - 1);	//	// go to the one level up
    }
    
    public static void main(String[] args) {
    	PathSumInBinaryTree pathSum = new PathSumInBinaryTree();
	Node root = new Node(1);
	root.left = new Node(1);
	root.left.left = new Node(1);
	root.right = new Node(2);
	List<List<Integer>> result = pathSum.pathSum(root, 3);
	System.out.println(result);	//	[[1, 1, 1], [1, 2]]
	System.out.println(pathSum.hasPathSum(root, 2));	// false
	System.out.println(pathSum.hasPathSum(root, 3));	// true
    }

    //	Program 2 : Check if the pathSum exists from root node to any leaf node
    public boolean hasPathSum(Node root, int sum) {
    	// base condition
        if (root == null) 
            return false;
        
        //	termination condition
        if (root.left == null && root.right == null) 
            return root.key == sum;	//	important check, if the value of node is equal to the sum remaining 
        
        // recursive calls
        return hasPathSum(root.left, sum - root.key) || 
        		hasPathSum(root.right, sum - root.key);		// sum can exists either in left or right tree 
    }
}
