package in.sachinshinde.heap_priority_queue;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//  https://leetcode.com/problems/reorganize-string

/*
        Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
        Return any possible rearrangement of s or return "" if not possible.
        
        Example 1:
        ---------
        Input: s = "aab"
        Output: "aba"
        
        Example 2:
        ---------
        Input: s = "aaab"
        Output: ""
        
        Constraints:
        -----------        
            1 <= s.length <= 500
            s consists of lowercase English letters.
 */

public class ReorganizeString {

    public String reorganizeString(String s) {
        Map<Character, Integer> hm = new HashMap<>();
        for (char ch : s.toCharArray()) {
            hm.put(ch, hm.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> hm.get(b) - hm.get(a));
        pq.addAll(hm.keySet());

        StringBuilder res = new StringBuilder();
        while (pq.size() >= 2) {
            char ch1 = pq.poll();
            char ch2 = pq.poll();

            res.append(ch1);
            res.append(ch2);

            hm.put(ch1, hm.get(ch1) - 1);
            hm.put(ch2, hm.get(ch2) - 1);

            if (hm.get(ch1) > 0) {
                pq.add(ch1);
            }
            if (hm.get(ch2) > 0) {
                pq.add(ch2);
            }
        }

        if (!pq.isEmpty()) {
            char ch = pq.poll();
            if (hm.get(ch) > 1) {
                return "";
            }
            res.append(ch);
        }

        return res.toString();
    }

    public static void main(String[] args) {
        ReorganizeString string = new ReorganizeString();
        System.out.println(string.reorganizeString("aab"));     //  "aba"
        System.out.println(string.reorganizeString("aaab"));    //  ""
    }
}