package in.sachinshinde.graph.bfs;

import java.util.*;

//  https://leetcode.com/problems/word-ladder/

/*
        A transformation sequence from word beginWord to word endWord
            using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

                -   Every adjacent pair of words differs by a single letter.
                -   Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
                -   sk == endWord
        Given two words, beginWord and endWord, and a dictionary wordList,
            return the number of words in the shortest transformation sequence from beginWord to endWord, or
            0 if no such sequence exists.

        Example 1:
        ---------
        Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
        Output: 5
        Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog",
        which is 5 words long.

        Example 2:
        ---------
        Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
        Output: 0
        Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.


        Constraints:
        -----------
            1 <= beginWord.length <= 10
            endWord.length == beginWord.length
            1 <= wordList.length <= 5000
            wordList[i].length == beginWord.length
            beginWord, endWord, and wordList[i] consist of lowercase English letters.
            beginWord != endWord
            All the words in wordList are unique.

 */

public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> hs = new HashSet<>(wordList);

        if(!hs.contains(endWord)) {
            return 0;
        }

        Queue<String> q = new LinkedList<>();
        q.add(beginWord);

        Set<String> visited = new HashSet<>();
        int pathNum = 1;

        while (!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                String currWord = q.poll();
                if(currWord.equals(endWord)) {
                    return pathNum;
                }

                for(int j = 0; j < currWord.length(); j++) {
                    for(int k = 'a'; k <= 'z'; k++) {
                        char[] chars = currWord.toCharArray();
                        chars[j] = (char) k;    // changing a single character

                        String nextWord = new String(chars);
                        if(hs.contains(nextWord) && !visited.contains(nextWord)) {
                            q.add(nextWord);
                            visited.add(nextWord);
                        }
                    }
                }
            }
            pathNum++;
        }

        return 0;
    }

    public static void main(String[] args) {
        WordLadder wordLadder = new WordLadder();
        System.out.println(wordLadder.ladderLength("hit",  "cog",
                Arrays.asList("hot","dot","dog","lot","log","cog")));   // 5
        System.out.println(wordLadder.ladderLength("hit",  "cog",
                Arrays.asList("hot","dot","dog","lot","log")));   // 0
    }

}
