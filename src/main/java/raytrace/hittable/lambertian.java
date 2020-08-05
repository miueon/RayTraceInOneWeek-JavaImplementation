package raytrace.hittable;

import raytrace.color;
import raytrace.ray;
import raytrace.vec3;

public class lambertian implements material {
    public color albedo;

    public lambertian(color a) {
        albedo = new color(a);
    }

    // a ray hit lambertian material will either be reflected or absorbed or, just mixed up

    @Override
    public boolean scatter(ray r_in, hit_record rec, color attenuation, ray scattered) {

        vec3 scatter_direction = vec3.Plus(rec.getNormal(), vec3.random_unit_vector());
        scattered.copyFrom(new ray(rec.p, scatter_direction));
        attenuation.copyFrom(albedo);
        return scattered.direction.dot(rec.normal) > 0;

    }


}
