package in.sachinshinde.misc;

//  https://leetcode.com/problems/robot-bounded-in-circle/

/*
    On an infinite plane, a robot initially stands at (0, 0) and faces north. Note that:

    The north direction is the positive direction of the y-axis.
    The south direction is the negative direction of the y-axis.
    The east direction is the positive direction of the x-axis.
    The west direction is the negative direction of the x-axis.
    The robot can receive one of three instructions:

    "G": go straight 1 unit.
    "L": turn 90 degrees to the left (i.e., anti-clockwise direction).
    "R": turn 90 degrees to the right (i.e., clockwise direction).
    The robot performs the instructions given in order, and repeats them forever.

    Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.

    Example 1:
    -----------
    Input: instructions = "GGLLGG"
    Output: true
        Explanation: The robot is initially at (0, 0) facing the north direction.
        "G": move one step. Position: (0, 1). Direction: North.
        "G": move one step. Position: (0, 2). Direction: North.
        "L": turn 90 degrees anti-clockwise. Position: (0, 2). Direction: West.
        "L": turn 90 degrees anti-clockwise. Position: (0, 2). Direction: South.
        "G": move one step. Position: (0, 1). Direction: South.
        "G": move one step. Position: (0, 0). Direction: South.
        Repeating the instructions, the robot goes into the cycle: (0, 0) --> (0, 1) --> (0, 2) --> (0, 1) --> (0, 0).
        Based on that, we return true.

    Example 2:
    -----------
    Input: instructions = "GG"
    Output: false
        Explanation: The robot is initially at (0, 0) facing the north direction.
        "G": move one step. Position: (0, 1). Direction: North.
        "G": move one step. Position: (0, 2). Direction: North.
        Repeating the instructions, keeps advancing in the north direction and does not go into cycles.
        Based on that, we return false.

    Example 3:
    -----------
    Input: instructions = "GL"
    Output: true
        Explanation: The robot is initially at (0, 0) facing the north direction.
        "G": move one step. Position: (0, 1). Direction: North.
        "L": turn 90 degrees anti-clockwise. Position: (0, 1). Direction: West.
        "G": move one step. Position: (-1, 1). Direction: West.
        "L": turn 90 degrees anti-clockwise. Position: (-1, 1). Direction: South.
        "G": move one step. Position: (-1, 0). Direction: South.
        "L": turn 90 degrees anti-clockwise. Position: (-1, 0). Direction: East.
        "G": move one step. Position: (0, 0). Direction: East.
        "L": turn 90 degrees anti-clockwise. Position: (0, 0). Direction: North.
        Repeating the instructions, the robot goes into the cycle: (0, 0) --> (0, 1) --> (-1, 1) --> (-1, 0) --> (0, 0).
        Based on that, we return true.


    Constraints:
    -----------
        1 <= instructions.length <= 100
        instructions[i] is 'G', 'L' or, 'R'.
 */

public class RobotBoundedInCircle {

    /*
        if robot finishes with face not towards north,
        it will get back to the initial status in another one or three sequences.
     */
    public boolean isRobotBounded(String instructions) {
        int x = 0;
        int y = 0;
        int[][] d = {{0, 1}, {1, 0}, {0, -1}, { -1, 0}};

        int i = 0;

        for (int j = 0; j < instructions.length(); ++j) {
            if (instructions.charAt(j) == 'R') {
                i = (i + 1) % 4;    //  will turn right
            }
            else if (instructions.charAt(j) == 'L') {
                i = (i + 3) % 4;    //  will turn left
            }
            else {
                x += d[i][0];
                y += d[i][1];
            }
        }
        return x == 0 && y == 0 || i > 0;
    }

    /*
         So in question its given we are initially at 0, 0 at North directions.
         We need to keep track of the points as well as the directions in which the robot travels.
         Let's have x, y = 0 and directions = North

        Now our problem is to find whether the robot is moving outside the circle after following some instructions.
        --> The robot leaves the circle if it keeps moving in the North direction.

        -----------------------------
                    North
            West                East
                    South
         -----------------------------
        So lets loop through each and every character from the instruction string,
            1. We check whether its G, if G then we have to move one point from the current position.
            2. Next we check whether its L, then we have to move 90 degree left wards.   //  anti-clockwise rotation
            3. Next whether the character if R, then we have to move 90 degree right wards. // clockwise direction
            //

        Finally, we check whether the robot get back to the position,
            if yes, return TRUE as the robot do not go out of the circle.
        We check whether the direction is still North, then it will be sure that the robot will go out of the circle,
            so return FALSE.
     */

