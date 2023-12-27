package in.sachinshinde.array;

import java.util.Arrays;

//	https://leetcode.ca/2016-08-08-252-Meeting-Rooms/
	
/*
 	Given an array of meeting time intervals consisting of start and end times 
 	[[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

	Example 1:
	
	Input: [[0,30],[5,10],[15,20]]
	Output: false
	Example 2:
	
	Input: [[7,10],[2,4]]
	Output: true
 */

public class MeetingRooms {
	
	public class Interval {
	    int start;
	    int end;
	    
	    public Interval(int start, int end) {
		this.start = start;
		this.end = end;
	    }
	}
	
	private boolean canAttendMeetings(Interval[] intervals) {
	    // 	sorting on the basis of start time
	    Arrays.sort(intervals, 
		    (a, b) 
		    	-> a.start - b.start);

	    for(int i = 1; i < intervals.length; i++)	// notice start of i=1
		if(intervals[i-1].end > intervals[i].start)
		    return false;
            
	    return true;
	}
	
	//	Method : 1
	public boolean canAttendMeetings1(int[][] intervals) {
	    if(intervals == null) 
		return false;
	    
	    if(intervals.length == 0) 
		return true;
	    
	    Interval[] classicIntervals = new Interval[intervals.length];
	    
	    for(int i=0; i<intervals.length; i++)
		classicIntervals[i] = new Interval(intervals[i][0], intervals[i][1]);
	    
	    return canAttendMeetings(classicIntervals);
	}
	
	//	Method : 2
	public boolean canAttendMeetings(int[][] intervals) {
	    int len = intervals.length;
	    int[] startTime = new int[len];
	    int[] endTime = new int[len];
	    int count = 0;
	    
	    for(int[] interval: intervals){
		startTime[count] = interval[0];
		endTime[count++] = interval[1];
	    }
	    
            Arrays.sort(startTime);
            Arrays.sort(endTime);
            
            for(int i = 1; i < len; i++)	// notice start i=1
                if(startTime[i] < endTime[i - 1]) 
                    return false;
            
            return true;
	}
	
	//	Method 3 
	public boolean canAttendMeetings2(int[][] intervals) {
	    if(intervals == null) 
		return false;
	    
	    if(intervals.length == 0) 
		return true;
	    
	    //	if equal start time then sort on the basis of end time 
	    //		otherwise sort by start time
	    Arrays.sort(intervals, 
		    		(a, b)-> {
		    		    	return a[0] == b[0] ? a[1] - b[1]: a[0] - b[0];
		    		});
	    
	    for(int i = 1; i < intervals.length; i++)	// start from the second interval i=1
 		if(intervals[i][0] < intervals[i - 1][1]) 
 		    return false;
	    
	    return true;
 	}
	
	public static void main(String[] args) {
	    MeetingRooms meetingRooms = new MeetingRooms();
	    int[][] t = new int [][] {{0, 30}, {5, 10}, {15, 20}};
	    System.out.println(meetingRooms.canAttendMeetings(t));	//	false
	    System.out.println(meetingRooms.canAttendMeetings1(t));	//	false
	    System.out.println(meetingRooms.canAttendMeetings2(t));	//	false
	    
	    t = new int [][] {{7,10},{2,4}};
	    System.out.println(meetingRooms.canAttendMeetings(t));	//	true
	    System.out.println(meetingRooms.canAttendMeetings1(t));	//	true
	    System.out.println(meetingRooms.canAttendMeetings2(t));	//	true
	}
	
}