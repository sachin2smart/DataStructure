package in.sachinshinde.array.binarysearch;

//  https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix

/*
        Given an n x n matrix where each of the rows and columns is sorted in ascending order,
        return the kth smallest element in the matrix.

        Note that it is the kth smallest element in the sorted order, not the kth distinct element.
        You must find a solution with a memory complexity better than O(n2).

        Example 1:
        ---------
        Input: matrix = [
                [1, 5,  9],
                [10,11,13],
                [12,13,15]],

            k = 8

        Output: 13
        Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13

        Example 2:
        ---------
        Input: matrix = [[-5]], k = 1
        Output: -5

        Constraints:
        -----------
            n == matrix.length == matrix[i].length
            1 <= n <= 300
            -109 <= matrix[i][j] <= 109
            All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
            1 <= k <= n2


        Follow up:
        ---------
            Could you solve the problem with a constant memory (i.e., O(1) memory complexity)?
            Could you solve the problem in O(n) time complexity?
            The solution may be too advanced for an interview but you may find reading this paper fun.
 */

import in.sachinshinde.heap_priority_queue.KthLargestElementInAnArray;

public class KthSmallestElementInASortedMatrix {

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int low = matrix[0][0], high = matrix[n - 1][n - 1];

        while(low < high) {

            int mid = low + (high - low) / 2;

            if(exists(matrix, mid, k, n)) {
                high = mid;
            }
            else {
                low = mid + 1;
            }

        }
        return low;
    }

    private boolean exists(int[][] matrix, int mid, int k, int n) {
        int count = 0;
        int high = n - 1, low = 0;
        while (high >= 0 && low < n) {
            if (matrix[high][low] <= mid) {
                count += (high + 1);
                low++;
            }
            else {
                high--;
            }
        }
        return count >= k;
    }

    public static void main(String[] args) {
        KthSmallestElementInASortedMatrix kthSmallestElementInASortedMatrix = new KthSmallestElementInASortedMatrix();
        int[][] mat = new int[][] {
                {1, 5,  9},
                {10,11,13},
                {12,13,15}
        };
        System.out.println(kthSmallestElementInASortedMatrix.kthSmallest(mat, 8));  //  13
        System.out.println(kthSmallestElementInASortedMatrix.kthSmallest2(mat, 8));  //  13

        mat = new int[][] {{-5}};

        System.out.println(kthSmallestElementInASortedMatrix.kthSmallest(mat, 1));  // -5
        System.out.println(kthSmallestElementInASortedMatrix.kthSmallest2(mat, 1));  //  -5
    }


    public int kthSmallest2(int[][] matrix, int k) {
        int low = matrix[0][0]; //  holds a value, not index
        int high = matrix[matrix.length - 1][matrix[0].length - 1] + 1; ////  holds a value, not index

        while(low < high) {
            int mid = low + (high - low) / 2;

            //  from each row count the number of elements which are less than mid-value calculated
            int numOfEleLessThanMid = 0,  columnIndex = matrix[0].length - 1;
            for (int[] currRow : matrix) {
                while (columnIndex >= 0 && currRow[columnIndex] > mid) {
                    columnIndex--;
                }
                numOfEleLessThanMid += (columnIndex + 1);
            }

            //  binary search flips
            if(numOfEleLessThanMid < k) {
                low = mid + 1;
            }
            else {
                high = mid;
            }
        }

        return low; //  the answer will be in "low" value
    }
}
