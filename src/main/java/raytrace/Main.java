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
        final int max_depth = 50;

        // World
        hittable_list world = new hittable_list();
        world.add(new sphere(new point3(0,0,-1), 0.5));
        world.add(new sphere(new point3(0,-100.5, -1), 100));


        // Camera
        camera cam = new camera();

       // System.out.println("test unit" + vec3.random_in_unit_sphere());

        // Render
        try (OutputStream outputStream = new FileOutputStream(f)) {
            String testStr = "P3\n" + image_width + " " + image_height + "\n255\n";
            outputStream.write(testStr.getBytes());

            for (int j = image_height - 1; j >= 0; j--) {
                System.out.print("\rScanlines remaining: " + j + " ");
                for (int i = 0; i < image_width; i++) {


                    color pixel = new color(0, 0, 0);
                    for (int s = 0; s < samples_per_pixel; s++) {
                        var u = (i + rtweekend.random_double()) / (image_width - 1);
                        var v = (j + rtweekend.random_double()) / (image_height - 1);
                        ray r = cam.get_ray(u, v);
                        // 用stream把所有的ray都集合在一起操作, 同时并行化
                        pixel.plus(ray_color(r, world, max_depth));
                    }

                    outputStream.write(
                            pixel.write_color(samples_per_pixel).getBytes());
                }
            }
        } catch (IOException e) {

        }
        System.out.println("Done");
    }

    public static color ray_color(final ray r, final hittable world, int depth) {
        hit_record rec = new hit_record();

        if (depth <= 0) {
            return new color(0, 0, 0);
        }

        if (world.hit(r, 0.001, rtweekend.infinity, rec)) {
           // point3 target = new point3(new vec3(rec.getP()).plus(rec.getNormal())
              //      .plus(vec3.random_in_unit_sphere()));
          /*  point3 target = new point3(new vec3(rec.getP()).plus(rec.getNormal())
                       .plus(vec3.random_unit_vector()));*/
            var tN = vec3.random_in_hemisphere(rec.getNormal());
            vec3 target = new vec3(rec.getP());
            target.plus(tN);
            // target = <center of unit circumscribed sphere >
            //               + <random vector which size less then 1>

            //return new color(1.0, 1.0, 1.0).plus(rec.getNormal()).multiply(0.5);

            /*System.out.println(depth);
            if (depth >= 49) {
                System.out.println("now the target is: " + target);
                System.out.println("hit point is: " + rec.getP());
                System.out.println();
            }*/

            //vec3.random_in_hemisphere(rec.getNormal())



            // 怀疑: 递归过多层的脱裤放屁(a+b-a) 的随机生成代码会被缓存或者优化
           // ray test = new ray(rec.getP(), target.minus(rec.getP()));

            return ray_color(new ray(rec.getP(), vec3.random_in_hemisphere(rec.getNormal())),
                    world
                    , depth-1)
                    .multiply(0.5);
        }
        vec3 unit_direction = vec3.Unit_vector(r.direction);
        var t = 0.5*(unit_direction.y() + 1.0);
        return new color(1, 1, 1).multiply(1.0 - t).plus(
                new color(0.5, 0.7, 1.0).multiply(t));

        /* return new color(1, 1, 1);*/
    }



}
