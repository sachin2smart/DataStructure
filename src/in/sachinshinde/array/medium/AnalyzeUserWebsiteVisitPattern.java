package in.sachinshinde.array.medium;

import java.util.*;

//  https://leetcode.com/problems/analyze-user-website-visit-pattern/

/*
        You are given two string arrays username and website and an integer array timestamp.
        All the given arrays are of the same length and the tuple [username[i], website[i], timestamp[i]] indicates that
            the user "username[i]" visited the website "website[i]" at time "timestamp[i]"

        A pattern is a list of three websites (not necessarily distinct).
            For example,
                ["home", "away", "love"]
                ["leetcode", "love", "leetcode"]
                ["luffy", "luffy", "luffy"]
            are all patterns.

        The score of a pattern is the number of users that
            visited all the websites in the pattern in the same order they appeared in the pattern.

        For example, if the pattern is ["home", "away", "love"],
            the score is the number of users x such that x visited "home" then visited "away" and visited "love" after that.
        Similarly, if the pattern is ["leetcode", "love", "leetcode"],
            the score is the number of users x such that x visited "leetcode" then visited "love" and visited "leetcode" one more time after that.
        Also, if the pattern is ["luffy", "luffy", "luffy"],
            the score is the number of users x such that x visited "luffy" three different times at different timestamps.

        Return the pattern with the largest score. If there is more than one pattern with the same largest score,
            return the lexicographically smallest such pattern.

        Example 1:
        ----------
        Input:
            username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"],
            timestamp = [1,2,3,4,5,6,7,8,9,10],
            website = ["home","about","career","home","cart","maps","home","home","about","career"]
        Output:
            ["home","about","career"]

        Explanation:
            The tuples in this example are:
                ["joe","home",1],["joe","about",2],["joe","career",3],["james","home",4],["james","cart",5],
                ["james","maps",6],["james","home",7],["mary","home",8],["mary","about",9], and ["mary","career",10].
        The pattern ("home", "about", "career") has score 2 (joe and mary).
        The pattern ("home", "cart", "maps") has score 1 (james).
        The pattern ("home", "cart", "home") has score 1 (james).
        The pattern ("home", "maps", "home") has score 1 (james).
        The pattern ("cart", "maps", "home") has score 1 (james).
        The pattern ("home", "home", "home") has score 0 (no user visited home 3 times).

        Example 2:
        ----------
        Input: username = ["ua","ua","ua","ub","ub","ub"], timestamp = [1,2,3,4,5,6], website = ["a","b","a","a","b","c"]
        Output: ["a","b","a"]


        Constraints:
        ------------
            3 <= username.length <= 50
            1 <= username[i].length <= 10
            timestamp.length == username.length
            1 <= timestamp[i] <= 109
            website.length == username.length
            1 <= website[i].length <= 10
            username[i] and website[i] consist of lowercase English letters.
            It is guaranteed that there is at least one user who visited at least three websites.
            All the tuples [username[i], timestamp[i], website[i]] are unique.
 */

public class AnalyzeUserWebsiteVisitPattern {

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        int n = username.length;
        String[][] tuples = new String[n][3];

        for(int i=0; i<n; i++) {
            tuples[i][0] = username[i];
            tuples[i][1] = website[i];
            tuples[i][2] = String.valueOf(timestamp[i]);
        }

        Arrays.sort(tuples, (a, b) -> {
            if(!a[0].equals(b[0])) {
                return a[0].compareTo(b[0]);    // compare username (String)
            }
            else {
                return Integer.parseInt(a[2]) - Integer.parseInt(b[2]); // compare timestamp (Integer)
            }
        });

        //  pattern<websites> --> set<users>
        Map<String, Set<String>> patternScore = new HashMap<>();

        int maxPatternUsersSize = 0;   // to store size of set<users> from above map
        String maxPattern = ""; // answer exists here

        for(int i = 0; i < n - 2; i++) {
            for(int j = i + 1; j < n - 1; j++) {
                for(int k = j + 1; k < n; k++) {
                    //  If 3 consecutive usernames are same
                    if(tuples[i][0].equals(tuples[j][0]) && tuples[j][0].equals(tuples[k][0]) ) {
                        String pattern = tuples[i][1] + "," + tuples[j][1] + "," + tuples[k][1];    // website names, separated by comma
                        patternScore.putIfAbsent(pattern, new HashSet<>());
                        patternScore.get(pattern).add(tuples[i][0]);

                        Set<String> currentPatternUsers = patternScore.get(pattern);
                        if (currentPatternUsers.size() == maxPatternUsersSize) {
                            // since the question asked : return the lexicographically smallest pattern
                            // string.compareTo(argument) :returns a value less than 0 if this string is lexicographically less than the string argument;
                            if (pattern.compareTo(maxPattern) < 0) {
                                maxPattern = pattern;
                            }
                        }
                        else if (currentPatternUsers.size() > maxPatternUsersSize) {
                            maxPatternUsersSize = currentPatternUsers.size();
                            maxPattern = pattern;
                        }
                    }
                }
            }
        }

        return new ArrayList<>(Arrays.asList(maxPattern.split(",")));
    }

    public static void main(String[] args) {
        AnalyzeUserWebsiteVisitPattern pattern = new AnalyzeUserWebsiteVisitPattern();

        System.out.println(pattern.mostVisitedPattern(
                new String[] {"joe","joe","joe","james","james","james","james","mary","mary","mary"},
                new int[] {1,2,3,4,5,6,7,8,9,10},
                new String[] {"home","about","career","home","cart","maps","home","home","about","career"}));

        //  [home, about, career]

        System.out.println(pattern.mostVisitedPattern(
                new String[] {"ua","ua","ua","ub","ub","ub"},
                new int[] {1,2,3,4,5,6},
                new String[] {"a","b","a","a","b","c"}));

        //  [a, b, a]
    }

}