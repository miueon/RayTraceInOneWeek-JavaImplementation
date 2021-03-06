package raytrace;

public class ray {
    public point3 origin;
    public vec3 direction;

    public ray(){
        origin = new point3(0,0,0);
        direction = new vec3();
    }

    public ray(final point3 origin, final vec3 direction) {
        this.origin = new point3(origin);
        this.direction = new vec3(direction);
    }

    public void copyFrom(ray r) {
        this.origin.copyFrom(r.origin);
        this.direction.copyFrom(r.direction);

    }

    public point3 getOrigin() {
        return origin;
    }

    public vec3 getDirection() {
        return direction;
    }

    public point3 at(double t) {
        return new point3(vec3.Plus(vec3.Multip(direction, t), origin));
    }
}
