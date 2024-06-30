package in.sachinshinde.heap_priority_queue;

//  https://leetcode.com/problems/distant-barcodes/

/*
        In a warehouse, there is a row of barcodes, where the ith barcode is barcodes[i].

        Rearrange the barcodes so that no two adjacent barcodes are equal.
        You may return any answer, and it is guaranteed an answer exists.

        Example 1:
        ---------
        Input: barcodes = [1,1,1,2,2,2]
        Output: [2,1,2,1,2,1]

        Example 2:
        ---------
        Input: barcodes = [1,1,1,1,2,2,3,3]
        Output: [1,3,1,3,1,2,1,2]

        Constraints:
        ------------
            1 <= barcodes.length <= 10000
            1 <= barcodes[i] <= 10000
 */

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class DistantBarcodes {

    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer,Integer> hm = new HashMap<>();
        int[] res = new int[barcodes.length];

        for(int b:barcodes) {
            hm.put(b, hm.getOrDefault(b,0) + 1);
        }

        Queue<int[]>pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        for(int barcodeValue: hm.keySet()) {
            pq.add(new int[]{hm.get(barcodeValue), barcodeValue});
        }

        int i = 0;

        while(!pq.isEmpty()) {
            int[] curr = pq.remove();
            while((curr[0]--) > 0) {
                res[i] = curr[1];
                if(i + 2 >= res.length) {
                    i = 1;
                }
                else {
                    i += 2;
                }
            }
        }

        return res;
    }


    public int[] rearrangeBarcodes2(int[] barcodes) {
        Map<Integer, Integer> hm  = new HashMap<>();
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> hm.get(b) - hm.get(a));

        int[] res = new int[barcodes.length];

        for(int barcode: barcodes) {
            hm.put(barcode, hm.getOrDefault(barcode, 0) + 1);
        }

        for(int barcode: hm.keySet()) {
            pq.offer(barcode);
        }

        res[0] = pq.peek();
        hm.put(res[0], hm.get(res[0]) - 1);

        for(int i = 1; i < barcodes.length; i++) {
            if(res[i-1] == pq.peek()){
                int sameNextValue = pq.poll();
                res[i] = pq.peek();
                hm.put(res[i], hm.get(res[i]) - 1);
                pq.offer(sameNextValue);
            }
            else {
                res[i] = pq.peek();
                hm.put(res[i], hm.get(res[i]) - 1);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        DistantBarcodes barcodes = new DistantBarcodes();
        System.out.println(barcodes.rearrangeBarcodes(new int[]{1,1,1,2,2,2}));
        System.out.println(barcodes.rearrangeBarcodes(new int[]{1,1,1,1,2,2,3,3}));

        System.out.println(barcodes.rearrangeBarcodes2(new int[]{1,1,1,2,2,2}));
        System.out.println(barcodes.rearrangeBarcodes2(new int[]{1,1,1,1,2,2,3,3}));
    }

}
