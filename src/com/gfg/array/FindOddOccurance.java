/**
 * https://www.geeksforgeeks.org/find-the-number-occurring-odd-number-of-times/
 */

package com.gfg.array;

import java.util.HashMap;

/**
 * @author Sachin
 *
 */
public class FindOddOccurance {

	private static int getOddOccuranceLietral(int a[]){
		
		HashMap<Integer, Integer> hm = new HashMap<Integer,Integer>();
		
		for(int i=0;i<a.length;i++) 
			if(hm.containsKey(a[i])) 
				hm.put(a[i],hm.get(a[i]) +1);
			else 
				hm.put(a[i], 1);
			
		for(int i=0;i<a.length; i++) 
			if(hm.get(a[i])%2 !=0)
				return a[i];
		
		
		return -1;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int a[] = {1,2,1,2,1,2};
		System.out.println(getOddOccuranceLietral(a));

	}

}
