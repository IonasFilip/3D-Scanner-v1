package Objects;

import java.util.ArrayList;
import java.util.List;

public class LineMap {
    private List<Line> map;

    public LineMap(){
        this.map = new ArrayList<>();
    }

    /**
     *
     * @param v1
     * @param v2
     * @return true if a line has been created
     */
    public boolean addLine(Vertex v1, Vertex v2){
        Line l = new Line(v1, v2);
        return map.add(l);
    }

    public void removeLine(Line l){
        map.remove(l);
    }

    public List<Line> getMap() {
        return map;
    }
}
