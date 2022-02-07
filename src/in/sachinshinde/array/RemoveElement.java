package in.sachinshinde.array;

//	https://leetcode.com/problems/remove-element/

/*
 *	Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
 * 	Return k after placing the final result in the first k slots of nums
 */

public class RemoveElement {

	public int removeElement(int[] nums, int val) {
	    int k = 0;
	    for(int i=0; i<nums.length; i++) 
	    	if(nums[i] != val) 
	    		nums[k++] = nums[i];
	    
	    return k;
	}
	
}
