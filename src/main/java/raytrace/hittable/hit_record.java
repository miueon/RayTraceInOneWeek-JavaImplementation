package raytrace.hittable;

import raytrace.point3;
import raytrace.ray;
import raytrace.vec3;

public class hit_record {
    point3 p;
    vec3 normal;
    double t;
    boolean front_face;

    public hit_record() {
        p = new point3(0,0,0);
        normal = new vec3();
    }

    public hit_record(point3 p, vec3 normal, double t) {
        this.p = p;
        this.normal = normal;
        this.t = t;
    }

    public void set_face_normal(final ray r, final vec3 outward_normal) {
        front_face = r.direction.dot(outward_normal) < 0;
        normal = front_face ? outward_normal:outward_normal.negative();
    }


    public point3 getP() {
        return p;
    }

    public void setP(point3 p) {
        this.p = p;
    }

    public vec3 getNormal() {
        return normal;
    }

    public void setNormal(vec3 normal) {
        this.normal = normal;
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }

    public boolean isFront_face() {
        return front_face;
    }

    public void setFront_face(boolean front_face) {
        this.front_face = front_face;
    }

    public void copyFrom(hit_record source) {
        setNormal(source.getNormal());
        setP(source.getP());
        setT(source.getT());
        setFront_face(source.isFront_face());
    }
}
