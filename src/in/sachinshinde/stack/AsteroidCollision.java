package in.sachinshinde.stack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

//	https://leetcode.com/problems/asteroid-collision/

/*
 	We are given an array asteroids of integers representing asteroids in a row.

        For each asteroid, the absolute value represents its size, and 
        	the sign represents its direction (positive meaning right, negative meaning left). 
        Each asteroid moves at the same speed.
        
        Find out the state of the asteroids after all collisions. 
        ** If two asteroids meet, the smaller one will explode. 
        ** If both are the same size, both will explode. 
        ** Two asteroids moving in the same direction will never meet.
        
        Example 1:
        ---------
        Input: asteroids = [5,10,-5]
        Output: [5,10]
        Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
        
        Example 2:
        ---------
        Input: asteroids = [8,-8]
        Output: []
        Explanation: The 8 and -8 collide exploding each other.
        
        Example 3:
        ---------
        Input: asteroids = [10,2,-5]
        Output: [10]
        Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
         
        
        Constraints:
        -----------
            2 <= asteroids.length <= 104
            -1000 <= asteroids[i] <= 1000
            asteroids[i] != 0
 */

public class AsteroidCollision {
    
    //	Method 1: Using Stack
    
    public int[] asteroidCollision(int[] asteroids) {
	Stack<Integer> stack = new Stack<Integer>();
	
	for(int asteroid: asteroids) {
	    if(asteroid > 0) {
		stack.push(asteroid);
	    }
	    else {
		while(!stack.isEmpty() && stack.peek() > 0 && stack.peek() < -asteroid) {
		    stack.pop();
		}
		if(stack.isEmpty() || stack.peek() < 0) {
		    stack.push(asteroid);
		}
		if(stack.peek() == -asteroid) {
		    stack.pop();
		}
	    }
	}
	
	int[] result = new int[stack.size()];
	int i = stack.size() - 1;
	while(!stack.isEmpty()) {
	    result[i--] = stack.pop();
	}
	
	return result;
    }
    
    //	Method 2 : Using LinkedList
    
    public int[] asteroidCollision_ll(int[] asteroids) {
	List<Integer> linkedList = new LinkedList<Integer>();
	
	for(int asteroid: asteroids) {
	    if(asteroid > 0) {
		linkedList.addLast(asteroid);
	    }
	    else {
		while(!linkedList.isEmpty() && linkedList.getLast() > 0 && linkedList.getLast() < -asteroid) {
		    linkedList.removeLast();
		}
		if(linkedList.isEmpty() || linkedList.getLast() < 0) {
		    linkedList.addLast(asteroid);
		}
		if(linkedList.getLast() == -asteroid) {
		    linkedList.removeLast();
		}
	    }
	}
	
	return linkedList.stream().mapToInt(i->i).toArray();
    }
    
    //	Method 3: Using In-Place replacement
    
    public int[] asteroidCollision_inplace(int[] asteroids) {
	int top = -1;

	for(int currAsteroid : asteroids) {
	    boolean isAlive = true;

	    while(isAlive && currAsteroid < 0 && top >= 0 && asteroids[top] > 0) {
		isAlive = (currAsteroid + asteroids[top]) < 0 ? true : false;
	        if(currAsteroid + asteroids[top] <= 0) {
	            top--; 
	        }
	    }

	    if(isAlive) {
		asteroids[++top] = currAsteroid;
	    }
	}

	return Arrays.copyOf(asteroids , top + 1);
    }
    
    public static void main(String[] args) {
	AsteroidCollision asteroidCollision = new AsteroidCollision();
	System.out.println(Arrays.toString(asteroidCollision.asteroidCollision(new int[] {-5,-10,15})));	// [-5,-10,15]
	System.out.println(Arrays.toString(asteroidCollision.asteroidCollision(new int[] {-5,-10,5})));		// [-5,-10,5]
	System.out.println(Arrays.toString(asteroidCollision.asteroidCollision(new int[] {-5,-10,-5})));	// [-5,-10,-5]
	System.out.println(Arrays.toString(asteroidCollision.asteroidCollision(new int[] {-5,10,-5})));		// [-5,10]
	System.out.println(Arrays.toString(asteroidCollision.asteroidCollision(new int[] {-5,10,-15})));	// [-5, -15]
	System.out.println(Arrays.toString(asteroidCollision.asteroidCollision(new int[] {5,10,-15})));	 	// [-15]
	System.out.println(Arrays.toString(asteroidCollision.asteroidCollision(new int[] {15,5,-10,-15})));	// []
	
	System.out.println(Arrays.toString(asteroidCollision.asteroidCollision_ll(new int[] {-5,-10,15})));	// [-5,-10,15]
	System.out.println(Arrays.toString(asteroidCollision.asteroidCollision_ll(new int[] {-5,-10,5})));	// [-5,-10,5]
	System.out.println(Arrays.toString(asteroidCollision.asteroidCollision_ll(new int[] {-5,-10,-5})));	// [-5,-10,-5]
	System.out.println(Arrays.toString(asteroidCollision.asteroidCollision_ll(new int[] {-5,10,-5})));	// [-5,10]
	System.out.println(Arrays.toString(asteroidCollision.asteroidCollision_ll(new int[] {-5,10,-15})));	// [-5, -15]
	System.out.println(Arrays.toString(asteroidCollision.asteroidCollision_ll(new int[] {5,10,-15})));	// [-15]
	System.out.println(Arrays.toString(asteroidCollision.asteroidCollision_ll(new int[] {15,5,-10,-15})));	// []
	
	System.out.println(Arrays.toString(asteroidCollision.asteroidCollision_inplace(new int[] {-5,-10,15})));// [-5,-10,15]
	System.out.println(Arrays.toString(asteroidCollision.asteroidCollision_inplace(new int[] {-5,-10,5})));	// [-5,-10,5]
	System.out.println(Arrays.toString(asteroidCollision.asteroidCollision_inplace(new int[] {-5,-10,-5})));// [-5,-10,-5]
	System.out.println(Arrays.toString(asteroidCollision.asteroidCollision_inplace(new int[] {-5,10,-5})));	// [-5,10]
	System.out.println(Arrays.toString(asteroidCollision.asteroidCollision_inplace(new int[] {-5,10,-15})));// [-5, -15]
	System.out.println(Arrays.toString(asteroidCollision.asteroidCollision_inplace(new int[] {5,10,-15})));	// [-15]
	System.out.println(Arrays.toString(asteroidCollision.asteroidCollision_inplace(new int[] {15,5,-10,-15})));// []
    }
}