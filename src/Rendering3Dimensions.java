import Objects.Vertex;

public class Rendering3Dimensions {

    public static Vertex transform(Vertex v, double alfa, double beta, double zoom){
        if (zoom < 0){
            zoom = 0;
        }
        double x = zoom * ( v.getX() * Math.cos(beta) + v.getY() * Math.sin(alfa)* Math.sin(beta) + v.getZ() * Math.sin(beta) * Math.cos(alfa));
        double y = zoom *( v.getY() * Math.cos(alfa) + v.getZ() * (-1) * Math.sin(alfa));
        double z =zoom * ( v.getX() * Math.sin(beta) * (-1) + v.getY() *Math.sin(alfa) * Math.cos(beta) + v.getZ() * Math.cos(alfa) * Math.cos(beta));
        Vertex vertex = new Vertex(v.getId(), x, y, z);
        return vertex;
    }

//    static boolean sameSide(Objects.Vertex v1, Objects.Vertex v2, Objects.Vertex v3, Objects.Vertex p){
//        Objects.Vertex V1V2 = new Objects.Vertex(8008, v2.getX() - v1.getX(), v2.getY() - v1.getY(), v2.getZ() - v1.getZ());
//        Objects.Vertex V1V3 = new Objects.Vertex(8008, v3.getX() - v1.getX(), v3.getY() - v1.getY(), v3.getZ() - v1.getZ());
//        Objects.Vertex V1P = new Objects.Vertex(8008, p.getX() - v1.getX(), p.getY() - v1.getY(), p.getZ() - v1.getZ());
//
//        double V1V2CrossV1V3 = V1V2.getX() * V1V3.getY()- V1V3.getX()* V1V2.getY();
//        double V1V2CrossP = V1V2.getX() * V1P.getY() - V1P.getX() * V1V2.getY();
//
//        return V1V2CrossV1V3 * V1V2CrossP >= 0;
//    }


}
