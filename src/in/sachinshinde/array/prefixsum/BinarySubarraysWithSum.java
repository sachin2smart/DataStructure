package in.sachinshinde.array.prefixsum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//  https://leetcode.com/problems/binary-subarrays-with-sum

/*
        Given a binary array nums and an integer goal,
            return the number of non-empty subarrays with a sum goal.

        A subarray is a contiguous part of the array.

        Example 1:
        ---------
        Input: nums = [1,0,1,0,1], goal = 2
        Output: 4
        Explanation: The 4 subarrays are bolded and underlined below:
        [1,0,1,0,1]
        [1,0,1,0,1]
        [1,0,1,0,1]
        [1,0,1,0,1]

        Example 2:
        ---------
        Input: nums = [0,0,0,0,0], goal = 0
        Output: 15



        Constraints:
        -----------
                1 <= nums.length <= 3 * 104
                nums[i] is either 0 or 1.
                0 <= goal <= nums.length
 */

public class BinarySubarraysWithSum {

    public int numSubarraysWithSum(int[] nums, int goal) {
        int res = 0;
        int psum = 0;
        Map<Integer, Integer> hm = new HashMap<>();

        hm.put(0, 1);

        for (int num : nums) {
            psum += num;
            res += hm.getOrDefault(psum - goal, 0);
            hm.put(psum, hm.getOrDefault(psum, 0) + 1);
        }

        return res;
    }

    public static void main(String[] args) {
        BinarySubarraysWithSum subarrays = new BinarySubarraysWithSum();
        System.out.println(subarrays.numSubarraysWithSum(new int[]{1, 0, 1, 0, 1}, 2));     //  4
        System.out.println(subarrays.numSubarraysWithSum(new int[]{0, 0, 0, 0, 0}, 0));     //  15
    }

}
