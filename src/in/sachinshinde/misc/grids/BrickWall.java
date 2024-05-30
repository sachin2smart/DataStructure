package in.sachinshinde.misc.grids;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//	https://leetcode.com/problems/brick-wall/

/*
	There is a rectangular brick wall in front of you with n rows of bricks. 
	The ith row has some number of bricks each of the same height (i.e., one unit) but 
	they can be of different widths. The total width of each row is the same.

        Draw a vertical line from the top to the bottom and cross the least bricks. 
        If your line goes through the edge of a brick, then the brick is not considered as crossed. 
        You cannot draw a line just along one of the two vertical edges of the wall, 
        in which case the line will obviously cross no bricks.
        
        Given the 2D array wall that contains the information about the wall, 
        return the minimum number of crossed bricks after drawing such a vertical line.
        
        
        Example 1:
        ---------
        Input: wall = [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
        Output: 2
        
        Example 2:
        ---------
        Input: wall = [[1],[1],[1]]
        Output: 3
         
        
        Constraints:
        -----------
            n == wall.length
            1 <= n <= 104
            1 <= wall[i].length <= 104
            1 <= sum(wall[i].length) <= 2 * 104
            sum(wall[i]) is the same for each row i.
            1 <= wall[i][j] <= 231 - 1
 * 
 */
public class BrickWall {

    // Nice Explanation: 
    //		https://leetcode.com/problems/brick-wall/discuss/888577/Intuitive-explanation-in-C%2B%2B-JAVA-w-Pictures-w-Comments
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> hm = new HashMap<>();
        int maxFreq = 0;
        
        for(int i=0; i<wall.size(); i++) {
            int k = 0;
            for(int j=0; j<wall.get(i).size()-1; j++) {
                k += wall.get(i).get(j);;
                hm.put(k, hm.getOrDefault(k, 0) + 1);
                maxFreq = Math.max(maxFreq, hm.get(k));
            }
        }
        
        return wall.size() - maxFreq;
    }
    
    public static void main(String[] args) {
	BrickWall brickWall = new BrickWall();
	System.out.println(brickWall.leastBricks(Arrays.asList(
		Arrays.asList(1,2,2,1),
		Arrays.asList(3,1,2),
		Arrays.asList(1,3,2),
		Arrays.asList(2,4),
		Arrays.asList(3,1,2),
		Arrays.asList(1,3,1,1))));	// 2
	
	System.out.println(brickWall.leastBricks(Arrays.asList(
		Arrays.asList(1),
		Arrays.asList(1), 
		Arrays.asList(1))));		// 3
    }
}
