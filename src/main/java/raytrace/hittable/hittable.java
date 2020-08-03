package raytrace.hittable;

import raytrace.ray;

public abstract class hittable {
    public abstract boolean hit(final ray r, double t_min, double t_max, hit_record rec);
}
