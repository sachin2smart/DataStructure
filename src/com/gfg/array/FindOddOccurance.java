/**
 * https://www.geeksforgeeks.org/find-the-number-occurring-odd-number-of-times/
 */

package com.gfg.array;

import java.util.HashMap;

public class FindOddOccurance {

	private static int getOddOccuranceLietral(int a[]){
		
		HashMap<Integer, Integer> hm = new HashMap<Integer,Integer>();

		for(int i=0;i<a.length;i++) {
			hm.computeIfPresent(a[i], (key, value) -> value + 1);
			hm.putIfAbsent(a[i], 1);
		}
			
		for(int i=0;i<a.length; i++) 
			if(hm.get(a[i])%2 !=0)
				return a[i];
		
		return -1;
	}

	public static void main(String[] args) {
		int a[] = {1,2,1,2,1,2,5,5};
		System.out.println(getOddOccuranceLietral(a));

	}

}
