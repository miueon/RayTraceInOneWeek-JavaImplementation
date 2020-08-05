package raytrace;

import raytrace.hittable.*;
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
        final int image_width = 1200;
        final var aspect_ratio = 3.0/2.0;
        final int image_height = (int) (image_width / aspect_ratio);
        final int samples_per_pixel = 10;
        final int max_depth = 50;

        long start = System.currentTimeMillis();
        // World
        hittable_list world = random_scene();


        point3 lookFrom = new point3(13, 2, 3);
        point3 lookAt = new point3(0, 0, 0);
        vec3 vup = new vec3(0, 1, 0);
        var dist_to_focus = 10.0;
        var apeture = 0.1;

        // Camera
        camera cam = new camera(lookFrom, lookAt, vup,20, aspect_ratio, apeture, dist_to_focus);

       // System.out.println("test unit" + vec3.random_in_unit_sphere());
        System.out.println("start");
        // Render
        try (OutputStream outputStream = new FileOutputStream(f)) {
            String testStr = "P3\n" + image_width + " " + image_height + "\n255\n";
            outputStream.write(testStr.getBytes());

            for (int j = image_height - 1; j >= 0; j--) {
               // System.out.print("\rScanlines remaining: " + j + " ");
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Done");
        long end = System.currentTimeMillis();
        float time = (float) (((float)(end - start) )/ 1000.0);
        System.out.println("time elapsed: " + time + " sec");
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

            ray scattered = new ray();
            color attenuation = new color(0,0,0);
            if (rec.material.scatter(r, rec, attenuation, scattered)) {
                return new color(
                        vec3.Multip(attenuation, ray_color(scattered, world, depth - 1)));
            }
            return new color(0, 0, 0);


            // 怀疑: 递归过多层的脱裤放屁(a+b-a) 的随机生成代码会被缓存或者优化
           // ray test = new ray(rec.getP(), target.minus(rec.getP()));
/*
            return ray_color(new ray(rec.getP(), vec3.random_in_hemisphere(rec.getNormal())),
                    world
                    , depth-1)
                    .multiply(0.5);*/
        }
        vec3 unit_direction = vec3.Unit_vector(r.direction);
        var t = 0.5*(unit_direction.y() + 1.0);
        return new color(1, 1, 1).multiply(1.0 - t).plus(
                new color(0.5, 0.7, 1.0).multiply(t));

        /* return new color(1, 1, 1);*/
    }

    public static hittable_list random_scene() {
        hittable_list world = new hittable_list();

        var ground_material = new lambertian(new color(0.5, 0.5, 0.5));
        world.add(new sphere(new point3(0, -1000, 0), 1000, ground_material));
        for (int a = -11; a < 11; a++) {
            for (int b = -11; b < 11; b++) {
                var choose_mat = rtweekend.random_double();
                point3 center = new point3(a + 0.9 * rtweekend.random_double(),
                        0.2, b + 0.9 * rtweekend.random_double());

                if ((new point3(4, 0.2, 0).minus(center)).length() > 0.9) {
                    material sphere_material;

                    if (choose_mat < 0.8) {
                        var albedo = vec3.Multip(vec3.random(), vec3.random());
                        sphere_material = new lambertian(new color(albedo));
                        world.add(new sphere(center, 0.2, sphere_material));
                    } else if (choose_mat < 0.95) {
                        var albedo = vec3.random(0.5, 1);
                        var fuzz = rtweekend.random_double(0, 0.5);
                        sphere_material = new metal(new color(albedo), fuzz);
                        world.add(new sphere(center, 0.2, sphere_material));
                    } else {
                        sphere_material = new dielectric(1.5);
                        world.add(new sphere(center, 0.2, sphere_material));
                    }
                }
            }
        }
        var material1 = new dielectric(1.5);
        world.add(new sphere(new point3(0,1,0), 1.0, material1));

        var material2 = new lambertian(new color(0.4, 0.2, 0.1));
        world.add(new sphere(new point3(-4,1,0), 1.0, material2));

        var material3 = new metal(new color(0.7, 0.6, 0.5), 0);

        world.add(new sphere(new point3(4, 1, 0), 1.0, material3));

        return world;

    }



}
