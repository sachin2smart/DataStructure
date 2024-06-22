package in.sachinshinde.heap_priority_queue;

import java.util.Comparator;
import java.util.PriorityQueue;

//  https://leetcode.com/problems/find-median-from-data-stream/

/*
        The median is the middle value in an ordered integer list.
        If the size of the list is even, there is no middle value, and
            the median is the mean of the two middle values.

        For example, for arr = [2,3,4], the median is 3.
        For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.

        Implement the MedianFinder class:

            1. MedianFinder()
                initializes the MedianFinder object.
            2. void addNum(int num)
                adds the integer num from the data stream to the data structure.
            3. double findMedian()
                returns the median of all elements so far.
                [ Answers within 10^-5 of the actual answer will be accepted ]


        Example 1:
        ---------
        Input
            ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
            [[], [1], [2], [], [3], []]
        Output
            [null, null, null, 1.5, null, 2.0]

        Explanation
            MedianFinder medianFinder = new MedianFinder();
            medianFinder.addNum(1);    // arr = [1]
            medianFinder.addNum(2);    // arr = [1, 2]
            medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
            medianFinder.addNum(3);    // arr[1, 2, 3]
            medianFinder.findMedian(); // return 2.0


        Constraints:
        -----------
            -10^5 <= num <= 10^5
            There will be at least one element in the data structure before calling findMedian.
            At most 5 * 10^4 calls will be made to addNum and findMedian.


        Follow up:
        ---------
            - If all integer numbers from the stream are in the range [0, 100],
                how would you optimize your solution?
            - If 99% of all integer numbers from the stream are in the range [0, 100],
                how would you optimize your solution?
 */

public class FindMedianFromDataStream {

    private PriorityQueue<Integer> smallHeap;
    private PriorityQueue<Integer> largeHeap;
    private boolean isEven;


    public FindMedianFromDataStream() {
        smallHeap = new PriorityQueue<>((a,b) -> b - a); // maxHeap
        largeHeap = new PriorityQueue<>();   // minHeap
        isEven = true;
    }

    public double findMedian() {
        if (isEven) {
            return (smallHeap.peek() + largeHeap.peek()) / 2.0;
        }
        else {
            return smallHeap.peek();
        }
    }

    public void addNum(int num) {
        if (isEven) {
            largeHeap.offer(num);
            smallHeap.offer(largeHeap.poll());
        }
        else {
            smallHeap.offer(num);
            largeHeap.offer(smallHeap.poll());
        }
        isEven = !isEven;
    }


    public static void main(String[] args) {
        FindMedianFromDataStream medianFinder = new FindMedianFromDataStream();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());  //  1.5
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());  //  2.0

        medianFinder = new FindMedianFromDataStream();
        medianFinder.addNum(3);
        medianFinder.addNum(7);
        System.out.println(medianFinder.findMedian());  //  5.0
        medianFinder.addNum(4);
        System.out.println(medianFinder.findMedian());  //  4.0
        medianFinder.addNum(5);
        System.out.println(medianFinder.findMedian());  //  4.5

        //  Second Approach
        MedianFinder medianFinder1 = new MedianFinder();
        medianFinder1.addNum(1);
        medianFinder1.addNum(2);
        System.out.println(medianFinder1.findMedian());  //  1.5
        medianFinder1.addNum(3);
        System.out.println(medianFinder1.findMedian());  //  2.0

        medianFinder1 = new MedianFinder();
        medianFinder1.addNum(3);
        medianFinder1.addNum(7);
        System.out.println(medianFinder1.findMedian());  //  5.0
        medianFinder1.addNum(4);
        System.out.println(medianFinder1.findMedian());  //  4.0
        medianFinder1.addNum(5);
        System.out.println(medianFinder1.findMedian());  //  4.5

    }

    static class MedianFinder {
        PriorityQueue<Integer> smallHeap = new PriorityQueue<>(Comparator.reverseOrder());  //  maxHeap
        PriorityQueue<Integer> largeHeap = new PriorityQueue<>();   //  minHeap


        public MedianFinder() {

        }

        public void addNum(int num) {
            if (smallHeap.size() == 0 || num < smallHeap.peek()) {
                smallHeap.offer(num);
            }
            else {
                largeHeap.offer(num);
            }
        }

        public double findMedian() {
            while (largeHeap.size() > smallHeap.size()) {
                smallHeap.offer(largeHeap.poll());
            }

            while (smallHeap.size() != largeHeap.size() && smallHeap.size() > 0 &&
                    smallHeap.size() - 1 > largeHeap.size()) {
                largeHeap.offer(smallHeap.poll());
            }

            if ((smallHeap.size() + largeHeap.size()) % 2 == 1) {
                return smallHeap.peek();
            }
            else {
                return ((double) smallHeap.peek() + (double) largeHeap.peek()) / 2.0;
            }
        }
    }
}
