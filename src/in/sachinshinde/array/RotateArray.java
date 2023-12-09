package in.sachinshinde.array;

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

	static int[] rotateArray(int arr[], int rorateBy) {
		for(int i=0; i<rorateBy ; i++) 
			arr = rorateArrayByOne(arr);
		
		return arr;
	}
	
	static int[] rorateArrayByOne(int[] arr) {
		int temp = arr[0];
		
		for(int i=0; i < arr.length-1; i++)
			arr[i] = arr[i+1];
		
		arr[arr.length-1] = temp;
		
		return arr;
	}
	
	public static void main(String[] args) {
		int a[] = {1,2,3,4,5};
		int r = 2;
		System.out.println(Arrays.toString(a));
		a = rotateArray(a, r);
		System.out.println(Arrays.toString(a));
		
		int[] b = {1,2,3,4,5};
		r = 2;
		System.out.println(Arrays.toString(b));
		b = rotateArray(b, r);
		System.out.println(Arrays.toString(b));
	}
	
	static void rotateArray_2(int nums[], int k) {
	    k %= nums.length;
	    reverse(nums, 0, nums.length - 1);
	    reverse(nums, 0, k - 1);
	    reverse(nums, k, nums.length - 1);
	    
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
