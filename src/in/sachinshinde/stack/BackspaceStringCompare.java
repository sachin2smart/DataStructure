package in.sachinshinde.stack;

import java.util.Stack;

//	https://leetcode.com/problems/backspace-string-compare/

/*
 * Given two strings s and t, 
 * 	return true if they are equal when both are typed into empty text editors. 
 * 	'#' means a backspace character.
 * 	Note that after backspacing an empty text, the text will continue empty.
 * 
 * 	Can you solve it in O(n) time and O(1) space?
 */

public class BackspaceStringCompare {
	
	public boolean compare(String s, String t) {
		return build(s).equals(build(t));
	}
	
	private String build(String str) {
		Stack<Character> st = new Stack<>();
		
		for(char ch: str.toCharArray())
			if(ch != '#') 
				st.push(ch);
			else
				if(!st.empty())
					st.pop();
		
		return String.valueOf(st);
	}
	
	public static void main(String[] args) {
		BackspaceStringCompare compare = new BackspaceStringCompare();
		System.out.println(compare.compare("a#b", "c#b"));	//	true
		System.out.println(compare.compare("ab##", "c#d#"));	//	true
		System.out.println(compare.compare("a#c", "b"));	//	false
		System.out.println("---------");
		System.out.println(compare.backspaceCompare("a#b", "c#b"));	//	true
		System.out.println(compare.backspaceCompare("ab##", "c#d#"));	//	true
		System.out.println(compare.backspaceCompare("a#c", "b"));	//	false
		System.out.println("---------");
		System.out.println(compare.backspaceCompare2("a#b", "c#b"));	//	true
		System.out.println(compare.backspaceCompare2("ab##", "c#d#"));	//	true
		System.out.println(compare.backspaceCompare2("a#c", "b"));	//	false
	}
	/*
	Implementation using two pointer- 1st to traverse the string and second to store the character at given position

	-	Suppose 2 pointer i & k
	-	Start traversing the by first pointer(i) 
			if it is # then decrease the 2nd pointer(k )(k>=0).
				And if it is not # then increase the pointer(k)
					and store the element at k th position.
					S[k]=S[i]
	-	Same will be done to 2nd string And suppose its 2nd pointer is p
	-	If k and p are not equal means the string have differnt length. If same, then compare every element.
	*/
	public boolean backspaceCompare(String s1, String t1) {
        int k=0,p=0;
        char[] s = s1.toCharArray();
        char[] t = t1.toCharArray();
        
        for(int i=0;i<s.length;i++) {
            if(s[i]=='#') {
                k--;
                 k=Math.max(0,k);
            }
            else {
               s[k]=s[i];
               k++;
            }
        }
        
        for(int i=0;i<t.length;i++) {
           if(t[i]=='#') {
        	   p--;
               p=Math.max(0,p);
           }
           else {
               t[p]=t[i];
               p++;
           }
        }
        
        if(k!=p)
            return false;
        else
            for(int i=0;i<k;i++)
                if(s[i]!=t[i])
                    return false;
            
        return true;   
    }
	
	//	Time: O(M+N); Space: O(1) : M,N are the lengths of s and t
	public boolean backspaceCompare2(String s, String t) {
        int i = s.length() - 1, j = t.length() - 1;
        int skipS = 0, skipT = 0;

        while (i >= 0 || j >= 0) { // While there may be chars in build(S) or build (T)
            
        	while (i >= 0) { // Find position of next possible char in build(S)
                if (s.charAt(i) == '#') {
                	skipS++; 
                	i--;
                }
                else if (skipS > 0) {
                	skipS--; 
                	i--;
                }
                else 
                	break;
            }
            
            while (j >= 0) { // Find position of next possible char in build(T)
                if (t.charAt(j) == '#') {
                	skipT++; 
                	j--;
                }
                else if (skipT > 0) {
                	skipT--; 
                	j--;
                }
                else 
                	break;
            }
            
            // If two actual characters are different
            if (i >= 0 && j >= 0 && s.charAt(i) != t.charAt(j))
                return false;
            
            // If expecting to compare char vs nothing
            if ((i >= 0) != (j >= 0))
                return false;
            
            i--; 
            j--;
        }
        return true;
    }
}
