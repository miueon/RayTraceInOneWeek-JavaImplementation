package raytrace;

import raytrace.hittable.hit_record;
import raytrace.hittable.hittable;
import raytrace.hittable.hittable_list;
import raytrace.hittable.sphere;
import raytrace.util.rtweekend;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Main {


    public static void main(String[] args) throws IOException {
        File f = new File("text.ppm");
        //  System.out.println(f.getAbsolutePath());
        f.createNewFile();

        // image canvas
        final int image_width = 400;
        final var aspect_ratio = 16.0 / 9.0;
        final int image_height = (int) (image_width / aspect_ratio);
        final int samples_per_pixel = 100;

        // World
        hittable_list world = new hittable_list();
        world.add(new sphere(new point3(0,0,-1), 0.5));
        world.add(new sphere(new point3(0,-5, -11), 10));


        // Camera
        camera cam = new camera();


      /*  var viewport_height = 2.0;
        var viewport_width = aspect_ratio * viewport_height;
        var focal_length = 1.0;

        var origin = new point3(0, 0, 0);
        var horizontal = new vec3(viewport_width, 0, 0);
        var vertical = new vec3(0, viewport_height, 0);

        var lowerLeftCorner = new vec3(origin).minus(vec3.Divide(horizontal, 2))
                .minus(vec3.Divide(vertical, 2))
                .minus(new vec3(0, 0, focal_length)); // canvas start point*/

        // Render
        try (OutputStream outputStream = new FileOutputStream(f)) {
            String testStr = "P3\n" + image_width + " " + image_height + "\n255\n";
            outputStream.write(testStr.getBytes());

            for (int j = image_height - 1; j >= 0; j--) {
                System.out.print("\rScanlines remaining: " + j + " ");
                for (int i = 0; i < image_width; i++) {

                    /*var u = ((double) i) / (image_width - 1);
                    var v = ((double) j) / (image_height - 1);

                   *//* ray r = new ray(origin,
                            vec3.Plus(
                                    lowerLeftCorner, vec3.Multip(horizontal, u))
                            .plus(vec3.Multip(vertical, v))
                            .minus(origin));*//*


                    ray r = cam.get_ray(u, v);
                    color pixel = ray_color(r, world);*/
                    color pixel = new color(0, 0, 0);
                    for (int s = 0; s < samples_per_pixel; s++) {
                        var u = (i + rtweekend.random_double()) / (image_width - 1);
                        var v = (j + rtweekend.random_double()) / (image_height - 1);
                        ray r = cam.get_ray(u, v);
                        pixel.plus(ray_color(r, world));
                    }

                    outputStream.write(
                            pixel.write_color(samples_per_pixel).getBytes());
                }
            }
        } catch (IOException e) {

        }
        System.out.println("Done");
    }

    public static color ray_color(final ray r, final hittable world) {
        hit_record rec = new hit_record();
        if (world.hit(r, 0, rtweekend.infinity, rec)) {
            return new color(1.0, 1.0, 1.0).plus(rec.getNormal()).multiply(0.5);
        }
        vec3 unit_direction = vec3.Unit_vector(r.direction);
        var t = 0.5*(unit_direction.y() + 1.0);
        return new color(1.0, 1.0, 1.0).multiply(1.0 - t).plus(
                new color(0.5, 0.7, 1.0).multiply(t));

        /* return new color(1, 1, 1);*/
    }

    public static double hit_sphere(final point3 center, double radius, final ray r) {
        vec3 oc = vec3.Minus(r.origin, center);
        var a = r.direction.length_squared();
        var half_b = oc.dot(r.direction);
        var c = oc.length_squared() - radius * radius;
        var discriminant = half_b*half_b -  a * c;
        if (discriminant < 0) {
            return -1.0;
        } else {
            return (-half_b - Math.sqrt(discriminant)) / a;
        }
    }

}
