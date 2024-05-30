package in.sachinshinde.array.hard;

//  https://leetcode.com/problems/median-of-two-sorted-arrays/

/*
        Given two sorted arrays nums1 and nums2 of size m and n respectively,
            return the median of the two sorted arrays.

        The overall run time complexity should be O(log (m+n)).

        Example 1:
        ---------
        Input: nums1 = [1,3], nums2 = [2]
        Output: 2.00000
        Explanation: merged array = [1,2,3] and median is 2.

        Example 2:
        ---------
        Input: nums1 = [1,2], nums2 = [3,4]
        Output: 2.50000
        Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.


        Constraints:
        -----------
        nums1.length == m
        nums2.length == n
        0 <= m <= 1000
        0 <= n <= 1000
        1 <= m + n <= 2000
        -10^6 <= nums1[i], nums2[i] <= 10^6
 */

public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i1 = 0;
        int i2 = 0;
        int med1 = 0;
        int med2 = 0;

        int i=0;
        int n = (nums1.length+nums2.length)>>1;

        while(i <= n) {
            med1 = med2;
            if (i1 == nums1.length) {
                med2 = nums2[i2];
                i2++;
            }
            else if (i2 == nums2.length) {
                med2 = nums1[i1];
                i1++;
            }
            else if (nums1[i1] < nums2[i2] ) {
                med2 = nums1[i1];
                i1++;
            }
            else {
                med2 = nums2[i2];
                i2++;
            }
            i++;
        }

        if ((nums1.length+nums2.length)%2 == 0) {
            return (float)(med1+med2)/2;
        }

        return med2;
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays med = new MedianOfTwoSortedArrays();
        System.out.println(med.findMedianSortedArrays(new int[]{1,3}, new int[]{2}));   // 2.0
        System.out.println(med.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));   // 2.5
    }
}