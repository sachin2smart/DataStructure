package in.sachinshinde.dp.minimize;

import java.util.Arrays;

//	https://leetcode.com/problems/minimum-operations-to-make-array-equal-ii/

/*
 	You are given two integer arrays nums1 and nums2 of equal length n and 
 	an integer k. 
 	
 	You can perform the following operation on nums1:
        	Choose two indexes i and j and increment nums1[i] by k and 
        	decrement nums1[j] by k. 
        	In other words, nums1[i] = nums1[i] + k and nums1[j] = nums1[j] - k.
        	nums1 is said to be equal to nums2 if for all indices i such that 
        	0 <= i < n, nums1[i] == nums2[i].
        
        Return the minimum number of operations required to make nums1 equal to nums2. 
        If it is impossible to make them equal, return -1.
        
        Example 1:
        ---------
            Input: nums1 = [4,3,1,4], nums2 = [1,3,7,1], k = 3
            Output: 2
            Explanation: In 2 operations, we can transform nums1 to nums2.
            1st operation: i = 2, j = 0. After applying the operation, nums1 = [1,3,4,4].
            2nd operation: i = 2, j = 3. After applying the operation, nums1 = [1,3,7,1].
            One can prove that it is impossible to make arrays equal in fewer operations.
        
        Example 2:
        ---------
            Input: nums1 = [3,8,5,2], nums2 = [2,4,1,6], k = 1
            Output: -1
            Explanation: It can be proved that it is impossible to make the two arrays equal.
        
        Constraints:
        -----------
            n == nums1.length == nums2.length
            2 <= n <= 105
            0 <= nums1[i], nums2[j] <= 109
            0 <= k <= 105
 */

public class MinimumOperationsToMakeArrayEqual2 {
    public long minOperations(int[] nums1, int[] nums2, int k) {
        if (k == 0) {
            return Arrays.equals(nums1, nums2) ? 0 : -1;
        }
        
        long nums1Diff = 0, nums2Diff = 0;
        int n = nums1.length-1;
        
        for(int i = 0; i <= n; i++) {
            if (nums1[i] >= nums2[i]) {
                if ((nums1[i] - nums2[i]) % k == 0) {
                    nums1Diff += nums1[i] - nums2[i];
                } 
                else {
                    return -1;
                }
            }
            else {
                if ((nums2[i] - nums1[i]) % k == 0) {
                    nums2Diff += nums2[i] - nums1[i];
                }
                else {
                    return -1;
                }
            }
        }
        
        return (nums1Diff - nums2Diff == 0) ? nums1Diff/k : -1;
    }
    
    //	------------------------------------------------------------------------------
    
    public long minOperations2(int[] nums1, int[] nums2, int k) {
        if(k == 0) 
            return Arrays.equals(nums1, nums2) ? 0 : -1;
        
        long counter = 0, sum = 0;
        
        for(int i=0; i<nums1.length; i++){
            int diff = nums1[i] - nums2[i];
            
            if(diff % k != 0) 
        	return -1;
            
            counter += Math.max(0, Math.abs(diff)/k );
            sum += diff;
        }
        
        return sum == 0 ? counter/2 : -1;
    }
    
    //	------------------------------------------------------------------------------
    
    public static void main(String[] args) {
	MinimumOperationsToMakeArrayEqual2 arr = new MinimumOperationsToMakeArrayEqual2();
	System.out.println(arr.minOperations(new int[] {4,3,1,4}, new int[] {1,3,7,1}, 3)); // 2
	System.out.println(arr.minOperations(new int[] {3,8,5,2}, new int[] {2,4,1,6}, 1)); // -1
	
	System.out.println(arr.minOperations2(new int[] {4,3,1,4}, new int[] {1,3,7,1}, 3)); // 2
	System.out.println(arr.minOperations2(new int[] {3,8,5,2}, new int[] {2,4,1,6}, 1)); // -1
    }
}
