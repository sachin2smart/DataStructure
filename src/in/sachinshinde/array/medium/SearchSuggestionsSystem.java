package in.sachinshinde.array.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

//  https://leetcode.com/problems/search-suggestions-system

/*
        You are given an array of strings products and a string searchWord.

        Design a system that suggests at most three product names from products after each character of searchWord is typed.
        Suggested products should have common prefix with searchWord.
        If there are more than three products with a common prefix return the three lexicographically minimums products.

        Return a list of lists of the suggested products after each character of searchWord is typed.

        Example 1:
        ---------
        Input: products = ["mobile","mouse","moneypot","monitor","mousepad"],
                searchWord = "mouse"
        Output:
                [["mobile","moneypot","monitor"],
                ["mobile","moneypot","monitor"],
                ["mouse","mousepad"],
                ["mouse","mousepad"],
                ["mouse","mousepad"]]
        Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"].
        After typing m and mo all products match and we show user ["mobile","moneypot","monitor"].
        After typing mou, mous and mouse the system suggests ["mouse","mousepad"].

        Example 2:
        ---------
        Input: products = ["havana"], searchWord = "havana"
        Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
        Explanation: The only word "havana" will be always suggested while typing the search word.


        Constraints:
        -----------
            1 <= products.length <= 1000
            1 <= products[i].length <= 3000
            1 <= sum(products[i].length) <= 2 * 104
            All the strings of products are unique.
            products[i] consists of lowercase English letters.
            1 <= searchWord.length <= 1000
            searchWord consists of lowercase English letters.
 */

public class SearchSuggestionsSystem {

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> suggestedProducts = new ArrayList<>();
        Arrays.sort(products);
        int searchLength = searchWord.length();
        StringBuilder search = new StringBuilder();
        for (int i = 0; i < searchLength; i++) {
            search.append(searchWord.charAt(i));
            String currSearch = search.toString();
            List<String> currSuggestedProducts = new ArrayList<>();
            int suggestCount = 0;
            for (String product : products) {
                if (product.indexOf(currSearch) == 0) {
                    currSuggestedProducts.add(product);
                    suggestCount++;
                    if (suggestCount == 3) {
                        break;
                    }
                }
            }
            suggestedProducts.add(currSuggestedProducts);
        }
        return suggestedProducts;
    }

    public static void main(String[] args) {
        SearchSuggestionsSystem system = new SearchSuggestionsSystem();
        System.out.println(system.suggestedProducts(new String[] {"mobile","mouse","moneypot","monitor","mousepad"}, "mouse"));
        System.out.println(system.suggestedProducts(new String[] {"havana"}, "havana"));

        System.out.println(system.suggestedProducts2(new String[] {"mobile","mouse","moneypot","monitor","mousepad"}, "mouse"));
        System.out.println(system.suggestedProducts2(new String[] {"havana"}, "havana"));
    }

    public List<List<String>> suggestedProducts2(String[] products, String searchWord) {
        List<List<String>> suggestedProducts = new ArrayList<>();
        for(int i = 1; i <= searchWord.length(); i++){
            PriorityQueue<String> pq = new PriorityQueue<>(3, (s1, s2) -> s1.compareTo(s2));
            String currSearchWord = searchWord.substring(0, i);
            for(String currProductName : products){
                if(currProductName.startsWith(currSearchWord)){
                    pq.offer(currProductName);
                }
            }
            List<String> currSuggestedProducts = new ArrayList<>();
            for(int j = 0; j < 3; j++){
                if(pq.peek() != null){
                    currSuggestedProducts.add(pq.poll());
                }
            }
            suggestedProducts.add(currSuggestedProducts);
        }
        return suggestedProducts;
    }
}
