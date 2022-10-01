package in.sachinshinde.array;

import java.util.Arrays;
import java.util.Stack;

//	https://leetcode.com/problems/daily-temperatures/

/*
 	Given an array of integers temperatures represents the daily temperatures, 
 	return an array answer such that 
 		answer[i] is the number of days you have to wait 
 			after the ith day to get a warmer temperature. 
 	If there is no future day for which this is possible, keep answer[i] == 0 instead.

 	---------
        Example 1:
        ---------
            Input: temperatures = [73,74,75,71,69,72,76,73]
            Output: [1,1,4,2,1,1,0,0]
        ---------
        Example 2:
        ---------
            Input: temperatures = [30,40,50,60]
            Output: [1,1,1,0]
        ---------
        Example 3:
        ---------
            Input: temperatures = [30,60,90]
            Output: [1,1,0]
 */
public class DailyTempratures {
    //	using stack
    public int[] dailyTemperatures(int[] temperatures) {
	Stack<Integer> st = new Stack<>();
	int[] ans = new int[temperatures.length];
	for (int i = 0; i < temperatures.length; i++) {
	    while (!st.isEmpty() && temperatures[i] > temperatures[st.peek()]) {
		int idx = st.pop();
		ans[idx] = i - idx;
	    }
	    st.push(i);
	}
	return ans;
    }

    //	using 2 arrays
    public int[] dailyTemperatures2(int[] temperatures) {
	int[] arr = new int[temperatures.length];
	int curri = -1;
	int[] ans = new int[temperatures.length];
	for (int i = 0; i < temperatures.length; i++) {
	    while (curri > -1 && temperatures[i] > temperatures[arr[curri]]) {
		int idx = arr[curri--];
		ans[idx] = i - idx;
	    }
	    arr[++curri] = i;
	}
	return ans;
    }

    //	using 1 array
    public int[] dailyTemperatures3(int[] temperatures) {
	int n = temperatures.length;
	int[] ans = new int[n];
	for (int i = n - 1; i >= 0; i--) {	//	start from last day
	    int j = i + 1;	//	pointer to check temperature of next day
	    
	    // while current temperature is higher than next
	    while (j < n && temperatures[j] <= temperatures[i])
		if(ans[j] > 0)  
		    j += ans[j];	//	jump "j" days ahead
		else
		    j = n;	//	this when we have ans[j] has 0 value 
	    
	    // here, we get the j value as next higher temperature  
	    if (j < n)
		ans[i] = j - i;
	}
	return ans;
    }

    public static void main(String[] args) {
	DailyTempratures dt = new DailyTempratures();
	System.out.println(Arrays.toString(dt.dailyTemperatures(new int[] { 73, 74, 75, 71, 69, 72, 76, 73 })));
	System.out.println(Arrays.toString(dt.dailyTemperatures2(new int[] { 73, 74, 75, 71, 69, 72, 76, 73 })));
	System.out.println(Arrays.toString(dt.dailyTemperatures3(new int[] { 73, 74, 75, 71, 69, 72, 76, 73 })));
	
	System.out.println(Arrays.toString(dt.dailyTemperatures(new int[] { 30, 40, 50, 60 }))); // [1, 1, 1, 0]
	System.out.println(Arrays.toString(dt.dailyTemperatures2(new int[] { 30, 40, 50, 60 }))); // [1, 1, 1, 0]
	System.out.println(Arrays.toString(dt.dailyTemperatures3(new int[] { 30, 40, 50, 60 }))); // [1, 1, 1, 0]

    }
}