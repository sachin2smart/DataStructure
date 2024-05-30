package in.sachinshinde.misc.distribute_candies;

import java.util.Arrays;

//  https://leetcode.com/problems/allocate-mailboxes/

/*
        Given the array houses where houses[i] is the location of the ith house along a street and an integer k,
        allocate k mailboxes in the street.

        Return the minimum total distance between each house and its nearest mailbox.
        The test cases are generated so that the answer fits in a 32-bit integer.

        Example 1:
        ---------
        Input: houses = [1,4,8,10,20], k = 3
        Output: 5
        Explanation: Allocate mailboxes in position 3, 9 and 20.
        Minimum total distance from each houses to nearest mailboxes is |3-1| + |4-3| + |9-8| + |10-9| + |20-20| = 5

        Example 2:
        ---------
        Input: houses = [2,3,5,12,18], k = 2
        Output: 9
        Explanation: Allocate mailboxes in position 3 and 14.
        Minimum total distance from each houses to nearest mailboxes is |2-3| + |3-3| + |5-3| + |12-14| + |18-14| = 9.

        Constraints:
        -----------
            1 <= k <= houses.length <= 100
            1 <= houses[i] <= 104
            All the integers of houses are unique.
 */

public class AllocateMailboxes {

    public int minDistance(int[] houses, int k) {
        Arrays.sort(houses);
        Integer[][] memo = new Integer[houses.length][k + 1];
        return min(houses, 0, k, memo);
    }

    private int min(int[] houses, int idx, int k, Integer[][] memo) {
        if (k == 0) {
            return idx == houses.length ? 0 : Integer.MAX_VALUE;
        }

        if (idx == houses.length) {
            return Integer.MAX_VALUE;
        }

        if (memo[idx][k] != null) {
            return memo[idx][k];
        }

        int cost = Integer.MAX_VALUE;
        for (int i = idx; i < houses.length; i++) {
            int val = min(houses, i + 1, k - 1, memo);
            if (val != Integer.MAX_VALUE) {
                cost = Math.min(cost, cost(houses, idx, i) + val);
            }
        }

        memo[idx][k] = cost;

        return cost;
    }

    private int cost(int[] houses, int left, int right) {
        int cost = 0;
        while (left < right) {
            cost += houses[right] - houses[left];
            left++;
            right--;
        }
        return cost;
    }

}
