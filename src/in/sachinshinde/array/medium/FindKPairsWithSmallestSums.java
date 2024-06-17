package in.sachinshinde.array.medium;

import java.util.*;

//  https://leetcode.com/problems/find-k-pairs-with-smallest-sums/

/*
        You are given two integer arrays nums1 and nums2 sorted in non-decreasing order and an integer k.
        Define a pair (u, v) which consists of one element from the first array and one element from the second array.
        Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.



        Example 1:
        ---------
        Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
        Output: [[1,2],[1,4],[1,6]]
        Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]

        Example 2:
        ---------
        Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
        Output: [[1,1],[1,1]]
        Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]


        Constraints:
        -----------
            1 <= nums1.length, nums2.length <= 105
            -109 <= nums1[i], nums2[i] <= 109
            nums1 and nums2 both are sorted in non-decreasing order.
            1 <= k <= 104
            k <= nums1.length * nums2.length
 */

public class FindKPairsWithSmallestSums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < Math.min(nums1.length, k); ++i) {
            pq.offer(new int[] {nums1[i] + nums2[0], i, 0});
        }
        List<List<Integer>> res = new ArrayList<>();
        while (!pq.isEmpty() && k-- > 0) {
            int[] arr = pq.poll();
            res.add(Arrays.asList(nums1[arr[1]], nums2[arr[2]]));
            if (arr[2] + 1 < nums2.length) {
                pq.offer(new int[] {nums1[arr[1]] + nums2[arr[2] + 1], arr[1], arr[2] + 1});
            }
        }
        return res;
    }


    public static void main(String[] args) {
        FindKPairsWithSmallestSums sums = new FindKPairsWithSmallestSums();
        System.out.println(sums.kSmallestPairs(new int[]{1,7,11}, new int[]{2,4,6}, 3));    //  [[1, 2], [1, 4], [1, 6]]
        System.out.println(sums.kSmallestPairs(new int[]{1,1,2}, new int[]{1,2,3}, 2));     //  [[1, 1], [1, 1]]

        System.out.println(sums.kSmallestPairs2(new int[]{1,7,11}, new int[]{2,4,6}, 3));   //  [[1, 2], [1, 4], [1, 6]]
        System.out.println(sums.kSmallestPairs2(new int[]{1,1,2}, new int[]{1,2,3}, 2));    //  [[1, 1], [1, 1]]

        System.out.println(sums.kSmallestPairs3(new int[]{1,7,11}, new int[]{2,4,6}, 3));   //  [[1, 2], [1, 4], [1, 6]]
        System.out.println(sums.kSmallestPairs3(new int[]{1,1,2}, new int[]{1,2,3}, 2));    //  [[1, 1], [1, 1]]
    }

    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);    // minHeap

        for (int num : nums1) {
            pq.offer(new int[] { num + nums2[0], 0 }); // The sum and the index of the second element in nums2
        }

        while (k > 0 && !pq.isEmpty()) {
            int[] pair = pq.poll();
            int sum = pair[0]; // Get the smallest sum
            int pos = pair[1]; // Get the index of the second element in nums2

            List<Integer> currentPair = new ArrayList<>();
            currentPair.add(sum - nums2[pos]);
            currentPair.add(nums2[pos]);
            res.add(currentPair);

            // If there are more elements in nums2, push the next pair into the priority queue
            if (pos + 1 < nums2.length) {
                pq.offer(new int[]{sum - nums2[pos] + nums2[pos + 1], pos + 1});
            }

            k--;
        }

        return res;
    }

    public List<List<Integer>> kSmallestPairs3(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] + a[1]) - (b[0] + b[1]));
        List<List<Integer>> res = new ArrayList<>();

        if (nums1.length == 0 || nums2.length == 0 || k == 0) {
            return res;
        }

        for (int i = 0; i < nums1.length && i < k; i++) {
            pq.offer(new int[]{nums1[i], nums2[0], 0});
        }

        while (k-- > 0 && !pq.isEmpty()) {
            int[] current = pq.poll();
            res.add(Arrays.asList(current[0], current[1]));
            if (current[2] != nums2.length - 1) {
                pq.offer(new int[]{current[0], nums2[current[2] + 1], current[2] + 1});
            }
        }

        return res;
    }
}
