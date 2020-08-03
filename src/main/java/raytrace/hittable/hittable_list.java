package raytrace.hittable;

import raytrace.ray;

import java.util.ArrayList;
import java.util.List;

public class hittable_list extends hittable{
    public List<hittable> objects = new ArrayList<>();

    public hittable_list() {

    }
    public hittable_list(hittable object) {
        objects.add(object);
    }

    public void add(hittable object) {
        objects.add(object);
    }

    public void clear() {
       objects.clear();
    }

    @Override
    public boolean hit(ray r, double t_min, double t_max, hit_record rec) {
        hit_record temp_rec = new hit_record();
        boolean hit_anything = false;
        var closest_so_far = t_max;

        for (var object : objects) {
            if (object.hit(r, t_min, closest_so_far, temp_rec)) {
                hit_anything = true;
                closest_so_far = temp_rec.t;

            }
        }
        rec.copyFrom(temp_rec);
        return hit_anything;
    }
}
