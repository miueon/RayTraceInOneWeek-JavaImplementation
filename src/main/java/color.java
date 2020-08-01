public class color extends vec3 {
    public color(double e0, double e1, double e2) {
        super(e0,e1,e2);
    }

    public color(vec3 a) {
        super(a.e[0], a.e[1], a.e[2]);
    }
    public String write_color() {
        float factor = (float) 255.99;
        int[] realColor = new int[3];
        for (int i = 0; i < realColor.length; i++) {
            realColor[i] = (int) (factor * this.e[i]);
        }

        return realColor[0] + " " + realColor[1] + " " + realColor[2] + "\n";
    }
}
