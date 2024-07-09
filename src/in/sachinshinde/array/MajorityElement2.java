package in.sachinshinde.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//  https://leetcode.com/problems/majority-element-ii

/*
        Given an integer array of size n, find all elements that appear more than n/3 times.

        Example 1:
        ---------
        Input: nums = [3,2,3]
        Output: [3]

        Example 2:
        ---------
        Input: nums = [1]
        Output: [1]

        Example 3:
        ---------
        Input: nums = [1,2]
        Output: [1,2]


        Constraints:
        -----------
            1 <= nums.length <= 5 * 10^4
            -10^9 <= nums[i] <= 10^9
 */

public class MajorityElement2 {

    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> hm = new HashMap<>();
        for(int num: nums) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }
        int threshold = nums.length / 3;

        List<Integer> res = new ArrayList<>();

        for(Map.Entry<Integer, Integer> ele: hm.entrySet()) {
            if(ele.getValue() > threshold) {
                res.add(ele.getKey());
            }
        }

        return res;
    }

    public static void main(String[] args) {
        MajorityElement2 majorityElement2 = new MajorityElement2();
        System.out.println(majorityElement2.majorityElement(new int[]{3,2,3})); //  [3]
        System.out.println(majorityElement2.majorityElement(new int[]{1}));     //  [1]
        System.out.println(majorityElement2.majorityElement(new int[]{1,2}));   //  [1,2]

    }
}
