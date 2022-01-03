package in.sachinshinde.recursion;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
	
	public static List<List<String>> getPalindromePartition(String s) {
	   List<List<String>> subsets = new ArrayList<>();
	   backtrack(subsets, new ArrayList<>(), s, 0);
	   return subsets;
	}

	public static void backtrack(List<List<String>> subsets, List<String> tempList, String s, int start){
	   if(start == s.length())
		   subsets.add(new ArrayList<>(tempList));
	   else{
	      for(int i = start; i < s.length(); i++){
	         if(isPalindrome(s, start, i)){
	            tempList.add(s.substring(start, i + 1));
	            backtrack(subsets, tempList, s, i + 1);
	            tempList.remove(tempList.size() - 1);
	         }
	      }
	   }
	}

	public static boolean isPalindrome(String s, int low, int high){
	   while(low < high)
	      if(s.charAt(low++) != s.charAt(high--)) 
	    	  return false;
	   
	   return true;
	} 
	
	public static void main(String args[]) {
        String s = "mam";
        List<List<String>> ans = getPalindromePartition(s);
        
        System.out.println("Possible palindromes of given string \'"+ s +"\' are : ");
        for(List<String> tupple: ans)
            System.out.println(" "+ tupple);
    }
}
