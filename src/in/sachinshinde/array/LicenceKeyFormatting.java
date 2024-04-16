package in.sachinshinde.array;

/*
 	You are given a license key represented as a string s 
 		that consists of only alphanumeric characters and dashes. 
 	The string is separated into n + 1 groups by n dashes. 
 	You are also given an integer k.
	
	We want to reformat the string s such that 
		each group contains exactly k characters, 
		except for the first group, which could be shorter than k 
		but still must contain at least one character. 
		
	Furthermore, there must be a dash inserted between two groups, 
	and you should convert all lowercase letters to uppercase.
	Return the reformatted license key.
 */
public class LicenceKeyFormatting {

    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--)
            if (s.charAt(i) != '-')	// Skipping the dash
                sb.append(sb.length() % (k + 1) == k ? '-' : "").append(s.charAt(i));
        return sb.reverse().toString().toUpperCase();
    } 
    
    //	why i%(k+1) == k ?
    //	say k = 4; n = 10
    /*		len = 0   ->   0%5 != 0  Add "" + char
     *		len = 1   ->   1%5 != 1  Add "" + char
     *		len = 2   ->   2%5 != 2  Add "" + char
     *		len = 3   ->   3%5 != 3  Add "" + char
     *		len = 4   ->   4%5 != 4  Add dash "-" + char
     *		len = 6   ->   6%5 != 1  Add "" + char
     *		len = 7   ->   7%5 != 2  Add "" + char
     *		len = 8   ->   8%5 != 3  Add "" + char
     *		len = 9   ->   9%5 != 4  Add dash "-" + char
     *		len = 11  ->  10%5 != 1  Add "" + char
     *
     *		total length = len + 1 = 12
     */
    
    public static void main(String[] args) {
	LicenceKeyFormatting key = new LicenceKeyFormatting();
	System.out.println(key.licenseKeyFormatting("5F3Z-2e-9-w", 4));		//	"5F3Z-2E9W"
	System.out.println(key.licenseKeyFormatting("2-5g-3-J", 2));		//	"2-5G-3J"
	System.out.println(key.licenseKeyFormatting("5F3Z-2e-9-wrG", 4));	//	"5F-3Z2E-9WRG"
    }
}