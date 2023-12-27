package in.sachinshinde.array;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MeetingRooms2 {

    public int minMeetingRooms(int[][] intervals) {
	if(intervals == null) 
	    return 0;
	    
	if(intervals.length == 0) 
	    return 0;
	    
	//	if equal start time then sort on the basis of end time 
	//		otherwise sort by start time
	Arrays.sort(intervals, 
		    	(a, b)-> {
		    	    return a[0] == b[0] ? a[1] - b[1]: a[0] - b[0];
		    	});
	
	int minRooms = 1;
	for(int i = 1; i < intervals.length; i++)	// start from the second interval i=1
	    if(intervals[i][0] < intervals[i - 1][1]) 
		minRooms++;
	    
	return minRooms;
    }
    
    //	------------------------------------------------------------------------------------ //
	
    public class Interval {
	int start;
	int end;
	    
	public Interval(int start, int end) {
	    this.start = start;
	    this.end = end;
	}
    }
	
    private int minMeetingRooms1(Interval[] intervals) {
	// sorting on the basis of start time
	Arrays.sort(intervals, 
		   	(a, b) 
		   	-> a.start - b.start);

	int count = 1;
	for(int i = 1; i < intervals.length; i++)	// notice start of i=1
	    if(intervals[i-1].end > intervals[i].start)
		count++;
        
	 return count;
    }
	
    //	Method : 2
    public int minMeetingRooms1(int[][] intervals) {
	if(intervals == null) 
	    return 0;
	    
	if(intervals.length == 0) 
	    return 0;
	    
	Interval[] classicIntervals = new Interval[intervals.length];
	    
	for(int i=0; i<intervals.length; i++)
	    classicIntervals[i] = new Interval(intervals[i][0], intervals[i][1]);
	    
	return minMeetingRooms1(classicIntervals);
    }
    
    //	------------------------------------------------------------------------------------ //
    
    private int minMeetingRooms2(Interval[] intervals) {
	//	Map of startTime/endTime------to-----1/0
        Map<Integer, Integer> tm = new TreeMap<>();

        for (Interval interval: intervals) {
            int start = interval.start;
            int end = interval.end;
            tm.put(start, tm.getOrDefault(start, 0) + 1);
            tm.put(end  , tm.getOrDefault(end,   0) - 1);
        }

        int totalOccupiedRooms = 0;
        int numRoomsRequired = 0;

        // If a time is the end time of one meeting and the start time of another meeting,
        // the mapping value is decreased first and then increased and remains at 0, no new room is allocated
        for (int occupiedRoomsAtThisInerval: tm.values()) {
            totalOccupiedRooms += occupiedRoomsAtThisInerval;
            numRoomsRequired = Math.max(numRoomsRequired, totalOccupiedRooms);
        }

        return numRoomsRequired;
    }
    
    //	Method : 3
    public int minMeetingRooms2(int[][] intervals) {
	if(intervals == null) 
	    return 0;
	    
	if(intervals.length == 0) 
	    return 0;
	    
	Interval[] classicIntervals = new Interval[intervals.length];
	    
	for(int i=0; i<intervals.length; i++)
	    classicIntervals[i] = new Interval(intervals[i][0], intervals[i][1]);
	    
	return minMeetingRooms2(classicIntervals);
    }
    
//------------------------------------------------------------------------------------------- //
    
    private int minMeetingRooms3(Interval[] intervals) {
        if(intervals == null || intervals.length == 0)
            return 0;
        

        // Sort the intervals by start time
        Arrays.sort(intervals, (i1, i2) -> i1.start - i2.start);

        // to store end time of each meeting, smaller value will be at the peek()
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        // start with the first meeting, put the "end" value into queue
        int count = 1;
        pq.offer(intervals[0].end);

        for(int i = 1; i < intervals.length; i++) {	// start from second sorted interval i=1 
            if(intervals[i].start < pq.peek()) {
                count++; // conflict, need 1 more room
                pq.offer(intervals[i].end);
            } 
            else {
                // if the current meeting starts right after
                // there's no need for a new room, merge the interval
                pq.offer(Math.max(intervals[i].end, pq.poll())); // poll then offer, conceptually merging 2 intervals
            }
        }

        return count;
    }
    
    // Method : 4
    public int minMeetingRooms3(int[][] intervals) {
	if(intervals == null) 
	    return 0;
	    
	if(intervals.length == 0) 
	    return 0;
	    
	Interval[] classicIntervals = new Interval[intervals.length];
	    
	for(int i=0; i<intervals.length; i++)
	    classicIntervals[i] = new Interval(intervals[i][0], intervals[i][1]);
	    
	return minMeetingRooms2(classicIntervals);
    }

 //------------------------------------------------------------------------------------------- //
    
    public static void main(String[] args) {
	MeetingRooms2 meetingRooms2 = new MeetingRooms2();
	int[][] t = new int [][] {{0, 30}, {5, 10}, {15, 20}};
	System.out.println(meetingRooms2.minMeetingRooms(t));	//	2
	System.out.println(meetingRooms2.minMeetingRooms1(t));	//	2
	System.out.println(meetingRooms2.minMeetingRooms2(t));	//	2
	System.out.println(meetingRooms2.minMeetingRooms3(t));	//	2

	t = new int [][] {{7,10},{2,4}};
	System.out.println(meetingRooms2.minMeetingRooms(t));	//	1
	System.out.println(meetingRooms2.minMeetingRooms1(t));	//	1
	System.out.println(meetingRooms2.minMeetingRooms2(t));	//	1
	System.out.println(meetingRooms2.minMeetingRooms3(t));	//	1
    }
}