package Objects;

import java.util.ArrayList;
import java.util.List;

public class FaceMap {
    List<Face> map;

    public FaceMap(){
        this.map = new ArrayList<>();
    }
    public List<Face> getMap(){
        return map;
    }

    public boolean addFace(Vertex v1, Vertex v2, Vertex v3){
               Face f = new Face(v1, v2, v3);
        System.out.println("added" );
               return this.map.add(f);
    }

}
