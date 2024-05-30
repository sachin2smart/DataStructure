package in.sachinshinde.z_basics_ds;

import java.util.*;
import java.util.stream.Collectors;

public class StringOperations {

    public static void main(String[] args) {
        StringOperations stringOperations = new StringOperations();
        System.out.println("---------------");
        //  Numbers    0...9
        for(int i=48; i <= 57; i++) {
            System.out.println(i +" : " + (char)i);
        }
        System.out.println("---------------");
        //  Uppercase english characters    A...Z
        for(int i=65; i <= 90; i++) {
            System.out.println(i +" : " + (char)i);
        }
        System.out.println("---------------");
        //  lowercase english characters a...z
        for(int i=97; i <= 122; i++) {
            System.out.println(i +" : " + (char)i);
        }
        System.out.println("---------------");
        stringOperations.generateSubstring("leetcode");
    }

    public List<String> generateSubstring(String s){
        if (s == null || s.length() == 0) {
            return null;
        }
        Set<String> hs = new TreeSet<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = 1; j <= s.length() - i; j++) {
                hs.add(s.substring(i, i + j));
            }
        }
        List<String> subStrs = new ArrayList<>(hs);
        subStrs = subStrs.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
        System.out.println(subStrs);
        System.out.println(subStrs.size());
        return subStrs;
    }
}
