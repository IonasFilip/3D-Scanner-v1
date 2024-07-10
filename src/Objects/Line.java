package Objects;

public class Line {
    private Vertex v1;
    private Vertex v2;
    private int point1;
    private int point2;

    public Line(Vertex v1, Vertex v2) {
        this.v1 = v1;
        this.v2 = v2;
        this.point1 = v1.getId();
        this.point2 = v2.getId();
    }

    public Vertex getV1() {
        return v1;
    }

    public Vertex getV2() {
        return v2;
    }

    public int getPoint1() {
        return point1;
    }

    public int getPoint2() {
        return point2;
    }
}
