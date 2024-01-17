package in.sachinshinde.bst;

import java.util.Arrays;

//	https://leetcode.com/problems/unique-binary-search-trees/

/*
 	Given an integer n, return the number of structurally unique BST's (binary search trees) 
 		which has exactly n nodes of unique values from 1 to n.


        Example 1:
        ---------
        Input: n = 3
        Output: 5
        
        Example 2:
        ---------
        Input: n = 1
        Output: 1
        
        Constraints:
        	1 <= n <= 19
 */

//	Video Link: https://youtu.be/Ox0TenN3Zpg

public class UniqueBSTs {

    // Using Dynamic Programming 
    /*
    	numTrees[4] = numTrees[0] * numTrees[3]  +
     		      numTrees[1] * numTrees[2]	 +
     		      numTrees[2] * numTrees[1]	 +
     		      numTrees[3] * numTrees[1]
     */
    
    public int numTrees(int n) {
        int dp[] = new int[n+1];
	Arrays.fill(dp, 0);
		
	dp[0] = 1;	// with 0 nodes - we could have 1 tree 
	dp[1] = 1;	// with 1 nodes - we could have 1 tree
		
	for(int i=2; i<=n; i++) {
	    for(int j=1; j<=i; j++) {
		int numOfLeftSubTrees = i-j;
		int numOfRightSubTrees = j-1;
		dp[i] += dp[numOfLeftSubTrees] * dp[numOfRightSubTrees];
	    }
	}
	
	return dp[n];
    }
	
	public static void main(String args[]) {
	    UniqueBSTs bst = new UniqueBSTs();
	    System.out.println(bst.numTrees(3));	// 5
	    System.out.println(bst.numTrees(1));	// 1
	}
}
