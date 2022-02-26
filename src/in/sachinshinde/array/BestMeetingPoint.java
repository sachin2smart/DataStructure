package in.sachinshinde.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//	https://leetcode.ca/all/296.html
//	https://leetcode.ca/2016-09-21-296-Best-Meeting-Point/

/*
	A group of two or more people wants to meet and minimize the total travel distance.
 	You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group.
 	The distance is calculated using Manhattan Distance, 
 		where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

 	For example, given three people living at (0,0), (0,4), and (2,2):

		 1 - 0 - 0 - 0 - 1
		 |   |   |   |   |
		 0 - 0 - 0 - 0 - 0
		 |   |   |   |   |
		 0 - 0 - 1 - 0 - 0

 	The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. 
 	So return 6.

 */
public class BestMeetingPoint {

	public int minTotalDistance(int[][] grid) {
        List<Integer> iPos = new ArrayList<>();
        List<Integer> jPos = new ArrayList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                	iPos.add(i);
                	jPos.add(j);
                }
            }
        }

        int sum = 0;
        Collections.sort(jPos);
        
        int iMid = iPos.get(iPos.size() / 2);
        int jMid = jPos.get(jPos.size() / 2);
        
        for(int i: iPos)
            sum += Math.abs(i - iMid);

        for(int j: jPos)
            sum += Math.abs(jMid - j);

        return sum;
    }

	public static void main(String[] args) {
		BestMeetingPoint bestMeetingPoint = new BestMeetingPoint();
		int[][] grid = new int[][] {
			{1,0,0,0,1},
			{0,0,0,0,0},
			{0,0,1,0,0}
		};
		System.out.println(bestMeetingPoint.minTotalDistance(grid));		//	6
	}
}
