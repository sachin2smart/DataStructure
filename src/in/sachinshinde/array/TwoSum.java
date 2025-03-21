package in.sachinshinde.array;

import java.util.Arrays;
import java.util.HashMap;

//	https://leetcode.com/problems/two-sum/

/*
 * 	Given an array of integers nums and an integer target, return indices of the two numbers such that 
 * 	they add up to target.
 * 	You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * 	You can return the answer in any order.
 *
 *
 *      Example 1:
        ---------
        Input: nums = [2,7,11,15], target = 9
        Output: [0,1]
        Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

        * Example 2:
        ---------
        Input: nums = [3,2,4], target = 6
        Output: [1,2]

        * Example 3:
        ---------
        Input: nums = [3,3], target = 6
        Output: [0,1]


        Constraints:
        ------------
            2 <= nums.length <= 104
            -109 <= nums[i] <= 109
            -109 <= target <= 109

        * Only one valid answer exists.

        Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?

 */

public class TwoSum {
	public int[] twoSum(int[] nums, int target) {
        // value to index map
        HashMap<Integer, Integer> hm = new HashMap<>();

        for(int i = 0; i < nums.length; i++)
            if(hm.containsKey(target - nums[i])) {  // if the other value exists in the map
                return new int[] { hm.get(target - nums[i]), i };   // the first value gives index of other value
            }
            else {
                hm.put(nums[i], i); //  mapping value to the index
            }
        
        return null;
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        System.out.println(Arrays.toString(twoSum.twoSum(new int[] {2,7,11,15}, 9)));   //  [0, 1]
        System.out.println(Arrays.toString(twoSum.twoSum(new int[] {3,2,4}, 6)));   //  [1, 2]
        System.out.println(Arrays.toString(twoSum.twoSum(new int[] {3,3}, 6)));     //  [0, 1]
    }
}
