package in.sachinshinde.z_basicsjava;

import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionalFlip {

    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        Function<Integer, Function<Integer, Integer>> curriedMultiply = a -> b -> multiply.apply(a, b);

        Function<Integer, Integer> threeTimes = curriedMultiply.apply(3);
        Function<Integer, Integer> fiveTimes = curriedMultiply.apply(5);

        System.out.println(threeTimes.apply(4));
        System.out.println((fiveTimes.apply(2)));

        Function<Integer, Integer> negate = a -> -a;
        BiFunction<Integer, Integer, Integer> curriedNegate = multiply.andThen(negate);

        System.out.println(curriedNegate.apply(3, 4));
    }

}
