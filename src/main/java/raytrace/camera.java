package raytrace;

public class camera {
    private point3 origin;
    private point3 lower_left_corner;
    private vec3 horizontal;
    private vec3 vertical;

    public camera() {
        var aspect_ratio = 16.0 / 9.0;
        var viewport_height = 2.0;
        var viewport_width = aspect_ratio * viewport_height;
        var focal_length = 1.0;

        origin = new point3(0, 0, 0);
        horizontal = new vec3(viewport_width, 0, 0);
        vertical = new vec3(0, viewport_height, 0);

        lower_left_corner = new point3(
                new point3(origin)
                .minus(vec3.Divide(horizontal, 2))
                .minus(vec3.Divide(vertical, 2))
                .minus(new vec3(0, 0, focal_length))); // canvas start point
        //System.out.println("lowerLeftCorner" + lower_left_corner);
    }

    public ray get_ray(double u, double v) {
        vec3 h = new vec3(horizontal);
        vec3 ve = new vec3(vertical);
        vec3 llc = new vec3(lower_left_corner);
        return new ray(origin,
                llc.plus(h.multiply(u)).plus(ve.multiply(v)).minus(origin));
    }
}
