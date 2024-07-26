package in.sachinshinde.array.prefixsum;

//  https://leetcode.com/problems/largest-sum-of-averages/

/*
        You are given an integer array nums and an integer k.
        You can partition the array into at most k non-empty adjacent subarrays.
        The score of a partition is the sum of the averages of each subarray.

        Note that the partition must use every integer in nums, and that the score is not necessarily an integer.

        Return the maximum score you can achieve of all the possible partitions.
        Answers within 10-6 of the actual answer will be accepted.

        Example 1:
        ---------
        Input: nums = [9,1,2,3,9], k = 3
        Output: 20.00000
        Explanation:
        The best choice is to partition nums into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
        We could have also partitioned nums into [9, 1], [2], [3, 9], for example.
        That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.

        Example 2:
        ---------
        Input: nums = [1,2,3,4,5,6,7], k = 4
        Output: 20.50000

         
        Constraints:
        -----------
            1 <= nums.length <= 100
            1 <= nums[i] <= 104
            1 <= k <= nums.length

 */

public class LargestSumOfAverages {

    //  Recursion
    public double largestSumOfAverages(int[] nums, int k) {
        return dfs(nums, 0, k);
    }

    private double dfs(int[] nums, int curri, int k) {
        if(k == 0) {
            return 0.0;
        }

        if(k == 1) {
            int sum = 0;
            for(int i = curri; i < nums.length; i++) {
                sum += nums[i];
            }
            return (double) sum / (nums.length - curri);
        }

        double sum = 0.0;
        double res = 0.0;

        for(int i = curri; i <= nums.length - k; i++) {
            sum += nums[i];
            double average = sum / (i - curri + 1);
            double nextAverage = dfs(nums,i+1,k - 1);

            res = Math.max(res, average + nextAverage);
        }

        return res;
    }


    // Memoization
    public double largestSumOfAverages2(int[] nums, int k) {
        double[][] memo = new double[nums.length + 1][k +1];
        return dfs2(nums, 0, k, memo);
    }

    private double dfs2(int[] nums, int curri, int k, double[][] memo) {
        if(k == 0) {
            return 0.0;
        }

        if(memo[curri][k]!=0.0)  {
            return memo[curri][k];
        }

        if(k == 1) {
            int sum = 0;
            for(int i = curri; i < nums.length; i++){
                sum += nums[i];
            }
            memo[curri][k] = (double) sum / (nums.length - curri);
            return memo[curri][k];
        }

        double sum = 0.0;
        double res = 0.0;

        for(int i = curri; i <= nums.length - k; i++) {
            sum += nums[i];
            double average = sum / (i - curri + 1);
            memo[i + 1][k - 1] = dfs2(nums,i + 1,k-1, memo);
            res = Math.max(res, average + memo[i + 1][k - 1]);
        }

        memo[curri][k] = res;
        return res;
    }


    //  Dynamic Programming
    public double largestSumOfAverages3(int[] nums, int k) {
        double[][] dp = new double[nums.length + 1][k + 1];
        double [] pSum = new double[nums.length+1];
        for(int i = 0; i < nums.length; i++) {
            pSum[i + 1]= pSum[i] + nums[i];
            dp[i + 1][1] = pSum[i + 1] / (i + 1);
        }
        for(int i = 1; i <= nums.length; i++) {
            for(int j = 2; j <= Math.min(k, i); j++) {
                for(int t = 0; t < i; t++) {
                    dp[i][j] = Math.max(dp[i][j], dp[t][j - 1] + (pSum[i] - pSum[t]) / (i - t));
                }
            }
        }
        return dp[nums.length][k];
    }


    public static void main(String[] args) {
        LargestSumOfAverages sum = new LargestSumOfAverages();
        System.out.println(sum.largestSumOfAverages(new int[]{9,1,2,3,9}, 3));          //  20.00000
        System.out.println(sum.largestSumOfAverages(new int[]{1,2,3,4,5,6,7}, 4));      //  20.50000

        System.out.println(sum.largestSumOfAverages2(new int[]{9,1,2,3,9}, 3));          //  20.00000
        System.out.println(sum.largestSumOfAverages2(new int[]{1,2,3,4,5,6,7}, 4));      //  20.50000

        System.out.println(sum.largestSumOfAverages3(new int[]{9,1,2,3,9}, 3));          //  20.00000
        System.out.println(sum.largestSumOfAverages3(new int[]{1,2,3,4,5,6,7}, 4));      //  20.50000
    }
}