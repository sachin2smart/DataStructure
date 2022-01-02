package in.sachinshinde.recursion;

import java.util.ArrayList;
import java.util.Collections;

//	https://practice.geeksforgeeks.org/problems/subset-sums2234/1
//	https://takeuforward.org/data-structure/subset-sum-sum-of-all-subsets/
//	https://www.youtube.com/watch?v=rYkfBRtMJr8&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=52

/*
 * 	Given a list arr of N integers, print sums of all subsets in it.
 * 	Note: Return all the element is increasing order.
 */

public class SubsetSum {
	
	public static ArrayList<Integer> getSubsetSum(ArrayList<Integer> arr, int n){
		ArrayList<Integer> sumSubset = new ArrayList<Integer>();
		constructSumSubset(0, 0, arr, n, sumSubset);
		Collections.sort(sumSubset);
		return sumSubset;
	}

	private static void constructSumSubset(int index, int sum, ArrayList<Integer> arr, int n, ArrayList<Integer> sumSubset) {
		if(index == n) { // when all indices been traversed
			sumSubset.add(sum);	// we are going to add sum to array **
			return;
		}
		constructSumSubset(index+1, sum+arr.get(index), arr, n, sumSubset);	//	at left recursion index increased, sum added
		constructSumSubset(index+1, sum,                arr, n, sumSubset);	//	at right right index increased, sum as is
	}
	
	public static void main(String args[]) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(3);
        arr.add(1);
        arr.add(2);
        ArrayList < Integer > ans = getSubsetSum(arr, arr.size());
        Collections.sort(ans);
        System.out.println("The sum of each subset is ");
        for (int i = 0; i < ans.size(); i++)
            System.out.print(ans.get(i) + " ");
    }
}
