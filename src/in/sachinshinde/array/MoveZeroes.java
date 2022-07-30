package in.sachinshinde.array;

import java.util.Arrays;

//	https://leetcode.com/problems/move-zeroes/

/*
 	Given an integer array nums, 
 	move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 	
	Note that you must do this in-place without making a copy of the array.
 */

public class MoveZeroes {
	public void moveZeroes(int[] nums) {
		if(nums == null || nums.length == 0)
			return;        

	    int i = 0;
	    for(int num: nums)
	        if(num != 0) 
	        	nums[i++] = num;
	    
	    while(i < nums.length)
	        nums[i++] = 0;
    }
	
	public static void main(String[] args) {
		MoveZeroes moveZeroes = new MoveZeroes();
		int[] nums = new int[] {0,3,0};
		moveZeroes.moveZeroes(nums);
		System.out.println(Arrays.toString(nums));	//	[3, 0, 0]
	}
}
