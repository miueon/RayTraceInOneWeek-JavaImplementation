package raytrace;

import raytrace.util.rtweekend;

import java.util.Comparator;

public class camera {
    private point3 origin;
    private point3 lower_left_corner;
    private vec3 horizontal;
    private vec3 vertical;
    private vec3 u;
    private vec3 v;
    private vec3 w;
    double len_radius;

    // vfov is vertical field-of-view in degrees
    public camera(
                  point3 lookFrom,
                  point3 lookAt,
                  vec3 vup,
                  double vfov,
                  double aspect_ratio,
                  double aperture,
                  double foucus_dist
                  ) {
        var theta = rtweekend.degrees_to_radians(vfov);
        var h = Math.tan(theta / 2);
        var viewport_height = 2.0 * h;
        var viewport_width = aspect_ratio * viewport_height;


        w = vec3.Unit_vector(vec3.Minus(lookFrom, lookAt));
         u = vec3.Unit_vector(vec3.Cross(vup, w));
         v = vec3.Cross(w, u);

        origin = new point3(lookFrom);
//        origin = new point3(0, 0, 0);
        horizontal = new vec3(u).multiply(viewport_width).multiply(foucus_dist);
        vertical = new vec3(v).multiply(viewport_height).multiply(foucus_dist);

        lower_left_corner = new point3(
                new point3(origin)
                        .minus(vec3.Divide(horizontal, 2))
                        .minus(vec3.Divide(vertical, 2))
                        .minus(new vec3(w).multiply(foucus_dist))); // canvas start point
        len_radius = aperture/2;
       // lower_left_corner = new point3(-4.23, 1.755, 0.72);
        //System.out.println("lowerLeftCorner" + lower_left_corner);
    }

    public ray get_ray(double s, double t) {
        final vec3 rd = vec3.random_in_unit_disk().multiply(len_radius);
        final vec3 offset = vec3.Multip(u, rd.x()).plus(vec3.Multip(v, rd.y()));

        vec3 h = new vec3(horizontal);
        vec3 ve = new vec3(vertical);
        vec3 llc = new vec3(lower_left_corner);
        return new ray(new point3(vec3.Plus(offset, origin)),
                llc.plus(h.multiply(s)).plus(ve.multiply(t)).minus(origin).minus(offset));
    }

    @Override
    public String toString() {
        return "origin: " + origin +"\nllc: " + lower_left_corner + "\nhorizental: " + horizontal
                + "\nvertical: " + vertical;
    }
}
