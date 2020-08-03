package testJava;

public class testInheritance {
    public static void main(String[] args) {
        AA a = new BB();
        System.out.println(a.run());
        change(a);
        System.out.println(a.run());
        double infinity = Double.POSITIVE_INFINITY;
        System.out.println(infinity);
        double test = 10.0;
        System.out.println(test < infinity);
    }

    static void change(AA aa) {
        aa = new CC();
    }
}

class AA {

    public AA() {

    }
    public int run() {
        return 10;
    }
}

class BB extends AA {
    public int run() {
        return 20;
    }
}

class CC extends AA {
    public int run() {
        return 30;
    }
}
