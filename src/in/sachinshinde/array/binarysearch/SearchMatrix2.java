package in.sachinshinde.array.binarysearch;

//  https://leetcode.com/problems/search-a-2d-matrix-ii/

/*
        Write an efficient algorithm that searches for a value target in an m x n integer matrix "matrix".
        This matrix has the following properties:
            - Integers in each row are sorted in ascending from left to right.
            - Integers in each column are sorted in ascending from top to bottom.

        Example 1:
        ---------
        Input: matrix = [
            [ 1, 4, 7, 11, 15],
            [ 2, 5, 8, 12, 19],
            [ 3, 6, 9, 16, 22],
            [10,13,14, 17, 24],
            [18,21,23, 26, 30]],

            target = 5

        Output: true

        Example 2:
        ---------
        Input: matrix = [
            [ 1, 4, 7, 11, 15],
            [ 2, 5, 8, 12, 19],
            [ 3, 6, 9, 16, 22],
            [10,13,14, 17, 24],
            [18,21,23, 26, 30]],

            target = 20

        Output: false


        Constraints:
        ------------
            m == matrix.length
            n == matrix[i].length
            1 <= n, m <= 300
            -109 <= matrix[i][j] <= 109
            All the integers in each row are sorted in ascending order.
            All the integers in each column are sorted in ascending order.
            -109 <= target <= 109
 */
public class SearchMatrix2 {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int row = 0;
        int col = n - 1;

        while(col >= 0 && row <= m - 1) {
            if (target == matrix[row][col]) {
                return true;
            }
            else if (target < matrix[row][col]) {
                col--;
            }
            else if (target > matrix[row][col]) {
                row++;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        SearchMatrix2 matrix2 = new SearchMatrix2();
        int[][] matrix = new int[][] {
                { 1, 4, 7, 11, 15},
                { 2, 5, 8, 12, 19},
                { 3, 6, 9, 16, 22},
                {10,13,14, 17, 24},
                {18,21,23, 26, 30}
        };

        System.out.println(matrix2.searchMatrix(matrix, 5));    // true
        System.out.println(matrix2.searchMatrix(matrix, 20));    // false
    }

}
