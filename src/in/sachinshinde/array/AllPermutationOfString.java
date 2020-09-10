//  https://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
//	Video 1 : https://youtu.be/AfxHGNRtFac  
//  Video 2 : https://www.youtube.com/watch?v=nYFd7VHKyWQ

package in.sachinshinde.array;

import java.util.ArrayList;
import java.util.List;

public class AllPermutationOfString {

	List<String> allStrings = new ArrayList<String>();
	public void permutationsWithoutDuplicates(String str, int l, int r) {
		if(l==r) {
				System.out.println(str);
		}else {
			for(int i=l; i<=r; i++) {
				if(str.charAt(l)==str.charAt(i) && l!=i) continue;
				str = swap(str, l, i); // backtrack
				permutationsWithoutDuplicates(str, l+1, r);
			}
		}
	}
	
	public void permutationsWithDuplicates(String str, int l, int r) {
		if(l==r) {
			if(allStrings.contains(str)) {
				System.out.println(str + " : Repeated");
			}else {
				System.out.println(str);
				allStrings.add(str);
			}
				
		}else {
			for(int i=l; i<=r; i++) {
				str = swap(str, l, i);
				permutationsWithDuplicates(str, l+1, r);
				str = swap(str, l, i); // backtrack
				
			}
		}
	}
	
	private String swap(String str, int l, int r) {
		char[] charArray = str.toCharArray();
		char temp = charArray[l];
		charArray[l] = charArray[r];
		charArray[r] = temp;
		return String.valueOf(charArray);
	}

	public static void main(String[] args) {
		AllPermutationOfString allPermutationOfString = new AllPermutationOfString();
		String str = "ABCC";
		System.out.println("--------------------------------------------------------");
		// number of combinations (4!) = 4*3*2*1 = 24  : 4 number of characters
		allPermutationOfString.permutationsWithDuplicates(str, 0, str.length()-1);
		System.out.println("--------------------------------------------------------");
		// number of combinations (4!)/(2!) = (4*3*2*1)/(2*1) = 12 : 4 number of characters, 2 C repeated twice
		allPermutationOfString.permutationsWithoutDuplicates(str, 0, str.length()-1);
		System.out.println("--------------------------------------------------------");
	}

}

/*
 *    OUTPUT
 * 
--------------------------------------------------------
ABCC
ABCC : Repeated
ACBC
ACCB
ACCB : Repeated
ACBC : Repeated
BACC
BACC : Repeated
BCAC
BCCA
BCCA : Repeated
BCAC : Repeated
CBAC
CBCA
CABC
CACB
CCAB
CCBA
CBCA : Repeated
CBAC : Repeated
CCBA : Repeated
CCAB : Repeated
CACB : Repeated
CABC : Repeated
--------------------------------------------------------
ABCC
ACBC
ACCB
BACC
BCAC
BCCA
CABC
CACB
CBAC
CBCA
CCAB
CCBA
-------------------------------------------------------
 * 
 * 
 */
