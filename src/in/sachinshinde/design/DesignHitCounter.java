package in.sachinshinde.design;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

//  https://leetcode.com/problems/design-hit-counter/

/*
        Design a hit counter which counts the number of hits received in the past 5 minutes (i.e., the past 300 seconds).

        Your system should accept a timestamp parameter (in seconds granularity), and
            you may assume that calls are being made to the system in chronological order
            (i.e., timestamp is monotonically increasing).
        Several hits may arrive roughly at the same time.

        Implement the HitCounter class:

            HitCounter() Initializes the object of the hit counter system.
            -   void hit(int timestamp) Records a hit that happened at timestamp (in seconds).
                    Several hits may happen at the same timestamp.
            -   int getHits(int timestamp) Returns the number of hits in the past 5 minutes from timestamp
                    (i.e., the past 300 seconds).



        Example 1:
        ---------
        Input
        ["HitCounter", "hit", "hit", "hit", "getHits", "hit", "getHits", "getHits"]
        [[], [1], [2], [3], [4], [300], [300], [301]]
        Output
        [null, null, null, null, 3, null, 4, 3]

        Explanation
        HitCounter hitCounter = new HitCounter();
        hitCounter.hit(1);       // hit at timestamp 1.
        hitCounter.hit(2);       // hit at timestamp 2.
        hitCounter.hit(3);       // hit at timestamp 3.
        hitCounter.getHits(4);   // get hits at timestamp 4, return 3.
        hitCounter.hit(300);     // hit at timestamp 300.
        hitCounter.getHits(300); // get hits at timestamp 300, return 4.
        hitCounter.getHits(301); // get hits at timestamp 301, return 3.


        Constraints:
        -----------
            1 <= timestamp <= 2 * 109
            All the calls are being made to the system in chronological order (i.e., timestamp is monotonically increasing).
            At most 300 calls will be made to hit and getHits.

        Follow up: What if the number of hits per second could be huge? Does your design scale?
 */


public class DesignHitCounter {
    Queue<Integer> q;

    public DesignHitCounter() {
        q = new LinkedList<>();
    }

    public void hit(int timestamp) {
        q.offer(timestamp);
    }

    public int getHits(int timestamp) {
        while(!q.isEmpty() && timestamp - q.peek() >= 300) {
            q.poll();
        }
        return q.size();
    }

    public static void main(String[] args) {
        DesignHitCounter hitCounter = new DesignHitCounter();
        hitCounter.hit(1);
        hitCounter.hit(2);
        hitCounter.hit(3);
        System.out.println(hitCounter.getHits(4));      //  3
        hitCounter.hit(300);
        System.out.println(hitCounter.getHits(300));    //  4
        System.out.println(hitCounter.getHits(301));    //  3

        System.out.println();
        //-----------------------------------------------------------
        DesignHitCounter hitCounter2 = new DesignHitCounter(2);
        hitCounter2.hit_Sol2(1);
        hitCounter2.hit_Sol2(2);
        hitCounter2.hit_Sol2(3);
        System.out.println(hitCounter2.getHits_Sol2(4));
        hitCounter2.hit_Sol2(300);
        System.out.println(hitCounter2.getHits_Sol2(300));
        System.out.println(hitCounter2.getHits_Sol2(301));
    }

    ArrayDeque<Integer> dq;

    public DesignHitCounter(int solution1) {
        dq = new ArrayDeque<>();
    }

    public void hit_Sol2(int timestamp) {
        dq.add(timestamp);
        int start = timestamp - 300 + 1;
        while(dq.peek() != null && dq.peek() < start) {
            dq.remove();
        }
    }

    public int getHits_Sol2(int timestamp) {
        int start = timestamp - 300 + 1;
        while(dq.peek() < start) {
            dq.remove();
        }
        return dq.size();
    }
}