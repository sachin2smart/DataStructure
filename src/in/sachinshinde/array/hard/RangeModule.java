package in.sachinshinde.array.hard;

//  https://leetcode.com/problems/range-module/

/*
        A Range Module is a module that tracks ranges of numbers.
        Design a data structure to track the ranges represented as half-open intervals and query about them.

        A half-open interval [left, right) denotes all the real numbers x where left <= x < right.

        Implement the RangeModule class:
            -   RangeModule() Initializes the object of the data structure.
            -   void addRange(int left, int right) Adds the half-open interval [left, right),
                    tracking every real number in that interval.
                    Adding an interval that partially overlaps with currently tracked numbers should add any numbers
                    in the interval [left, right) that are not already tracked.
            -   boolean queryRange(int left, int right) Returns true if every real number in the interval [left, right)
                    is currently being tracked, and false otherwise.
            -   void removeRange(int left, int right) Stops tracking every real number currently being tracked in
                    the half-open interval [left, right).


        Example 1:
        ---------
        Input
        ["RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange"]
        [[], [10, 20], [14, 16], [10, 14], [13, 15], [16, 17]]
        Output
        [null, null, null, true, false, true]

        Explanation
        RangeModule rangeModule = new RangeModule();
        rangeModule.addRange(10, 20);
        rangeModule.removeRange(14, 16);
        rangeModule.queryRange(10, 14); // return True,(Every number in [10, 14) is being tracked)
        rangeModule.queryRange(13, 15); // return False,(Numbers like 14, 14.03, 14.17 in [13, 15) are not being tracked)
        rangeModule.queryRange(16, 17); // return True, (The number 16 in [16, 17) is still being tracked, despite the remove operation)

        Constraints:
        -----------
            1 <= left < right <= 109
            At most 104 calls will be made to addRange, queryRange, and removeRange.
 */

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class RangeModule {

    TreeMap<Integer, Integer> treeMap;

    public RangeModule() {
        treeMap = new TreeMap<>();
    }

    public void addRange(int left, int right) {
        if (right <= left) {
            return;
        }

        Integer start = treeMap.floorKey(left);
        Integer end = treeMap.floorKey(right);

        if (start == null && end == null) {
            treeMap.put(left, right);
        }
        else if (start != null && treeMap.get(start) >= left) {
            treeMap.put(start, Math.max(treeMap.get(end), Math.max(treeMap.get(start), right)));
        }
        else {
            treeMap.put(left, Math.max(treeMap.get(end), right));
        }
        // clean up intermediate intervals
        Map<Integer, Integer> subMap = treeMap.subMap(left, false, right, true);
        Set<Integer> set = new HashSet<>(subMap.keySet());
        treeMap.keySet().removeAll(set);
    }

    public boolean queryRange(int left, int right) {
        Integer start = treeMap.floorKey(left);
        if (start == null) {
            return false;
        }
        return treeMap.get(start) >= right;
    }

    public void removeRange(int left, int right) {
        if (right <= left) {
            return;
        }
        Integer start = treeMap.floorKey(left);
        Integer end = treeMap.floorKey(right);
        if (end != null && treeMap.get(end) > right) {
            treeMap.put(right, treeMap.get(end));
        }
        if (start != null && treeMap.get(start) > left) {
            treeMap.put(start, left);
        }
        // clean up intermediate intervals
        Map<Integer, Integer> subMap = treeMap.subMap(left, true, right, false);
        Set<Integer> set = new HashSet<>(subMap.keySet());
        treeMap.keySet().removeAll(set);

    }

    public static void main(String[] args) {
        RangeModule rangeModule = new RangeModule();
        rangeModule.addRange(10, 20);
        rangeModule.removeRange(14, 16);
        System.out.println(rangeModule.queryRange(10, 14));     //  true
        System.out.println(rangeModule.queryRange(13, 15));     //  false
        System.out.println(rangeModule.queryRange(16, 17));     //  true
    }
}
