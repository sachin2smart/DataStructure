package in.sachinshinde.z_java;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalQuery {
    public static void main(String[] args)
    {
 
        System.out.println(new BigDecimal("4.0").divide(new BigDecimal("5.0"), 10, RoundingMode.HALF_UP));
        System.out.println(new BigDecimal("5.0").divide(new BigDecimal("4.0"), 10, RoundingMode.HALF_UP));
        System.out.println(new BigDecimal("5.0").divide(new BigDecimal("5.0"), 10, RoundingMode.HALF_UP));
        System.out.println(new BigDecimal("4.1").divide(new BigDecimal("5.0"), 10, RoundingMode.HALF_UP));
        System.out.println(new BigDecimal("4.0").divide(new BigDecimal("5.2"), 10, RoundingMode.HALF_UP));
        System.out.println(new BigDecimal("4.3").divide(new BigDecimal("5.4"), 10, RoundingMode.HALF_UP));
        System.out.println(new BigDecimal("4.05").divide(new BigDecimal("5.03"), 10, RoundingMode.HALF_UP));
        System.out.println(new BigDecimal("4.0990").divide(new BigDecimal("5.0003"), 10, RoundingMode.HALF_UP));
        System.out.println(new BigDecimal("4.00007").divide(new BigDecimal("5.00006"), 10, RoundingMode.HALF_UP));
  
    }
}