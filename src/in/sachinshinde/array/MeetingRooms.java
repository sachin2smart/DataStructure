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
	
	//	Method 1
	public class Interval {
        int start;
        int end;
    }
	
	public boolean canAttendMeetings(Interval[] intervals) {

        Arrays.sort(intervals, (a, b) -> a.start - b.start);

        for(int i = 0; i + 1 < intervals.length; i++)
            if(intervals[i].end > intervals[i + 1].start)
                return false;
            
        return true;
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
        for(int i = 1; i < len; i++){
            if(startTime[i] < endTime[i - 1]) return false;
        }
        return true;
    }
	
	//	Method 3 
	public boolean canAttendMeetings2(int[][] intervals) {
 		if(intervals == null) return false;
 		if(intervals.length == 0) return true;
 		Arrays.sort(intervals, (a, b)->{
 			return a[0] == b[0] ? a[1] - b[1]: a[0] - b[0];
 		});
 		for(int i = 1; i < intervals.length; i++){
 			if(intervals[i][0] < intervals[i - 1][1]) return false;
 		}
 		return true;
 	}
	
	public static void main(String[] args) {
		MeetingRooms meetingRooms = new MeetingRooms();
		int[][] t = {{0, 3}, {5, 10}, {15, 20}};
		System.out.println(meetingRooms.canAttendMeetings(t));	//	true
		System.out.println(meetingRooms.canAttendMeetings2(t));	//	true
	}
	
}