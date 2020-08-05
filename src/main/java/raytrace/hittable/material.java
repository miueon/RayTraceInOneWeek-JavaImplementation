package raytrace.hittable;

import raytrace.color;
import raytrace.ray;

public interface material {
    public boolean scatter(final ray r_in, final hit_record rec, color attenuation, ray scattered);

    default public double schlick(double cosine, double ref_idx) {
        var r0 = (1 - ref_idx) / (1 + ref_idx);
        r0 = r0*r0;
        return r0 + (1 - r0) * Math.pow((1 - cosine), 5);
    }
}
