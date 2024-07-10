package Objects;

import java.util.ArrayList;
import java.util.List;

public class VertexMap {

    List<Vertex> map;

    public VertexMap (){
        this.map = new ArrayList<>();
    }


    /**
     *
     * @param x
     * @param y
     * @param z
     * @return true/false depending if a line can be created
     */
    public boolean addVertex(double x, double y, double z){
        int id = map.size() + 1;
        Vertex v = new Vertex(id, x, y, z);
        map.add(v);
        return map.size() > 1;
    }

    public boolean addVertex(Vertex v){
        map.add(v);
        return map.size() > 1;
    }
    public List<Vertex> getMap(){
        return map;
    }


}
