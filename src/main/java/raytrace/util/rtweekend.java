package raytrace.util;

public  class rtweekend {
    public static double infinity = Double.POSITIVE_INFINITY;
    public static double pi = 3.1415926535897932385;

    public static double degrees_to_radians(double degrees) {
        return degrees * pi / 180.0;
    }

    public static double random_double() {
        return Math.random();
    }

    public static double random_double(double min, double max) {
        return min + (max-min) * random_double();
    }

    public static double clamp(double x, double min, double max) {
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }
}
