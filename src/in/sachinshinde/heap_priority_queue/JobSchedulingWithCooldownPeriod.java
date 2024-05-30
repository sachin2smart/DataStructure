package in.sachinshinde.heap_priority_queue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//	https://www.careercup.com/question?id=5675602320293888
//	Asked during a phone interview with Amazon

/*
 	Given a list of input tasks to run, and the cooldown interval, 
 	output the minimum number of time slots required to run them.
	 Tasks: 1, 1, 2, 1, 2
	 Recovery interval (cooldown): 2
	 Output: 8 (order is 1 _ _ 1 2 _ 1 2 )

	=========
	Tasks are task numbers in that order coming in for execution. 
	Cooling time is time interval required to cool down the machine after executing a task. 
	So it's like if CPU executed task 1 then it needs 2 cooling time intervals before executing another task 1 
		but meanwhile, it can execute other tasks which are not same as 1 and so on. 
		So before executing any task, you have to check if you have executed same task number before and 
		if yes, then if its cooling time interval is done or not.
	The output is basically the number of cycles/time slots CPU took to execute these tasks in that order
	 	(including when task executed and cooling intervals).
 */

public class JobSchedulingWithCooldownPeriod {
	public static int timeSlots(List<Integer> tasks, int cooldown) {
	    if (cooldown <= 0) 
	    	return tasks.size();

	    Map<Integer, Integer> lastTimeSlot = new HashMap<>();
	    int result = 0;
	    int taskIndex = 0;

	    while(taskIndex < tasks.size()) {
	        Integer task = tasks.get(taskIndex);
	        Integer last = lastTimeSlot.get(task);
	        if(last == null || result-last > cooldown) {
	            lastTimeSlot.put(task, result);
	            taskIndex++;
	        }
	        result++;
	    }
	    
	    return result;
	}
	
	//	Another flavor of the same problem with individual cooldown period
	public static int getMinTimeRequired(List<String> jobs, List<Integer> cooldown) {
	    
	    Map<String, Integer> lastTimeSlot = new HashMap<>();
	    int result = 0;
	    int taskIndex = 0;

	    while(taskIndex < jobs.size()) {
	        String task = jobs.get(taskIndex);
	        Integer last = lastTimeSlot.get(task);
	        Integer time = cooldown.get(taskIndex);
	        if(last == null || result-last > time) {
	            lastTimeSlot.put(task, result);
	            taskIndex++;
	        }
	        result++;
	    }
	    
	    return result;
	}
	
	public static void main(String[] args) {
		List<Integer> tasks = new ArrayList<Integer>();
		tasks.add(1);	tasks.add(1);	tasks.add(2);	tasks.add(1); 	tasks.add(2);
		System.out.println(JobSchedulingWithCooldownPeriod.timeSlots(tasks, 2));	//	8

		System.out.println(JobSchedulingWithCooldownPeriod.getMinTimeRequired(tasks, 2));	//	8
		
		// Another flavor of the same problem with individual cooldown period 
		List<String> tasksList = new ArrayList<String>();
		tasksList.add("J1");	tasksList.add("J1");	tasksList.add("J2");	tasksList.add("J1"); 	tasksList.add("J2");
		
		List<Integer> cooldownList = new ArrayList<Integer>();
		cooldownList.add(2);	cooldownList.add(2);	cooldownList.add(2);	cooldownList.add(2); 	cooldownList.add(2);
		
		System.out.println(JobSchedulingWithCooldownPeriod.getMinTimeRequired(tasksList, cooldownList));	//	8

	}

	public static int getMinTimeRequired(List<Integer> tasks, int cooldown) {
		int timeSlots = 0;
		Map<Integer, Integer> cooldowns = new HashMap<>(); // Map to store the last execution time of each task

		for (int task : tasks) {
			timeSlots++;

			// If the task has been executed recently and the cooldown period hasn't passed
			if (cooldowns.containsKey(task) && timeSlots - cooldowns.get(task) <= cooldown) {
				timeSlots = cooldowns.get(task) + cooldown + 1;
			}

			cooldowns.put(task, timeSlots); // Update the last execution time of the current task
		}

		return timeSlots;
	}
}
