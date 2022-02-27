package in.sachinshinde.misc;

/*

Find the Celebrity
Suppose you are at a party withnpeople (labeled from0ton - 1) and among them, there may exist one celebrity. 
The definition of a celebrity is that all the other n-1 people know him/her but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. 
The only thing you are allowed to do is to ask questions like: 
	"Hi, A. Do you know B?" to get information of whether A knows B. 
You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper functionbool knows(a, b)which tells you whether A knows B. 
Implement a functionint findCelebrity(n), your function should minimize the number of calls toknows.

Note: There will be exactly one celebrity if he/she is in the party. 
Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return-1.

 */

public class FindTheCelebrity {
	public int findCelebrity(int n) {
	    int candidate = 0;

	    // everyone knows the celebrity
	    for (int i = 1; i < n; ++i)
	      if (knows(candidate, i))
	        candidate = i;

	    // candidate knows nobody and everyone knows the celebrity
	    for (int i = 0; i < n; ++i) {
	      if (i < candidate && knows(candidate, i) || !knows(i, candidate))
	        return -1;
	      if (i > candidate && !knows(i, candidate))
	        return -1;
	    }

	    return candidate;
	}

	private boolean knows(int candidate, int i) {
		// this is programmatically set 
		return false;
	}
}
