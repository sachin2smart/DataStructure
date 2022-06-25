package in.sachinshinde.graph.dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//https://leetcode.com/problems/accounts-merge/

/*
* 	Given a list of accounts where each element accounts[i] is a list of strings, 
* 	where the first element accounts[i][0] is a name, 
* 		and the rest of the elements are emails representing emails of the account.

	Now, we would like to merge these accounts. 
	Two accounts definitely belong to the same person if there is some common email to both accounts. 
	Note that - even if two accounts have the same name, 
		they may belong to different people as people could have the same name. 
	A person can have any number of accounts initially, 
		but all of their accounts definitely have the same name.
	
	After merging the accounts, return the accounts in the following format: 
		the first element of each account is the name, 
			and the rest of the elements are emails in sorted order. 
		The accounts themselves can be returned in any order.
*/


//	Approach : DFS 

//	Data Structures being used : 
//		Set-HashSet
//		Map-HashMap
//		List-ArrayList

public class AcconutMerge {
    Set<String> visited = new HashSet<>();
    Map<String, List<String>> adjacent = new HashMap<String, List<String>>();
    
    public List<List<String>> accountsMerge(List<List<String>> accountList) {
    	// Building adjacency list
        // Adding edge between first email to all other emails in the account
        for(List<String> account : accountList) {
            int accountSize = account.size();
            String accountFirstEmail = account.get(1);
            for(int j = 2; j < accountSize; j++) {
                String accountEmail = account.get(j);
                
                //	If A is adjacent to B then make B adjacent to A
                if(!adjacent.containsKey(accountFirstEmail))
                    adjacent.put(accountFirstEmail, new ArrayList<String>());
                
                adjacent.get(accountFirstEmail).add(accountEmail);
                
                if(!adjacent.containsKey(accountEmail))
                    adjacent.put(accountEmail, new ArrayList<String>());
                
                adjacent.get(accountEmail).add(accountFirstEmail);
            }
        }
        
        // Traverse over all th accounts to store components
        List<List<String>> mergedAccounts = new ArrayList<>();
        for(List<String> account : accountList) {
            String accountName = account.get(0);
            String accountFirstEmail = account.get(1);
            
            // If email is visited, then it's a part of different component
            // Hence perform DFS only if email is not visited yet
            if(!visited.contains(accountFirstEmail)) {
                List<String> mergedAccount = new ArrayList<>();
                // Adding account name at the 0th index
                mergedAccount.add(accountName);
                
                DFS(mergedAccount, accountFirstEmail);
                Collections.sort(mergedAccount.subList(1, mergedAccount.size())); 
                mergedAccounts.add(mergedAccount);
            }
        }
        
        return mergedAccounts;
    }
    
    private void DFS(List<String> mergedAccount, String email) {
        visited.add(email);
        // Add the email vector that contains the current component's emails
        mergedAccount.add(email);
        
        if(!adjacent.containsKey(email))
            return;
        
        for(String neighbor : adjacent.get(email))
            if(!visited.contains(neighbor))
                DFS(mergedAccount, neighbor);

    }
}