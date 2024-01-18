package in.sachinshinde.array.twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PalindromePairs {
    // Method 1: Using 2 loops - Gives TLE error for large dataset
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0; i<words.length; i++) {
            String currWord = words[i];
            for(int j=0; j<words.length; j++) {
        	if(i != j) {
        	    String formedWord = currWord + words[j];
        	    String revWord = new StringBuilder(formedWord).reverse().toString();
        	    if(formedWord.equals(revWord)) {
        		res.add(Arrays.asList(i, j));
        	    }
        	}
            }
        }
        return res;
    }
    
    //	Method 2:
    public List<List<Integer>> palindromePairs_2(String[] words) {
	List<List<Integer>> res = new ArrayList<>(); 
	
	if (words == null || words.length < 2) { 
	    return res;
	}
	Map<String, Integer> hm = new HashMap<String, Integer>();
	    
	for (int i=0; i<words.length; i++) {
	    hm.put(words[i], i);
	}

	for (int i=0; i<words.length; i++) {
	    for (int j=0; j<=words[i].length(); j++) { // notice: "j <= words[i].length()"
	        String str1 = words[i].substring(0, j);
	        String str2 = words[i].substring(j);
	        
	        if (isPalindrome(str1)) {
	            String str2reverse = new StringBuilder(str2).reverse().toString();
	            if (hm.containsKey(str2reverse) && hm.get(str2reverse) != i) {
	                res.add(Arrays.asList(hm.get(str2reverse), i));
	            }
	        }
	        if (isPalindrome(str2)) {
	            String str1reverse = new StringBuilder(str1).reverse().toString();
	            // "str.length() != 0" to avoid duplicates
	            if (hm.containsKey(str1reverse) && hm.get(str1reverse) != i && str2.length()!=0) {
	                res.add(Arrays.asList(i, hm.get(str1reverse)));
	            }
	        }
	    }
	}	
	return res;
    }

    private boolean isPalindrome(String str) {
	int left = 0;
	int right = str.length() - 1;
	while (left <= right) {
	    if (str.charAt(left++) !=  str.charAt(right--)) { 
		return false;
	    }
	}
	return true;
    }
    
    public static void main(String[] args) {
	PalindromePairs pairs = new PalindromePairs();
	System.out.println(pairs.palindromePairs(new String[] {"abcd","dcba","lls","s","sssll"}));
	System.out.println(pairs.palindromePairs_2(new String[] {"abcd","dcba","lls","s","sssll"}));
    }
}
