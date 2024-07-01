package in.sachinshinde.array.prefixsum;

//  https://www.geeksforgeeks.org/maximum-score-possible-after-performing-given-operations-on-an-array/

/*
        Statement:
            Given an array A of size N, the task is to find the maximum score possible of this array.
            The score of an array is calculated by performing the following operations on the array N times:
                [*] If the operation is odd-numbered, the score is incremented by the sum of all elements of the current array.
                [*] If the operation is even-numbered, the score is decremented by the sum of all elements of the current array.
                [*] After every operation, either remove the first or the last element of the remaining array.
 */

/*
        Examples:

    [1] :
            Input: A = {1, 2, 3, 4, 2, 6}
            Output: 13

            Explanation:
                The optimal operations are:
                1. Operation 1 is odd.
                    -> So add the sum of the array to the score: Score = 0 + 18 = 18
                    -> remove 6 from last,
                    -> new array A = [1, 2, 3, 4, 2]
                2. Operation 2 is even.
                    -> So subtract the sum of the array from the score: Score = 18 – 12 = 6
                    -> remove 2 from last,
                    -> new array A = [1, 2, 3, 4]
                3. Operation 1 is odd.
                    -> So add the sum of the array to the score: Score = 6 + 10 = 16
                    -> remove 4 from last,
                    -> new array A = [1, 2, 3]
                4. Operation 4 is even.
                    -> So subtract the sum of the array from the score: Score = 16 – 6 = 10
                    -> remove 1 from start,
                    -> new array A = [2, 3]
                5. Operation 5 is odd.
                    -> So add the sum of the array to the score: Score = 10 + 5 = 15
                    -> remove 3 from last,
                    -> new array A = [2]
                6. Operation 6 is even.
                    -> So subtract the sum of the array from the score: Score = 15 – 2 = 13
                    -> remove 2 from first,
                    -> new array A = []
            The array is empty so no further operations are possible.

    [2] :
            Input: A = [5, 2, 2, 8, 1, 16, 7, 9, 12, 4]
            Output: 50
 */

public class MaximumScorePossibleAfterPerformingGivenOperationsOnAnArray {

    /*
            Approach 1:
                [*] In each operation, we have to remove either the leftmost or the rightmost element.
                    A simple way would be to consider all possible ways to remove elements and
                        for each branch compute the score and find the maximum score out of all.
                    This can simply be done using recursion.

                [*]  The information we need to keep in each step would be
                        (-) The remaining array [l, r], where l represents the leftmost index and r the rightmost,
                        (-) The operation number, and
                        (-) The current score.

                [*] In order to calculate the sum of any array from [l, r] in each recursive step optimally,
                    we will keep a prefix sum array.

                [*] Using prefix sum array, new sum from [l, r] can be calculated in O(1) as:
                    Sum(l, r) = prefix_sum[r] – prefix_sum[l-1]

                    ------------------------------
                        Time complexity: O(2^N)
                        Auxiliary space: O(N)
                    ------------------------------
     */

    private int maxScore(int l, int r, int[] prefixSum, int num) {
        if(l > r) {
            return 0;
        }

        // Sum of array in range (l, r)
        int currentSum = prefixSum[r] - (l >= 1 ? prefixSum[l - 1] : 0);

        // If the operation is even-numbered the score is decremented
        if (num % 2 == 0) {
            currentSum *= -1;
        }

        // Exploring all paths, by removing leftmost and rightmost element and selecting the maximum value
        return currentSum + Math.max(maxScore(l + 1, r, prefixSum, num + 1),
                maxScore(l, r - 1, prefixSum, num + 1));
    }

    private int findMaxScore(int[] a) {
        int n = a.length;
        int[] prefixSum = new int[n];
        prefixSum[0] = a[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + a[i];
        }
        return maxScore(0, n - 1, prefixSum, 1);
    }

    public static void main(String[] args) {
        MaximumScorePossibleAfterPerformingGivenOperationsOnAnArray maximumScore = new MaximumScorePossibleAfterPerformingGivenOperationsOnAnArray();
        System.out.println(maximumScore.findMaxScore(new int[]{1, 2, 3, 4, 2, 6}));     //  13
        System.out.println(maximumScore.findMaxScore(new int[]{5, 2, 2, 8, 1, 16, 7, 9, 12, 4}));   //  50

        System.out.println(maximumScore.findMaxScore2(new int[]{1, 2, 3, 4, 2, 6}));     //  13
        System.out.println(maximumScore.findMaxScore2(new int[]{5, 2, 2, 8, 1, 16, 7, 9, 12, 4}));   //  50

        System.out.println(maximumScore.findMaxScore3(new int[]{1, 2, 3, 4, 2, 6}));     //  13
        System.out.println(maximumScore.findMaxScore3(new int[]{5, 2, 2, 8, 1, 16, 7, 9, 12, 4}));   //  50

        System.out.println(maximumScore.findMaxScore4(new int[]{1, 2, 3, 4, 2, 6}));     //  13
        System.out.println(maximumScore.findMaxScore4(new int[]{5, 2, 2, 8, 1, 16, 7, 9, 12, 4}));   //  50
    }

    /*
            Approach 2  : Using Dynamic Programming

                    ------------------------------
                        Time complexity: O(N^3)
                        Auxiliary space: O(N^3)
                    ------------------------------

     */

    //  dp[l][r][num] = where l and r represent the endpoints of the current array and num represents the operation number.
    private final int[][][] dp = new int[100][100][100];

