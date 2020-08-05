package raytrace;

import raytrace.util.rtweekend;

public class color extends vec3 {
    public color(double e0, double e1, double e2) {
        super(e0,e1,e2);
    }

    public color(vec3 a) {
        super(a.e[0], a.e[1], a.e[2]);
    }

    @Override
    public color minus(vec3 v) {
        super.minus(v);
        return this;
    }

    @Override
    public color divide(double t) throws IllegalArgumentException {
         super.divide(t);
         return this;
    }

    @Override
    public color multiply(double value) {
        super.multiply(value);
        return this;
    }

    @Override
    public color plus(vec3 vec3) {
        super.plus(vec3);
        return this;
    }


    public String write_color() {

        float factor = (float) 255.99;
        int[] realColor = new int[3];
        for (int i = 0; i < realColor.length; i++) {
            realColor[i] = (int) (factor * this.e[i]);
        }

        return realColor[0] + " " + realColor[1] + " " + realColor[2] + "\n";
    }

    public String write_color(int samplesPerPixel) {
        var r = this.x();
        var g = this.y();
        var b = this.z();

        var scale = 1.0 / samplesPerPixel;
        // gama correct for gama=2.0
        r = Math.sqrt(scale * r);
        g = Math.sqrt(scale * g);
        b = Math.sqrt(scale * b);
        int factor = 256;

        int a = (int) (factor * rtweekend.clamp(r, 0.0, 0.999));
        int bb = (int) (factor * rtweekend.clamp(g, 0.0, 0.999));
        int c = (int) (factor * rtweekend.clamp(b, 0.0, 0.999));
        return a + " " + bb + " " + c + "\n";
    }

    public void copyFrom(color v) {
        for (int i = 0; i < e.length; i++) {
            e[i] = v.e[i];
        }
    }
}
