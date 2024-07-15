package in.sachinshinde.array.prefixsum;

import java.util.Arrays;

//  https://leetcode.com/problems/brightest-position-on-street/description/

/*
        A perfectly straight street is represented by a number line.
        The street has street lamp(s) on it and is represented by a 2D integer array lights.
        Each lights[i] = [positioni, rangei] indicates that
            there is a street lamp at position positioni that lights up the area
                from [positioni - rangei, positioni + rangei] (inclusive).

        The brightness of a position p is defined as the number of street lamp that light up the position p.

        Given lights, return the brightest position on the street.
        If there are multiple brightest positions, return the smallest one.

        Example 1:
        ---------
        Input: lights = [[-3,2],[1,2],[3,3]]
        Output: -1
        Explanation:
        The first street lamp lights up the area from [(-3) - 2, (-3) + 2] = [-5, -1].
        The second street lamp lights up the area from [1 - 2, 1 + 2] = [-1, 3].
        The third street lamp lights up the area from [3 - 3, 3 + 3] = [0, 6].

        Position -1 has a brightness of 2, illuminated by the first and second street light.
        Positions 0, 1, 2, and 3 have a brightness of 2, illuminated by the second and third street light.
        Out of all these positions, -1 is the smallest, so return it.

        Example 2:
        ---------
        Input: lights = [[1,0],[0,1]]
        Output: 1
        Explanation:
        The first street lamp lights up the area from [1 - 0, 1 + 0] = [1, 1].
        The second street lamp lights up the area from [0 - 1, 0 + 1] = [-1, 1].

        Position 1 has a brightness of 2, illuminated by the first and second street light.
        Return 1 because it is the brightest position on the street.

        Example 3:
        ---------
        Input: lights = [[1,2]]
        Output: -1
        Explanation:
        The first street lamp lights up the area from [1 - 2, 1 + 2] = [-1, 3].

        Positions -1, 0, 1, 2, and 3 have a brightness of 1, illuminated by the first street light.
        Out of all these positions, -1 is the smallest, so return it.

        Constraints:
        -----------
            1 <= lights.length <= 10^5
            lights[i].length == 2
            -10^8 <= positioni <= 10^8
            0 <= rangei <= 10^8


 */

public class BrightestPositionOnStreet {

    public int brightestPosition(int[][] lights) {
        /*
            "starts" contains start of the intervals.
            "ends" contains end of the intervals.
         */

        int n = lights.length;

        int[] starts = new int[n];
        int[] ends = new int[n];

        for (int i = 0; i < n; i++) {
            starts[i] = lights[i][0] - lights[i][1];
            ends[i] = lights[i][0] + lights[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);

        /*
            Keep two pointers.
                "startIndex" for starts Intervals array and
                "endIndex" for ends Intervals.
         */

        int startIndex = 0, endIndex = 0;
        int brightness = 0, max = 0;
        int res = starts[0];

        while (startIndex < starts.length) {
            int curStart = starts[startIndex];
            int curEnd = ends[endIndex];
            if (curStart <= curEnd) {
                /*
                    Found a new start increase the brightness by 1 and
                    move current of start to next position.
                 */
                brightness++;
                startIndex++;
            }
            else {
                /*
                    current end is bigger than the current start then decrease the brightness and
                    move current of end to next position.
                 */
                brightness--;
                endIndex++;
            }
            if (brightness > max) { 
                max = brightness;
                res = curStart;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        BrightestPositionOnStreet pos = new BrightestPositionOnStreet();
        System.out.println(pos.brightestPosition(new int[][]{{-3,2},{1,2},{3,3}}));     //  -1
        System.out.println(pos.brightestPosition(new int[][]{{1,0},{0,1}}));            //  1
        System.out.println(pos.brightestPosition(new int[][]{{1,2}}));                  //  -1
    }
}
