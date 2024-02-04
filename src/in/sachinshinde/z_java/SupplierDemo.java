package in.sachinshinde.z_java;

import java.util.function.Supplier;

public class SupplierDemo {

    public static void main(String[] args) {
    	getValue(() -> "Output1");
    	getValue(() -> "OutPut2");
    }

    public static void getValue(Supplier<?> supplier) {
    	System.out.println(supplier.get());
    }

}
