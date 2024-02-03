package in.sachinshinde.dp.minimize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//	https://leetcode.com/problems/triangle/

/*
 	Given a triangle array, return the minimum path sum from top to bottom.

        For each step, you may move to an adjacent number of the row below. 
        More formally, if you are on index i on the current row, 
        you may move to either index i or index i + 1 on the next row.
        
        
        Example 1:
        ---------
            Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
            Output: 11
            Explanation: The triangle looks like:
                   2
                  3 4
                 6 5 7
                4 1 8 3
        The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
        
        Example 2:
        ---------
            Input: triangle = [[-10]]
            Output: -10
         
        
        Constraints:
        -----------
            1 <= triangle.length <= 200
            triangle[0].length == 1
            triangle[i].length == triangle[i - 1].length + 1
            -104 <= triangle[i][j] <= 104
         
        
        Follow up: Could you do this using only O(n) extra space, 
        where n is the total number of rows in the triangle?
 */

//	video	: https://youtu.be/OM1MTokvxs4

public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] arr = new int[n+1];
        for(int i=n-1; i>=0; i--) {
            for(int j=0; j<triangle.get(i).size(); j++) {
                arr[j] = Math.min(arr[j], arr[j+1]) + triangle.get(i).get(j);
            }
        }
        return arr[0];
    }
    
    //	--------------------------------------------------------------------------------
    //	Method 2 ; Recursive Solution
    
    public int minimumTotal2(List<List<Integer>> triangle) {
        return recurse(triangle, 0, 0);
    }
    
    private int recurse(List<List<Integer>> triangle, int row, int col) {
        if (row == triangle.size())
            return 0;
        
        // either go to [row + 1, col] or [row + 1, col + 1]
        int remain = Math.min(recurse(triangle, row + 1, col),
                              recurse(triangle, row + 1, col + 1));
        
        // combine answer with current value
        return triangle.get(row).get(col) + remain;
    }
    
    //	--------------------------------------------------------------------------------
    //	Method 3: Memoization
    public int minimumTotal3(List<List<Integer>> triangle) {
        int[][] memo = new int[triangle.size()][triangle.get(triangle.size() - 1).size()];
        for (int[] row: memo)
            Arrays.fill(row, Integer.MAX_VALUE);
        
        return recurse(triangle, 0, 0, memo);
    }
    
    private int recurse(List<List<Integer>> triangle, int row, int col, int[][] memo) {
        if (row == triangle.size())
            return 0;
        
        if (memo[row][col] != Integer.MAX_VALUE)
            return memo[row][col];
        
        // either go to [row + 1, col] or [row + 1, col + 1]
        int remain = Math.min(recurse(triangle, row + 1, col, memo),
                              recurse(triangle, row + 1, col + 1, memo));
        
        // combine answer with current value & memoize it
        memo[row][col] = triangle.get(row).get(col) + remain;
        return memo[row][col];
    }
    
    //	--------------------------------------------------------------------------------
    //	Method 4 : Tabulation
    
    public int minimumTotal4(List<List<Integer>> triangle) {
	List<List<Integer>> table = new ArrayList<>();
        for (List<Integer> row: triangle) 
            table.add(new ArrayList<>(row));
        
        for (int row = triangle.size() - 2; row >= 0; row--) {
            for (int col = 0; col < triangle.get(row).size(); col++) {
                int remain = Math.min(table.get(row + 1).get(col),
                                      table.get(row + 1).get(col + 1));
                
                table.get(row).set(col, triangle.get(row).get(col) + remain);
            }
        }
        
        return table.get(0).get(0);
    }

    //	--------------------------------------------------------------------------------
    //	Method 5 : Tabulation with optimized space
    
    public int minimumTotal5(List<List<Integer>> triangle) {
        int[] table = new int[triangle.size() + 1];
        Arrays.fill(table, 0);
                
        for (int row = triangle.size() - 1; row >= 0; row--) {
            for (int col = 0; col <= row; col++) {
                int remain = Math.min(table[col],
                                      table[col + 1]);
                
                table[col] = triangle.get(row).get(col) + remain;
            }
        }
        
        return table[0];
    }
    
    //	--------------------------------------------------------------------------------
    
    public static void main(String[] args) {
	Triangle triangle = new Triangle();
	
	System.out.println(triangle.minimumTotal(Arrays.asList(
		Arrays.asList(2),
		Arrays.asList(3,4),
		Arrays.asList(6,5,7),
		Arrays.asList(4,1,8,3))));	//	11
	
	System.out.println(triangle.minimumTotal(Arrays.asList(
		Arrays.asList(-10))));		//	-10
	
	System.out.println(triangle.minimumTotal(Arrays.asList(
		Arrays.asList(2),
		Arrays.asList(3,4),
		Arrays.asList(6,5,0),
		Arrays.asList(4,1,8,0))));	//	6
	
	System.out.println(triangle.minimumTotal2(Arrays.asList(
		Arrays.asList(2),
		Arrays.asList(3,4),
		Arrays.asList(6,5,7),
		Arrays.asList(4,1,8,3))));	//	11
	
	System.out.println(triangle.minimumTotal2(Arrays.asList(
		Arrays.asList(2),
		Arrays.asList(3,4),
		Arrays.asList(6,5,0),
		Arrays.asList(4,1,8,0))));	//	6
	
	System.out.println(triangle.minimumTotal3(Arrays.asList(
		Arrays.asList(2),
		Arrays.asList(3,4),
		Arrays.asList(6,5,7),
		Arrays.asList(4,1,8,3))));	//	11
	
	System.out.println(triangle.minimumTotal3(Arrays.asList(
		Arrays.asList(2),
		Arrays.asList(3,4),
		Arrays.asList(6,5,0),
		Arrays.asList(4,1,8,0))));	//	6
	System.out.println(triangle.minimumTotal4(Arrays.asList(
		Arrays.asList(2),
		Arrays.asList(3,4),
		Arrays.asList(6,5,7),
		Arrays.asList(4,1,8,3))));	//	11
	
	System.out.println(triangle.minimumTotal4(Arrays.asList(
		Arrays.asList(2),
		Arrays.asList(3,4),
		Arrays.asList(6,5,0),
		Arrays.asList(4,1,8,0))));	//	6
	
	System.out.println(triangle.minimumTotal5(Arrays.asList(
		Arrays.asList(2),
		Arrays.asList(3,4),
		Arrays.asList(6,5,7),
		Arrays.asList(4,1,8,3))));	//	11
	
	System.out.println(triangle.minimumTotal5(Arrays.asList(
		Arrays.asList(2),
		Arrays.asList(3,4),
		Arrays.asList(6,5,0),
		Arrays.asList(4,1,8,0))));	//	6
    }
}