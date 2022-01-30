package in.sachinshinde.recursion;

//	https://leetcode.com/problems/power-of-three/

/*
 * 	Power of Three
 * 	Given an integer n, return true if it is a power of three. Otherwise, return false.
	An integer n is a power of three, if there exists an integer x such that n == 3^x.
 */

/*
 	Example 1:	Input: n = 27	Output: true
	Example 2:	Input: n = 0	Output: false
 */

public class Pow3 {
	public boolean isPowerOfThree(int n) {
        if(n == 0) 
            return false;
        
        if(n == 1) 
            return true;
        
        if(n % 3 == 0) 
            return isPowerOfThree(n/3);
        
        return false;
    }
	
	
}
