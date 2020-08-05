package raytrace;

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

    @Override
    public String toString() {
        return "point3: " + super.toString();
    }

    @Override
    public point3 plus(vec3 vec3) {
         super.plus(vec3);
         return this;
    }

    @Override
    public point3 multiply(double value) {
         super.multiply(value);
        return this;
    }

    @Override
    public point3 minus(vec3 v) {
         super.minus(v);
        return this;
    }

    @Override
    public point3 divide(double t) throws IllegalArgumentException {
         super.divide(t);
        return this;
    }
}
