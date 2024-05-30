package in.sachinshinde.array.medium;

import java.util.Arrays;

//	https://leetcode.com/problems/house-robber/

/*
 	You are a professional robber planning to rob houses along a street. 
 	Each house has a certain amount of money stashed, the only constraint stopping you 
 		from robbing each of them is that adjacent houses have security systems connected and 
 		it will automatically contact the police if two adjacent houses were broken into on the same night.

	Given an integer array nums representing the amount of money of each house, 
	return the maximum amount of money you can rob tonight without alerting the police.
	
	Example 1:
	---------
		Input: nums = [1,2,3,1]
		Output: 4
		Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
		Total amount you can rob = 1 + 3 = 4.
	
	Example 2:
	---------
		Input: nums = [2,7,9,3,1]
		Output: 12
		Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
		Total amount you can rob = 2 + 9 + 1 = 12.
	 
	
	Constraints:
	-----------
		1 <= nums.length <= 100
		0 <= nums[i] <= 400
 */

public class HouseRobber {
	
	//	1. Recursive Top-Down
    public int rob1(int[] nums) {
        return rob1Recursive(nums, nums.length -1);
    }
    
    private int rob1Recursive(int[] nums, int i) {
    	if(i < 0)
    		return 0;
    	return Math.max(rob1Recursive(nums,  i-2) + nums[i],
    					rob1Recursive(nums, i-1));
    }
    
    //	2. Recursive + memo (top-down)
    int[] memo;
    public int rob2(int[] nums) {
    	memo = new int[nums.length+1];
    	Arrays.fill(memo, -1);
        return rob2Recursive(nums, nums.length -1);
    }
    
    private int rob2Recursive(int[] nums, int i) {
    	if(i < 0)
    		return 0;
    	
    	if(memo[i] >= 0)
    		return memo[i];
    	
    	int result = Math.max(rob2Recursive(nums,  i-2) + nums[i],
    					rob2Recursive(nums, i-1));
    	
    	memo[i] = result;
    	
    	return result;
    }
    
    //	3. Iterative + memo (bottom-up)
    public int rob3(int[] nums) {
    	if(nums.length ==0)
    		return 0;
    	
    	int[] memo = new int[nums.length + 1];
    	memo[0] = 0;
    	memo[1] = nums[0];
    	
    	for(int i=1; i< nums.length; i++)
    		memo[i+1] = Math.max(memo[i], memo[i-1] + nums[i]); 
    	
    	return memo[nums.length];
    }
    
    //	4. Iterative + 2 variables (bottom-up)  
    public int rob4(int[] nums) {
    	if(nums.length ==0 )
    		return 0;
    	
    	int prev1 = 0;
    	int prev2 = 0;
    	
    	for(int num: nums) {
    		int temp = prev1;
    		prev1 = Math.max(prev2 + num, prev1);
    		prev2 = temp;
    	}
    	
    	return prev1;
    }

	public int rob5(int[] nums) {
		int n = nums.length;
		int[] dp = new int[n];

		dp[0] = nums[0];

		if(n == 1) {
			return nums[0];
		}

		dp[1] = Math.max(nums[0], nums[1]);

		for(int i=2; i<n; i++) {
			dp[i] = Math.max(nums[i] + dp[i-2], dp[i-1]);
		}

		return dp[n-1];

	}

	public int rob6(int[] nums) {
		if(nums.length == 0) {
			return 0;
		}

		int i = 0;
		int j = 0;

		for(int num: nums) {
			int prev = i;
			i = Math.max(j + num, i);
			j = prev;
		}

		return i;
	}
    
    public static void main(String[] args) {
		HouseRobber houseRobber = new HouseRobber();
		System.out.println(houseRobber.rob1(new int[] {1,2,3,1}));		//	4
		System.out.println(houseRobber.rob1(new int[] {2,7,9,3,1}));	//	12
		
		System.out.println(houseRobber.rob2(new int[] {1,2,3,1}));		//	4
		System.out.println(houseRobber.rob2(new int[] {2,7,9,3,1}));	//	12
		
		System.out.println(houseRobber.rob3(new int[] {1,2,3,1}));		//	4
		System.out.println(houseRobber.rob3(new int[] {2,7,9,3,1}));	//	12
		
		System.out.println(houseRobber.rob4(new int[] {1,2,3,1}));		//	4
		System.out.println(houseRobber.rob4(new int[] {2,7,9,3,1}));	//	12
	}
}
