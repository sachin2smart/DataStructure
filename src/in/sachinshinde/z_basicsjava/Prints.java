package in.sachinshinde.z_basicsjava;

import java.util.Arrays;

public class Prints {
	public static void main(String[] args) {
		
	//	1. Print 1-D Array
		int[] arr1D = new int[] {1,2};
		System.out.println(Arrays.toString(arr1D));
			
	//	2. Print 2-D Array
		int[][] arr2D = new int[][] {{1,2},{3,4}};
		System.out.println(Arrays.deepToString(arr2D));
		
	}
}
