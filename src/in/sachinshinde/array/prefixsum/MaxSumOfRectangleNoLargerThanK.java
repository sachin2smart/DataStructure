package in.sachinshinde.array.prefixsum;

import java.util.TreeSet;

//  https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/

/*
        Given an m x n matrix matrix and an integer k,
        return the max sum of a rectangle in the matrix such that its sum is no larger than k.

        It is guaranteed that there will be a rectangle with a sum no larger than k.

        Example 1:
        ----------
        Input: matrix = [[1,0,1],[0,-2,3]], k = 2
        Output: 2
        Explanation: Because the sum of the blue rectangle [[0, 1], [-2, 3]] is 2, and
        2 is the max number no larger than k (k = 2).

        Example 2:
        ----------
        Input: matrix = [[2,2,-1]], k = 3
        Output: 3



        Constraints:
        ------------
            m == matrix.length
            n == matrix[i].length
            1 <= m, n <= 100
            -100 <= matrix[i][j] <= 100
            -10^5 <= k <= 10^5

        Follow up: What if the number of rows is much larger than the number of columns?

 */

public class MaxSumOfRectangleNoLargerThanK {

    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int max = Integer.MIN_VALUE;

        for (int left = 0; left < n; left++) {
            int[] rowSum = new int[m];
            for (int right = left; right < n; right++) {

                for (int i = 0; i < m; i++) {
                    rowSum[i] += matrix[i][right];
                }

                max = Math.max(max, findMaxSumNoLargerThanK(rowSum, k));
            }
        }
        return max;
    }

    private int findMaxSumNoLargerThanK(int[] arr, int k) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum <= k) {
                    max = Math.max(max, sum);
                }
            }
        }
        return max;
    }


    public int maxSumSubmatrix2(int[][] matrix, int k) {
        int res = Integer.MIN_VALUE;

        for(int left = 0; left < matrix[0].length; left++) {
            int[] rSum = new int[matrix.length];

            for(int right = left; right < matrix[0].length; right++) {
                for(int row = 0; row < matrix.length; row++) {
                    rSum[row] += matrix[row][right];
                }

                TreeSet<Integer> ts = new TreeSet<>();

                ts.add(0);
                int currSum = 0;

                for(int a: rSum) {
                    currSum += a;

                    Integer target = ts.ceiling(currSum - k);

                    if(target != null) {
                        res = Math.max(res, currSum - target);
                    }

                    ts.add(currSum);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MaxSumOfRectangleNoLargerThanK sum = new MaxSumOfRectangleNoLargerThanK();
        System.out.println(sum.maxSumSubmatrix(new int[][]{{1,0,1}, {0,-2,3}}, 2));     //  2
        System.out.println(sum.maxSumSubmatrix(new int[][]{{2,2,-1}}, 3));              //  3

        System.out.println(sum.maxSumSubmatrix2(new int[][]{{1,0,1}, {0,-2,3}}, 2));     //  2
        System.out.println(sum.maxSumSubmatrix2(new int[][]{{2,2,-1}}, 3));              //  3
    }
}