    public boolean isRobotBounded2(String instructions) {
        if (instructions.length() == 0) {
            return false;
        }
        
        int x = 0;
        int y = 0;
        String currDirection = "North"; // initial direction of robot
        
        /*
                    North
            West                East
                    South

        */
        
        for (char ch: instructions.toCharArray()) {
            if (ch == 'G') {
                if (currDirection.equals("North")) {
                    y += 1;
                }
                else if (currDirection.equals("South")) {
                    y -= 1;
                }
                else if(currDirection.equals("East")) {
                    x += 1;
                }
                else {
                    x -= 1;
                }
            }
            else if (ch == 'L') {
                if (currDirection.equals("North")) {
                    currDirection = "West";
                }
                else if (currDirection.equals("West")) {
                    currDirection = "South";
                }
                else if (currDirection.equals("South")) {
                    currDirection = "East";
                }
                else {
                    currDirection = "North";
                }
            }
            else if (ch == 'R') {
                if (currDirection.equals("North")) {
                    currDirection = "East";
                }
                else if (currDirection.equals("East")) {
                    currDirection = "South";
                }
                else if (currDirection.equals("South")) {
                    currDirection = "West";
                }
                else {
                    currDirection = "North";
                }
            }
        }

        if (x == 0 && y == 0) {
            return true;
        }

        if (currDirection.equals("North")) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        RobotBoundedInCircle robotBoundedInCircle = new RobotBoundedInCircle();
        System.out.println(robotBoundedInCircle.isRobotBounded("GGLLGG"));  //  true
        System.out.println(robotBoundedInCircle.isRobotBounded2("GGLLGG")); //  true
        System.out.println(robotBoundedInCircle.isRobotBounded3("GGLLGG")); //  true

        System.out.println(robotBoundedInCircle.isRobotBounded("GG"));  //  false
        System.out.println(robotBoundedInCircle.isRobotBounded2("GG")); //  false
        System.out.println(robotBoundedInCircle.isRobotBounded3("GG")); //  false

        System.out.println(robotBoundedInCircle.isRobotBounded("GL"));  //  true
        System.out.println(robotBoundedInCircle.isRobotBounded2("GL")); //  true
        System.out.println(robotBoundedInCircle.isRobotBounded3("GL")); //  true
    }

    enum Direction {
        NORTH,
        SOUTH,
        WEST,
        EAST
    }

    public boolean isRobotBounded3(String instructions) {
        if (instructions.length() == 0) {
            return false;
        }

        int x = 0, y = 0;
        Direction direction = Direction.NORTH;

        for (char instruction : instructions.toCharArray()) {
            if (instruction == 'G') {
                switch (direction) {
                    case NORTH:
                        y++;
                        break;
                    case SOUTH:
                        y--;
                        break;
                    case WEST:
                        x--;
                        break;
                    case EAST:
                        x++;
                        break;
                }
            } else if (instruction == 'L') {
                switch (direction) {
                    case NORTH:
                        direction = Direction.WEST;
                        break;
                    case SOUTH:
                        direction = Direction.EAST;
                        break;
                    case WEST:
                        direction = Direction.SOUTH;
                        break;
                    case EAST:
                        direction = Direction.NORTH;
                        break;
                }
            } else if (instruction == 'R') {
                switch (direction) {
                    case NORTH:
                        direction = Direction.EAST;
                        break;
                    case SOUTH:
                        direction = Direction.WEST;
                        break;
                    case WEST:
                        direction = Direction.NORTH;
                        break;
                    case EAST:
                        direction = Direction.SOUTH;
                        break;
                }
            }
        }

        if (x == 0 && y == 0) {
            return true;
        }

        return direction != Direction.NORTH;
    }
}
