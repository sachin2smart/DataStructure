package in.sachinshinde.sort;

//	https://leetcode.com/problems/h-index/

/*
 *	Given an array of integers citations where 
 *		citations[i] is the number of citations a researcher received for their ith paper
 *	Return compute the researcher's h-index.
 *	
 *	##  H-Index: 
 *			A scientist has an index h if h of their n papers have at least h citations each, 
 *			and the other n - h papers have no more than h citations each.
 *	
 *	If there are several possible values for h, the maximum one is taken as the h-index. 	
 */

import java.util.Arrays;

public class HIndex_CountingSort {
	
	public int hIndex(int[] citations) {
        int n = citations.length;
        int[] buckets = new int[n+1];
        
        for(int c : citations)
            if(c >= n)
                buckets[n]++;
            else
                buckets[c]++;
                
        int count = 0;
        for(int i = n; i >= 0; i--) {
            count += buckets[i];
            if(count >= i) 
                return i;
        }
        return 0;
    }
	
	public static void main(String[] args) {
		HIndex_CountingSort sort = new HIndex_CountingSort();
		System.out.println(sort.hIndex(new int[] {100,8,2,2,1,2,2}));	//	2 
		System.out.println(sort.hIndex(new int[] {8,9,7,5,2,2,3}));		//	4 
		System.out.println(sort.hIndex(new int[] {800,900,700,500,200,200,300}));	//	7
	}

    //  Time Complexity : soring (n log n), iteration: O(n); Overall: O(n log n)
    public int hIndex2(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        for (int i = 0; i < n; i++) {
            int currCtIndex = n - i;
            if (citations[i] >= currCtIndex) {
                return currCtIndex;
            }
        }
        return 0;
    }
}

/*
	Google Interview:
		Given the user and there posts, maximize the likes of N posts where N appears at least N times.
		For eg. 
		1. 	Consider likes = [8,6,5,5,2,1] Output: 4 (Because there are 4 post with 4 likes. )
			Explanation: 8 contains 4 likes, 6 contains 4 likes,5 contains 4 likes,5 contains 4 likes and
				more importantly number of posts are 4
		2. 	likes = [1,2,2] Output 2 (Because there are 2 post with 2 likes)
		3. 	likes = [1,3,4,2] Output 2(Because there are 2 post with 2 likes)
 */

/* 
 * 	    
 	[3,0,6,1,5]
    
    n=5
    bucket_size = 6
    
    (1) 3>=5  b[3] = 1
    (2) 0>=5  b[0] = 1
    (3) 6>=5  b[5] = 1
    (4) 1>=5  b[1] = 1
    (5) 5>=5  b[5] = 2
    
    b = [1,1,0,1,0,2]
    
  i = 5 to 0 |    c+=b[i]   |   c>=i
 -------------------------------
    5        |    0+2 = 2   |   F
    4        |    2+0 = 2   |   F
    3        |    2+1 = 3   |   T
    
    hIndex = 3  

 */
