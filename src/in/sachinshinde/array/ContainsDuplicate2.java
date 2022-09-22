package in.sachinshinde.array;

import java.util.HashSet;
import java.util.Set;

//	https://leetcode.com/problems/contains-duplicate-ii/

/*
 	Given an integer array nums and an integer k, 
 	return true if there are two distinct indices 
 		i and j in the array such that nums[i] == nums[j] and 
 		abs(i - j) <= k.

	Example 1:
	---------
	Input: nums = [1,2,3,1], k = 3
	Output: true
	
	Example 2:
	---------
	Input: nums = [1,0,1,1], k = 1
	Output: true
	
	Example 3:
	---------
	Input: nums = [1,2,3,1,2,3], k = 2
	Output: false
	
 */

public class ContainsDuplicate2 {
	public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> hs = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++) {
            if(i > k) 
            	hs.remove(nums[i-k-1]);
            
            if(hs.contains(nums[i]))
            	return true;
            
            hs.add(nums[i]);
        }
        return false;
	}
	
	public static void main(String[] args) {
		ContainsDuplicate2 dups = new ContainsDuplicate2();
		System.out.println(dups.containsNearbyDuplicate(new int[] {1,2,3,1}, 3));
	}
}
