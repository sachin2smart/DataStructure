package in.sachinshinde.array.prefixsum;

import java.util.Arrays;
import java.util.TreeSet;

//  https://leetcode.com/problems/plates-between-candles/

/*
        There is a long table with a line of plates and candles arranged on top of it.
        You are given a 0-indexed string s consisting of characters '*' and '|' only,
            where a '*' represents a plate and a '|' represents a candle.

        You are also given a 0-indexed 2D integer array queries where
            queries[i] = [lefti, righti] denotes the substring s[lefti...righti] (inclusive).
        For each query, you need to find the number of plates between candles that are in the substring.
        A plate is considered between candles
            if there is at least one candle to its left and at least one candle to its right in the substring.

        For example, s = "||**||**|*", and a query [3, 8] denotes the substring "*||**|".
            The number of plates between candles in this substring is 2,
            as each of the two plates has at least one candle in the substring to its left and right.
        Return an integer array answer where answer[i] is the answer to the ith query.

        Example 1:
        ---------
        ex-1
        Input: s = "**|**|***|", queries = [[2,5],[5,9]]
        Output: [2,3]
        Explanation:
        - queries[0] has two plates between candles.
        - queries[1] has three plates between candles.

        Example 2:
        ---------
        ex-2
        Input: s = "***|**|*****|**||**|*", queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
        Output: [9,0,0,0,0]
        Explanation:
        - queries[0] has nine plates between candles.
        - The other queries have zero plates between candles.


        Constraints:
        -----------
            3 <= s.length <= 105
            s consists of '*' and '|' characters.
            1 <= queries.length <= 105
            queries[i].length == 2
            0 <= lefti <= righti < s.length
 */

public class PlatesBetweenCandles {

    public int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();

        int psum[] = new int[n + 1];
        int next[] = new int[n + 1];
        int prev[] = new int[n + 1];

        Arrays.fill(next, Integer.MAX_VALUE);

        int res[] = new int[queries.length];

        for(int i = 0; i < n; i++) {
            psum[i + 1] = psum[i] + (s.charAt(i) == '|' ? 1 : 0);
            prev[i + 1] = s.charAt(i) == '|' ? i : prev[i];
        }

        for(int i = n - 1; i >= 0; i--) {
            next[i] = s.charAt(i) == '|' ? i : next[i + 1];
        }

        for(int i = 0; i < queries.length; i++) {
            int l = next[queries[i][0]];        // why left gets values from next[] array ?
            int r = prev[queries[i][1] + 1];    // why +1 here ?
            res[i] = l < r ? r - l - (psum[r] - psum[l]) : 0;
        }

        return res;
    }

    public static void main(String[] args) {
        PlatesBetweenCandles plates = new PlatesBetweenCandles();
        System.out.println(Arrays.toString(plates.platesBetweenCandles("**|**|***|", new int[][]{{2, 5}, {5, 9}})));
        System.out.println(Arrays.toString(plates.platesBetweenCandles2("**|**|***|", new int[][]{{2, 5}, {5, 9}})));
    }

    public int[] platesBetweenCandles2(String s, int[][] queries) {
        TreeSet<Integer> candles = new TreeSet<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '|') {
                candles.add(i);
            }
        }

        int[] prefixSum = new int[s.length()];
        prefixSum[0] = (s.charAt(0) == '*') ? 1 : 0;

        for(int i = 1;i < s.length(); i++) {
            prefixSum[i] = prefixSum[i - 1] + ((s.charAt(i) == '*') ? 1 : 0);
        }

        int[] res = new int[queries.length];
        int i = 0;

        for (int[] query : queries) {
            int queryLeftPos = query[0];
            int queryRightPos = query[1];

            Integer leftCandlePos = candles.ceiling(queryLeftPos);
            Integer rightCandlePos = candles.floor(queryRightPos);

            if (leftCandlePos != null &&
                    rightCandlePos != null &&
                    leftCandlePos < rightCandlePos &&
                    leftCandlePos <= queryRightPos &&
                    rightCandlePos >= queryLeftPos) {
                res[i++] = prefixSum[rightCandlePos] - prefixSum[leftCandlePos];
            }
            else {
                res[i++] = 0;
            }
        }

        return res;
    }
}
