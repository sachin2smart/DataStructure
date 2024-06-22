package in.sachinshinde.heap_priority_queue;

import java.util.*;

//  https://leetcode.com/problems/sort-characters-by-frequency/

/*
        Given a string s, sort it in decreasing order based on the frequency of the characters.
            The frequency of a character is the number of times it appears in the string.

        Return the sorted string. If there are multiple answers, return any of them.

        Example 1:
        ---------
        Input: s = "tree"
        Output: "eert"
        Explanation: 'e' appears twice while 'r' and 't' both appear once.
        So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

        Example 2:
        ---------
        Input: s = "cccaaa"
        Output: "aaaccc"
        Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
        Note that "cacaca" is incorrect, as the same characters must be together.

        Example 3:
        ---------
        Input: s = "Aabb"
        Output: "bbAa"
        Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
        Note that 'A' and 'a' are treated as two different characters.


        Constraints:
        -----------
            1 <= s.length <= 5 * 105
            s consists of uppercase and lowercase English letters and digits.
 */

public class SortCharactersByFrequency {

    public String frequencySort(String s) {
        HashMap<Character, Integer> hm = new HashMap<>();

        for(char ch: s.toCharArray()) {
            hm.put(ch, hm.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        pq.addAll(hm.entrySet());
        StringBuilder res = new StringBuilder();

        while(!pq.isEmpty()) {
            Map.Entry<Character, Integer> entry = pq.poll();
            res.append(String.valueOf(entry.getKey()).repeat(entry.getValue()));
        }

        return res.toString();
    }

    public static void main(String[] args) {
        SortCharactersByFrequency sortChar = new SortCharactersByFrequency();
        System.out.println(sortChar.frequencySort("tree"));     //  eert
        System.out.println(sortChar.frequencySort("cccaaa"));   //  aaaccc
        System.out.println(sortChar.frequencySort("Aabb"));     //  bbAa

        System.out.println(sortChar.frequencySort2("tree"));     //  eert
        System.out.println(sortChar.frequencySort2("cccaaa"));   //  aaaccc
        System.out.println(sortChar.frequencySort2("Aabb"));     //  bbAa
    }

    public String frequencySort2(String s) {
        int[] frequency = new int[128];
        for (char ch : s.toCharArray()) {
            frequency[ch]++;
        }

        List<Character> chars = new ArrayList<>();
        for (char ch = 0; ch < 128; ch++) {
            if (frequency[ch] > 0) {
                chars.add(ch);
            }
        }

        chars.sort((a, b) -> frequency[b] - frequency[a]);

        StringBuilder sb = new StringBuilder();
        for (char ch : chars) {
            for (int i = 0; i < frequency[ch]; i++) {
                sb.append(ch);
            }
        }

        return sb.toString();
    }

}