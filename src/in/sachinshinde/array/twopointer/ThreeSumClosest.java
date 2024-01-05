package in.sachinshinde.array.twopointer;

import java.util.Arrays;

//	https://leetcode.com/problems/3sum-closest/

/*
 	Given an integer array nums of length n and an integer target, 
 		find three integers in nums such that the sum is closest to target.

        Return the sum of the three integers.
        You may assume that each input would have exactly one solution.
        
        Example 1:
        ---------
        Input: nums = [-1,2,1,-4], target = 1
        Output: 2
        Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
        
        Example 2:
        ---------
        Input: nums = [0,0,0], target = 1
        Output: 0
        Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).
        
        Constraints:
            3 <= nums.length <= 500
            -1000 <= nums[i] <= 1000
            -104 <= target <= 104
 */

public class ThreeSumClosest {
    
    public int threeSumClosest(int[] array, int target) {
        Arrays.sort(array);
        int diff = Integer.MAX_VALUE, res = 0;
        for (int i = 0; i < array.length - 2; i++) {
            int leftP = i + 1, rightP = array.length - 1;
            while (leftP < rightP) {
                int sum = array[i] + array[leftP] + array[rightP];
                if (sum == target) {
                    return target;
                } else if (sum > target) {
                    if (diff > sum - target) {
                        diff = sum - target;
                        res = sum;
                    }
                    rightP--;
                } else {
                    if (diff > target - sum) {
                        diff = target - sum;
                        res = sum;
                    }
                    leftP++;
                }
            }

        }
        return res;
    }
    
    public int threeSumClosest2(int[] nums, int target) {
        Arrays.sort(nums);
        int closestSum = nums[0] + nums[1] + nums[2]; 

        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;	//	Left Pointer
            int k = nums.length - 1;	//	Right Pointer

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];

                if(sum == target) {
                    return sum;    
                }
                
                if (sum < target) {
                    j++;
                } 
                else {
                    k--; 
                }
                
                if (Math.abs(target - sum) < Math.abs(target - closestSum)) {
                    closestSum = sum; 
                }

            }
        }

        return closestSum;        
    }

    public static void main(String[] args) {
	ThreeSumClosest closest = new ThreeSumClosest();
	
	System.out.println(closest.threeSumClosest(new int[] {-1,2,1,-4}, 1));	// 2
	System.out.println(closest.threeSumClosest2(new int[] {0,0,0}, 3));	// 0
	
	System.out.println(closest.threeSumClosest(new int[] {-1,2,1,-4}, 1));	// 2
	System.out.println(closest.threeSumClosest2(new int[] {0,0,0}, 0));	// 0
    }
}
