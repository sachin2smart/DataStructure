package in.sachinshinde.z_basicsjava.cloning;

public class TestCloneByAssignment {

    public static void main(String[] args) {
        Test ob1 = new Test();

        System.out.println(ob1.x + " " + ob1.y);

        // Creating a new reference variable ob2
        // pointing to same address as ob1
        Test ob2 = ob1;

        // Any change made in ob2 will
        // be reflected in ob1
        ob2.x = 100;

        System.out.println(ob1.x + " " + ob1.y);    // x = 100, y = 20
        System.out.println(ob2.x + " " + ob2.y);    // x = 100, y = 20
    }
}
