package in.sachinshinde.dp.range_sum;

//	https://leetcode.com/problems/maximum-subarray/

/*
 *	Given an integer array nums, 
 *		find the contiguous subarray (containing at least one number) 
 *		which has the largest sum and return its sum.
 *
 *	A subarray is a contiguous part of an array. 
 */

/*
 * 	Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 	Output: 6
 * 	Explanation: [4,-1,2,1] has the largest sum = 6.
 */

public class MaximumSubArraySum {

	public int maxSubArray(int[] nums) {
		int n = nums.length;
		int[] dp = new int[n];

		dp[0] = nums[0];
		int max = dp[0];

		for(int i=1; i<n; i++) {
			dp[i] = nums[i] + (dp[i-1]>0 ? dp[i-1] : 0);
			max = Math.max(max, dp[i]);
		}
		
		return max;
	}
	
}

//	maximum sum at i'th index = max sum so far or dp[i] where dp[i] is nums[i] + previous index sum (if greater than 0)