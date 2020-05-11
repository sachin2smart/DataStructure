package com.gfg.array;

public class EquilibriumIndex {

	public static void main(String[] args) {
		int a[] = {1,2,3,4,3,2,1};
		
		long startTime = System.nanoTime();
		int ind = getEquilibriumIndex(a);
		long endTime = System.nanoTime();
		
		if(ind !=0) {
			System.out.println("Given array has equilibrium index at the postion : "+ ind + " , value : " + a[ind]);
			System.out.println("Time taken : " + (endTime - startTime));   //6037    4830
		}
		else {
			System.out.println("Array do not have an equilibrium index");
		}
			
	}

	private static int getEquilibriumIndex(int a[]) {
		int len = a.length;
		int i,j;
		
		for(i=0; i<len; i++) {
			
			int leftSum = 0;
			for(j=0; j<i; j++) {
				leftSum += a[j]; 
			}
			
			int rightSum = 0;
			for(int k=i+1; k<len; k++) {
				rightSum += a[k];
			}
			
			if(leftSum == rightSum) {
				return i;
			}
		}
		
		return 0;
	}
	
	private static int getEqIndex(int a[]) {
		
		int i, sum=0, leftSum=0;
		
		for(i=0; i<a.length; i++)
			sum += a[i];
			
		for(i=0; i<a.length; i++) {
			sum -=  a[i];
			
			if(sum == leftSum)
				return i;
			
			leftSum += a[i];
		}
		
		return 0;
	}

}
