package raytrace.hittable;

import raytrace.color;
import raytrace.ray;
import raytrace.vec3;

public class metal implements material {
    public color albedo;
    public double fuzz;

    public metal(final color a, double f) {
        albedo = new color(a);
        fuzz = f<1? f: 1;
    }
    @Override
    public boolean scatter(ray r_in, hit_record rec, color attenuation, ray scattered) {
        vec3 reflected = vec3.reflect(vec3.Unit_vector(r_in.direction), rec.normal);
        scattered.copyFrom(new ray(rec.getP(), vec3.random_in_unit_sphere().multiply(fuzz)
                .plus(reflected)));
        attenuation.copyFrom(albedo);
        return scattered.direction.dot(rec.normal) > 0;
    }
}
