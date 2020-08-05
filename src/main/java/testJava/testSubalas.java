package testJava;

public class testSubalas {

    public static void main(String[] args) {
        bean a = new bean(10);
        bean b = new bean(20);

        sphere s = new sphere(a);
        rec r = new rec(b);
        System.out.println("sphere s.bean: " + s.mat.a);
        System.out.println("rec .bean: " + r.mat.a);
        
        s.change(r);
        System.out.println("now rec : " + r.mat.a);
    }
}

class bean{
    public int a;

    public bean(int value) {
        a = value;
    }
}

class sphere {
    public bean mat;

    public sphere(bean a) {
        this.mat =a;
    }

    public void change(rec r) {
        r.mat = mat;
    }
}

class rec {
    public bean mat;

    public rec(bean b) {
        this.mat = b;
    }
}
