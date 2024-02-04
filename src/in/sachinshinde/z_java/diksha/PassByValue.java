package in.sachinshinde.z_java.diksha;

public class PassByValue {

    public static void main(String[] args) {
	StringBuilder sb = new StringBuilder("Sachin");
	String str = new String("Dan");
	
	completeName(sb, str);
	
	System.out.println(sb);
	System.out.println(str);
	
	sb = null;
	str = null;
	
	System.out.println(sb);
	System.out.println(str);
    }
    
    private static void completeName(StringBuilder sb, String str) {
	sb = sb.append(" Shinde");
	str = str.concat(" Richardson");
	
	sb = null;
	str = null;
    }
}
