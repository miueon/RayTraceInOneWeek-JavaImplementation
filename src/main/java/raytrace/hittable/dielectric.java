package raytrace.hittable;

import raytrace.color;
import raytrace.ray;
import raytrace.util.rtweekend;
import raytrace.vec3;

public class dielectric implements material {
    public double ref_idx;

    public dielectric(double ri) {
        ref_idx = ri;
    }

    @Override
    public boolean scatter(ray r_in, hit_record rec, color attenuation, ray scattered) {
        attenuation.copyFrom(new color(1.0, 1.0, 1.0));
        double etai_over_etat = rec.front_face? (1.0/ref_idx): ref_idx;
        vec3 unit_direction = vec3.Unit_vector(r_in.direction);

        double cos_theta = Math.min(new vec3(unit_direction).negative().dot(rec.normal), 1.0);
        double sin_theta = Math.sqrt(1 - cos_theta * cos_theta);
        if (etai_over_etat * sin_theta > 1.0) {
            vec3 reflected = vec3.reflect(unit_direction, rec.normal);
            scattered.copyFrom(new ray(rec.p, reflected));
            return true;
        }

        double reflect_prob = schlick(cos_theta, etai_over_etat);
        if (rtweekend.random_double() < reflect_prob) {
            vec3 reflected = vec3.reflect(unit_direction, rec.normal);
            scattered.copyFrom(new ray(rec.p, reflected));
            return true;
        }

        vec3 refracted = vec3.refract(unit_direction, rec.normal, etai_over_etat);
        scattered.copyFrom(new ray(rec.p, refracted));
        return true;
    }


}
