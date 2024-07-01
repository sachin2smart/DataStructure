package in.sachinshinde.array.medium;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//  https://www.geeksforgeeks.org/find-the-sum-of-medians-of-all-odd-length-subarrays/

/*
    Statement:
    ----------
        Given an array arr[] of size N, the task is to find the sum of medians of all sub-array of odd-length.
 */

/*
        Examples:

            Input: arr[] = {4, 2, 5, 1}
            Output: 18

            Explanation :
                Sub-Arrays of odd length and their medians are :

                [4]  -> Median is 4
                [4, 2, 5]  -> Median is 4
                [2]  -> Median is 2
                [2, 5, 1]  -> Median is 2
                [5]  -> Median is 5
                [1]  -> Median is 1

            Their sum = 4 + 4+ 2 + 2 + 5 +1 = 18

            Input: arr[] = {1, 2}
            Output: 3
 */
public class SumOfMediansOfAllOddLengthSubArrays {

    /*
            Time Complexity: O(N^3 * Log(N))
            Auxiliary Space: O(N)
     */
    private int getMediansSum(int[] a) {
        int res = 0;
        int n = a.length;

        for (int i = 0; i < n; i++) {
            List<Integer> currA = new LinkedList<>();
            for (int j = i; j < n; j++) {
                currA.add(a[j]);
                if ((currA.size() % 2) == 1) {
                    Collections.sort(currA);
                    int mid = currA.size() / 2;
                    res += currA.get(mid);
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        SumOfMediansOfAllOddLengthSubArrays medians = new SumOfMediansOfAllOddLengthSubArrays();
        System.out.println(medians.getMediansSum(new int[]{4,2,5,1}));  //  18
        System.out.println(medians.getMediansSum(new int[]{1,2}));  //  3
    }
}
