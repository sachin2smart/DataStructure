package in.sachinshinde.array;

import java.util.Arrays;

//	https://practice.geeksforgeeks.org/problems/n-meetings-in-one-room-1587115620/1

/*
 *	There is one meeting room in a firm. There are N meetings in the form of (start[i], end[i]) 
 *		where start[i] is start time of meeting i and end[i] is finish time of meeting i.
 *
 *	What is the maximum number of meetings that can be accommodated in the meeting room 
 *		when only one meeting can be held in the meeting room at a particular time?
 *
 *	Note: Start time of one chosen meeting can't be equal to the end time of the other chosen meeting. 
 * 
 */

public class NMeetingsInOneRoom {
	
	public static int maxMeetings(int start[], int end[], int n)
    {
       int pair[][]=new int[n][2];
       
       for(int i=0;i<n;i++){
           pair[i][0]=start[i];
           pair[i][1]=end[i];
       }
       
       Arrays.sort(pair,(a,b)->a[1]-b[1]);
       
       int i=0;
       int j=1;	//start from the second pair 
       int count=1; // First meeting will always going to happen 
       
       while(j<n){
          if(pair[i][1] < pair[j][0]){ // compare end of first meeting (i'th) with start of next meeting (j'th)
              i=j;
              j++;
              count++;
          }
          else
            j++;
       }
       
       return count;
    }
}
