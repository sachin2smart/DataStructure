package in.sachinshinde.z_basicsjava;

public class Checks {
    public static void main(String[] args) {
        // check if the string exists in another
        String s1 = "Mahabharat";
        String s2 = "bharat";
        System.out.println(s1.indexOf(s2)); // 4 (start position)
        String s3 = "adharma";
        System.out.println(s1.indexOf(s3)); // -1
        String s4 = "Maha";
        System.out.println(s1.indexOf(s4)); // 0
        String s5 = "Mahabharat";
        System.out.println(s1.indexOf(s5)); // 0

        // 1. String.subString(beginIndex, endIndex) :
        //      beginIndex – the beginning index, inclusive.
        //      endIndex – the ending index, exclusive.
        //  2. String.subString(beginIndex) :
        //      beginIndex – the beginning index, inclusive.
        String sachin = "Sachin";
        System.out.println(sachin.substring(0,3));  // sac
        System.out.println(sachin.substring(2));  // chin
    }
}
