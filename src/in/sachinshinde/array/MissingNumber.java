package in.sachinshinde.array;

import java.util.Arrays;

//	https://leetcode.com/problems/missing-number/

/*
 	Given an array nums containing n distinct numbers in the range [0, n], 
 	return the only number in the range that is missing from the array.
 	
 	Example 1:
	Input: nums = [3,0,1]
	Output: 2
	Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 
	2 is the missing number in the range since it does not appear in nums.
	
	Example 2:
	Input: nums = [0,1]
	Output: 2
	Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 
	2 is the missing number in the range since it does not appear in nums.
	
	Example 3:
	Input: nums = [9,6,4,2,3,5,7,0,1]
	Output: 8
	Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 
	8 is the missing number in the range since it does not appear in nums.
 */

public class MissingNumber {
	//	using xor, Time:O(N), Space: O(1)
	public int missingNumber(int[] nums) { 
	    int res = nums.length;
	    for(int i=0; i<nums.length; i++)
	        res ^= i ^ nums[i];
	    return res;
	}
	
	public static void main(String[] args) {
		MissingNumber missingNumber = new MissingNumber();
		System.out.println(missingNumber.missingNumber(new int[] {4,1,3,2}));	//	0
		System.out.println(missingNumber.missingNumber(new int[] {0,1,3,2}));	//	4
		System.out.println(missingNumber.missingNumber(new int[] {0,1,4,2}));	//	3
		
		System.out.println(missingNumber.missingNumber2(new int[] {4,1,3,2}));	//	0
		System.out.println(missingNumber.missingNumber2(new int[] {0,1,3,2}));	//	4
		System.out.println(missingNumber.missingNumber2(new int[] {0,1,4,2}));	//	3
		
		System.out.println(missingNumber.missingNumber3(new int[] {4,1,3,2}));	//	0
		System.out.println(missingNumber.missingNumber3(new int[] {0,1,3,2}));	//	4
		System.out.println(missingNumber.missingNumber3(new int[] {0,1,4,2}));	//	3
	}
	
	//	by calculating sum, Time: O(N), Space: O(1)
	public int missingNumber2(int[] nums) {
	    int len = nums.length;
	    int sum = (0+len)*(len+1)/2;
	    for(int i=0; i<len; i++)
	        sum-=nums[i];
	    return sum;
	}
	
	//	using binary search, Time:O(NlogN), Space: O(N)
	public int missingNumber3(int[] nums) { 
	    Arrays.sort(nums);
	    int left = 0, right = nums.length, mid= (left + right)/2;
	    while(left<right) {
	        mid = (left + right)/2;
	        if(nums[mid]>mid) 
	        	right = mid;
	        else 
	        	left = mid+1;
	    }
	    return left;
	}
}
