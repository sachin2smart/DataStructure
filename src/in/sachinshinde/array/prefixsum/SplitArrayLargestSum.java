package in.sachinshinde.array.prefixsum;

//  https://leetcode.com/problems/split-array-largest-sum

/*
        Given an integer array nums and an integer k, split nums into k non-empty subarrays such that
            the largest sum of any subarray is minimized.

        Return the minimized largest sum of the split.
        A subarray is a contiguous part of the array.

        Example 1:
        ---------
        Input: nums = [7,2,5,10,8], k = 2
        Output: 18
        Explanation: There are four ways to split nums into two subarrays.
        The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.

        Example 2:
        ---------
        Input: nums = [1,2,3,4,5], k = 2
        Output: 9
        Explanation: There are four ways to split nums into two subarrays.
        The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.

        Constraints:
        -----------
            1 <= nums.length <= 1000
            0 <= nums[i] <= 10^6
            1 <= k <= min(50, nums.length)


 */

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

public class SplitArrayLargestSum {
    //  Approach : Binary Search + Recursion
    public int splitArray(int[] nums, int k) {
        int start = 0;
        int end = 0;

        for(int num:nums) {
            start = Math.max(start, num);
            end += num;
        }

        return binarySearch(nums, k, start, end);
    }

    public int binarySearch(int[] nums, int k, int start, int end) {
        int mid = start + (end - start) / 2;

        int peaces = 1;
        int sum = 0;

        if (start < end) {
            for (int num: nums) {
                if (sum + num > mid) {
                    peaces++;
                    sum = num;
                }
                else {
                    sum += num;
                }
            }

            if (mid < end && peaces > k){
                return binarySearch(nums, k, mid + 1, end);
            }
            else {
                return binarySearch(nums, k, start, mid);
            }
        }
        return start;
    }


    //  Approach : Backtracking
    int min = Integer.MAX_VALUE;

    public int splitArray2(int[] nums, int k) {
        int n = nums.length;
        int[][] memo = new int[n + 1][n + 1];

        for (int i = 0; i <  n + 1; i++) {
            for (int j = 0; j < k + 1; j++) {
                memo[i][j] = Integer.MAX_VALUE;
            }
        }

        int[] pSum = new int[nums.length+1];
        pSum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            pSum[i + 1] = pSum[i] + nums[i];
        }

        backtrack(pSum, memo, 1, 1, 0, n,  k);

        return min;
    }

    private void backtrack(int[] pSum, int[][] memo, int start, int currSplitNum, int sum, int n, int numSplits) {
        if (start > n || memo[start][currSplitNum] <= sum) {
            return;
        }
        if (currSplitNum == numSplits) {
            sum = Math.max(sum, pSum[n] - pSum[start - 1]);
            min = Math.min(min, sum);
            return;
        }
        for (int i = start; i < n && pSum[i] - pSum[start - 1] < min; i++) {
            int nextSum = Math.max(sum, pSum[i] - pSum[start - 1]);
            if (nextSum < min) {
                backtrack(pSum, memo, i+1, currSplitNum + 1, nextSum, n, numSplits);
            }
        }
        memo[start][currSplitNum] = sum;
    }

    public static void main(String[] args) {
        SplitArrayLargestSum sum = new SplitArrayLargestSum();
        System.out.println(sum.splitArray(new int[]{7,2,5,10,8}, 2));       //  18
        System.out.println(sum.splitArray(new int[]{1,2,3,4,5}, 2));        //  9
        System.out.println(sum.splitArray(new int[]{1}, 1));                //  1

        System.out.println(sum.splitArray2(new int[]{7,2,5,10,8}, 2));       //  18
        System.out.println(sum.splitArray2(new int[]{1,2,3,4,5}, 2));        //  9
        System.out.println(sum.splitArray2(new int[]{1}, 1));                //  1

        System.out.println(sum.splitArray3(new int[]{7,2,5,10,8}, 2));       //  18
        System.out.println(sum.splitArray3(new int[]{1,2,3,4,5}, 2));        //  9
        System.out.println(sum.splitArray3(new int[]{1}, 1));                //  1
    }

    //  Approach : Binary Search
    //----------------------------
    //  if you know the maxValue and sum of all elements - how you can reach to the solution ?
    public int splitArray3(int[] nums, int k) {
        int left =  Arrays.stream(nums).summaryStatistics().getMax();
        //  Arrays.stream(nums).max().getAsInt();
        //  getMaximum(nums);

        int right = (int) Arrays.stream(nums).summaryStatistics().getSum();
        //  IntStream.of(nums).sum();
        //  getSumArray(nums);

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isValid(nums, k, mid)) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return right;
    }

    private boolean isValid(int[] nums, int k, int mid) {
        int steps = 1, currSum = 0;
        for (int num: nums) {
            currSum += num;
            if (currSum > mid) {
                steps++;
                currSum = num;
                if (steps > k) {
                    return false;
                }
            }
        }
        return true;
    }

    //  getting the maximum among the element present in the array
    private int getMaximum(int[] nums) {
        int max = nums[0];
        for (int num : nums) {
            max = Math.max(max, num);
        }
        return max;
    }

    // getting the sum of all the element present in the array
    private int getSumArray(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }
}
