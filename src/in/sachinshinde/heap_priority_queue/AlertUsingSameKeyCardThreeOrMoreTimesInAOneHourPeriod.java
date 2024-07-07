package in.sachinshinde.heap_priority_queue;

import java.util.*;

//  https://leetcode.com/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period

/*
        LeetCode company workers use key-cards to unlock office doors.
        Each time a worker uses their key-card, the security system saves the worker's name and the time when it was used.
        The system emits an alert if any worker uses the key-card three or more times in a one-hour period.

        You are given a list of strings keyName and keyTime where [keyName[i], keyTime[i]] corresponds to
            a person's name and the time when their key-card was used in a single day.

        Access times are given in the 24-hour time format "HH:MM", such as "23:51" and "09:49".

        Return a list of unique worker names who received an alert for frequent keycard use.
        Sort the names in ascending order alphabetically.

        Notice that "10:00" - "11:00" is considered to be within a one-hour period,
            while "22:51" - "23:52" is not considered to be within a one-hour period.

        Example 1:
        ---------
        Input: keyName = ["daniel","daniel","daniel","luis","luis","luis","luis"], keyTime = ["10:00","10:40","11:00","09:00","11:00","13:00","15:00"]
        Output: ["daniel"]
        Explanation: "daniel" used the keycard 3 times in a one-hour period ("10:00","10:40", "11:00").

        Example 2:
        ---------
        Input: keyName = ["alice","alice","alice","bob","bob","bob","bob"], keyTime = ["12:01","12:00","18:00","21:00","21:20","21:30","23:00"]
        Output: ["bob"]
        Explanation: "bob" used the keycard 3 times in a one-hour period ("21:00","21:20", "21:30").

        Constraints:
        -----------
            1 <= keyName.length, keyTime.length <= 10^5
            keyName.length == keyTime.length
            keyTime[i] is in the format "HH:MM".
            [keyName[i], keyTime[i]] is unique.
            1 <= keyName[i].length <= 10
            keyName[i] contains only lowercase English letters.
 */

public class AlertUsingSameKeyCardThreeOrMoreTimesInAOneHourPeriod {

    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, TreeSet<Integer>> nameToTimesMap = new HashMap<>();
        List<String> alertedWorkersList = new ArrayList<String>();

        for(int i = 0; i < keyTime.length; i++) {
            String[] parts = keyTime[i].split(":");
            Integer period = Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);

            TreeSet<Integer> timeSet = nameToTimesMap.getOrDefault(keyName[i], new TreeSet<>());
            timeSet.add(period);

            nameToTimesMap.put(keyName[i], timeSet);
        }

        for(Map.Entry<String, TreeSet<Integer>> entry: nameToTimesMap.entrySet()) {
            String name = entry.getKey();
            TreeSet<Integer> times = entry.getValue();
            Deque<Integer> dq = new LinkedList<>();

            for(Integer time : times) {
                dq.addLast(time);

                while(dq.size() >= 3 && dq.getLast() - dq.getFirst() > 60)
                    dq.removeFirst();

                if(dq.size() >= 3) {
                    alertedWorkersList.add(name);
                    break;
                }
            }
        }

        Collections.sort(alertedWorkersList);
        return alertedWorkersList;
    }

    public static void main(String[] args) {
        AlertUsingSameKeyCardThreeOrMoreTimesInAOneHourPeriod alert = new AlertUsingSameKeyCardThreeOrMoreTimesInAOneHourPeriod();
        System.out.println(alert.alertNames(new String[]{"daniel","daniel","daniel","luis","luis","luis","luis"},
                new String[] {"10:00","10:40","11:00","09:00","11:00","13:00","15:00"}));   //  ["daniel"]

        System.out.println(alert.alertNames(new String[]{"alice","alice","alice","bob","bob","bob","bob"},
                new String[]{"12:01","12:00","18:00","21:00","21:20","21:30","23:00"}));    //  ["bob"]

        System.out.println(alert.alertNames2(new String[]{"daniel","daniel","daniel","luis","luis","luis","luis"},
                new String[] {"10:00","10:40","11:00","09:00","11:00","13:00","15:00"}));   //  ["daniel"]

        System.out.println(alert.alertNames2(new String[]{"alice","alice","alice","bob","bob","bob","bob"},
                new String[]{"12:01","12:00","18:00","21:00","21:20","21:30","23:00"}));    //  ["bob"]
    }


    //  Using PriorityQueue
    public List<String> alertNames2(String[] keyName, String[] keyTime) {
        Map<String, PriorityQueue<Integer>> personToSortedKeyTimeHm = new HashMap<>();

        // for every entry in keyName and keyTime, add that time to a priorityqueue for that name
        for(int i = 0; i < keyName.length; i++){
            PriorityQueue<Integer> pq = personToSortedKeyTimeHm.getOrDefault(keyName[i], new PriorityQueue<>());
            //convert the time to an integer (0- 2359 inclusive) for easy comparisons
            pq.add(Integer.parseInt(keyTime[i].substring(0, 2)) * 100 + Integer.parseInt(keyTime[i].substring(3)));
            personToSortedKeyTimeHm.put(keyName[i], pq);
        }

        List<String> res = new ArrayList<>();
        for(String person: personToSortedKeyTimeHm.keySet()){
            // For each name in the map, determine if that name used the keycard within 1 hour
            PriorityQueue<Integer> pq = personToSortedKeyTimeHm.get(person);
            if(isPersonActive(pq)){
                res.add(person);
            }
        }

        Collections.sort(res);
        return res;
    }

    // Greedy function to determine if there were 3 uses within an hour
    private boolean isPersonActive(PriorityQueue<Integer> pq){
        // If there are two or fewer entries, the user could not have entered 3 times, return false
        if(pq.size() < 3) {
            return false;
        }

        // Create rolling data
        // Using PriorityQueues, the lowest number is removed first by default
        int a = pq.poll();
        int b = pq.poll();
        int c = pq.poll();

        // Test if two entrances are within 1 hour (100 in integer)
        if(c - a <=100) {
            return true;
        }

        while(pq.size() > 0){
            a = b;
            b = c;
            c = pq.poll();
            //  if next 2 entries are within 1 hour
            if(c - a <=100) {
                return true;
            }
        }

        // If the full Queue has been checked, return false
        return false;
    }
}
