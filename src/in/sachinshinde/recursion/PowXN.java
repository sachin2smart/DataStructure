package in.sachinshinde.recursion;

//	https://leetcode.com/problems/powx-n/
//	Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).

/*
 * 	Input: x = 2.00000, n = 10		Output: 1024.00000
 * 	Input: x = 2.10000, n = 3		Output: 9.26100
 * 	Input: x = 2.00000, n = -2		Output: 0.25000
 */

public class PowXN {

	public double myPow(double x, int n) {
        if(n == 0)
            return 1;
        
        if(n < 0)  
            return 1/x * myPow(1/x, -(n + 1));
        
        return (n%2 == 0) ? 
        			myPow(x*x, n/2) : 
                    x * myPow(x*x, n/2) ;
    }

}
