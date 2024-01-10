package in.sachinshinde.greedy;

import java.util.Arrays;

//	https://leetcode.com/problems/non-overlapping-intervals/

/*
 	Given an array of intervals intervals where intervals[i] = [starti, endi], 
 		return the minimum number of intervals 
 		you need to remove to make the rest of the intervals non-overlapping.
        
        Example 1:
        ----------
        Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
        Output: 1
        Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
        
        Example 2:
        ---------
        Input: intervals = [[1,2],[1,2],[1,2]]
        Output: 2
        Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
        
        Example 3:
        ---------
        Input: intervals = [[1,2],[2,3]]
        Output: 0
        Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
         
        
        Constraints:
        ----------
            1 <= intervals.length <= 105
            intervals[i].length == 2
            -5 * 104 <= starti < endi <= 5 * 104
 */

public class NonOverlappingIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        
        // Sorting based on end time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int count = 1;

        /*
         	For each interval at index i, it checks 
         		if the start time of the current interval (intervals[i][0]) 
         			is greater than or equal to the end time of the previous interval (intervals[prev][1]). 
         	
         	If this condition is true, it means the current interval does not overlap with the previous one, 
         		and we can safely attend this meeting. 
         	In that case, we update prev to the current index i and 
         		increment count to reflect that we have attended one more meeting.

         */
        int prev = 0;
        for (int i = 1; i < n; i++) {
            if (intervals[i][0] >= intervals[prev][1]) {
                prev = i;
                count++;
            }
        }
        
        //	count == number of meetings we can attend
        return n - count;
    }

    public int eraseOverlapIntervals_2(int[][] intervals) {       
        Arrays.sort(intervals, (a,b)-> a[1] - b[1]);
        int endTime = Integer.MIN_VALUE;
        int count = 0;
        for(int i=0; i<intervals.length; i++) {
            if(intervals[i][0] >= endTime)
        	endTime = intervals[i][1];
            else
                count++;
        }
        return count;      
    }
    
    public static void main(String[] args) {
	NonOverlappingIntervals intervals = new NonOverlappingIntervals();
	System.out.println(intervals.eraseOverlapIntervals(new int[][] {{1,2},{2,3},{3,4},{1,3}}));	//	1
	System.out.println(intervals.eraseOverlapIntervals(new int[][] {{1,2},{1,2},{1,2}}));		//	2
	System.out.println(intervals.eraseOverlapIntervals(new int[][] {{1,2},{2,3}}));			//	0
	
	System.out.println(intervals.eraseOverlapIntervals_2(new int[][] {{1,2},{2,3},{3,4},{1,3}}));	//	1
	System.out.println(intervals.eraseOverlapIntervals_2(new int[][] {{1,2},{1,2},{1,2}}));		//	2
	System.out.println(intervals.eraseOverlapIntervals_2(new int[][] {{1,2},{2,3}}));		//	0
    }
}
