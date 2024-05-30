package in.sachinshinde.heap_priority_queue;

//  https://leetcode.com/problems/minimum-cost-to-hire-k-workers

import java.util.*;
import javafx.util.Pair;

/*
        There are n workers. You are given two integer arrays quality and wage
            where quality[i] is the quality of the ith worker and
            wage[i] is the minimum wage expectation for the ith worker.

        We want to hire exactly k workers to form a paid group.
        To hire a group of k workers, we must pay them according to the following rules:

            - Every worker in the paid group must be paid at least their minimum wage expectation.
            - In the group, each worker's pay must be directly proportional to their quality.
                This means if a worker’s quality is double that of another worker in the group, then
                    they must be paid twice as much as the other worker.

        Given the integer k,
                return the least amount of money needed to form a paid group satisfying the above conditions.

        Answers within 10-5 of the actual answer will be accepted.

        Example 1:
        ---------
        Input: quality = [10,20,5], wage = [70,50,30], k = 2
        Output: 105.00000
        Explanation: We pay 70 to 0th worker and 35 to 2nd worker.

        Example 2:
        ---------
        Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
        Output: 30.66667
        Explanation: We pay 4 to 0th worker, 13.33333 to 2nd and 3rd workers separately.


        Constraints:
        -----------
            n == quality.length == wage.length
            1 <= k <= n <= 104
            1 <= quality[i], wage[i] <= 104
 */
public class MinimumCostToHireKWorkers {

    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        List<Pair<Double, Integer>> ratio = new ArrayList<>();
        int n = quality.length;
        for (int i = 0; i < n; ++i) {
            ratio.add(new Pair<>((double) wage[i] / quality[i], i));
        }
        ratio.sort(Comparator.comparingDouble(p -> p.getKey()));

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int qualitySum = 0;
        double maxRate = 0.0;

        for (int i = 0; i < k; ++i) {
            qualitySum += quality[ratio.get(i).getValue()];

            maxRate = Math.max(maxRate, ratio.get(i).getKey());
            pq.offer(quality[ratio.get(i).getValue()]);
        }

        double res = maxRate * qualitySum;

        for (int i = k; i <n; ++i) {
            qualitySum -= pq.poll();
            qualitySum += quality[ratio.get(i).getValue()];

            maxRate = Math.max(maxRate, ratio.get(i).getKey());
            pq.offer(quality[ratio.get(i).getValue()]);

            res = Math.min(res, maxRate * qualitySum);
        }

        return res;
    }

    public static void main(String[] args) {
        MinimumCostToHireKWorkers minimumCostToHireKWorkers = new MinimumCostToHireKWorkers();
        System.out.println(minimumCostToHireKWorkers.mincostToHireWorkers(new int[]{10,20,5},
                                                                            new int[]{70, 50, 30}, 2)); // 105.00
        System.out.println(minimumCostToHireKWorkers.mincostToHireWorkers(new int[]{3,1,10,10,1},
                                                                            new int[]{4,8,2,2,7}, 3));  // 30.66667
    }
}
