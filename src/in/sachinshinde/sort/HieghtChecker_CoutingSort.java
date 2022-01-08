package in.sachinshinde.sort;

//	https://leetcode.com/problems/height-checker/

/*
 * 	A school is trying to take an annual photo of all the students. 
 * 	The students are asked to stand in a single file line in non-decreasing order by height. 
 * 	Let this ordering be represented by the integer array expected where expected[i] is 
 * 		the expected height of the ith student in line.

	You are given an integer array heights representing the current order that the students are standing in. 
	Each heights[i] is the height of the ith student in line (0-indexed).

	Return the number of indices where heights[i] != expected[i].
 */

public class HieghtChecker_CoutingSort {

	public static int heightChecker(int[] heights) {
		int[] hFreqArr = new int[101];
	    
	    for (int h : heights) {
	        hFreqArr[h]++;
	    }
	    
	    int count = 0;
	    int currH = 0;
	    
	    for (int i = 0; i < heights.length; i++) {
	        while (hFreqArr[currH] == 0) {
	            currH++;
	        }
	        
	        if (currH != heights[i]) {  // the height of each student linearly should be                                                 //equivalent to the current height
	            count++;
	        }
	        
	        hFreqArr[currH]--;  // this will reduce the frequency of height curretly being checked
	    }
	    
	    return count;
	}
	
	public static void main(String[] args) {
		int[] heights = new int[] {1,1,4,2,1,3};
		
		int numOfUnmatchedPositions = heightChecker(heights);
		System.out.println("Number of unmatched positions : "+ numOfUnmatchedPositions);
	}	
	
}

/*	
 * 	[1,1,4,2,1,3]
    
    hfreqArr[0] = 0;
    hfreqArr[1] = 3;
    hfreqArr[2] = 1;
    hfreqArr[3] = 1;
    hfreqArr[4] = 1;
    
    
 */