package in.sachinshinde.heap_priority_queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//	https://leetcode.ca/all/253.html

/*
 	-----Meeting Rooms II-----
 	
	Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
	find the minimum number of conference rooms required.

	For example, Given [[0, 30],[5, 10],[15, 20]], return 2.
 */


public class MeetingRoom2 {

	//	Sort
	public int minMeetingRooms(int[][] intervals) {
        int len = intervals.length;
        
        int[] startTime = new int[len];
        int[] endTime = new int[len];
        int index = 0;
        for(int[] interval: intervals){
            startTime[index] = interval[0];
            endTime[index] = interval[1];
            index++;
        }
        
        Arrays.sort(startTime);
        Arrays.sort(endTime);
        
        int i = 0, j = 0;
        int roomOccupied = 0, numOfRooms = 0;
        
        while(i < len && j < len) {
            if(startTime[i] < endTime[j]) {
            	roomOccupied++;
                i++;
            }
            else {
            	roomOccupied--;
                j++;
            }
            numOfRooms = Math.max(numOfRooms, roomOccupied);
        }
        
        return numOfRooms;
    }
	
	//	1D - PQ + Sort 
	public int minMeetingRooms2(int[][] intervals) {
        if(intervals == null || intervals.length == 0) 
        	return 0;
        
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];	//	Sort according to the start time 
            }
        });
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int numOfRooms = 0;
        
        for(int i=0; i<intervals.length; i++) {
            pq.offer(intervals[i][1]);	// Get the end time of next meeting as sorted above
            
            if(intervals[i][0] < pq.peek())  
            	numOfRooms++;	//	if current start time is less than end time of first meeting
            else
                pq.poll();	//	if current start time is greater than end time of first meeting 
            
        }
        
        return numOfRooms;
    }
	
	//	PQ + Sort 
	public int minMeetingRooms3(int[][] intervals) {
 		if(intervals.length <= 1) return intervals.length;
 		int result = 1;
 		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->{
 			return a[1] - b[1];
 		});
 		Arrays.sort(intervals, (a, b)->{
 			return a[0] != b[0] ? a[0] - b[0]: a[1] - b[1];
 		});
 		pq.offer(intervals[0]);
 		for(int i = 1; i < intervals.length; i++){
 			if(intervals[i][0] >= pq.peek()[1]){
 				pq.poll();
 			}
 			pq.offer(intervals[i]);
 			result = Math.max(result, pq.size());
 		}
 		return result;
 	}
	
	public static void main(String[] args) {
		MeetingRoom2 meetingRoom2 = new MeetingRoom2();
		
		int[][] intervals = new int[][] {{0, 30},{5, 10},{15, 20}};
		System.out.println(meetingRoom2.minMeetingRooms(intervals));	//	2
		System.out.println(meetingRoom2.minMeetingRooms2(intervals));
		System.out.println(meetingRoom2.minMeetingRooms3(intervals));
		
		int[][] intervals2 = new int[][] {{7, 10},{2, 4}};
		System.out.println(meetingRoom2.minMeetingRooms(intervals2));	//	1
		System.out.println(meetingRoom2.minMeetingRooms2(intervals2));
		System.out.println(meetingRoom2.minMeetingRooms3(intervals2));
	}
}
