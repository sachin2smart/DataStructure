package in.sachinshinde.graph.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

//	https://leetcode.com/problems/accounts-merge/

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


//	Approach : DFS 

public class AccountMerge {

	/*	Data Structures being used :-
			(1) List...ArrayList
			(2) Map...HashMap
			(3) Set...TreeSet
	 */

	//	TreeSet - It does not allow duplicates
	//			- It stores values in sorted order 
	
   public static List<List<String>> getMergedAccounts(List<List<String>> accounts) {
        Map<String, String> owners = new HashMap<>();
        Map<String, String> parents = new HashMap<>();
        Map<String, TreeSet<String>> unions = new HashMap<>(); //used treeset here simply because the question requires the emails are returned in sorted order
        
        // initialize each email's parent to be itself
        for (List<String> account: accounts) {
            for (int i = 1; i<account.size(); i++) {
                String owner = account.get(0);
                String email = account.get(i);
                parents.put(email, email);
                owners.put(email, owner);
            }
        }
        //	parents: {johnnybravo@mail.com=johnnybravo@mail.com, johnsmith@mail.com=johnsmith@mail.com, john00@mail.com=john00@mail.com, john_newyork@mail.com=john_newyork@mail.com, mary@mail.com=mary@mail.com}
        //	owners : {johnnybravo@mail.com=John, johnsmith@mail.com=John, john00@mail.com=John, john_newyork@mail.com=John, mary@mail.com=Mary}
        
        //union each account's parent to be the first one in the list
        for (List<String> account: accounts) {
            String p1 = find(parents, account.get(1));
            for (int i = 2; i<account.size(); i++) {
                String p2 = find(parents, account.get(i));
                //union
                parents.put(p2, p1);
            }
        }
        // parents : {johnnybravo@mail.com=johnnybravo@mail.com, johnsmith@mail.com=johnsmith@mail.com, john00@mail.com=johnsmith@mail.com, john_newyork@mail.com=johnsmith@mail.com, mary@mail.com=mary@mail.com}
        
        //now combine the union sets
        for (List<String> account: accounts) {
            String p1 = find(parents, account.get(1));
            if (!unions.containsKey(p1)) {
                unions.put(p1, new TreeSet<>());
            }
            Set<String> emailSets = unions.get(p1);
            for (int i = 1; i<account.size(); i++) {
                emailSets.add(account.get(i));
            }
        }
        
        //	unions : {johnnybravo@mail.com=[johnnybravo@mail.com], johnsmith@mail.com=[john00@mail.com, john_newyork@mail.com, johnsmith@mail.com], mary@mail.com=[mary@mail.com]}
        
        List<List<String>> res = new ArrayList<>();
        for (String p : unions.keySet()) {
            List<String> emails = new ArrayList<String>(unions.get(p));
            emails.add(0, owners.get(p));
            res.add(emails);
        }
        
        return res;
    }
    
    //find
    public static String find(Map<String, String> parents, String email) {
        while (!parents.get(email).equals(email)) {
            parents.put(email, parents.get(parents.get(email))); //path compression
            email = parents.get(email);
        }
        return email;
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
