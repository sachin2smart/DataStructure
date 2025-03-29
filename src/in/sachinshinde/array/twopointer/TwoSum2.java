package in.sachinshinde.array.twopointer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//  https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/

/*
        Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
        find two numbers such that they add up to a specific target number.
        Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.

        Return the indices of the two numbers, index1 and index2, added by one as
            an integer array [index1, index2] of length 2.

        The tests are generated such that there is exactly one solution. You may not use the same element twice.

        Your solution must use only constant extra space.



        Example 1:

        Input: numbers = [2,7,11,15], target = 9
        Output: [1,2]
        Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
        Example 2:

        Input: numbers = [2,3,4], target = 6
        Output: [1,3]
        Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].
        Example 3:

        Input: numbers = [-1,0], target = -1
        Output: [1,2]
        Explanation: The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We return [1, 2].


        Constraints:

        2 <= numbers.length <= 3 * 104
        -1000 <= numbers[i] <= 1000
        numbers is sorted in non-decreasing order.
        -1000 <= target <= 1000
        The tests are generated such that there is exactly one solution.
 */

public class TwoSum2 {

    /*
            If our current sum is too small, move closer to the right.
            If our current sum is too large, move closer to the left.
     */
    public int[] twoSum(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int currSum = nums[left] + nums[right];

        while (currSum != target) {
            if (currSum < target) {
                left++;
            }
            else {
                right--;
            }
            currSum = nums[left] + nums[right];
        }

        return new int[] {left + 1, right + 1};
    }

    public static void main(String[] args) {
        TwoSum2 twoSum2 = new TwoSum2();
        System.out.println(Arrays.toString(twoSum2.twoSum(new int[]{-3, -1, 0, 3, 6, 8, 10}, 6)));  //  [3, 5]
    }

    public int[] twoSum2(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int total = numbers[left] + numbers[right];

            if (total == target) {
                return new int[]{left + 1, right + 1};
            } else if (total > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{-1, -1}; // If no solution is found
    }

    public int[] twoSum_hm(int[] numbers, int target) {
        int len = numbers.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if (map.containsKey(target - numbers[i])) {
                return new int[]{map.get(target - numbers[i]), i + 1};
            }
            map.putIfAbsent(numbers[i], i + 1);
        }
        return new int[0];
    }
}
