package in.sachinshinde.heap_priority_queue.easy;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.PriorityQueue;

//  https://leetcode.com/problems/relative-ranks/

/*
       You are given an integer array score of size n,
       where score[i] is the score of the ith athlete in a competition.
       All the scores are guaranteed to be unique.

       The athletes are placed based on their scores,
        where the 1st place athlete has the highest score,
        the 2nd place athlete has the 2nd highest score, and so on.
        The placement of each athlete determines their rank:

           - The 1st place athlete's rank is "Gold Medal".
           - The 2nd place athlete's rank is "Silver Medal".
           - The 3rd place athlete's rank is "Bronze Medal".
           - For the 4th place to the nth place athlete, their rank is their placement number
                (i.e., the xth place athlete's rank is "x").
        Return an array answer of size n where answer[i] is the rank of the ith athlete.

        Example 1:
        ---------
        Input: score = [5,4,3,2,1]
        Output: ["Gold Medal","Silver Medal","Bronze Medal","4","5"]
        Explanation: The placements are [1st, 2nd, 3rd, 4th, 5th].

        Example 2:
        ---------
        Input: score = [10,3,8,9,4]
        Output: ["Gold Medal","5","Bronze Medal","Silver Medal","4"]
        Explanation: The placements are [1st, 5th, 3rd, 2nd, 4th].

        Constraints:
        ------------
            n == score.length
            1 <= n <= 104
            0 <= score[i] <= 106
            All the values in score are unique.
 */
public class RelativeRanks {

    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        String[] res = new String[n];

        PriorityQueue<Integer> pq =
                new PriorityQueue<>((a,b) -> score[b] - score[a]);

        for(int i = 0; i < n; i++) {
            pq.add(i);
        }

        int i=1;

        while(!pq.isEmpty()){

            int qIdx = pq.poll();

            if(i > 3) {
                res[qIdx] = Integer.toString(i);
            }
            else if(i == 1) {
                res[qIdx] = "Gold Medal";
            }
            else if(i == 2) {
                res[qIdx] = "Silver Medal";
            }
            else if(i == 3) {
                res[qIdx] = "Bronze Medal";
            }

            i++;
        }

        return res;
    }

    public static void main(String[] args) {
        RelativeRanks ranks = new RelativeRanks();
        System.out.println(Arrays.toString(ranks.findRelativeRanks(new int[]{1,8,2,7,3})));     // [5, Gold Medal, 4, Silver Medal, Bronze Medal]
        System.out.println(Arrays.toString(ranks.findRelativeRanks2(new int[]{1,8,2,7,3})));    // [5, Gold Medal, 4, Silver Medal, Bronze Medal]
    }

    public String[] findRelativeRanks2(int[] score) {
        int n = score.length;
        String[] res = new String[n];

        PriorityQueue<Pair<Integer, Integer>>  q =
                new PriorityQueue<Pair<Integer, Integer>>((a,b) -> b.getKey()-a.getKey());

        for (int i = 0; i < score.length; i++) {
            q.add(new Pair(score[i], i));
        }

        int i = 1;
        String[] rank = new String[]{"",
                "Gold Medal", "Silver Medal", "Bronze Medal"};

        while(!q.isEmpty()) {
            Pair<Integer, Integer> player = q.poll();
            int qIdx = player.getValue();

            if (i > 3) {
                res[qIdx] = String.valueOf(i);
            }
            else {
                res[qIdx] = rank[i];
            }
            i++;
        }
        return res;
    }
}
