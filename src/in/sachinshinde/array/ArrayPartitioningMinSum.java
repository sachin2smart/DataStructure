package in.sachinshinde.array;

import java.util.Arrays;

//	https://leetcode.com/problems/array-partition-i/

/*
 *	Given an integer array nums of 2n integers, group these integers into 
 *		n pairs (a1, b1), (a2, b2), ..., (an, bn) 
 *	such that the sum of min(ai, bi) for all i is maximized. Return the maximized sum.
 *
 * 	Example 1:
	
	Input: nums = [1,4,3,2]
	Output: 4
	Explanation: All possible pairings (ignoring the ordering of elements) are:
	1. (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
	2. (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
	3. (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4
	So the maximum possible sum is 4.

 */
	
public class ArrayPartitioningMinSum {
	
	public static int getMaxArrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int result = 0;
        for (int i = 0; i < nums.length; i += 2) // incremented by 2
            result += nums[i];
        return result;
    }
	
	public static void main(String[] args) {
		int[] nums = new int[] {1,1,4,2,1,3};
		
		int maxArrayPairSum = getMaxArrayPairSum(nums);
		System.out.println("Max sum from grouping 2 elements : "+ maxArrayPairSum);
	}
}


/*	## Explanation : 
 * 
 * 	The array is of size 2n, meaning its size is even.

	Consider the input [6,4,2,1,3,5]
	
	First, we sort the array from the smallest to largest -
	
	[1,2,3,4,5,6]
	
	Then, we don't need to create all of the sets, as we can simply pick the largest minimum of each pair 
	by increasing our cursor by 2.
	
	So, our sets are basically - (1,2), (3,4), (5,6)
	
	Now, we must pick the minimum and since we've sorted the array - n[i] will be <= n[i+1]
	
	So - n[i] is always the minimum of (n[i], n[i+1])
	
	Iterating the array by 2 will give us the desired result -
	
	1 + 3 + 5 = 9
 */

