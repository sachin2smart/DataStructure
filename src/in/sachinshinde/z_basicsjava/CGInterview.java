package in.sachinshinde.z_basicsjava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CGInterview {


    
    public static void main(String[] args) {
	/*
	     -------------------------------
	    given: array of integers
	        [21, 23, 42]
	        
	    getPrimeFactors():
	        [[3,7],[23],[2,3,7]]
	        
	    getPrimeFactorsUnique():
	        [3,7,23,2]
	        
	    -------------------------------
	*/
	List<Integer> arr = new ArrayList<>();
	arr.add(21);
	arr.add(23);
	arr.add(42);
	
	List<List<Integer>> primeFactors = getPrimeFactors(arr);
	System.out.println("All Factors: "+primeFactors);
	
	List<Integer> primeFactorsUnique = getPrimeFactorsUnique(arr); 
	System.out.println("Unique Factors: "+primeFactorsUnique);
	
	/*
	 	Output:
	 	
	 	All Factors: [[3, 7], [23], [2, 3, 7]]
		Unique Factors: [3, 7, 23, 2]

	 */
	
	/*
	 	email consolidation
    
                    first entity : Name
                    remaining entities : emails 
                
                Example:    
                [["Sachin","Sachin1@gmail.com","Sachin2@gmail.com"],
                    ["Sachin","Sachin1@gmail.com","Sachin5@gmail.com"],
                    ["Asha","Asha1@mail.com"],
                    ["Sachin","Sachin6@gmail.com"]]
                
                expected  result:
                [["Sachin", "Sachin1@gmail.com", "Sachin2@gmail.com" "Sachin5@gmail.com"],
                ["Asha","Asha1@mail.com"],
                ["Sachin","Sachin6@gmail.com"]]
                
                
                public List<List<String>>  consolidateEmails(List<List<String>> accountList) {
                }
	 */
    }

    private static List<List<Integer>> getPrimeFactors(List<Integer> arr) {
        List<List<Integer>> result = new ArrayList<>();

        for(int i=0; i<arr.size(); i++) {
            List<Integer> currPrimeFactors = getPrimeFactors(arr.get(i));
            result.add(currPrimeFactors);
        }
        return result;
   }
    
    private static List<Integer> getPrimeFactorsUnique(List<Integer> arr) {
        List<Integer> primeFactors = new ArrayList<>();

        for(int i=0; i<arr.size(); i++) {
            List<Integer> currPrimeFactors = getPrimeFactors(arr.get(i));
            primeFactors = Stream.concat(primeFactors.stream(), currPrimeFactors.stream()).distinct().collect(Collectors.toList());
        }

        return primeFactors;
    }

    private static List<Integer> getPrimeFactors(int num) {
        List<Integer> primeFactors = new ArrayList<>();
        int j=0;
        for(int i=2; i<num; i++) {
            while(num%i ==0) {
                primeFactors.add(i);
                num /= i;
            }
        }
        if(num>2) {
            primeFactors.add(num);
        }
        return primeFactors;
    }
    
    //-------------------------------------------------------------------------------------------------------
    
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
