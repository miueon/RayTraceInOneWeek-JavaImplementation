package raytrace;

public  class vec3 {

    public double[] e;

    public vec3() {
        e = new double[]{0, 0, 0};
    }

    public vec3(double e0, double e1, double e2) {
        e = new double[]{e0, e1, e2};
    }

    public vec3(final vec3 a) {
        this(a.e[0], a.e[1], a.e[2]);
    }

    public double x() {
        return e[0];
    }

    public double y() {
        return e[1];
    }

    public double z() {
        return e[2];
    }

    public vec3 negative() {
        for (int i = 0; i < e.length; i++) {
            e[i] = -e[i];
        }
        return this;
    }

    public double getByIndex(int i) {
        return e[i];
    }

    public double setByIndex(int i, double value) {
        e[i] = value;
        return e[i];
    }

    public vec3 plus(vec3 vec3) {
        for (int i = 0; i < e.length; i++) {
            e[i] += vec3.e[i];
        }
        return this;
    }

    public vec3 multiply(double value) {
        for (int i = 0; i < e.length; i++) {
            e[i] *= value;
        }
        return this;
    }

    public vec3 minus(vec3 v) {
        return plus(v.negative());
    }

    public vec3 divide(double t)throws IllegalArgumentException {
        if (t == 0) {
            throw new IllegalArgumentException("t can't be 0");
        }
        return this.multiply(1/t);
    }

    public double length() {
        return Math.sqrt(length_squared());
    }

    public double length_squared() {
        return e[0] * e[0] + e[1] * e[1] + e[2] * e[2];
    }

    public double dot(vec3 u) {
        double sum = 0;
        for (int i = 0; i < e.length; i++) {
            sum += e[i] * u.e[i];
        }
        return sum;
    }

    public static vec3 Plus(final vec3 u, final vec3 v) {
        return new vec3(u.e[0] + v.e[0], u.e[1] + v.e[1], u.e[2] + v.e[2]);
    }

    public static vec3 Minus(final vec3 u, final vec3 v) {
        return new vec3(u.e[0] - v.e[0], u.e[1] - v.e[1], u.e[2] - v.e[2]);
    }

    public static vec3 Multip(final vec3 u, final vec3 v) {
        return new vec3(u.e[0] * v.e[0], u.e[1] * v.e[1], u.e[2] * v.e[2]);
    }

    public static vec3 Multip(final vec3 u, double value) {
        return new vec3(u.e[0] * value, u.e[1] * value, u.e[2] * value);
    }

    public static vec3 Negative(final vec3 u) {
        vec3 temp = new vec3();
        for (int i = 0; i < temp.e.length; i++) {
            temp.e[i] = -u.e[i];
        }
        return temp;
    }

    public static vec3 Divide(final vec3 u, double value)throws IllegalArgumentException {
        if (value == 0) {
            throw new IllegalArgumentException("divider can't be 0");
        }
        return Multip(u, 1 / value);
    }


    public static vec3 Unit_vector(vec3 v) {
        return Divide(v, v.length());
    }
    @Override
    public String toString() {
        return this.e[0] + " " + this.e[1] + " " + this.e[2];
    }



}
