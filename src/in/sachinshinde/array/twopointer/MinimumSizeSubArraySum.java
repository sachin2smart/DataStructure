package in.sachinshinde.array.twopointer;

//	https://leetcode.com/problems/minimum-size-subarray-sum/

/*
 	Given an array of positive integers nums and a positive integer target, 
 	return the minimal length of a subarray whose sum is greater than or equal to target. 
 	If there is no such subarray, return 0 instead.

        Example 1:
        ----------
        Input: target = 7, nums = [2,3,1,2,4,3]
        Output: 2
        Explanation: The subarray [4,3] has the minimal length under the problem constraint.
        
        Example 2:
        ---------
        Input: target = 4, nums = [1,4,4]
        Output: 1
        
        Example 3:
        ---------
        Input: target = 11, nums = [1,1,1,1,1,1,1,1]
        Output: 0
         
        
        Constraints:
        -----------
            1 <= target <= 109
            1 <= nums.length <= 105
            1 <= nums[i] <= 104
 */

public class MinimumSizeSubArraySum {
    
    //	Sliding Window : video solution - https://youtu.be/aYqYMIqZx5s 
    public int minSubArrayLen(int target, int[] nums) {
	int left = 0, right = 0;
	int n = nums.length;
	int minLength = Integer.MAX_VALUE, sum = 0;
	
	while(right < n) {
	    sum += nums[right];
	    right++;
	    
	    while(sum >= target) {
		minLength = Math.min(minLength, right - left);
		sum -= nums[left];
		left++;
	    }
	}
	
	return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
    
    public static void main(String[] args) {
	MinimumSizeSubArraySum minSum = new MinimumSizeSubArraySum();
	System.out.println(minSum.minSubArrayLen(7, new int[] {2,3,1,2,4,3}));	// 2
	System.out.println(minSum.minSubArrayLen(1, new int[] {1,4,4}));  // 1
	System.out.println(minSum.minSubArrayLen(11, new int[] {1,1,1,1,1,1,1,1})); // 0
    }
}
