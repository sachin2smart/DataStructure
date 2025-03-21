package in.sachinshinde.array.medium;

//	https://leetcode.com/problems/maximum-subarray/

/*
 *	Given an integer array nums, 
 *		find the contiguous subarray (containing at least one number) 
 *		which has the largest sum and return its sum.
 *
 *	A subarray is a contiguous part of an array. 
 */

/*
	Example 1:

	Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
	Output: 6
	Explanation: The subarray [4,-1,2,1] has the largest sum 6.
	Example 2:

	Input: nums = [1]
	Output: 1
	Explanation: The subarray [1] has the largest sum 1.
	Example 3:

	Input: nums = [5,4,-1,7,8]
	Output: 23
	Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.


	Constraints:

	1 <= nums.length <= 105
	-104 <= nums[i] <= 104


	Follow up: If you have figured out the O(n) solution,
		try coding another solution using the divide and conquer approach,
		which is more subtle.

 */


public class MaximumSubArraySum {

	public int maxSubArray(int[] nums) {
		int res = nums[0];
		int total = 0;

		for (int num : nums) {
			if (total < 0) {
				total = 0;
			}

			total += num;
			res = Math.max(res, total);
		}

		return res;
	}

	public int maxSubArray2(int[] nums) {
		int n = nums.length;
		int[] dp = new int[n];

		dp[0] = nums[0];
		int max = dp[0];

		for(int i = 1; i < n; i++) {
			dp[i] = nums[i] + (Math.max(dp[i - 1], 0));
			max = Math.max(max, dp[i]);
		}
		
		return max;
	}

	public static void main(String[] args) {
		MaximumSubArraySum maximumSubArraySum = new MaximumSubArraySum();
		System.out.println(maximumSubArraySum.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));	// 6
		System.out.println(maximumSubArraySum.maxSubArray(new int[]{1}));	// 1
		System.out.println(maximumSubArraySum.maxSubArray(new int[]{5,4,-1,7,8}));	// 23
		System.out.println(maximumSubArraySum.maxSubArray(new int[]{-6,-1,-2,-3,-4,-5}));	// -1
		System.out.println("-----------");
		System.out.println(maximumSubArraySum.maxSubArray2(new int[]{-2,1,-3,4,-1,2,1,-5,4}));	// 6
		System.out.println(maximumSubArraySum.maxSubArray2(new int[]{1}));	// 1
		System.out.println(maximumSubArraySum.maxSubArray2(new int[]{5,4,-1,7,8}));	// 23
		System.out.println(maximumSubArraySum.maxSubArray2(new int[]{-6,-1,-2,-3,-4,-5}));	// -1
		System.out.println("-----------");
		System.out.println(maximumSubArraySum.maxSubArray3(new int[]{-2,1,-3,4,-1,2,1,-5,4}));	// 6
		System.out.println(maximumSubArraySum.maxSubArray3(new int[]{1}));	// 1
		System.out.println(maximumSubArraySum.maxSubArray3(new int[]{5,4,-1,7,8}));	// 23
		System.out.println(maximumSubArraySum.maxSubArray3(new int[]{-6,-1,-2,-3,-4,-5}));	// -1
		System.out.println("-----------");
		System.out.println(maximumSubArraySum.maxSubArray4(new int[]{-2,1,-3,4,-1,2,1,-5,4}));	// 6
		System.out.println(maximumSubArraySum.maxSubArray4(new int[]{1}));	// 1
		System.out.println(maximumSubArraySum.maxSubArray4(new int[]{5,4,-1,7,8}));	// 23
		System.out.println(maximumSubArraySum.maxSubArray4(new int[]{-6,-1,-2,-3,-4,-5}));	// -1
	}

	//	Kedane's Algorithm
	public int maxSubArray3(int[] nums) {
		int max = Integer.MIN_VALUE, sum = 0;

		for (int num : nums) {
			sum += num;
			max = Math.max(sum, max);

			if (sum < 0) {
				sum = 0;
			}
		}

		return max;
	}

	public int maxSubArray4(int[] nums) {
		int maxSum = nums[0];
		int currSum = nums[0];
		for (int i = 1; i < nums.length; i++) {
			currSum = Math.max(currSum + nums[i], nums[i]);
			maxSum = Math.max(maxSum, currSum);
		}
		return maxSum;
	}

}

//	maximum sum at i'th index = max sum so far or dp[i] where dp[i] is nums[i] + previous index sum (if greater than 0)