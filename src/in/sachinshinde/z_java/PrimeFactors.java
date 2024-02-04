package in.sachinshinde.z_java;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactors {

    public static void main(String[] args) {
	List<Integer> input = new ArrayList<>();
        input.add(21);
        input.add(23);
        input.add(27);
        
	getPrimeFactorsInPlace(input);
	getPrimeFactorsAll(input);
        
        input.stream()
        	.map(PrimeFactors::getPrimeFactors)
        	.forEach(System.out::print);
        
        input.stream()
        	.map(PrimeFactors::getPrimeFactors)
        	.toList()
        	.stream()
                .flatMap(List::stream)
                .forEach(e -> {System.out.print(" " + e);});
    }
    
    private static void getPrimeFactorsInPlace(List<Integer> input) {
	List<List<Integer>> result = new ArrayList<>();
	for(int i=0; i<input.size(); i++) {
	    List<Integer> primeFactors = getPrimeFactors(input.get(i));
	    result.add(primeFactors);
	}
	System.out.println(result);
    }

    private static void getPrimeFactorsAll(List<Integer> input) {
	List<Integer> result = new ArrayList<>();
	for(int i=0; i<input.size(); i++) {
	    List<Integer> primeFactors = getPrimeFactors(input.get(i));
	    result.addAll(primeFactors);
	}
	System.out.println(result);
    }

    private static List<Integer> getPrimeFactors(Integer num) {
	List<Integer> primeFactors = new ArrayList<>();
	int count;
	for (int i = 2; i <= num; i++) {
            count = 0;
            while (num % i == 0) {
                num /= i;
                count++;
            }
            if (count == 0) {
                 continue;
            }
            for(int j = 0; j < count; j++)
        	primeFactors.add(i);
        }
	return primeFactors;
    }
}
