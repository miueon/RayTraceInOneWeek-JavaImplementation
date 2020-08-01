import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class testPPM {
   static final int image_width = 256;
   static final int image_height = 256;


    public static void main(String[] args)throws IOException {
        File f = new File("text.ppm");
        System.out.println(f.getAbsolutePath());
        f.createNewFile();
        try (OutputStream outputStream = new FileOutputStream(f)) {
            String testStr = "P3\n" + image_width + " " + image_height + "\n255\n";
            outputStream.write(testStr.getBytes());

            for (int j = image_height - 1; j >= 0; j--) {
                System.out.print("\rScanlines remaining: " + j + " ");
                for (int i = 0; i < image_width; i++) {
                    color pixel = new color(((double) i) / (image_width + 1),
                            ((double) j) / (image_height - 1), 0.25);
                    outputStream.write(pixel.write_color().getBytes());
                }
            }
        } catch (IOException e) {

        }
        System.out.println("Done");
    }

    public static color ray_color(final ray r) {
        vec3 unit_direction = vec3.Unit_vector(r.getDirection());
        var t = 0.5 * (unit_direction.y() + 1.0);
        return new color(vec3.Plus(
                new color(1.0, 1.0,1.0).multiply(1.0-t),
                new color(0.5, 0.7,1.0).multiply(t)
                ));
    }
}
