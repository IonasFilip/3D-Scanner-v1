//import com.fazecast.jSerialComm.*;

import Objects.FaceMap;
import Objects.LineMap;
import Objects.Vertex;
import Objects.VertexMap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class RenderAndBuild {
    VertexMap vertexMap;
    LineMap lineMap;
    FaceMap faceMap;
    GUI gui;
    Output o;
    public synchronized void RenderAndBuild() {
        vertexMap = new VertexMap();
        lineMap = new LineMap();
        faceMap = new FaceMap();
        gui = new GUI();
        o = new Output();

        List<Vertex> vertexList = vertexMap.getMap();


        gui.runGUI();

        boolean manual = false;

        System.out.println("Input coordinates manually y/n");
        Scanner s = new Scanner(System.in);
        String answer = s.nextLine();

        if (answer.contains("y")) {
            manual = true;
        }

        while (manual) {
            vertexList = vertexMap.getMap();

            System.out.println("New Objects.Vertex: ");
            String line = s.nextLine();
            String[] coordinates = line.split(" ");
            double distance = (Double.parseDouble(coordinates[0]) + Double.parseDouble(coordinates[1]) + Double.parseDouble(coordinates[2])) / 3.0;
            vertexMap.addVertex(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]), Double.parseDouble(coordinates[2]));
            gui.setVertexMap(vertexMap.getMap());
            System.out.println("Writing output");
//            o.output(vertexMap.getMap(), null);
            int curentId = vertexList.get(vertexList.size() - 1).getId();
            for (Vertex v : vertexMap.getMap()) {
                double point = (v.getY() + v.getZ() + v.getX()) / 3;
                System.out.println(point);
                System.out.println(distance);
                if (((distance - point < 100 && distance - point >= 0) || (distance - point > -100 && distance - point <= 0)) && v.getId() != curentId) {
                    System.out.println("Drawing line...");
                    vertexList = vertexMap.getMap();
                    if (lineMap.addLine(vertexList.get(vertexList.size() - 1), v)) {
                        System.out.println("Rendering...");

                        gui.render(gui.getPane(),vertexMap.getMap(), lineMap.getMap(), faceMap.getMap());
                    }
                }
            }


        }

        System.out.println("What test would you like to run: rendering/scanning");
        answer = s.nextLine();
        if (answer.equals("rendering")) {
            try {
                FileReader fr = new FileReader("test3.obj");
                BufferedReader br = new BufferedReader(fr);
                String lines = null;
                lines = br.readLine();
                String[] data = lines.split(" ");
                do {
//            for (int i = 0; i <= 2123; i++){
//                lines = br.readLine();
//                String[] data = lines.split(" ");
                    vertexMap.addVertex(Double.parseDouble(data[1]) * 100.0, Double.parseDouble(data[2]) * 100.0, Double.parseDouble(data[3]) * 100.0);

//                    double distance = (Double.parseDouble(data[1]) * 100.0 + Double.parseDouble(data[2]) * 100.0 + Double.parseDouble(data[3]) * 100.0) / 3.0;
                    gui.setVertexMap(vertexMap.getMap());
//                System.out.println("Writing output");
//                o.output(vertexMap.getMap(),null);
                    int curentId = vertexList.get(vertexList.size() - 1).getId();
//                for (Objects.Vertex v : vertexMap.getMap()){
//                    double point = (v.getY() + v.getZ() + v.getX())/3;
////                    System.out.println( " ----------------------- "+ i+ " -------------------------- ");
////                    System.out.println(point);
////                    System.out.println(distance);
//                    if( ((distance - point < 0.1 && distance-point >= 0) || (distance + point > -0.1 && distance+point <= 0))&& v.getId() != curentId){
//                        System.out.println("Drawing line...");
//                        vertexList = vertexMap.getMap();
//                        if (lineMap.addLine(vertexList.get(vertexList.size()-1), v)){
//                            System.out.println("Rendering...");
//
//
////                            wait(1);
//                        }
//                    }
//                }

                    /**
                     * creating lines nicely
                     */

                    if (curentId % 200 == 0) {
                        System.out.println("Drawing line...");
                        if (lineMap.addLine(vertexList.get(vertexList.size() - 1), vertexList.get(curentId - 200))) {
                            System.out.println("Rendering...");
                        }
                    }
                    if (curentId > 200) {
                        System.out.println("Drawing line...");
                        if (lineMap.addLine(vertexList.get(vertexList.size() - 1), vertexList.get(curentId - 201))) {
                            System.out.println("Rendering...");
                        }
                        if (lineMap.addLine(vertexList.get(vertexList.size() - 1), vertexList.get(curentId - 200))) {
                            System.out.println("Rendering...");
                        }
                        if (lineMap.addLine(vertexList.get(vertexList.size() - 1), vertexList.get(curentId - 199))) {
                            System.out.println("Rendering...");
                        }
                    }

                    if (curentId != 1) {
                        if (lineMap.addLine(vertexList.get(vertexList.size() - 1), vertexList.get(vertexList.size() - 2))) {
                            System.out.println("Rendering...");
                        }
                    }

                    /**
                     * Creating faces
                     */
                    System.out.println("doing something");
                    System.out.println(curentId > 200 && curentId % 10 != 1);
                    if (curentId > 200 && curentId % 10 != 1) {
                        faceMap.addFace(vertexList.get(curentId - 1), vertexList.get(curentId - 2), vertexList.get(curentId - 201));
                        faceMap.addFace(vertexList.get(curentId - 1), vertexList.get(curentId - 200), vertexList.get(curentId - 201));
                    }

                    lines = br.readLine();
                    data = lines.split(" ");
                } while (data[0].equals("v"));
//                while (data[0].equals("vn"))
                o.output(vertexMap.getMap(), faceMap.getMap());
                gui.render(gui.getPane(),vertexMap.getMap(), lineMap.getMap(), faceMap.getMap());
                while (true) {
                    System.out.println("New Objects.Vertex: ");
                    String line = s.nextLine();
                }

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//        catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        }else{

            for (int i = 0; i <= 100; i ++){
                for (int j = 0; j <= 100; j ++){
                    Vertex v = CoordinateTransformation.transform(j, i, Math.random() * 1 + 180, 200.0, vertexMap.getMap());
                    vertexMap.addVertex(v);
                    gui.setVertexMap(vertexMap.getMap());
                    int curentId = vertexList.get(vertexList.size() - 1).getId();
                    if (curentId % 200 == 0) {
                        System.out.println("Drawing line...");
                        if (lineMap.addLine(vertexList.get(vertexList.size() - 1), vertexList.get(curentId - 200))) {
                            System.out.println("Rendering...");
                        }
                    }
                    if (curentId > 200) {
                        System.out.println("Drawing line...");
                        if (lineMap.addLine(vertexList.get(vertexList.size() - 1), vertexList.get(curentId - 201))) {
                            System.out.println("Rendering...");
                        }
                        if (lineMap.addLine(vertexList.get(vertexList.size() - 1), vertexList.get(curentId - 200))) {
                            System.out.println("Rendering...");
                        }
                        if (lineMap.addLine(vertexList.get(vertexList.size() - 1), vertexList.get(curentId - 199))) {
                            System.out.println("Rendering...");
                        }
                    }

                    if (curentId != 1) {
                        if (lineMap.addLine(vertexList.get(vertexList.size() - 1), vertexList.get(vertexList.size() - 2))) {
                            System.out.println("Rendering...");
                        }
                    }


//                    /**
//                     * Creating faces
//                     */
//                    System.out.println("doing something");
//                    System.out.println(curentId > 200 && curentId % 10 != 1);
//                    if (curentId > 200 && curentId != 1) {
//                        faceMap.addFace(vertexList.get(curentId - 1), vertexList.get(curentId - 2), vertexList.get(curentId - 201));
//                        faceMap.addFace(vertexList.get(curentId - 1), vertexList.get(curentId - 200), vertexList.get(curentId - 201));
//                    }
                }
            }
            gui.render(gui.getPane(), vertexMap.getMap() ,lineMap.getMap(), faceMap.getMap());

//            System.out.println(vertexMap.getMap().size());
//            o.output(vertexMap.getMap(), faceMap.getMap());
//
//
//            List<String> scannerCalibration = new ArrayList<>();
////            int j = 0;
//
//             SerialPort comPort = SerialPort.getCommPort("COM9");
//            comPort.openPort();
//            comPort.addDataListener(new SerialPortDataListener() {
//                @Override
//                public int getListeningEvents() {
//                    return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
//                }
//                int j = 0;
//                String line = "";
//                @Override
//                public void serialEvent(SerialPortEvent serialPortEvent) {
//                    if (serialPortEvent.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE){
//                        return;}
//                    byte[] newData = new byte[comPort.bytesAvailable()];
//                    int numRead = comPort.readBytes(newData, newData.length);
////                    System.out.println("Read " + numRead + " bytes.");
//                    for (int i = 0; i < newData.length; ++i) {
//
//
//                        System.out.print((char) newData[i]);
//                        line += ((char) newData[i]);
//                    }
////                    System.out.println(line);
////                    scannerCalibration.set(j, line);
////                    j++;
//                }
//            });
        }

    }

}
