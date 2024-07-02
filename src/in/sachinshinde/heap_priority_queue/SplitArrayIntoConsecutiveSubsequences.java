package in.sachinshinde.heap_priority_queue;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//  https://leetcode.com/problems/split-array-into-consecutive-subsequences/

/*
        You are given an integer array nums that is sorted in non-decreasing order.

        Determine if it is possible to split nums into one or more subsequences such that
            both of the following conditions are true:
            -   Each subsequence is a consecutive increasing sequence
                (i.e. each integer is exactly one more than the previous integer).
            -   All subsequences have a length of 3 or more.

        Return true if you can split nums according to the above conditions, or false otherwise.

        A subsequence of an array is a new array that is formed from the original array by
            deleting some (can be none) of the elements
            without disturbing the relative positions of the remaining elements.
        (i.e., [1,3,5] is a subsequence of [1,2,3,4,5] while [1,3,2] is not).

        Example 1:
        ---------
        Input: nums = [1,2,3,3,4,5]
        Output: true
        Explanation: nums can be split into the following subsequences:
        [1,2,3,3,4,5] --> 1, 2, 3
        [1,2,3,3,4,5] --> 3, 4, 5

        Example 2:
        ---------
        Input: nums = [1,2,3,3,4,4,5,5]
        Output: true
        Explanation: nums can be split into the following subsequences:
        [1,2,3,3,4,4,5,5] --> 1, 2, 3, 4, 5
        [1,2,3,3,4,4,5,5] --> 3, 4, 5

        Example 3:
        ---------
        Input: nums = [1,2,3,4,4,5]
        Output: false
        Explanation: It is impossible to split nums into consecutive increasing subsequences of length 3 or more.


        Constraints:
        ------------
            1 <= nums.length <= 104
            -1000 <= nums[i] <= 1000
            nums is sorted in non-decreasing order.
 */

public class SplitArrayIntoConsecutiveSubsequences {

    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> availabilityMap = new HashMap<>();
        Map<Integer, Integer> wantsMap = new HashMap<>();

        for(int num: nums) {
            availabilityMap.put(num, availabilityMap.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            if(availabilityMap.get(num) != 0 ) {
                if (wantsMap.getOrDefault(num, 0) > 0) {
                    availabilityMap.put(num, availabilityMap.get(num) - 1);
                    wantsMap.put(num, wantsMap.get(num) - 1);
                    wantsMap.put(num + 1, wantsMap.getOrDefault(num + 1, 0) + 1);
                }
                else {
                    if (availabilityMap.getOrDefault(num + 1, 0) > 0 &&
                            availabilityMap.getOrDefault(num + 2, 0) > 0) {
                        availabilityMap.put(num, availabilityMap.get(num) - 1);
                        availabilityMap.put(num + 1, availabilityMap.get(num + 1) - 1);
                        availabilityMap.put(num + 2, availabilityMap.get(num + 2) - 1);
                        wantsMap.put(num + 3, wantsMap.getOrDefault(num + 3, 0) + 1);
                    }
                    else {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public boolean isPossible2(int[] nums) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
                a[1] == b[1] ? (a[1] - a[0]) - (b[1] - b[0]) : a[1] - b[1]);

        for(int num : nums){
            while(!pq.isEmpty() && pq.peek()[1] + 1 < num){
                int[] current = pq.poll();
                if( current[1] - current[0] < 2) {
                    return false;
                }
            }

            if(pq.isEmpty() || pq.peek()[1] == num) {
                pq.add(new int[] {num, num});
            }
            else {
                int[] current = pq.poll();
                pq.add(new int[]{current[0], num});
            }
        }

        while(!pq.isEmpty()) {
            int[] current = pq.poll();
            if( current[1] - current[0] < 2) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        SplitArrayIntoConsecutiveSubsequences canSplit = new SplitArrayIntoConsecutiveSubsequences();
        System.out.println(canSplit.isPossible(new int[]{1,2,3,3,4,5}));        //  true
        System.out.println(canSplit.isPossible(new int[]{1,2,3,3,4,4,5,5}));    //  true
        System.out.println(canSplit.isPossible(new int[]{1,2,3,4,4,5}));        //  false

        System.out.println(canSplit.isPossible2(new int[]{1,2,3,3,4,5}));        //  true
        System.out.println(canSplit.isPossible2(new int[]{1,2,3,3,4,4,5,5}));    //  true
        System.out.println(canSplit.isPossible2(new int[]{1,2,3,4,4,5}));        //  false
    }
}
