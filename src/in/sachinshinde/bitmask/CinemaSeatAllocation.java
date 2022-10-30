package in.sachinshinde.bitmask;

import java.util.HashMap;
import java.util.Map;

//	https://leetcode.com/problems/cinema-seat-allocation/

/*	A cinema has n rows of seats, numbered from 1 to n and
 	 there are ten seats in each row, labelled from 1 to 10.

	Given the array reservedSeats containing the numbers of seats already reserved, 
	for example, reservedSeats[i] = [3,8] means 
		the seat located in row 3 and labelled with 8 is already reserved.

	Return the maximum number of four-person groups you can assign on the cinema seats. 
	A four-person group occupies four adjacent seats in one single row. 
	Seats across an aisle (such as [3,3] and [3,4]) are not considered to be adjacent, 
	  but there is an exceptional case on which an aisle split a four-person group, 
	  in that -case, the aisle split a four-person group in the middle, 
	  which means to have two people on each side.
*/

/*
 	Example : 1
  	Input: n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
	Output: 4
	Explanation: The figure above shows the optimal allocation for four groups, 
		where seats mark with blue are already reserved and 
		contiguous seats mark with orange are for one group.
		
	Example 2:
        Input: n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
        Output: 2
        
        Example 3:
        Input: n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]]
        Output: 4
 */

public class CinemaSeatAllocation {
    
    /*
	Sequence of seats to be available:
		2,3,4,5
		6,7,8,9
		4,5,6,7
		
	Using "Bit Vector" these can be represented as :
        	
        System.out.println((1 << 2) | (1 << 3) | (1 << 4) | (1 << 5));	//	60
        System.out.println((1 << 4) | (1 << 5) | (1 << 6) | (1 << 7));	//	240
        System.out.println((1 << 6) | (1 << 7) | (1 << 8) | (1 << 9));	//	960
        
     */
    
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
	Map<Integer, Integer> reservationMap = new HashMap<>();
        for(int[] reserved : reservedSeats)
            reservationMap.put(reserved[0], 
        	    reservationMap.getOrDefault(reserved[0], 0) | (1 << reserved[1]));
        
        int maxTickets = 0;
        for (int row : reservationMap.keySet()) {
            int reserved = reservationMap.get(row);
            int numTicketsFromCurrentRow = 0;
            
            // check if seats 2,3,4,5 are available
            if ((reserved & 60) == 0) 
        	numTicketsFromCurrentRow += 1; 
            
            // check if seats 6,7,8,9 are available
            if ((reserved & 960) == 0) 
        	numTicketsFromCurrentRow += 1; 
            
            // check if seats 4,5,6,7 are available
            if ((reserved & 240) == 0 && numTicketsFromCurrentRow == 0) 
        	numTicketsFromCurrentRow = 1; 
            
            maxTickets += numTicketsFromCurrentRow;
        }
        
        return maxTickets + 2 * (n - reservationMap.size());
    }

    public static void main(String[] args) {
	CinemaSeatAllocation seats = new CinemaSeatAllocation();
	int[][] reservedSeats  = new int[][] {
	    {1,2}, {1,3}, {1,8},
	    {2,6},
	    {3,1},{3,10}
	};
	
	System.out.println(seats.maxNumberOfFamilies(3, reservedSeats));	//	4
	System.out.println(seats.maxNumberOfFamilies2(3, reservedSeats));	//	4
	
	reservedSeats  = new int[][] {
	    {1,8},
	    {2,1}, {2,6}
	};
	System.out.println(seats.maxNumberOfFamilies(2, reservedSeats));	//	2
	System.out.println(seats.maxNumberOfFamilies2(2, reservedSeats));	//	2
    }
    
    public int maxNumberOfFamilies2(int n, int[][] reservedSeats) {
        Map<Integer, int[]> seats = new HashMap<>();
        int availableSlots = 2 * n; // max available slots since each empty row could fit at max 2 slots
        
        for (int[] seat: reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            
            int[] slots = seats.getOrDefault(row, new int[3]);
            
            if (col >= 2 && col <= 5) // left slot booked
                slots[0] = 1;
            
            if (col >= 4 && col <= 7) // middle slot booked
                slots[1] = 1;
            
            if (col >= 6 && col <= 9) // right slot booked
                slots[2] = 1;
            
            seats.put(seat[0], slots);
        }
        
        /*
         * 	slots 
         * 		{0, [1,1,1]}   = 0
         * 		{1, [0,1,1]}   = 1
         * 		{2, [1,1,0]}   = 1
         * 		{3, [1,0,1]}   = 1
         * 		{4, [0,0,1]}   = 2
         * 		{5, [0,1,0]}   = 2
         * 		{6, [0,0,1]}   = 2
         * 		{7, [1,0,0]}   = 2 
         * 		{8, [0,0,0]}   = 2 
         */
        
        for (int[] slots: seats.values()) {
            int taken = slots[0] + slots[2];
            if (taken == 2) { // both slots at either ends are taken
                if (slots[1] == 0) // check if middle slot not taken
                    availableSlots--; // reduce available slots by 1 since middle slot is available
                else
                    availableSlots -= 2; // entire row not available - reduce by 2
            } 
            else if (taken == 1) // one of the slots at either ends are taken
                availableSlots--; // reduce by 1 since either side of the slots not available
            else
                continue; // entire row is available - no need to reduce the available slots
            
        }        
        return availableSlots;
    }
}