package in.sachinshinde.z_company.Robinhood;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*

    You're exploring a mysterious labyrinth in the shape of a rectangular matrix, containing obstacles and teleports.
    Starting from the upper-left corner, you're wondering if it's possible to reach the lower-right corner
        by only moving to the right.

    You are given integers n and m representing the dimensions of the labyrinth, as well as obstacles and teleports,
        which are lists containing the coordinates of all the obstacles and teleports respectively.

    Here's how everything in the labyrinth works:
    • An obstacle cannot be traversed - you must stop immediately if you reach a cell containing an obstacle.
    • A teleport is a pair of cells (start, end), where start is the starting cell of teleportation, and
        end is the destination cell.
        If you reach the start cell, you are immediately teleported to the end cell.


    Note, that it doesn't work backwards: you cannot teleport from the end point to the start point.
    • It is guaranteed that there are no teleports with the same starting points
    (i.e. each cell has at most one option for teleportation).

    It is also guaranteed that both the starting and destination cell of the teleport do not contain obstacles.

    Any cell that doesn't contain an obstacle or a teleport is considered a free cell, and
    you can walk through it normally.

    You start on the cell with coordinates (0, 0) and the goal is located at the cell with coordinates (n-1, m-1)

    You move according to the following rules:
    • You will always move to the right:
    if you're currently standing on the cell with coordinates (row, col), you will try moving to
    the cell with coordinates (row, col + 1).
        -   If the destination cell is the starting point of a teleport, proceed to the teleportation end point.
        -   If the destination cell either contains an obstacle or is outside the labyrinth bounds,
            stop moving and stay where you are

    Your task is to check whether you can reach the exit of the labyrinth by following the algorithm above.
    Return true if you will eventually reach the goal, and false otherwise.
    It's guaranteed that the starting cell (e, e) and the goal cell (n - 1, m - 1)
    do not contain an obstacle or the start point of a teleport.

    Example
    • For n = 3, m = 3, obstacles = [[2, 1]], and teleports = [[0, 1, 2, 0]],
    the output should be labyrinthEscape (n, m, obstacles, teleports) = false.
    You will never reach the exit because of the obstacle, so the answer is false.

    • For n = 3, m = 4, obstacles = [[1, 1]], and teleports = [[0, 2, 0, 1], [0, 3, 2, 0]],
    the output should be labyrinthEscape (n, m, obstacles, teleports) = false.
    You will never reach the exit because of the infinite loop, so the answer is false.

    • For n = 3, m = 4, obstacles = [[2, 0], [1, 0]], and teleports = [[0, 1, 1, 1], [1, 2, 0, 2], [0, 3, 2, 1]],
    the output should be labyrinthEscape (n, m, obstacles, teleports) = true.
    By moving to the right and following teleports, you will eventually reach the exit cell (2,3),
    So the answer is true.

 */

public class LabyrinthEscape {

    public boolean labyrinthEscape(int n, int m, int[][] obstacles, int[][] teleports) {
        // Create a set of obstacles for quick lookup
        Set<String> obstacleSet = new HashSet<>();
        for (int[] obstacle : obstacles) {
            obstacleSet.add(obstacle[0] + "," + obstacle[1]);
        }

        // Create a map for teleports
        Map<String, int[]> teleportMap = new HashMap<>();
        for (int[] teleport : teleports) {
            teleportMap.put(teleport[0] + "," + teleport[1], new int[]{teleport[2], teleport[3]});
        }

        // Starting point
        int[] currentPosition = {0, 0};
        Set<String> visited = new HashSet<>();

        while (currentPosition[1] < m - 1) {
            // Move to the right
            int nextCol = currentPosition[1] + 1;

            // Check if the next position is an obstacle
            if (obstacleSet.contains(currentPosition[0] + "," + nextCol)) {
                return false;
            }

            // Check if we are outside the bounds
            if (nextCol >= m) {
                return false;
            }

            // Check if the next position is a teleport start point
            if (teleportMap.containsKey(currentPosition[0] + "," + nextCol)) {
                currentPosition = teleportMap.get(currentPosition[0] + "," + nextCol);
            } else {
                currentPosition[1] = nextCol;
            }

            // Check if we have visited this position before (to detect infinite loop)
            String posKey = currentPosition[0] + "," + currentPosition[1];
            if (visited.contains(posKey)) {
                return false;
            }

            visited.add(posKey);
        }

        // Check if we reached the goal
        return currentPosition[0] == n - 1 && currentPosition[1] == m - 1;
    }

    public static void main(String[] args) {
        LabyrinthEscape labyrinthEscape = new LabyrinthEscape();
        System.out.println(labyrinthEscape.labyrinthEscape(3, 3, new int[][]{{2, 1}}, new int[][]{{0, 1, 2, 0}}));      //  false
        System.out.println(labyrinthEscape.labyrinthEscape(3, 4, new int[][]{{1, 1}}, new int[][]{{0, 2, 0, 1}, {0, 3, 2, 0}}));    //  false
        System.out.println(labyrinthEscape.labyrinthEscape(3, 4, new int[][]{{2, 0}, {1, 0}}, new int[][]{{0, 1, 1, 1}, {1, 2, 0, 2}, {0, 3, 2, 1}}));  //  false
    }
}