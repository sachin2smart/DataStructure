package in.sachinshinde.graph.union_find;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/accounts-merge/

/*
* 	Given a list of accounts where each element accounts[i] is a list of strings, 
* 	where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. 
Note that even if two accounts have the same name, they may belong to different people as people could have the same name. 
A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: 
	the first element of each account is the name, and the rest of the elements are emails in sorted order. 
	The accounts themselves can be returned in any order.
*/


// Approach : Union Find 

public class AccountMerge {
	
	 public static List<List<String>> getMergedAccounts(List<List<String>> accounts) {
	        int size = accounts.size();
	        UnionFind2 uf = new UnionFind2(size);

	        Map<String, Integer> uniqueEmails = new  HashMap<>();
	        for(int i = 0; i < size; i++) {
	            List<String> account = accounts.get(i);
	            for(int j = 1; j < account.size(); j++) {
	                String email = account.get(j);
					if (uniqueEmails.containsKey(email))
	                    uf.unite(i, uniqueEmails.get(email));
	                else
	                	uniqueEmails.put(email, i);
	            }
	        }
	        
	        Map<Integer, List<String>> idToEmailsMap = new HashMap<>();
	        for(String key : uniqueEmails.keySet()) {
	            int root = uf.root(uniqueEmails.get(key));
	            if (!idToEmailsMap.containsKey(root)) {
	            	idToEmailsMap.put(root, new ArrayList<String>());
	            }
	            idToEmailsMap.get(root).add(key);
	        }
	        
	        List<List<String>> mergedAccounts =  new ArrayList<>();
	        for(Integer id : idToEmailsMap.keySet()) {
	            List<String> emails =  idToEmailsMap.get(id);
	            Collections.sort(emails);
	            emails.add(0, accounts.get(id).get(0));
	            mergedAccounts.add(emails);
	        }
	        
	        return  mergedAccounts;
	    }
	 
	 public static void main(String[] args) {
		List<List<String>> accounts = Arrays.asList(
				Arrays.asList("John","johnsmith@mail.com","john_newyork@mail.com"),
				Arrays.asList("John","johnsmith@mail.com","john00@mail.com"),
				Arrays.asList("Mary","mary@mail.com"),
				Arrays.asList("John","johnnybravo@mail.com"));
		
		accounts = getMergedAccounts(accounts);
		System.out.println(accounts);
		
		accounts = Arrays.asList(Arrays.asList("Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"),
		            Arrays.asList("Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"),
		            Arrays.asList("Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"),
		            Arrays.asList("Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"),
		            Arrays.asList("Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"));
		
		accounts = getMergedAccounts(accounts);
		System.out.println(accounts);
		
	}
}
