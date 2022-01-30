package in.sachinshinde.recursion;

//	https://leetcode.com/problems/power-of-two/

/*
	Given an integer n, return true if it is a power of two. Otherwise, return false.
	An integer n is a power of two, if there exists an integer x such that n == 2x.
	Example:
		Input: n = 1
		Output: true
		Explanation: 20 = 1
 */

public class Pow2 {

	public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & n - 1) == 0;
    } 

}

/*
	n (in bits) 	-> 1 0 0 0 0 	(16 is a power of 2 and thus has only one high bit)
	n-1 (in bits) 	-> 0 1 1 1 1 	( n-1 i.e. 15 will make all bits high except the 5th bit)
	n & n-1 		-> 0 0 0 0 0 	(& operation will make all the bits to 0, thus its power of 2)
 */