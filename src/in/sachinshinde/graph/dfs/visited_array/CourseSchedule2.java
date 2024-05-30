package in.sachinshinde.graph.dfs.visited_array;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//  https://leetcode.com/problems/course-schedule-ii/

public class CourseSchedule2 {

    public int[] findOrder(int numCourse, int[][] prerequisites) {
        List<List<Integer>> coursesList = new ArrayList<>();

        for(int i=0; i<numCourse; i++) {
            coursesList.add(new ArrayList<>());
        }

        for(int[] prerequisite: prerequisites) {
            coursesList.get(prerequisite[1]).add(prerequisite[0]);
        }

        boolean[] isCourseTaken = new boolean[numCourse];
        Stack<Integer> coursesTaken = new Stack<>();

        for(int currCourse=0; currCourse < numCourse; currCourse++) {
            boolean[] isAlreadyTaken = new boolean[numCourse];
            if(isCyclic(currCourse, coursesList, coursesTaken, isAlreadyTaken, isCourseTaken)) {
                return new int[0];
            }
        }

        int[] res = new int[numCourse];
        for(int i=0; i<numCourse; i++) {
            res[i] = coursesTaken.pop();
        }
        return res;
    }

    private boolean isCyclic(int currCourse, List<List<Integer>> coursesList, Stack<Integer> coursesTaken, boolean[] isAlreadyTaken, boolean[] isCourseTaken) {
        if(isCourseTaken[currCourse]) {
            return false;
        }

        if(isAlreadyTaken[currCourse]) {
            return true;
        }

        isAlreadyTaken[currCourse] = true;

        for(int nextCourse: coursesList.get(currCourse)) {
            if(isCyclic(nextCourse, coursesList, coursesTaken, isAlreadyTaken, isCourseTaken)) {
                return true;
            }
        }

        coursesTaken.push(currCourse);
        isAlreadyTaken[currCourse] = false;
        isCourseTaken[currCourse] = true;

        return false;
    }


}
