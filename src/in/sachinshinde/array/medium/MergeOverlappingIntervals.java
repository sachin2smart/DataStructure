package in.sachinshinde.array.medium;

import java.util.*;

//	https://leetcode.com/problems/merge-intervals/
	
/*
 * 	Given an array of intervals where intervals[i] = [starti, endi], 
 * 		merge all overlapping intervals, and return an array of the non-overlapping intervals 
 * 		that cover all the intervals in the input.
 * 
	Example 1:
	
	Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
	Output: [[1,6],[8,10],[15,18]]
	
	Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 */

public class MergeOverlappingIntervals {
	
	public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length <= 1) {
            return intervals;
        }
        
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        
        LinkedList<int[]> mergedIntervals = new LinkedList<>();
        
        for(int[] curr : intervals) {
        	//	if next start is greater than the last merged interval's end, just add it as a new interval
            if(mergedIntervals.isEmpty() || mergedIntervals.getLast()[1] < curr[0]) {
                mergedIntervals.add(curr);
            }
            else {
                //	if curr's start is less than merged interval's end, it means it falls under merged,
                //	so check for end and update the max of next's end or merged end
                mergedIntervals.getLast()[1] = Math.max(mergedIntervals.getLast()[1], curr[1]);
            }
        }
        
        return mergedIntervals.toArray(new int[0][]);	
        //	Converting LinkedList of Arrays into the 2D array
    }

    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1)
            return intervals;

        // Sort by ascending starting point using an anonymous Comparator
        intervals.sort(Comparator.comparingInt(i -> i.start));

        List<Interval> result = new LinkedList<>();
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;

        for (Interval interval : intervals) {
            if (interval.start <= end) { // Overlapping intervals, move the end if needed
                end = Math.max(end, interval.end);
            }
            else {                     // Disjoint intervals, add the previous one and reset bounds
                result.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }

        // Add the last interval
        result.add(new Interval(start, end));
        return result;
    }

    private static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "["+this.start+", "+this.end+"]";
        }
    }

    public static void main(String[] args) {
        MergeOverlappingIntervals intervals = new MergeOverlappingIntervals();
        System.out.println(Arrays.deepToString(intervals.merge(new int[][]{
                {1,3}, {2,6}, {8,10}, {15,18}
        })));   // [[1, 6], [8, 10], [15, 18]]

        List<Interval> intervalsList = new ArrayList<>();
        intervalsList.add(new Interval(1,3));
        intervalsList.add(new Interval(2,6));
        intervalsList.add(new Interval(8,10));
        intervalsList.add(new Interval(15,18));
        System.out.println(intervals.merge(intervalsList));
        // [[1, 6], [8, 10], [15, 18]]

        System.out.println(Arrays.deepToString(intervals.merge_2(new int[][]{
                {1, 3}, {2, 6}, {8, 10}, {15, 18}
        })));
        // [[1, 6], [8, 10], [15, 18]]
    }

    public int[][] merge_2(int[][] intervals) {
        List<int[]> res = new ArrayList<>();

        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));

        for (int[] interval : intervals) {
            if (res.isEmpty() || res.get(res.size() - 1)[1] < interval[0]) {
                res.add(interval);
            }
            else {
                res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], interval[1]);
            }
        }

        return res.toArray(new int[0][]);
    }
}
