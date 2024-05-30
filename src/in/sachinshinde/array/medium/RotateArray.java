package in.sachinshinde.array.medium;

import java.util.Arrays;

//	https://leetcode.com/problems/rotate-array/

/*
 
 	Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.

        Example 1:
        ---------
        Input: nums = [1,2,3,4,5,6,7], k = 3
        Output: [5,6,7,1,2,3,4]
        Explanation:
        rotate 1 steps to the right: [7,1,2,3,4,5,6]
        rotate 2 steps to the right: [6,7,1,2,3,4,5]
        rotate 3 steps to the right: [5,6,7,1,2,3,4]
        
        Example 2:
        ---------
        Input: nums = [-1,-100,3,99], k = 2
        Output: [3,99,-1,-100]
        Explanation: 
        rotate 1 steps to the right: [99,-1,-100,3]
        rotate 2 steps to the right: [3,99,-1,-100]

 */

public class RotateArray {
	public static void main(String[] args) {
		int a[] = {1,2,3,4,5,6,7};
		int r = 3;
		System.out.println(Arrays.toString(a));
		a = rotateArray(a, r);
		System.out.println(Arrays.toString(a));
		
		int[] b = {1,2,3,4,5};
		r = 2;
		System.out.println(Arrays.toString(b));
		b = rotateArray(b, r);
		System.out.println(Arrays.toString(b));
	}
	
	static int[] rotateArray(int nums[], int k) {
	    if(k> nums.length) {
			k %= nums.length;
		}
	    reverse(nums, 0, nums.length - 1);	// rotated array as [7,6,5,4,3,2,1].
	    reverse(nums, 0, k - 1);				// rotated array as [5,6,7,4,3,2,1].
	    reverse(nums, k, nums.length - 1);			// rotated array as [5,6,7,1,2,3,4].
	    return nums;
	}

	static void reverse(int[] nums, int start, int end) {
	    while (start < end) {
	        int temp = nums[start];
	        nums[start] = nums[end];
	        nums[end] = temp;
	        start++;
	        end--;
	    }
	}

}
