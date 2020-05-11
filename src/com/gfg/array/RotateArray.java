package com.gfg.array;

import java.util.Arrays;

public class RotateArray {

	static int[] rotateArray(int arr[], int rorateBy) {
		for(int i=0; i<rorateBy ; i++) 
			arr = rorateArrayByOne(arr);
		
		return arr;
	}
	
	static int[] rorateArrayByOne(int[] arr) {
		int temp = arr[0];
		
		for(int i=0; i < arr.length-1; i++)
			arr[i] = arr[i+1];
		
		arr[arr.length-1] = temp;
		
		return arr;
	}
	
	public static void main(String[] args) {
		int a[] = {1,2,3,4,5};
		int r = 2;
		System.out.println(Arrays.toString(a));
		a = rotateArray(a, r);
		System.out.println(Arrays.toString(a));
	}

}
