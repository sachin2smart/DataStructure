package in.sachinshinde.z_company.Amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Result1 {

    public static int getMax(List<Integer> importance) {
        importance.sort(Collections.reverseOrder());

        int prefixSum = 0;
        for (int i = 0; i < importance.size(); i++) {
            prefixSum += importance.get(i);
            if (prefixSum <= 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        List<Integer> l1 = new ArrayList<>();
        l1.add(2);
        l1.add(3);
        l1.add(-1);

    }
}
