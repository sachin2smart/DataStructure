package in.sachinshinde.array.prefixsum;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;


//  https://leetcode.com/problems/maximum-subarray-min-product

/*
    The min-product of an array is equal to the minimum value in the array multiplied by the array's sum.

    For example, the array [3,2,5] (minimum value is 2) has a min-product of 2 * (3+2+5) = 2 * 10 = 20.

    Given an array of integers nums, return the maximum min-product of any non-empty subarray of nums.
    Since the answer may be large, return it modulo 10^9 + 7.

    Note that the min-product should be maximized before performing the modulo operation.
    Testcases are generated such that the maximum min-product without modulo will fit in a 64-bit signed integer.

    A subarray is a contiguous part of an array.

    Example 1:
    ---------
    Input: nums = [1,2,3,2]
    Output: 14
    Explanation: The maximum min-product is achieved with the subarray [2,3,2] (minimum value is 2).
    2 * (2+3+2) = 2 * 7 = 14.

    Example 2:
    ---------
    Input: nums = [2,3,3,1,2]
    Output: 18
    Explanation: The maximum min-product is achieved with the subarray [3,3] (minimum value is 3).
    3 * (3+3) = 3 * 6 = 18.

    Example 3:
    ---------
    Input: nums = [3,1,5,6,4,2]
    Output: 60
    Explanation: The maximum min-product is achieved with the subarray [5,6,4] (minimum value is 4).
    4 * (5+6+4) = 4 * 15 = 60.



    Constraints:
    -----------
        1 <= nums.length <= 10^5
        1 <= nums[i] <= 10^7

 */

public class MaximumSubarrayMinProduct {

    public int maxSumMinProduct(int[] nums) {
        int mod = (int)Math.pow(10,9)+7;
        int n = nums.length;

        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(-1);

        long currSum = 0;
        long maxProduct = 0;

        long[] prefixSum = new long[n + 1];

        for(int i = 0;i <= n ; ++i) {
            while(dq.peek() != -1 && (i == n || nums[dq.peek()] > nums[i])){
                long currProduct = nums[dq.pop()] * (prefixSum[i] - prefixSum[dq.peek() + 1]);
                maxProduct = Math.max(maxProduct, currProduct);
            }

            dq.push(i);

            if(i < n) {
                currSum += nums[i];
                prefixSum[i + 1] = currSum;
            }
        }

        return (int) (maxProduct % mod);
    }

    public static void main(String[] args) {
        MaximumSubarrayMinProduct product = new MaximumSubarrayMinProduct();
        System.out.println(product.maxSumMinProduct(new int[]{1,2,3,2}));       //  14
        System.out.println(product.maxSumMinProduct(new int[]{2,3,3,1,2}));     //  18
        System.out.println(product.maxSumMinProduct(new int[]{3,1,5,6,4,2}));   //  60

        System.out.println(product.maxSumMinProduct2(new int[]{1,2,3,2}));       //  14
        System.out.println(product.maxSumMinProduct2(new int[]{2,3,3,1,2}));     //  18
        System.out.println(product.maxSumMinProduct2(new int[]{3,1,5,6,4,2}));   //  60
    }

    int MOD = 1000000007;
    public int maxSumMinProduct2(int[] nums) {
        int n = nums.length;
        long[] pSum = new long[n + 1];

        for(int i = 0; i < n; i++) {
            pSum[i + 1] = pSum[i] + nums[i];
        }

        Stack<Integer> stack = new Stack<>();
        long res = 0;

        for (int i = 0; i <= n; i++){
            int currVal = (i < n) ? nums[i] : 0;

            while(!stack.isEmpty() && currVal < nums[stack.peek()]){
                int mid = stack.pop();
                int left = !stack.isEmpty() ? stack.peek() + 1 : 0;

                long sum = pSum[i] - pSum[left];
                long product = sum * nums[mid];
                res = Math.max(res, product);
            }

            stack.push(i);
        }

        return (int)(res % MOD);

    }
}
