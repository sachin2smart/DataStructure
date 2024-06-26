package in.sachinshinde.heap_priority_queue;

import java.util.*;

//  https://leetcode.com/problems/sliding-window-median/

/*
        The median is the middle value in an ordered integer list.
        If the size of the list is even, there is no middle value.
        So the median is the mean of the two middle values.
        `
        For examples, if arr = [2,3,4], the median is 3.
        For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.

        You are given an integer array nums and an integer k.
        There is a sliding window of size k which is moving from the very left of the array to the very right.
         You can only see the k numbers in the window.
         Each time the sliding window moves right by one position.

        Return the median array for each window in the original array.
        Answers within 10-5 of the actual value will be accepted.

        Example 1:
        ---------
        Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
        Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]

        Explanation:
        Window position                Median
        ---------------                -----
        [1  3  -1] -3  5  3  6  7        1
         1 [3  -1  -3] 5  3  6  7       -1
         1  3 [-1  -3  5] 3  6  7       -1
         1  3  -1 [-3  5  3] 6  7        3
         1  3  -1  -3 [5  3  6] 7        5
         1  3  -1  -3  5 [3  6  7]       6

        Example 2:
        ---------
        Input: nums = [1,2,3,4,2,3,1,4,2], k = 3
        Output: [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]


        Constraints:
        -----------
            1 <= k <= nums.length <= 10^5
            -2^31 <= nums[i] <= 2^31 - 1`
 */

public class SlidingWindowMedian {

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        int j = 0;

        TreeSet<Integer> low = new TreeSet<>((a, b) -> (nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b));
        TreeSet<Integer> high = new TreeSet<>((a, b) -> (nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b));

        for (int i = 0; i < nums.length; i++) {
            low.add(i);
            high.add(low.pollLast());
            if (high.size() > low.size()) {
                low.add(high.pollFirst());
            }
            if (low.size() + high.size() == k) {
                result[j] = low.size() == high.size()
                        ? nums[low.last()] / 2.0 + nums[high.first()] / 2.0
                        : nums[low.last()];
                if (!low.remove(j)) {
                    high.remove(j);
                }
                j++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SlidingWindowMedian slidingWindowMedian = new SlidingWindowMedian();
        System.out.println(Arrays.toString(slidingWindowMedian.medianSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3)));   //  [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
        System.out.println(Arrays.toString(slidingWindowMedian.medianSlidingWindow(new int[]{1,2,3,4,2,3,1,4,2}, 3)));   //  [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]

        System.out.println(Arrays.toString(slidingWindowMedian.medianSlidingWindow2(new int[]{1,3,-1,-3,5,3,6,7}, 3)));   //  [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
        System.out.println(Arrays.toString(slidingWindowMedian.medianSlidingWindow2(new int[]{1,2,3,4,2,3,1,4,2}, 3)));   //  [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]

        System.out.println(Arrays.toString(slidingWindowMedian.medianSlidingWindow3(new int[]{1,3,-1,-3,5,3,6,7}, 3)));   //  [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
        System.out.println(Arrays.toString(slidingWindowMedian.medianSlidingWindow3(new int[]{1,2,3,4,2,3,1,4,2}, 3)));   //  [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]
    }

    //  Second Approach
    public double[] medianSlidingWindow2(int[] nums, int k) {
        double[] medianArray = new double[nums.length - k + 1];
        List<Integer> list = new ArrayList<>();

        for (int i = 0, j = 0; i < nums.length; i++) {
            addElement (list, nums[i]);
            if (i >= k - 1) {
                medianArray[j++] = findMedian(list, k);
                removeElement(list, nums[i - k + 1]);
            }
        }

        return medianArray;
    }

    public void addElement (List<Integer> list, int num) {
        int index = Collections.binarySearch(list, num);
        index = index < 0 ? Math.abs(index) - 1 : index;
        list.add(index, num);
    }

    public void removeElement(List<Integer> list, int num) {
        int index = Collections.binarySearch(list, num);
        list.remove(index);
    }

    public double findMedian(List<Integer> list, int k) {
        return k % 2 == 0 ? ((double) list.get(k / 2 - 1) + list.get(k / 2)) / 2 : list.get(k / 2);
    }

    //  Third Approach
    public double[] medianSlidingWindow3(int[] nums, int k) {
        if (nums == null || k <= 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = nums.length;
        double[] result = new double[len - k + 1];
        if (k == 1) {
            for (int i = 0; i < len; i++) {
                result[i] = (double) nums[i];
            }
            return result;
        }

        PriorityQueue<Integer> smallNums = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> largeNums = new PriorityQueue<>();

        for (int i = 0; i < len; i++) {
            if (i >= k) {
                removeElement(smallNums, largeNums, nums[i - k]);
            }
            addElement(smallNums, largeNums, nums[i]);
            if (i >= k - 1) {
                result[i - (k - 1)] = getMedian(smallNums, largeNums);
            }
        }

        return result;
    }

    private void addElement(PriorityQueue<Integer> smallNums, PriorityQueue<Integer> largeNums, int n) {
        smallNums.offer(n);
        largeNums.offer(smallNums.poll());
        if (smallNums.size() < largeNums.size()) {
            smallNums.offer(largeNums.poll());
        }
    }

    private void removeElement(PriorityQueue<Integer> smallNums, PriorityQueue<Integer> largeNums, int n) {
        if (n >= largeNums.peek()) {
            largeNums.remove(n);
            if (smallNums.size() == largeNums.size() + 2) {
                largeNums.offer(smallNums.poll());
            }
        } else {
            smallNums.remove(n);
            if (smallNums.size() < largeNums.size()) {
                smallNums.offer(largeNums.poll());
            }
        }
    }

    private double getMedian(PriorityQueue<Integer> smallNums, PriorityQueue<Integer> largeNums) {
        if (smallNums.size() == largeNums.size()) {
            return ((double) smallNums.peek() + largeNums.peek()) / 2.0;
        }
        return smallNums.peek();
    }
}
