package in.sachinshinde.recursion;

//	https://leetcode.com/problems/power-of-four/

/*
 * 	Power of Four
 * 	Given an integer n, return true if it is a power of four. Otherwise, return false.
	An integer n is a power of four, if there exists an integer x such that n == 4^x.
 */

/*
 	Example 1:	Input: n = 16	Output: true
	Example 2:	Input: n = 5	Output: false
 */

public class Pow4 {

	public boolean isPowerOfFour(int n) {
        if(n>1) 
            while(n%4==0) 
                n /= 4;
        
        return n==1; 
    }
	
	public boolean isPowerOfFour_1(int n) {
        return (int)(Math.log10(n)/Math.log10(4))  == 
        			(Math.log10(n)/Math.log10(4));
    }
	
	public boolean isPowerOfFour_2(int n) {
        if(n == 0)
        	return false;
        
        if(n == 1)	
        	return true;
        
        if(n%4 == 0)
            return isPowerOfFour(n/4);
        
        else
            return false;
    }
}
