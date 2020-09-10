/*
 * https://www.geeksforgeeks.org/find-two-prime-numbers-with-given-sum/
 */

/*
 *  Given an even number (greater than 2 ), print two prime numbers 
 *  whose sum will be equal to given number. There may be several combinations possible. 
 *  Print only first such pair.
 */

package in.sachinshinde.array;

public class FindSumOfPrimeNumbers {
	
	static void SieveOfEratosthemes(boolean primeNumbers[], int n) {
		
		primeNumbers[0] = false;
		primeNumbers[1] = false;
		
		for(int i=2; i<n; i++) {
			primeNumbers[i] = true;
		}
		
		for(int p=2; p*p < n ; p++ ) {
			if(primeNumbers[p] == true) {	
				for(int i=p*p; i<=n ; i+=p ) {
					primeNumbers[i] = false;
				}
			}		
		}
	}
	
	static void findPrimePair(int n) {
		boolean primeNumbers[] = new boolean[n+1];
		SieveOfEratosthemes(primeNumbers, n);
		
		for(int i=0; i<n; i++) {
			if(primeNumbers[i] && primeNumbers[n-i]) {
				System.out.println(i + " " + (n-i));
			}
		}
	}
	
	public static void main(String args[]) {
		int n= 74;
		findPrimePair(n);
	}
}
