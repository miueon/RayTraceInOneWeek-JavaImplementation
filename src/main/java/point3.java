public class point3 extends vec3 {
    public point3(double e0, double e1, double e2) {
        super(e0,e1,e2);
    }

    public point3(final point3 a) {
        super(a.e[0], a.e[1], a.e[2]);
    }

    public point3(final vec3 a) {
        super(a.e[0], a.e[1], a.e[2]);
    }
}
