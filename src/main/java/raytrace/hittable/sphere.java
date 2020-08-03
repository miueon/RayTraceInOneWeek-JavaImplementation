package raytrace.hittable;

import raytrace.point3;
import raytrace.ray;
import raytrace.vec3;

public class sphere extends hittable {
    public point3 center;
    public double radius;

    public sphere(point3 center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public sphere() {

    }



    @Override
    public boolean hit(ray r, double t_min, double t_max, hit_record rec) {
        vec3 oc = vec3.Minus(r.origin, center);
        var a = r.direction.length_squared();
        var half_b = oc.dot(r.direction);
        var c = oc.length_squared() - radius*radius;
        var discriminant = half_b*half_b - a*c;

        if (discriminant > 0) {
            var root = Math.sqrt(discriminant);
            var temp = (-half_b -root)/a;

            // the temp is the t of hit vector

            if (temp < t_max && temp > t_min) {
                rec.t = temp;
                rec.p = r.at(rec.t); // hit point
               // rec.normal = (vec3.Minus(rec.p, center)).divide(radius);
                vec3 outward_normal = vec3.Minus(rec.p, center).divide(radius);
                rec.set_face_normal(r, outward_normal);
                return true;
            }

            temp = (-half_b + root) / a;
            if (temp < t_max && temp > t_min) {
                rec.t = temp;
                rec.p = r.at(rec.t); // hit point
               // rec.normal = (vec3.Minus(rec.p, center)).divide(radius);
                // normalization
                vec3 outward_normal = vec3.Minus(rec.p, center).divide(radius);
                rec.set_face_normal(r, outward_normal);
                return true;
            }
        }
        return false;
    }


}
