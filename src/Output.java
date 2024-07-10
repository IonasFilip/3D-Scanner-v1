import Objects.Face;
import Objects.Vertex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Output {
    private File file = new File("src/output.txt");
    private FileWriter fw;

    public void output(List<Vertex> vertexList, List<Face> faceList){
        try {
            fw = new FileWriter("output.obj");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Vertex v: vertexList) {
//                System.out.println("Output" + "v " + v.getX() + " " + v.getY() + " " + v.getZ());
                bw.write("v " + v.getX() + " " + v.getY() + " " + v.getZ() + "\n");
                bw.flush();
            }
            for (Face f : faceList){
                bw.write("f " + f.getV1().getId() + " " + f.getV2().getId() + " " + f.getV3().getId() + "\n");
                bw.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
