package in.sachinshinde.array.medium;

import java.util.HashMap;
import java.util.Map;

//  https://leetcode.com/problems/custom-sort-string/

/*
        You are given two strings order and s.
        All the characters of order are unique and were sorted in some custom order previously.

        Permute the characters of s so that they match the order that order was sorted.
        More specifically, if a character x occurs before a character y in order, then
            x should occur before y in the permuted string.

        Return any permutation of s that satisfies this property.

        Example 1:
        ---------
        Input:  order = "cba", s = "abcd"
        Output:  "cbad"

        Explanation: "a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c", "b", and "a".
            Since "d" does not appear in order, it can be at any position in the returned string.
            "dcba", "cdba", "cbda" are also valid outputs.

        Example 2:
        ---------
        Input:  order = "bcafg", s = "abcd"
        Output:  "bcad"

        Explanation: The characters "b", "c", and "a" from order dictate the order for the characters in s.
        The character "d" in s does not appear in order, so its position is flexible.
        Following the order of appearance in order, "b", "c", and "a" from s should be arranged as "b", "c", "a".
        "d" can be placed at any position since it's not in order. The output "bcad" correctly follows this rule.
        Other arrangements like "bacd" or "bcda" would also be valid, as long as "b", "c", "a" maintain their order.

        Constraints:
        -----------
            1 <= order.length <= 26
            1 <= s.length <= 200
            order and s consist of lowercase English letters.
            All the characters of order are unique.
 */

public class CustomSortString {

    public String customSortString(String order, String s) {

        Map<Character, Integer> hm = new HashMap<>();   // will put the chars from String s

        for (char stringCh: s.toCharArray()) {
            hm.put(stringCh, hm.getOrDefault(stringCh, 0) + 1);
        }

        StringBuilder res = new StringBuilder();

        for (char orderCh: order.toCharArray()) {
            if (hm.containsKey(orderCh)) {
                int count = hm.get(orderCh);
                res.append((orderCh+"").repeat(count));
                hm.remove(orderCh);
            }
        }

        for(char stringCh: hm.keySet()){ // Traversing remaining in the map
            int count = hm.get(stringCh);
            res.append((stringCh+"").repeat(count));
        }

        return res.toString();
    }

    public static void main(String[] args) {
        CustomSortString str = new CustomSortString();
        System.out.println(str.customSortString("cba", "abcdabc"));    // ccbbaad
        System.out.println(str.customSortString2("cba", "abcdabc"));    // ccbbaad

        System.out.println(str.customSortString("cba", "abcd"));    // cbad
        System.out.println(str.customSortString2("cba", "abcd"));    // cbad

        System.out.println(str.customSortString("bcafg", "abcd"));    // bcad
        System.out.println(str.customSortString2("bcafg", "abcd"));    // bcad

    }

    public String customSortString2(String order, String s) {
        Map<Character, Integer> hm = new HashMap<>();   // will put the chars from String s

        for (char stringCh: s.toCharArray()) {
            hm.put(stringCh, hm.getOrDefault(stringCh, 0) + 1);
        }

        StringBuilder res = new StringBuilder();

        for (char orderCh: order.toCharArray()) {
            if(hm.containsKey(orderCh)) {
                for(int i = 0; i < hm.get(orderCh); i++) {
                    res.append(orderCh);
                }
            }
            hm.remove(orderCh);
        }

        if(hm.isEmpty()) {
            return res.toString();
        }

        for(char stringCh: hm.keySet()) {
            for(int i = 0; i < hm.get(stringCh); i++) {
                res.append(stringCh);
            }
        }

        return res.toString();
    }
}
