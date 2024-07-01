package in.sachinshinde.heap_priority_queue;

import java.util.*;
import java.util.stream.Collectors;

//  https://www.geeksforgeeks.org/job-sequencing-problem/

/*
        Statement:
        ---------
            Given an array of jobs where every job has a deadline and
                associated profit if the job is finished before the deadline.
            It is also given that every job takes a single unit of time,
                so the minimum possible deadline for any job is 1.
            Maximize the total profit if only one job can be scheduled at a time.
 */

/*
    Example 1:
    ---------
    Input: Four Jobs with following deadlines and profits

    JobID       Deadline    Profit

      a           4          20
      b           1          10
      c           1          40
      d           1          30

    Output: Following is maximum profit sequence of jobs: c, a

    Example 2:
    ---------
    Input:  Five Jobs with following deadlines and profits

    JobID       Deadline    Profit

      a            2          100
      b            1          19
      c            2          27
      d            1          25
      e            3          15

    Output: Following is maximum profit sequence of jobs: c, a, e

 */

public class JobSequencingProblem {

    public List<Job> getJobScheduling(List<Job> jobs) {
        int n = jobs.size();

        // sorting the array on the basis of their deadlines
        Collections.sort(jobs, Comparator.comparingInt(a -> a.deadline));

        List<Job> res = new ArrayList<>();
        PriorityQueue<Job> pq = new PriorityQueue<>((a, b) ->  b.profit - a.profit);   //  maxHeap based on profit

        // starting the iteration from the end
        for (int i = n - 1; i >= 0; i--) {
            int numSlotsAvailable;

            // calculate slots between two deadlines
            if (i == 0) {
                numSlotsAvailable = jobs.get(i).deadline;
            }
            else {
                numSlotsAvailable = jobs.get(i).deadline - jobs.get(i - 1).deadline;
            }

            // include the profit of job(as priority), deadline and job_id in maxHeap
            pq.offer(jobs.get(i));

            while (numSlotsAvailable > 0 && pq.size() > 0) {
                res.add(pq.poll());     // include the job in the result array
                numSlotsAvailable--;
            }
        }

        // jobs included might be shuffled sort the result array by their deadlines
        Collections.sort(res, Comparator.comparingInt(job -> job.deadline));
        return res;
    }


    public List<Job> getJobScheduling2(List<Job> jobs) {
        int n = jobs.size();

        // sorting the array on the basis of their deadlines
        jobs.sort(Comparator.comparingInt(job -> job.deadline));

        List<Job> res = new ArrayList<>();
        PriorityQueue<Job> pq = new PriorityQueue<>((j1, j2) ->  j2.profit - j1.profit);   //  maxHeap based on profit

        // starting the iteration from the end
        for (int i = n - 1; i >= 0; i--) {
            int numSlotsAvailable;

            // calculate slots between two deadlines
            if (i == 0) {
                numSlotsAvailable = jobs.get(i).deadline;
            }
            else {
                numSlotsAvailable = jobs.get(i).deadline - jobs.get(i - 1).deadline;
            }

            // include the profit of job(as priority), deadline and job_id in maxHeap
            pq.add(jobs.get(i));

            while (numSlotsAvailable > 0 && pq.size() > 0) {
                res.add(pq.poll());     // include the maxProfit job in the result array
                numSlotsAvailable--;
            }
        }

        // jobs included might be shuffled sort the result array by their deadlines
        res.sort(Comparator.comparingInt(job -> job.deadline));
        return res;
    }

    public static void main(String[] args) {
        JobSequencingProblem jobSequencing = new JobSequencingProblem();
        System.out.println(jobSequencing.getJobScheduling(new ArrayList<>(List.of(
                new Job('a', 4, 20),
                new Job('b', 1, 10),
                new Job('c', 1, 40),
                new Job('d', 1, 30)))).stream().map(j -> j.jobId).collect(Collectors.toList()));
        //  [c, a]

        System.out.println(jobSequencing.getJobScheduling(new ArrayList<>(List.of(
                new Job('a', 2, 100),
                new Job('b', 1, 19),
                new Job('c', 2, 27),
                new Job('d', 1, 25),
                new Job('e', 3, 15)))).stream().map(j -> j.jobId).collect(Collectors.toList()));
        //  [a, c, e]

        System.out.println(jobSequencing.getJobScheduling2(new ArrayList<>(List.of(
                new Job('a', 4, 20),
                new Job('b', 1, 10),
                new Job('c', 1, 40),
                new Job('d', 1, 30)))).stream().map(j -> j.jobId).collect(Collectors.toList()));
        //  [c, a]

        System.out.println(jobSequencing.getJobScheduling2(new ArrayList<>(List.of(
                new Job('a', 2, 100),
                new Job('b', 1, 19),
                new Job('c', 2, 27),
                new Job('d', 1, 25),
                new Job('e', 3, 15)))).stream().map(j -> j.jobId).collect(Collectors.toList()));
        //  [a, c, e]
    }
}

class Job {
    char jobId;
    int deadline;
    int profit;

    public Job(char jobId, int deadline, int profit) {
        this.deadline = deadline;
        this.jobId = jobId;
        this.profit = profit;
    }
}