package in.sachinshinde.array.twopointer;

import java.util.Arrays;

//	https://leetcode.com/problems/squares-of-a-sorted-array

/*
 * 	Given an integer array nums sorted in non-decreasing order, 
 * 	return an array of the squares of each number sorted in non-decreasing order.

        Example 1:
        ---------
        Input: nums = [-4,-1,0,3,10]
        Output: [0,1,9,16,100]
        Explanation: After squaring, the array becomes [16,1,0,9,100].
        After sorting, it becomes [0,1,9,16,100].
        
        Example 2:
        ---------
        Input: nums = [-7,-3,2,3,11]
        Output: [4,9,9,49,121]
 */

public class SquaresOfASortedArray {
    
    public int[] sortedSquares(int[] nums) {
        
        int n = nums.length;
        int l = 0, r = n-1;
        
        int[] res = new int[n];
        
        for(int p = n-1; p>=0; p--) {
            if(nums[l] * nums[l] > nums[r] * nums[r]) {
                res[p] = nums[l] * nums[l];
                l++;
            }
            else {
                res[p] = nums[r] * nums[r];                   
                r--;
            }
        }
        
        return res;
    }
    
    public static void main(String[] args) {
	SquaresOfASortedArray array = new SquaresOfASortedArray();
	System.out.println(Arrays.toString(array.sortedSquares(new int[] {-4,-1,0,3,10})));	//	[0,1,9,16,100]
	System.out.println(Arrays.toString(array.sortedSquares(new int[] {-7,-3,2,3,11})));	//	[4,9,9,49,121]
    }

}
