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

        CC c = new CC();
        
        ref(c);
        System.out.println(c.a);
    }

    static void change(AA aa) {
        aa = new CC();
    }

    static void ref(CC cc) {
        cc.copyfrom(100);
    }
}

class AA {
    int a = 1;

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

    public void copyfrom(int val) {
        this.a = val;
    }
}
