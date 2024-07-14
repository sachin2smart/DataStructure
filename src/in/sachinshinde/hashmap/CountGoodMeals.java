package in.sachinshinde.hashmap;

import java.util.HashMap;
import java.util.Map;

//  https://leetcode.com/problems/count-good-meals/

/*
    A good meal is a meal that contains exactly two different food items with
        a sum of deliciousness equal to a power of two.

    You can pick any two different foods to make a good meal.

    Given an array of integers deliciousness where deliciousness[i] is the deliciousness of the i'th item of food,
        return the number of different good meals you can make from this list modulo 10^9 + 7.

    Note that items with different indices are considered different even if they have the same deliciousness value.



    Example 1:
    ---------
    Input: deliciousness = [1,3,5,7,9]
    Output: 4
    Explanation: The good meals are (1,3), (1,7), (3,5) and, (7,9).
    Their respective sums are 4, 8, 8, and 16, all of which are powers of 2.

    Example 2:
    ---------
    Input: deliciousness = [1,1,1,3,3,3,7]
    Output: 15
    Explanation: The good meals are (1,1) with 3 ways, (1,3) with 9 ways, and (1,7) with 3 ways.

    Constraints:
    -----------
        1 <= deliciousness.length <= 10^5
        0 <= deliciousness[i] <= 2^20


 */

public class CountGoodMeals {

    private static final int MOD = 1000000007;

    public int countPairs(int[] arr) {
        Map<Integer, Integer> hm = new HashMap<>();
        long res = 0;
        for (int num : arr) {
            int power = 1;
            for (int i = 0; i < 22; i++) {
                if (hm.containsKey(power - num)) {
                    res += hm.get(power - num);
                    res %= MOD;
                }
                power *= 2;
            }
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }
        return (int) res;
    }

    public static void main(String[] args) {
        CountGoodMeals countGoodMeals = new CountGoodMeals();
        System.out.println(countGoodMeals.countPairs(new int[] {1,3,5,7,9}));       //  4
        System.out.println(countGoodMeals.countPairs(new int[] {1,1,1,3,3,3,7}));   //  15
    }

}