    private int MaximumScoreDP(int l, int r, int[] prefixSum, int num) {
        if (l > r) {
            return 0;
        }

        // If the same state has already been computed
        if (dp[l][r][num] != -1) {
            return dp[l][r][num];
        }

        // Sum of array in range (l, r)
        int currentSum = prefixSum[r] - (l >= 1 ? prefixSum[l - 1] : 0);

        // If the operation is even-numbered the score is decremented
        if (num % 2 == 0) {
            currentSum *= -1;
        }

        // Exploring all paths, and storing maximum value in DP table to avoid further repetitive recursive calls
        dp[l][r][num] = currentSum + Math.max(MaximumScoreDP(l + 1, r, prefixSum, num + 1),
                MaximumScoreDP(l, r - 1, prefixSum, num + 1));

        return dp[l][r][num];
    }

    // Function to find the max score
    private int findMaxScore2(int[] a) {
        int n = a.length;
        int[] prefixSum = new int[n];
        prefixSum[0] = a[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + a[i];
        }

        // Initialising the DP table,
        // -1 represents the sub-problem hasn't been solved yet
        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                for(int l = 0; l < 100; l++) {
                    dp[i][j][l] = -1;
                }
            }
        }

        return MaximumScoreDP(0, n - 1, prefixSum, 1);
    }


    /*
        Approach 3  : Using Dynamic Programming (Iterative)

            Efficient approach : DP tabulation (iterative)
                The approach to solve this problem is same but
                    DP tabulation(bottom-up) method is better than Dp + memorization(top-down)
                because memorization method needs extra stack space of recursion calls.

                In this approach we use 3D DP store computation of sub-problems and
                    find the final result using iteration and not with the help of recursion.

                Steps that were to follow the above approach:
                --------------------------------------------
                    -   Initialize a 3D DP array dp of size n x n x n.
                    -   Calculate the prefix sum of the input array a in an array prefix_sum.
                    -   Fill the dp table diagonally, considering all possible lengths of subarrays.
                    -   For each subarray of length len, consider all possible starting indices i, ending indices j,
                        and even-numbered operations num.
                    -   Calculate the current sum of the subarray based on prefix_sum and
                        the even/odd nature of the operation.
                    -   If the subarray has only one element (i.e., i == j), store the current sum in dp[i][j][num].
                    -   Otherwise, calculate the maximum score by exploring all possible paths, and
                        store the maximum value in dp[i][j][num] to avoid further repetitive recursive calls.
                    -   Return dp[0][n – 1][1] as the maximum score.

                ------------------------------
                    Time complexity: O(N^3)
                    Auxiliary space: O(N^3)
                ------------------------------

    */

    private int findMaxScore3(int[] a) {
        int n = a.length;
        int[][][] dp = new int[n + 3][n + 3][n + 3];

        int[] prefixSum = new int[n];
        prefixSum[0] = a[0];

        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + a[i];
        }

        // Filling the table diagonally
        for (int l = 1; l <= n; l++) {
            for (int i = 0; i + l - 1 < n; i++) {
                int j = i + l - 1;
                for (int num = 1; num <= n; num++) {
                    // Sum of array in range (l, r)
                    int currentSum = prefixSum[j] - (i  >= 1 ? prefixSum[i - 1] : 0);

                    // If the operation is even-numbered, the score is decremented
                    if (num % 2 == 0) {
                        currentSum *= -1;
                    }

                    if (i == j) {
                        dp[i][j][num] = currentSum;
                    }
                    else {
                        dp[i][j][num] = Math.max(currentSum + dp[i + 1][j][num + 1],
                                currentSum + dp[i][j - 1][num + 1]);    //  // Exploring all paths, and storing maximum value in DP table
                    }
                }
            }
        }

        return dp[0][n - 1][1];
    }

    /*
        Approach 4 :    Using Dynamic Programming (Iterative & Space-Optimized)

            Follow the step below to implement the above idea:
            -------------------------------------------------
                -   Initialize a 2D array dp of size n x n to store maximum scores.
                -   Calculate the prefix sum array prefix_sum by iterating through the input array a[] and
                    adding the previous prefix sum.
                -   Iterate over different lengths of subarrays (len) and starting indices of subarrays (i).
                -   Iterate over the number of elements to consider in the subarray (num).
                -   Calculate the current sum of the subarray and handle even/odd number conditions.
                -   Determine the maximum score by choosing between including or excluding the current element.
                -   Return the maximum score for the entire array considering 1 element (dp[0][1]).
                ------------------------------
                    Time complexity: O(N^3)
                    Auxiliary space: O(N^2)
                ------------------------------
     */

    private int findMaxScore4(int[] a) {
        int n = a.length;
        int[][] dp = new int[n][n + 1];

        int[] prefixSum = new int[n];
        prefixSum[0] = a[0];

        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + a[i];
        }

        for (int l = 1; l <= n; l++) {
            for (int i = 0; i + l <= n ; i++) {
                int j = i + l - 1;

                for (int num = 1; num <= n; num++) {
                    int currentSum = prefixSum[j] - ((i - 1 >= 0) ? prefixSum[i - 1] : 0);

                    if (num % 2 == 0) {
                        currentSum *= -1;
                    }

                    if (i == j) {
                        dp[i][num] = currentSum;
                    }
                    else {
                        // Choose the maximum score among two options:
                        // 1. Include the current element and move to the next element
                        // 2. Exclude the current element and move to the next element
                        dp[i][num] = Math.max(
                                currentSum + ((num + 1 <= n) ? dp[i + 1][num + 1] : 0),
                                currentSum + ((num + 1 <= n) ? dp[i][num + 1] : 0));
                    }
                }
            }
        }

        return dp[0][1];
    }
}