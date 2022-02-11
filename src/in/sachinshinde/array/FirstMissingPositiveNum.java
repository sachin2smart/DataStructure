package in.sachinshinde.array;

//	https://leetcode.com/problems/first-missing-positive/

/*
 * 	Given an unsorted integer array nums, return the smallest missing positive integer.
 * 	You must implement an algorithm that runs in O(n) time and uses constant extra space.
 */

public class FirstMissingPositiveNum {

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

    	if(nums == null || n == 0) 
    		return 1;

        for(int i = 0; i<n; i++) {
            int curr = nums[i];
            while(curr-1 >= 0 && curr-1 < n && curr != nums[curr-1]) {
                int next = nums[curr-1];
                nums[curr-1] = curr;
                curr = next;
            }
        }

        for(int i = 0; i<n; i++) {
            if(nums[i] != i+1) 
            	return i+1;
        }

        return n+1;  
    }
	
}

/*
	
	Input: nums = [1,2,0]
	Output: 3
	
	Input: nums = [3,4,-1,1]
	Output: 2
*/