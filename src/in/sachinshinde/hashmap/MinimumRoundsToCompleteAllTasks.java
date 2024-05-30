package in.sachinshinde.hashmap;

import java.util.HashMap;
import java.util.Map;

//	https://leetcode.com/problems/minimum-rounds-to-complete-all-tasks/

/*
 * 	You are given a 0-indexed integer array tasks, 
 * 		where tasks[i] represents the difficulty level of a task. 
 * 	
 * 	In each round, you can complete either 2 or 3 tasks of the same difficulty level.

        Return the minimum rounds required to complete all the tasks, 
        	or -1 if it is not possible to complete all the tasks.
        
        
        Example 1:
        ---------
        	Input: tasks = [2,2,3,3,2,4,4,4,4,4]
        	Output: 4
        	Explanation: To complete all the tasks, a possible plan is:
        		- In the first round, you complete 3 tasks of difficulty level 2. 
        		- In the second round, you complete 2 tasks of difficulty level 3. 
        		- In the third round, you complete 3 tasks of difficulty level 4. 
        		- In the fourth round, you complete 2 tasks of difficulty level 4.  
        	It can be shown that all the tasks cannot be completed in fewer than 4 rounds, 
        	so the answer is 4.
        
        Example 2:
        ---------
        Input: tasks = [2,3,3]
        Output: -1
        Explanation: There is only 1 task of difficulty level 2, 
        	but in each round, you can only complete either 2 or 3 tasks of the same difficulty level. 
        	Hence, you cannot complete all the tasks, and the answer is -1.
 
 */

public class MinimumRoundsToCompleteAllTasks {

    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> hm = new HashMap<>();
        
        for(int t: tasks)
            hm.put(t, hm.getOrDefault(t,0) + 1);
            
        int res = 0;
        
        for(Map.Entry<Integer, Integer> entry: hm.entrySet()) {
            int freq = entry.getValue();
            if(freq == 1)
                return -1;
            if(freq % 3 != 0) 
                res += freq / 3 + 1;
            else 
                res += freq / 3;
        }
        
        return res;
    }
    
    public int minimumRounds_2(int[] tasks) {
	Map<Integer, Integer> hm = new HashMap<>();
        
        for(int t: tasks)
            hm.put(t, hm.getOrDefault(t,0) + 1);
            
        int res = 0;
        
        for(Integer freq: hm.values()) {
            if(freq == 1)
                return -1;
            if(freq % 3 != 0) 
                res += freq / 3 + 1;
            else 
                res += freq / 3;
        }
        
        return res;
    }
    
    public int minimumRounds_3(int[] tasks) {
        Map<Integer, Integer> hm = new HashMap<>();
        
        for(int t: tasks)
            hm.put(t, hm.getOrDefault(t,0) + 1);
            
        int res = 0;
        
        for(Integer freq: hm.values()) {
            if(freq == 1)
                return -1;
            res += (freq + 2) / 3;
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        MinimumRoundsToCompleteAllTasks rounds = new MinimumRoundsToCompleteAllTasks();
        System.out.println(rounds.minimumRounds(new int[] {2,2,3,3,2,4,4,4,4,4}));	// 4
        System.out.println(rounds.minimumRounds(new int[] {2,3,3}));	// -1

        System.out.println(rounds.minimumRounds_2(new int[] {2,2,3,3,2,4,4,4,4,4}));	// 4
        System.out.println(rounds.minimumRounds_2(new int[] {2,3,3}));	// -1

        System.out.println(rounds.minimumRounds_3(new int[] {2,2,3,3,2,4,4,4,4,4}));	// 4
        System.out.println(rounds.minimumRounds_3(new int[] {2,3,3}));	// -1
    }
    
}