package in.sachinshinde.array.hard;

public class IntegerToEnglishWord {

	private static final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	private static final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
	private static final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};
	
	public String numberToWords(int num) {
	    if (num == 0) {
			return "Zero";
		}

	    int i = 0;
	    String words = "";
	    
	    while (num > 0) {
	        if (num % 1000 != 0) {
				words = helper(num % 1000) + THOUSANDS[i] + " " + words;
			}
	    	num /= 1000;
	    	i++;
	    }
	    
	    return words.trim();
	}
	
	private String helper(int num) {
	    if (num == 0)
	        return "";
	    else if (num < 20)
	        return LESS_THAN_20[num] + " ";
	    else if (num < 100)
	        return TENS[num / 10] + " " + helper(num % 10);
	    else
	        return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
	}
	
	public static void main(String[] args) {
		IntegerToEnglishWord integerToEnglishWord = new IntegerToEnglishWord();
		System.out.println(integerToEnglishWord.numberToWords(10000000));	//	Ten Million
		System.out.println(integerToEnglishWord.numberToWords2(10000000));	//	Ten Million
	}
	
	//	Solution 2 

    String[] ones = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven",
            "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords2(int num) {
        if (num == 0) {
			return "Zero";
		}
        return helper2(num).trim();
    }
    
    private String helper2(int num) {
        if (num >= 1000000000)
        	return (helper2(num / 1000000000) + " Billion " + helper2(num % 1000000000)); // 10 crores
        if (num >= 1000000)
        	return (helper2(num / 1000000) + " Million " + helper2(num % 1000000)); // 10 lacks
        if (num >= 1000) 
        	return (helper2(num / 1000) + " Thousand " + helper2(num % 1000));
        if (num >= 100) 
        	return (helper2(num / 100) + " Hundred " + helper2((num % 100))).trim(); //  trim to remove unnecessary spaces
        if (num >= 20) 
        	return (tens[num / 10] + " " + helper2(num % 10)).trim(); //  trim to remove unnecessary spaces
        return ones[num];
    }
}
