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

	/* Steps:
			1.	Reverse the entire array
			2.	Reverse the array (0...k-1)
			3.	Reverse the array (k...n-1)
	 */
	static void rotateArray(int[] nums, int k) {	//	input : 1,2,3,4,5,6,7
		int n = nums.length;

		if(k > n) {
			k %= n;
		}

	    reverse(nums, 0, n - 1);		// rotated array as [7,6,5,4,3,2,1].
	    reverse(nums, 0, k - 1);		// rotated array as [5,6,7,4,3,2,1].
	    reverse(nums, k, n - 1);				// rotated array as [5,6,7,1,2,3,4].
	}

	static void reverse(int[] nums, int start, int end) {
	    while (start < end) {
	        int currNum = nums[start];
	        nums[start] = nums[end];
	        nums[end] = currNum;
	        start++;
	        end--;
	    }
	}

	public static void main(String[] args) {
		int[] a = {1,2,3,4,5,6,7};
		int r = 3;
		System.out.println(Arrays.toString(a));
		rotateArray(a, r);
		System.out.println(Arrays.toString(a));

		int[] b = {1,2,3,4,5};
		r = 2;
		System.out.println(Arrays.toString(b));
		rotateArray(b, r);
		System.out.println(Arrays.toString(b));
	}

}
