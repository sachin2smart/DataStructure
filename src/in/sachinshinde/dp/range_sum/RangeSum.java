package in.sachinshinde.dp.range_sum;

//	https://leetcode.com/problems/range-sum-query-immutable/

/*
 * -----------------------------
 * 	Range Sum Query - Immutable
 * -----------------------------
 * 	Given an integer array nums, handle multiple queries of the following type:
 * 		(1) Calculate the sum of the elements of nums between indices left and right inclusive 	
 * 			where left <= right.
		(2) Implement the NumArray class:
			- RangeSum(int[] nums) 
				Initializes the object with the integer array nums.
			- int sumRange(int left, int right) 
				Returns the sum of the elements of nums between indices left and right inclusive 
				(i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 
 */

/*
 * 	Input
		["NumArray", "sumRange", "sumRange", "sumRange"]
		[[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
	Output
		[null, 1, -1, -3]
 */

//	https://www.youtube.com/watch?v=FoLSpCsQ0rA

// Approach : Dynamic Programming

public class RangeSum {
	int[] dp;
    public RangeSum(int[] nums) {
        int n = nums.length;
        dp = new int[n+1];
        dp[0] = 0;
        for(int i=1; i<=n; i++)
            dp[i] = dp[i-1] + nums[i-1];
        
    }
    
    public int sumRange(int left, int right) {
        return dp[right+1] - dp[left];
    }
    
    public static void main(String[] args) {
		RangeSum rangeSum = new RangeSum(new int[] {-2,0,3,-5,2,-1});
		System.out.println(rangeSum.sumRange(0, 2));	//  1
		System.out.println(rangeSum.sumRange(2, 5));	// -1
		System.out.println(rangeSum.sumRange(0, 5));	// -3
	}
}
