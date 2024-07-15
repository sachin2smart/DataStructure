package in.sachinshinde.hashmap;

import java.util.*;

//  https://leetcode.com/problems/restore-the-array-from-adjacent-pairs/

/*
        There is an integer array nums that consists of n unique elements, but you have forgotten it.
        However, you do remember every pair of adjacent elements in nums.

        You are given a 2D integer array adjacentPairs of size n - 1 where
            each adjacentPairs[i] = [ui, vi] indicates that the elements ui and vi are adjacent in nums.

        It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs,
            either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]].
        The pairs can appear in any order.

        Return the original array nums. If there are multiple solutions, return any of them.

        Example 1:
        ---------
        Input: adjacentPairs = [[2,1],[3,4],[3,2]]
        Output: [1,2,3,4]
        Explanation: This array has all its adjacent pairs in adjacentPairs.
        Notice that adjacentPairs[i] may not be in left-to-right order.

        Example 2:
        ---------
        Input: adjacentPairs = [[4,-2],[1,4],[-3,1]]
        Output: [-2,4,1,-3]
        Explanation: There can be negative numbers.
        Another solution is [-3,1,4,-2], which would also be accepted.

        Example 3:
        ---------
        Input: adjacentPairs = [[100000,-100000]]
        Output: [100000,-100000]


        Constraints:
        -----------
            nums.length == n
            adjacentPairs.length == n - 1
            adjacentPairs[i].length == 2
            2 <= n <= 10^5
            -105 <= nums[i], ui, vi <= 10^5
            There exists some nums that has adjacentPairs as its pairs.
 */

public class RestoreTheArrayFromAdjacentPairs {

    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> hm = new HashMap<>();

        for (int[] pair : adjacentPairs) {
            hm.computeIfAbsent(pair[0], k -> new ArrayList<>()).add(pair[1]);
            hm.computeIfAbsent(pair[1], k -> new ArrayList<>()).add(pair[0]);
        }

        List<Integer> res = new ArrayList<>();

        for (Map.Entry<Integer, List<Integer>> entry : hm.entrySet()) {
            if (entry.getValue().size() == 1) {
                res.add(entry.getKey());
                res.add(entry.getValue().get(0));
                break;
            }
        }

        while (res.size() < adjacentPairs.length + 1) {
            int last = res.get(res.size() - 1);
            int prev = res.get(res.size() - 2);
            List<Integer> nextCandidates = hm.get(last);

            if(nextCandidates.get(0) != prev) {
                res.add(nextCandidates.get(0));
            }
            else {
                res.add(nextCandidates.get(1));
            }
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        RestoreTheArrayFromAdjacentPairs restoreArray = new RestoreTheArrayFromAdjacentPairs();
        System.out.println(Arrays.toString(restoreArray.restoreArray(new int[][]{{2,1},{3,4},{3,2}})));
        System.out.println(Arrays.toString(restoreArray.restoreArray(new int[][]{{4,-2},{1,4},{-3,1}})));
        System.out.println(Arrays.toString(restoreArray.restoreArray(new int[][]{{100000,-100000}})));
    }

}
