package raytrace;

import raytrace.hittable.hit_record;
import raytrace.hittable.sphere;
import raytrace.util.rtweekend;

public class vec3 {

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
        return plus(Negative(v));
    }

    public vec3 divide(double t) throws IllegalArgumentException {
        if (t == 0) {
            throw new IllegalArgumentException("t can't be 0");
        }
        return this.multiply(1 / t);
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

    public static vec3 Cross(final vec3 u, final vec3 v) {
        return new vec3(u.e[1] * v.e[2] - u.e[2] * v.e[1],
                u.e[2] * v.e[0] - u.e[0] * v.e[2],
                u.e[0] * v.e[1] - u.e[1] * v.e[0]);
    }

    public static vec3 reflect(final vec3 v, final vec3 n) {
        return vec3.Minus(v, vec3.Multip(n, 2 * v.dot(n)));
    }

    public static vec3 refract(final vec3 uv, final vec3 n, double etai_over_etat) {
        // etai_over_eta is n'/n;
        // uv is income ray unit vector;
        //
        var cos_theta = new vec3(uv).negative().dot(n);
        vec3 r_out_perp = Plus(uv, Multip(n, cos_theta)).multiply(etai_over_etat);
        vec3 r_out_parallel = Multip(n,
                -Math.sqrt(1.0 - r_out_perp.length_squared()));
        return r_out_parallel.plus(r_out_perp);
    }

    public void copyFrom(vec3 v) {
        for (int i = 0; i < e.length; i++) {
            e[i] = v.e[i];
        }
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

    public static vec3 Divide(final vec3 u, double value) throws IllegalArgumentException {
        if (value == 0) {
            throw new IllegalArgumentException("divider can't be 0");
        }
        return Multip(u, 1 / value);
    }

    public static vec3 random() {
        return new vec3(rtweekend.random_double(), rtweekend.random_double(), rtweekend.random_double());
    }

    public static vec3 random(double min, double max) {
        return new vec3(rtweekend.random_double(min, max),
                rtweekend.random_double(min, max),
                rtweekend.random_double(min, max));
    }

    public static vec3 random_in_unit_sphere() {
        while (true) {
            var p = random(-1, 1);
            if (p.length_squared() >= 1) {
                continue;
            }

            return p; // p can be seen as a vector from (0,0,0), but lies on unit sphere
        }
    }

    public static vec3 random_unit_vector() {
        var a = rtweekend.random_double(0, rtweekend.pi * 2);
        var z = rtweekend.random_double(-1, 1);
        var r = Math.sqrt(1 - z * z);
        return new vec3(r * Math.cos(a), r * Math.sin(a), z);
    }

    public static vec3 random_in_hemisphere(final vec3 normal) {
        vec3 in_unit_sphere = random_in_unit_sphere();
        if (in_unit_sphere.dot(normal) > 0.0) {
            return in_unit_sphere;
        } else {
            return in_unit_sphere.negative();
        }
    }

    public static vec3 random_in_unit_disk() {
        while (true) {
            var p = new vec3(rtweekend.random_double(-1, 1), rtweekend.random_double(-1, 1), 0);
            if (p.length_squared() >= 1) {
                continue;
            }

            return p;
        }
    }
    public static vec3 Unit_vector(vec3 v) {
        return Divide(v, v.length());
    }

    @Override
    public String toString() {
        return this.e[0] + " " + this.e[1] + " " + this.e[2];
    }

    public static void main(String[] args) {
        hit_record rec = new hit_record();
        /*sphere s = new sphere(new point3(0, 0, -1), 0.5);
        camera cam = new camera();
        boolean loop = true;
        for (int j = 0; j < 255 && loop; j++) {
            for (int i = 0; i < 400; i++) {
                double u = j / 255.0;
                double v = i / 400.0;
                ray r = cam.get_ray(u, v);
                if (s.hit(r, 0, rtweekend.infinity, rec)) {
                    System.out.println(rec.getP());
                    System.out.println(rec.getNormal());
                    vec3 tN = vec3.Minus(s.center, rec.getP());
                    double test = tN.dot(rec.getNormal()) / (tN.length()  * rec.getNormal().length());
                    
                    loop = false;
                    break;
                }
            }
        }
        vec3 p =random_in_hemisphere(rec.getNormal());
        System.out.println(p);
        vec3 target = vec3.Plus(rec.getP(), p);
        
        System.out.println(target.minus(rec.getP()));*/


        //vec3 wtf = new vec3(0, 0, -1);
/*
        vec3 vup = new vec3(0, 1, 0);
        vec3 lookFrom = new vec3(-2, 2, 1);
        vec3 lookAt = new vec3(0, 0, -1);

        var w = Unit_vector(new vec3(lookFrom).minus(lookAt));
        var u = Unit_vector(Cross(vup, w));
        var v = Cross(w, u);

        point3 origin = new point3(lookFrom);

        camera cam = new camera(new point3(lookFrom), new point3(lookAt), vup, 90, 16.0 / 9.0);
        

        ray test = cam.get_ray(1, 1);
        var linus = Minus(lookFrom, lookAt);
        System.out.println("minus: " + linus);
        var wTest = vec3.Unit_vector(vec3.Minus(lookFrom, lookAt));
        System.out.println("wTest: " + wTest);
        System.out.println("cam: " + cam);
        System.out.println("ray org: " + test.origin);
        System.out.println("ray dire: " + test.direction);*/
    }


}
