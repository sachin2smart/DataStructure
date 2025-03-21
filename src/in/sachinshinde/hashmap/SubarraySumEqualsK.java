package in.sachinshinde.hashmap;

import java.util.HashMap;
import java.util.Map;

//	https://leetcode.com/problems/subarray-sum-equals-k/
//	Video Solution : https://youtu.be/fFVZt-6sgyo

/*
 	Given an array of integers nums and an integer k, 
 		return the total number of subarrays whose sum equals to k.

        A subarray is a "contiguous" non-empty sequence of elements within an array.
        
        
        Example 1:
        ---------
        Input: nums = [1,1,1], k = 2
        Output: 2
        
        Example 2:
        ---------
        Input: nums = [1,2,3], k = 3
        Output: 2
        
        Constraints:
        -----------
        	1 <= nums.length <= 2 * 104
        	-1000 <= nums[i] <= 1000
        	-107 <= k <= 107 
 */

public class SubarraySumEqualsK {
    
    public int subarraySum(int[] nums, int k) { 
	// Map : PrefixSum -> Count
        Map<Integer, Integer> hm = new HashMap<>();
        int sum = 0, res = 0;
        
        for(int num: nums) {
            sum += num;
            
            if(sum == k) {
                res++;
            }
            
            if(hm.containsKey(sum - k)) {    // if prefix sum found, add the corresponding count into the result
                res += hm.get(sum - k);
            }
            
            hm.put(sum, hm.getOrDefault(sum, 0) + 1);
        }
        
        return res;
    }
    
    public int subarraySum_2(int[] nums, int k) {
        int sum = 0, res = 0;

        // Map : PrefixSum -> Count
        Map<Integer, Integer> hm = new HashMap<>();
        hm.put(0, 1);   //  why?
        
        for(int num: nums) {
            sum += num;
            
            if(hm.get(sum-k) != null) {    //	hm value is of Integer type, if the key does not have a value - it will return a NULL
                res += hm.get(sum - k);
            }
             
            hm.put(sum, hm.getOrDefault(sum, 0) + 1);
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        SubarraySumEqualsK sum = new SubarraySumEqualsK();
        System.out.println(sum.subarraySum(new int[] {1,-1,1,1,1,1}, 3));	// 4
        System.out.println(sum.subarraySum(new int[] {0,0,0,0,0,0}, 0));	// 21
        System.out.println(sum.subarraySum_2(new int[] {1,-1,1,1,1,1}, 3));	// 4
        System.out.println(sum.subarraySum(new int[] {0,0,0,0,0,0}, 0));	// 21

        System.out.println(sum.subarraySum3(new int[] {1,-1,1,1,1,1}, 3));	// 4
        System.out.println(sum.subarraySum3(new int[] {0,0,0,0,0,0}, 0));	// 21
    }

    //  without hashmap
    public int subarraySum3(int[] nums, int k) {
        int count = 0;

        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        for (int start = 0; start < sum.length; start++) {
            for (int end = start + 1; end < sum.length; end++) {
                if (sum[end] - sum[start] == k) {
                    count++;
                }
            }
        }

        return count;
    }
}