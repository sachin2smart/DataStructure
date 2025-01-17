package in.sachinshinde.array.divideandconquer;

import java.util.*;

//  https://leetcode.com/problems/beautiful-array/

/*
        An array nums of length n is beautiful if:

        nums is a permutation of the integers in the range [1, n].
        For every 0 <= i < j < n, there is no index k with i < k < j where 2 * nums[k] == nums[i] + nums[j].
        Given the integer n, return any beautiful array nums of length n. There will be at least one valid answer for the given n.



        Example 1:

        Input: n = 4
        Output: [2,1,4,3]
        Example 2:

        Input: n = 5
        Output: [3,1,2,5,4]


        Constraints:
            1 <= n <= 1000
 */

public class BeautifulArray {

    Map<Integer, int[]> memo;

    public int[] beautifulArray(int n) {
        memo = new HashMap<>();
        return getArray(n);
    }

    public int[] getArray(int n) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int[] ans = new int[n];

        if (n == 1) {
            ans[0] = 1;
        }
        else {
            int i = 0;
            for (int x: getArray((n + 1) / 2)) {
                ans[i++] = 2 * x - 1;
            }
            for (int x: getArray(n / 2)) {
                ans[i++] = 2 * x;
            }
        }

        memo.put(n, ans);
        return ans;
    }

    public static void main(String[] args) {
        BeautifulArray beautifulArray = new BeautifulArray();
        System.out.println(Arrays.toString(beautifulArray.beautifulArray(4)));
        System.out.println(Arrays.toString(beautifulArray.beautifulArray2(4)));
    }

    public int[] beautifulArray2(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(1);

        while (res.size() < n) {
            List<Integer> currRes = new ArrayList<>();
            for (int i : res) {
                if (i * 2 - 1 <= n) {
                    currRes.add(i * 2 - 1);
                }
            }

            for (int i : res) {
                if (i * 2 <= n) {
                    currRes.add(i * 2);
                }
            }

            res = currRes;
        }
        return res.stream().mapToInt(i -> i).toArray();
    }

}
