package in.sachinshinde.graph.dfs;

import java.util.ArrayList;
import java.util.Arrays;
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
//		Set-HashSet  : Set<String>
//		Map-HashMap  : Map<String, List<String>>
//		List-ArrayList

public class AccountMerge {
    
    public List<List<String>> accountsMerge(List<List<String>> accountList) {
        Map<String, List<String>> adjacencyMapList = new HashMap<>();

        // Step 1: Building adjacency list
        // Adding edge between first email to all other emails in each account
        for(List<String> account : accountList) {
            String accountFirstEmail = account.get(1);

            // Make a 1-on-1 linkage between first email with all remaining
            for(int j = 2; j < account.size(); j++) {
                String currEmail = account.get(j);

                //  Now, currEmail and accountFirstEmail are adjacent
                if(!adjacencyMapList.containsKey(accountFirstEmail)) {
                    adjacencyMapList.put(accountFirstEmail, new ArrayList<>());
                }
                //  accountFirstEmail --> currEmail
                adjacencyMapList.get(accountFirstEmail).add(currEmail);

                //-------------------------------------------------
                //	If A is adjacent to B then make B adjacent to A
                if(!adjacencyMapList.containsKey(currEmail)) {
                    adjacencyMapList.put(currEmail, new ArrayList<>());
                }
                // currEmail --> accountFirstEmail
                adjacencyMapList.get(currEmail).add(accountFirstEmail);
            }
        }

        List<List<String>> mergedAccounts = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        // Traverse over all the accounts to store components
        for(List<String> account : accountList) {
            String accountName = account.get(0);
            String accountFirstEmail = account.get(1);
            
            // If email is visited, then it's a part of different component
            // Hence perform DFS only if email is not visited yet
            if(!visited.contains(accountFirstEmail)) {
                List<String> mergedAccount = new ArrayList<>();
                // Adding account name at the 0th index
                mergedAccount.add(accountName);

                /* For this DFS recursive function:
                    -   adjacencyMapList : constant
                    -   visited : keeps on increasing
                    -   mergedAccount : only one value as name initially
                    -   accountFirstEmail : this will gets added into the DFS function as second value of mergedAccount List
                 */
                DFS(adjacencyMapList, mergedAccount, visited, accountFirstEmail);

                Collections.sort(mergedAccount.subList(1, mergedAccount.size())); 
                mergedAccounts.add(mergedAccount);
            }
        }
        
        return mergedAccounts;
    }
    
    private void DFS( Map<String, List<String>> adjacencyMapList, List<String> mergedAccount,
                      Set<String> visited, String email) {

        // Here, visited set and mergedAccount list needs to be updated
        visited.add(email);
        // Add the email vector that contains the current component's emails
        mergedAccount.add(email);
        
        if(!adjacencyMapList.containsKey(email)) {
            return;
        }

        // visit each neighbor and add them into the mergedAccount list
        for(String neighbor : adjacencyMapList.get(email)) {
            if (!visited.contains(neighbor)) {
                DFS(adjacencyMapList, mergedAccount, visited, neighbor);
            }
        }
    }

    public static void main(String[] args) {
	AccountMerge am = new AccountMerge();
	
        List<String> l1 = Arrays.asList("Sachin","Sachin1@gmail.com","Sachin2@gmail.com");
        List<String> l2 = Arrays.asList("Sachin","Sachin1@gmail.com","Sachin5@gmail.com");
        List<String> l3 = Arrays.asList("Diksha","Diksha1@mail.com");
        List<String> l4 = Arrays.asList("Sachin","Sachin6@gmail.com","Sachin7@gmail.com");

        List<List<String>> lf = Arrays.asList(l1,l2,l3,l4);
        
        List<List<String>> lr = am.accountsMerge(lf);
        
        System.out.println(lr);
        // [[Sachin, Sachin1@gmail.com, Sachin2@gmail.com, Sachin5@gmail.com], 
        //	[Diksha, Diksha1@mail.com],
        //	[Sachin, Sachin6@gmail.com, Sachin7@gmail.com]]

    }
}