package in.sachinshinde.array.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
        
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        LinkedList<int[]> mergedIntervals = new LinkedList<>();
        
        for(int[] curr : intervals) {
        	//	if next start is greater than the merged's end, just add it to merged 
            if(mergedIntervals.isEmpty() || mergedIntervals.getLast()[1] < curr[0]) {
                mergedIntervals.add(curr);
            }
            else {
                //	if next start is less than merged's end, it means it falls under merged,
                //	so check for end and update the max of next's end or merged's end
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
        intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));

        List<Interval> result = new LinkedList<Interval>();
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
    }
}
