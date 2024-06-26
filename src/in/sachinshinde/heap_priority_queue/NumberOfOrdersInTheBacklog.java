package in.sachinshinde.heap_priority_queue;

import java.util.PriorityQueue;

//  https://leetcode.com/problems/number-of-orders-in-the-backlog

/*
        You are given a 2D integer array orders, where each
            orders[i] = [pricei, amounti, orderTypei] denotes that
                amounti orders have been placed of type orderTypei at the price pricei.

                The orderTypei is:
                -   0 if it is a batch of buy orders, or
                -   1 if it is a batch of sell orders.

        Note that orders[i] represents a batch of amounti independent orders with the same price and order type.
        All orders represented by orders[i] will be placed before all orders represented by orders[i+1] for all valid i.

        There is a backlog that consists of orders that have not been executed.
        The backlog is initially empty.

        When an order is placed, the following happens:
            -   If the order is a buy order, you look at the sell order with the smallest price in the backlog.
            -   If that sell order's price is smaller than or equal to the current buy order's price,
                    they will match and be executed, and that sell order will be removed from the backlog.
                Else, the buy order is added to the backlog.
            -   Vice versa, if the order is a sell order, you look at the buy order with the largest price in the backlog.
            -   If that buy order's price is larger than or equal to the current sell order's price,
                    they will match and be executed, and that buy order will be removed from the backlog.
                Else, the sell order is added to the backlog.

        Return the total amount of orders in the backlog after placing all the orders from the input.
        Since this number can be large, return it modulo 109 + 7.

        Example 1:
        ---------
        Input: orders = [[10,5,0],[15,2,1],[25,1,1],[30,4,0]]
        Output: 6
        Explanation: Here is what happens with the orders:
        - 5 orders of type buy with price 10 are placed. There are no sell orders, so the 5 orders are added to the backlog.
        - 2 orders of type sell with price 15 are placed. There are no buy orders with prices larger than or equal to 15, so the 2 orders are added to the backlog.
        - 1 order of type sell with price 25 is placed. There are no buy orders with prices larger than or equal to 25 in the backlog, so this order is added to the backlog.
        - 4 orders of type buy with price 30 are placed. The first 2 orders are matched with the 2 sell orders of the least price, which is 15 and these 2 sell orders are removed from the backlog. The 3rd order is matched with the sell order of the least price, which is 25 and this sell order is removed from the backlog. Then, there are no more sell orders in the backlog, so the 4th order is added to the backlog.
        Finally, the backlog has 5 buy orders with price 10, and 1 buy order with price 30. So the total number of orders in the backlog is 6.

        Example 2:
        ---------
        Input: orders = [[7,1000000000,1],[15,3,0],[5,999999995,0],[5,1,1]]
        Output: 999999984
        Explanation: Here is what happens with the orders:
        - 109 orders of type sell with price 7 are placed. There are no buy orders, so the 109 orders are added to the backlog.
        - 3 orders of type buy with price 15 are placed. They are matched with the 3 sell orders with the least price which is 7, and those 3 sell orders are removed from the backlog.
        - 999999995 orders of type buy with price 5 are placed. The least price of a sell order is 7, so the 999999995 orders are added to the backlog.
        - 1 order of type sell with price 5 is placed. It is matched with the buy order of the highest price, which is 5, and that buy order is removed from the backlog.
        Finally, the backlog has (1000000000-3) sell orders with price 7, and (999999995-1) buy orders with price 5. So the total number of orders = 1999999991, which is equal to 999999984 % (109 + 7).

        Constraints:
        ---------
            1 <= orders.length <= 10^5
            orders[i].length == 3
            1 <= pricei, amounti <= 10^9
            orderTypei is either 0 or 1.
 */

public class NumberOfOrdersInTheBacklog {

    public int getNumberOfBacklogOrders(final int[][] orders) {
        PriorityQueue<int[]> buy  = new PriorityQueue<>((a, b) -> b[0] - a[0]);  //  maxHeap
        PriorityQueue<int[]> sell = new PriorityQueue<>((a, b) -> a[0] - b[0]); //  minHeap

        for(int[] order : orders) {
            if(order[2] == 0) {
                buy.offer(new int[]{order[0], order[1]});
            }
            else {
                sell.offer(new int[]{order[0], order[1]});
            }

            while(!buy.isEmpty() && !sell.isEmpty() && sell.peek()[0] <= buy.peek()[0]) {
                int[] sellOrder = sell.peek();
                int[] buyOrder  = buy.peek();

                int processed = Math.min(sellOrder[1], buyOrder[1]);

                sellOrder[1] -= processed;
                buyOrder[1] -= processed;

                if(sellOrder[1] == 0) {
                    sell.poll();
                }

                if(buyOrder[1] == 0) {
                    buy.poll();
                }
            }
        }

        int backlog = 0;

        while(!buy.isEmpty()) {
            backlog = (backlog + buy.poll()[1]) % 1000000007;
        }

        while(!sell.isEmpty()) {
            backlog = (backlog + sell.poll()[1]) % 1000000007;
        }

        return backlog;
    }

    public static void main(String[] args) {
        NumberOfOrdersInTheBacklog backlog = new NumberOfOrdersInTheBacklog();
        System.out.println(backlog.getNumberOfBacklogOrders(new int[][]{{10,5,0},{15,2,1},{25,1,1},{30,4,0}})); //  6
        System.out.println(backlog.getNumberOfBacklogOrders(new int[][]{{7,1000000000,1},{15,3,0},{5,999999995,0},{5,1,1}}));   //  999999984

        System.out.println(backlog.getNumberOfBacklogOrders(new int[][]{{10,5,0},{15,2,1},{25,1,1},{30,4,0}})); //  6
        System.out.println(backlog.getNumberOfBacklogOrders(new int[][]{{7,1000000000,1},{15,3,0},{5,999999995,0},{5,1,1}}));   //  999999984
    }

    public int getNumberOfBacklogOrders2(int[][] orders) {
        if(orders == null || orders.length == 0) {
            return 0;
        }

        PriorityQueue<int[]> minHeap=new PriorityQueue<>((a,b)->Integer.compare(a[0],b[0]));
        PriorityQueue<int[]> maxHeap=new PriorityQueue<>((a,b)->Integer.compare(b[0],a[0]));

        long backlog = 0;

        for(int[] order: orders){
            if(order[2] == 0) {
                while(minHeap.size() > 0 && minHeap.peek()[0] <= order[0] && order[1] > 0){
                    if(minHeap.peek()[1] >= order[1]){
                        minHeap.peek()[1] -= order[1];
                        backlog -= order[1];
                        order[1] = 0;

                    }
                    else {
                        order[1] -= minHeap.peek()[1];
                        backlog -= minHeap.poll()[1];
                    }

                }
                if(order[1] > 0) {
                    maxHeap.offer(new int[]{order[0], order[1]});
                    backlog += order[1];
                }
            }
            else {
                while(maxHeap.size() > 0 && maxHeap.peek()[0] >= order[0] && order[1] > 0){
                     if(maxHeap.peek()[1] >= order[1]){
                        maxHeap.peek()[1] -= order[1];
                        backlog -= order[1];
                        order[1] = 0;
                    }
                    else {
                        order[1] -= maxHeap.peek()[1];
                         backlog -= maxHeap.poll()[1];
                    }
                }
                if(order[1] > 0) {
                    minHeap.offer(new int[]{order[0],order[1]});
                    backlog += order[1];
                }
            }
        }

        int MOD = (int) 1e9 + 7;
        return (int)(backlog % MOD);
    }
}
