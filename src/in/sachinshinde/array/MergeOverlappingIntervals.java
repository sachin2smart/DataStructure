package in.sachinshinde.array;

import java.util.Arrays;
import java.util.LinkedList;

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
        if(intervals == null || intervals.length == 0)
            return intervals;
        
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        LinkedList<int[]> mergedIntervals = new LinkedList<>();
        
        for(int[] curr : intervals) {
        	//	if next start is greater than the merged's end, just add it to merged 
            if(mergedIntervals.isEmpty() || mergedIntervals.getLast()[1] < curr[0])
                mergedIntervals.add(curr);
            else 	//	if next start is less than merged's end, it means it falls under merged, 
            		//	so check for end and update the max of next's end or merged's end 
                mergedIntervals.getLast()[1] = Math.max(mergedIntervals.getLast()[1], curr[1]);
        }
        
        return mergedIntervals.toArray(new int[0][]);	
        //	Converting LinkedList of Arrays into the 2D array
    }
	
}
