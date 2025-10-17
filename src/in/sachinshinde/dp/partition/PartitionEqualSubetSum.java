package in.sachinshinde.dp.partition;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//	https://leetcode.com/problems/partition-equal-subset-sum

/*
 	Given a non-empty array nums containing only positive integers, 
 	find if the array can be partitioned into two subsets such that 
 		the sum of elements in both subsets is equal.

	Example 1:
		Input: nums = [1,5,11,5]
		Output: true
	Explanation: The array can be partitioned as [1, 5, 5] and [11].

	Example 2:
		Input: nums = [1,2,3,5]
		Output: false
	Explanation: The array cannot be partitioned into equal sum subsets.
 
 */

public class PartitionEqualSubetSum {

	public boolean canPartition(int[] nums) {
        int sum = 0;

        for (int num : nums)
            sum += num;
        
        if ((sum & 1) == 1)
            return false;
        
        sum /= 2;

        boolean[] dp = new boolean[sum+1];
        Arrays.fill(dp, false);
        dp[0] = true;

        for (int num : nums) 
            for (int i = sum; i > 0; i--) 
                if (i >= num) 
                    dp[i] = dp[i] || dp[i-num];
        
        return dp[sum];
    }
	
	public static void main(String[] args) {
		PartitionEqualSubetSum sum = new PartitionEqualSubetSum();
		System.out.println(sum.canPartition(new int[] {1,5,11,5}));		//	true
		System.out.println(sum.canPartition(new int[] {1,2,3, 5}));		//	false

        System.out.println(sum.canPartition_1(new int[] {1,5,11,5}));		//	true
        System.out.println(sum.canPartition_1(new int[] {1,2,3, 5}));		//	false
	}

    public boolean canPartition_1(int[] nums) {
        int sum = 0;

        for(int i: nums) {
            sum += i;
        }

        if(sum % 2 != 0) {
            return false;
        }

        int target = sum/2;

        Set<Integer> set = new HashSet<>();
        set.add(0);

        for (int num: nums) {
            Set<Integer> tempSet = new HashSet<>(set);
            for (Integer subInt : tempSet) {
                if (subInt + num == target) {
                    return true;
                }
                if (subInt + num < target) {
                    set.add(subInt + num);
                }
            }
        }

        return false;
    }
}