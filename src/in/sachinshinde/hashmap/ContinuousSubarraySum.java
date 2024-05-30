package in.sachinshinde.hashmap;

import java.util.HashMap;
import java.util.Map;

//	https://leetcode.com/problems/continuous-subarray-sum/

/*
 	Given an integer array nums and an integer k, return 
 		true if nums has a good subarray or 
 		false otherwise.

        A good subarray is a subarray where:
        
        its length is at least two, and
        the sum of the elements of the subarray is a multiple of k.
        Note that:
        
        A subarray is a contiguous part of the array.
        An integer x is a multiple of k if there exists an integer n such that
         	x = n * k.
         0 is always a multiple of k.
         
        
        Example 1:
        ---------
            Input: nums = [23,2,4,6,7], k = 6
            Output: true
            Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.
        
        Example 2:
        ---------
            Input: nums = [23,2,6,4,7], k = 6
            Output: true
            Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
            42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.
        
        Example 3:
        ---------
            Input: nums = [23,2,6,4,7], k = 13
            Output: false
         
        
        Constraints:
        -----------
            1 <= nums.length <= 105
            0 <= nums[i] <= 109
            0 <= sum(nums[i]) <= 231 - 1
            1 <= k <= 231 - 1
 */

// Video Solution : Video Solution: https://youtu.be/OKcrLfR-8mE

public class ContinuousSubarraySum {
    
    //	-----------------------------------------------------------------------------
    public boolean checkSubarraySum(int[] nums, int k) {
        // Map of <Remainder, Index>
        Map<Integer, Integer> hm = new HashMap<>();
        hm.put(0, -1);

        int currSum = 0;

        for(int i = 0; i < nums.length; i++) {
            currSum += nums[i];

            int rem = currSum % k;

            if(hm.containsKey(rem)) {
                if(i - hm.get(rem) >= 2) {
                    return true;
                }
            }
            else {
                hm.put(rem, i);
            }
	    }
	    return false;
    }
    //	-----------------------------------------------------------------------------    
    public boolean checkSubarraySum2(int[] nums, int k) {
        Map<Integer,Integer> hm  = new HashMap<>();
        hm.put(0,0);
        
        int currSum = 0; 
        
        for( int  i = 0;i < nums.length; i++) {
            currSum += nums[i];
      
            if(hm.containsKey(currSum % k)) {
                if(hm.get(currSum % k)  < i)
                    return true;
            }
            else
                hm.put(currSum % k, i+1);
            
        }
        return false;
    }
   //	----------------------------------------------------------------------------- 
    //	Video Solution: https://youtu.be/OKcrLfR-8mE
    public boolean checkSubarraySum3(int[] nums, int k) {
        Map<Integer,Integer> hm  = new HashMap<>();
        hm.put(0, -1);
        
        int currSum = 0; 
        
        for( int  i = 0;i < nums.length; i++) {
            currSum += nums[i];
      
            if(hm.containsKey(currSum % k)) {
                if(i- hm.get(currSum % k) > 1)
                    return true;
            }
            else
                hm.put(currSum % k, i);
            
        }
        return false;
    }
    
    public static void main(String[] args) {
	ContinuousSubarraySum arr = new ContinuousSubarraySum();
	System.out.println(arr.checkSubarraySum(new int[] {23,2,4,6,7}, 6));
	System.out.println(arr.checkSubarraySum(new int[] {23,2,6,4,7}, 6));
	System.out.println(arr.checkSubarraySum(new int[] {23,2,6,4,7}, 13));
	
	System.out.println(arr.checkSubarraySum2(new int[] {23,2,4,6,7}, 6));
	System.out.println(arr.checkSubarraySum2(new int[] {23,2,6,4,7}, 6));
	System.out.println(arr.checkSubarraySum2(new int[] {23,2,6,4,7}, 13));
	
	System.out.println(arr.checkSubarraySum3(new int[] {23,2,4,6,7}, 6));
	System.out.println(arr.checkSubarraySum3(new int[] {23,2,6,4,7}, 6));
	System.out.println(arr.checkSubarraySum3(new int[] {23,2,6,4,7}, 13));
    }
}
