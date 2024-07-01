package in.sachinshinde.heap_priority_queue;

//  https://leetcode.com/problems/car-pooling/

/*
        There is a car with capacity empty seats.
        The vehicle only drives east (i.e., it cannot turn around and drive west).

        You are given the integer capacity and an array trips
            where trips[i] = [numPassengersi, fromi, toi] indicates that
                the ith trip has numPassengersi passengers and
                the locations to pick them up and drop them off are fromi and toi respectively.
            The locations are given as the number of kilometers due east from the car's initial location.

        Return true if it is possible to pick up and drop off all passengers for all the given trips, or
            false otherwise.



        Example 1:
        ----------
        Input: trips = [[2,1,5],[3,3,7]], capacity = 4
        Output: false

        Example 2:
        ----------
        Input: trips = [[2,1,5],[3,3,7]], capacity = 5
        Output: true


        Constraints:
        ------------
            1 <= trips.length <= 1000
            trips[i].length == 3
            1 <= numPassengersi <= 100
            0 <= fromi < toi <= 1000
            1 <= capacity <= 105
 */

import java.util.*;

public class CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] stops = new int[1001];
        for (int[] t: trips) {
            stops[t[1]] += t[0];
            stops[t[2]] -= t[0];
        }
        for (int i = 0; capacity >= 0 && i < 1001; i++) {
            capacity -= stops[i];
        }
        return capacity >= 0;
    }

    public static void main(String[] args) {
        CarPooling carPooling = new CarPooling();
        System.out.println(carPooling.carPooling(new int[][]{{2,1,5}, {3,3,7}}, 4));    // false
        System.out.println(carPooling.carPooling(new int[][]{{2,1,5}, {3,3,7}}, 5));    // true

        System.out.println(carPooling.carPooling2(new int[][]{{2,1,5}, {3,3,7}}, 4));    // false
        System.out.println(carPooling.carPooling2(new int[][]{{2,1,5}, {3,3,7}}, 5));    // true

        System.out.println(carPooling.carPooling3(new int[][]{{2,1,5}, {3,3,7}}, 4));    // false
        System.out.println(carPooling.carPooling3(new int[][]{{2,1,5}, {3,3,7}}, 5));    // true
    }

    public boolean carPooling2(int[][] trips, int capacity) {
        // 1. sort trips by startLocation
        Arrays.sort(trips, Comparator.comparing((int[] arr) -> arr[1]));    //  Arrays.parallelSort() can be used here to optimize the performance

        //  2. minHeap by endLocation
        PriorityQueue<int[]> pq = new PriorityQueue<>((t1, t2) -> t1[2] - t2[2]);

        for(int[] trip : trips) {
            //  3. add passenger -
            //      if the last trip's endLocation is less than or equal to current trips startLocation
            while(!pq.isEmpty() && pq.peek()[2] <= trip[1]) {
                capacity += pq.peek()[0];   //  4. update capacity with numOfPassengers
                pq.poll();  //  5. remove dead trips
            }

            pq.offer(trip); //  6. keep current trip in the queue
            capacity -= trip[0];    // 7. update capacity

            if(capacity < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean carPooling3(int[][] trips, int capacity) {
        // 1. sort trips by start_location
        Arrays.sort(trips, new Comparator<int[]>(){
            public int compare(int[] i1, int[] i2) {
                return i1[1] - i2[1];
            }
        });

        Queue<int[]> theQueue = new PriorityQueue<int[]>(new Comparator<int[]>(){
            public int compare(int[] t1, int[] t2) {
                return t1[2] - t2[2];
            }
        });

        for (int i = 0; i < trips.length; i++) {
            while (!theQueue.isEmpty() && theQueue.peek()[2] <= trips[i][1]) { // 2. remove dead trips
                capacity += theQueue.peek()[0];
                theQueue.poll();
            }
            // 3. update capacity
            capacity -= trips[i][0];
            theQueue.offer(trips[i]);
            if( capacity < 0 )
                return false;
        }
        return true;
    }
}
