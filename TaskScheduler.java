package com.exmaple.problems;

import java.util.*;
//Time Complexity : O(N) for Map
//Space Complexity : O(1)
//Did this code successfully run on Leetcode : YES
//Any problem you faced while coding this : NO

//Your code here along with comments explaining your approach
/**
 * The problems asks to find minimum of slots to finish tasks in a CPU with
 * constraint that same tasks must be started after N time interavals.These N
 * time intervals can be idle or occupied by any other task We count the
 * frequency of All tasks and store in HashMap.Also we keep of tasks with
 * highest frequency task.We perform following steps to find slots;
 * 
 * 1. Count highest frequency of task.Let this be highest_frequency
 * 2. Place highest frequent task into slots with N gaps 
 * 3. Now count the remaining available slots for placing other tasks. Use following formula
 * 		remaining_slots = N * (highest_frequency - 1) 
 * 4. Aso count pending task available for execution 
 * 		total_pending_tasks = total_tasks - highest_frequency; 
 * 5. Now use following formulate to count extra slots
 * 		extra_slots = max(0,remaining_slots - total_pending_tasks) 
 * 6. Now use following formula to calculate minimum slots
 *  	min_slots = total_tasks +extra_slots
 * 
 * The above also may not work with multiple highest frequent task.So keep count
 * of most frequent tasks and modified the above algo as follows
 * 
 * total_slots_available = highest_frequency - 1; 
 * remaining_slots = (n - (count_highest_frequency_tasks -1 ) ) * total_slots_available;
 * total_pending_tasks = total_tasks - (highest_frequency_task * number_of_highest_frequency_task);
 *   extra_slots = max(0,remaining_slots - total_pending_tasks) 
 *   min_slots = total_tasks + extra_slots
 * 
 * We have placed the highest frequency tasks into consecutive slots
 * 
 *
 */
public class TaskScheduler {
	public int leastInterval(char[] tasks, int n) {
		HashMap<Character, Integer> freqMap = new HashMap<>();

		int total_tasks = tasks.length;
		int highest_frequency = 0, number_of_highest_frequency_task = 0;
		for (char task : tasks) {
			if (!freqMap.containsKey(task)) {
				freqMap.put(task, 0);
			}
			int newCount = freqMap.get(task) + 1;
			highest_frequency = Math.max(highest_frequency, newCount);
			freqMap.put(task, newCount);
		}
		for (int freq : freqMap.values()) {
			if (freq == highest_frequency) {
				number_of_highest_frequency_task++;
			}
		}
		int total_slots_available = highest_frequency - 1;
		int remaining_slots = (n - (number_of_highest_frequency_task - 1)) * total_slots_available;
		int total_pending_tasks = total_tasks - (number_of_highest_frequency_task * highest_frequency);
		int extra_slots = Math.max(0, remaining_slots - total_pending_tasks);
		int min_slots = total_tasks + extra_slots;
		return min_slots;
	}

	public static void main(String args[]) {
		char tasks[] = { 'A', 'A', 'A', 'B', 'B', 'B' };
		int n = 2;
		System.out.println(new TaskScheduler().leastInterval(tasks, n));
	}
}
