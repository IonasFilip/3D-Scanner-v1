package Objects;

public class Vertex {
    private int id;
    private double x;
    private double y;
    private double z;

    public Vertex (int id, double x,double y,double z){
                this.id = id;
                this.x = x;
                this.y = y;
                this.z = z;
    }

    public int getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}

