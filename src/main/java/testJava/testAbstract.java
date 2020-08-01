package testJava;

public class testAbstract {
    //A b = new B(10);
}

abstract class A {
    public int a;

    public A() {
        a = 0;
    }
    public A(int a) {
        this.a = a;
    }
}

class B extends A {

}
