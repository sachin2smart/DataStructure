package in.sachinshinde.array.hard;

import java.util.*;

//  https://leetcode.com/problems/concatenated-words/

/*
        Given an array of strings words (without duplicates),
        return all the concatenated words in the given list of words.

        A concatenated word is defined as a string that is
            comprised entirely of at least two shorter words (not necessarily distinct) in the given array.

        Example 1:
        ---------
        Input: words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
        Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
        Explanation:
            "catsdogcats" can be concatenated by "cats", "dog" and "cats";
            "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
            "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".

        Example 2:
        ---------
        Input: words = ["cat","dog","catdog"]
        Output: ["catdog"]


        Constraints:
        -----------
            1 <= words.length <= 10^4
            1 <= words[i].length <= 30
            words[i] consists of only lowercase English letters.
            All the strings of words are unique.
            1 <= sum(words[i].length) <= 10^5
 */

public class ConcatenatedWords {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> wordSet = new HashSet<>();
        Set<String> res = new HashSet<>();

        Collections.addAll(wordSet, words);

        for(String word : words){
            wordSet.remove(word);
            dfs(word, res, wordSet, 0);
            wordSet.add(word);
        }

        return new ArrayList<>(res);
    }

    private void dfs(String currWord, Set<String> res, Set<String> wordSet, int currIdx){
        if(currWord.length() > 0 && currIdx >= currWord.length()) {
            res.add(currWord);
            return;
        }

        for(int i = currIdx; i < currWord.length(); i++) {
            String currSubstring = currWord.substring(currIdx, i + 1);
            if(wordSet.contains(currSubstring)) {
                dfs(currWord, res, wordSet, i + 1);
            }
        }
    }

    public static void main(String[] args) {
        ConcatenatedWords words = new ConcatenatedWords();
        System.out.println(words.findAllConcatenatedWordsInADict(new String[]{"cat","cats","catsdogcats","dog",
                "dogcatsdog","hippopotamuses","rat","ratcatdogcat"}));
        //   ["catsdogcats","dogcatsdog","ratcatdogcat"]

        System.out.println(words.findAllConcatenatedWordsInADict(new String[]{"cat","dog","catdog"}));
        //  ["catdog"]
    }
}
