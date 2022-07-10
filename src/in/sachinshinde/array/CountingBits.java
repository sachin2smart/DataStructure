package in.sachinshinde.array;

import java.util.Arrays;

//	https://leetcode.com/problems/counting-bits/

//	Given an integer n, 
//	return an array ans of length n + 1 such that for each i (0 <= i <= n), 
//		ans[i] is the number of 1's in the binary representation of i.

public class CountingBits {

    public int[] countBits(int n) {
        int res[] = new int[n + 1]; 
        
        for(int i = 0; i <= n; i++)
            res[i] = solve(i, res);

        return res;
    }

    public int solve(int n, int arr[]) {

        if(n == 0) 
        	return 0;

        if(n == 1) 
        	return 1;
        
        if(arr[n] != 0) 
        	return arr[n]; 

        if(n % 2 == 0) {
            arr[n] = solve(n / 2, arr);
            return solve(n / 2, arr);
        }
        else {
            arr[n] = 1 + solve(n / 2, arr);
            return 1 + solve(n / 2, arr);
        } 
    }
    
    public static void main(String[] args) {
		CountingBits countingBits = new CountingBits();
		System.out.println(Arrays.toString(countingBits.countBits(2)));		//	[0, 1, 1]				
		System.out.println(Arrays.toString(countingBits.countBits(5)));		//	[0, 1, 1, 2, 1, 2]
		System.out.println(Arrays.toString(countingBits.countBits(95)));		
		//	[0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4, 1, 2, 2, 3, 2, 3, 
		//		3, 4, 2, 3, 3, 4, 3, 4, 4, 5, 1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 
		//		3, 4, 4, 5, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 1, 2, 
		//		2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5, 2, 3, 3, 4, 3, 4, 4, 5, 
		//		3, 4, 4, 5, 4, 5, 5, 6]
		System.out.println(Arrays.toString(countingBits.countBits2(2)));		//	[0, 1, 1]				
		System.out.println(Arrays.toString(countingBits.countBits2(5)));		//	[0, 1, 1, 2, 1, 2]
		System.out.println(Arrays.toString(countingBits.countBits2(95)));
		//	[0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4, 1, 2, 2, 3, 2, 3, 
			//		3, 4, 2, 3, 3, 4, 3, 4, 4, 5, 1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 
			//		3, 4, 4, 5, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 1, 2, 
			//		2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5, 2, 3, 3, 4, 3, 4, 4, 5, 
			//		3, 4, 4, 5, 4, 5, 5, 6]
	}
    
    public int[] countBits2(int n) {
    	int[] res = new int[n+1];
    	res[0] = 0;
    	
    	for(int i=1; i<=n; i++) 
    		res[i] = res[i/2] + i%2;
    	return res;
    }
}