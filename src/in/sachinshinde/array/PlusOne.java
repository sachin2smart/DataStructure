package in.sachinshinde.array;

import java.util.Arrays;

//	https://leetcode.com/problems/plus-one/

/*
 * 	You are given a large integer represented as an integer array digits, 
 * 		where each digits[i] is the ith digit of the integer. 
 * 	The digits are ordered from most significant to least significant in left-to-right order. 
 *	The large integer does not contain any leading 0's.

        Increment the large integer by one and return the resulting array of digits.
        
        Example 1:
        ---------
        Input: digits = [1,2,3]
        Output: [1,2,4]
        Explanation: The array represents the integer 123.
        Incrementing by one gives 123 + 1 = 124.
        Thus, the result should be [1,2,4].
        
        Example 2:
        ---------
        Input: digits = [4,3,2,1]
        Output: [4,3,2,2]
        Explanation: The array represents the integer 4321.
        Incrementing by one gives 4321 + 1 = 4322.
        Thus, the result should be [4,3,2,2].
 */

public class PlusOne {
    public int[] plusOne(int[] digits) {

	int n = digits.length;
	    
	for(int i=n-1; i>=0; i--) {
	    if(digits[i] < 9) {
		digits[i]++;
		return digits;
	    }

	    digits[i] = 0;
	}

	int[] newNum = new int [n+1];
	newNum[0] = 1;

	return newNum;
    }
    
    public static void main(String[] args) {
	PlusOne one = new PlusOne();
	System.out.println(Arrays.toString(one.plusOne(new int[] {1,2,3})));	//	[1,2,4]
	System.out.println(Arrays.toString(one.plusOne(new int[] {9,9})));	//	[1,0,0]
	System.out.println(Arrays.toString(one.plusOne(new int[] {1,9,9})));	//	[2,0,0]
    }
}
