package in.sachinshinde.sort;

import java.util.Arrays;

//	https://www.geeksforgeeks.org/counting-sort/

public class CountingSort {

	private static int TOTAL = 10;
	
	// ** CAUTION : Do not modify given array arr
	private static int[] sortByCountingSort(int[] arr) {
		
		int[] count = new int[TOTAL];
		int[] res = new int[arr.length];
		int n = arr.length;
		
		//	Get the frequency of each element
		for(int i=0; i<n; i++)
			count[arr[i]]++;
		
		// Shift the frequency to next index
		for(int i=1; i<TOTAL; i++)
			count[i] += count[i-1];
		
		// to keep the stability, starts from last index
		for(int i=n-1; i>=0; i--) {
			res[count[arr[i]] - 1] = arr[i]; 	// Very important step	
			count[arr[i]]--;
	    }
		
		// copy result array into arr
		for(int i=0; i<n; i++)
			arr[i] = res[i];
		
		return arr;
	}
	
	public static void main(String[] args) {
		int[] arr = new int[] {0,1,4,2,4,5,2};
		System.out.println("Given  : " + Arrays.toString(arr));
		arr = sortByCountingSort(arr);
		System.out.println("Sorted : " + Arrays.toString(arr));
	}
	
}
