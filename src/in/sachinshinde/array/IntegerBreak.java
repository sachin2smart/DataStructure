package in.sachinshinde.array;

//  https://leetcode.com/problems/integer-break/

/*
        Given an integer n, break it into the sum of k positive integers, where k >= 2, and
            maximize the product of those integers.

        Return the maximum product you can get.

        Example 1:
        ---------
            Input: n = 2
            Output: 1
            Explanation: 2 = 1 + 1, 1 × 1 = 1.

        Example 2:
        ---------
            Input: n = 10
            Output: 36
            Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.


        Constraints:
        -----------
            2 <= n <= 58
 */

public class IntegerBreak {

    public int integerBreak(int n) {
        if (n == 2) {
            return 1;
        }

        if (n == 3) {
            return 2;
        }

        int res = 1;

        while (n > 4) {
            n -= 3;
            res *= 3;
        }

        res *= n;

        return res;
    }

    public static void main(String[] args) {
        IntegerBreak integerBreak = new IntegerBreak();
        System.out.println(integerBreak.integerBreak(2));   //      1
        System.out.println(integerBreak.integerBreak(10));   //     36
        System.out.println(integerBreak.integerBreak(58));   //     1549681956
    }

    public int integerBreak2(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for(int i = 2; i <= n; i ++) {
            for(int j = 1; j < i; j ++) {
                dp[i] = Math.max(dp[i], (Math.max(j, dp[j])) * (Math.max(i - j, dp[i - j])));
            }
        }
        return dp[n];
    }
}