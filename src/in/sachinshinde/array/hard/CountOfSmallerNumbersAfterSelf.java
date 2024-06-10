package in.sachinshinde.array.hard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CountOfSmallerNumbersAfterSelf {

    //  Video Solution : https://youtu.be/MXwA_cK2GkA
    public List <Integer> countSmaller(int[] nums) {
        List<Integer> smallerToRight = new LinkedList<>();
        List<Integer> sortedList = new ArrayList<>();
        for(int i = nums.length-1; i >= 0; i--) {
            int index = insertAndGetIndex(sortedList, nums[i]);
            smallerToRight.add(0, index);
        }
        return smallerToRight;
    }

    private int insertAndGetIndex(List < Integer > sortedList, int num) {
        if(sortedList.isEmpty() || num < sortedList.get(0)) {
            sortedList.add(0, num);
            return 0;
        }

        int low = 0;
        int high = sortedList.size() - 1;

        if(num > sortedList.get(high)) {
            sortedList.add(num);
            return high + 1;
        }

        while(high - low > 1) {
            int mid = (low + high) / 2;
            if(num <= sortedList.get(mid)) {
                high = mid;
            }
            else {
                low = mid;
            }
        }

        if(num <= sortedList.get(low)) {
            sortedList.add(low, num);
            return low;
        }
        else if(num > sortedList.get(high)) {
            sortedList.add(high + 1, num);
            return high + 1;
        }
        else {
            sortedList.add(high, num);
            return high;
        }
    }

    public static void main(String[] args) {
        CountOfSmallerNumbersAfterSelf num = new CountOfSmallerNumbersAfterSelf();
        System.out.println(num.countSmaller(new int[]{5,2,6,1}));       // [2,1,1,0]
    }
}