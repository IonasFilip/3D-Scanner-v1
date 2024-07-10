import Objects.Vertex;

import java.util.List;

public class CoordinateTransformation {

    public static Vertex transform(int motor1, int motor2, double distance, double R, List<Vertex> vertexList){
//        System.out.println(motor1 * 1.8);
        double degree = motor1 * 1.8;
        double z = (R-distance) * Math.cos(degree * 3.1415/180.0);
        double x = (R-distance) * Math.sin(degree * 3.1415/180.0);
        double y = motor2 * 0.1;
        return new Vertex(vertexList.size()+1, x, y, z);
    }

}
