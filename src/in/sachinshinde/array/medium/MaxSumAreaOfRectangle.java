package in.sachinshinde.array.medium;

import java.util.*;

// https://leetcode.com/discuss/interview-question/4908388/OA-Question/

/*
        Given an array of n integers, you are required to create rectangles using pairs of integers from the array.
        Note that a particular integer can be used in at most one rectangle, and in order to create a rectangle,
            we must have exactly two pairs of integers with the same values.
        For example, you can create a rectangle using integers [2, 2, 5, 5] and [4, 4, 4, 4],
            but not with [3, 3, 5, 8].

        The goal is to maximize the total sum of areas of all the rectangles formed.
        To make the problem more interesting, you are allowed to reduce any integer by at most 1.
        Given the array of integers, find the maximum sum of areas of rectangles that can be formed such that
            each element of the array can be used as length or breadth of at most one rectangle, and
            you are allowed to decrease any integer by at most 1.
 */

public class MaxSumAreaOfRectangle {

    public int maxSumArea(int[] sideLengths){
        int result = 0;

        Map<Integer, Integer> freq = new TreeMap<>(Collections.reverseOrder());
        Set<Integer> uniqueSide = new HashSet<>();
        for(int side : sideLengths){
            uniqueSide.add(side);
            freq.put(side, freq.getOrDefault(side, 0)+1);
        }
        Map<Integer, Integer> pairMap = new TreeMap<>(Collections.reverseOrder());
        for(Map.Entry<Integer, Integer> entry : freq.entrySet()){
            int sideNum = entry.getKey();
            int sideFreq = entry.getValue();

            if(sideFreq % 2 == 0){
                pairMap.put(sideNum, sideFreq / 2);
            }
            else {
                int qu = sideFreq / 2;
                if(qu > 0) {
                    pairMap.put(sideNum, sideFreq / 2);
                }
                if(uniqueSide.contains(sideNum - 1)){
                    freq.put(sideNum - 1, freq.get(sideNum - 1) + 1);
                }
            }
        }

        List<Integer> rem = new ArrayList<>();

        for(Map.Entry<Integer, Integer> entry : pairMap.entrySet()){
            if(entry.getValue() % 2 != 0){
                rem.add(entry.getKey());
                if(entry.getValue() / 2 > 0){
                    result += Math.pow(entry.getKey(), entry.getValue()-1);
                }
            }
            else {
                result += Math.pow(entry.getKey(), entry.getValue());
            }
        }

        for(int i = 0; i < rem.size(); i++){
            result += rem.get(i) * rem.get(i+1);
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        MaxSumAreaOfRectangle rectangle = new MaxSumAreaOfRectangle();

        List<Integer> list = new ArrayList<>();
        list.add(1);list.add(1);list.add(2);list.add(2);
        list.add(3);list.add(3);list.add(4);list.add(5);

        System.out.println(rectangle.maxSumArea(new int[]{1,1,2,2,3,3,4,5}));
        System.out.println(rectangle.maxSumArea(list));
        //  1*2+3*5 = 14

        list = new ArrayList<>();
        list.add(5);list.add(4);list.add(4);list.add(5);
        list.add(4);list.add(5);list.add(5);list.add(4);
        // 4*4+5*5 = 41
        System.out.println(rectangle.maxSumArea(list));
    }

    public int maxSumArea(List<Integer> sideLengths){
        int result = 0;

        Map<Integer, Integer> freqMap = new TreeMap<>(Collections.reverseOrder());
        Map<Integer, Integer> pairMap = new TreeMap<>(Collections.reverseOrder());

        Set<Integer> uniqueSideSet = new HashSet<>();

        for(int side : sideLengths){
            uniqueSideSet.add(side);
            freqMap.put(side, freqMap.getOrDefault(side, 0)+1);
        }

        for(Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int sideNum = entry.getKey();
            int sideFreq = entry.getValue();

            if(sideFreq % 2 == 0){
                pairMap.put(sideNum, sideFreq / 2);
            }
            else {
                if(sideFreq / 2 > 0) {
                    pairMap.put(sideNum, sideFreq / 2);
                }
                // back-fill match value
                if(uniqueSideSet.contains(sideNum - 1)) {
                    freqMap.put(sideNum - 1, freqMap.get(sideNum - 1) + 1);
                }
            }
        }

        List<Integer> rectangleValues = new ArrayList<>();

        for(Map.Entry<Integer, Integer> entry : pairMap.entrySet()){
            if(entry.getValue() % 2 != 0) {
                rectangleValues.add(entry.getKey());
                if(entry.getValue() / 2 > 0) {
                    result += Math.pow(entry.getKey(), entry.getValue()-1);
                }
            }
            else {
                result += Math.pow(entry.getKey(), entry.getValue());
            }
        }

        for(int i = 0; i < rectangleValues.size(); i+=2) {
            result += rectangleValues.get(i) * rectangleValues.get(i + 1);
        }

        return result;
    }
}