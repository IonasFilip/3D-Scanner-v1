import Objects.Face;
import Objects.Line;
import Objects.Vertex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GUI {
    JFrame frame;

    double zoom = 5;
    List<Vertex> vertexM;
    List<Line> lineM;
    List<Face> faceM;
    Container pane;
    JPanel renderer;

    double alfa = 0;
    double beta = 0;
    List<Vertex> vertexMap = new ArrayList<>();
    List<Vertex> transformedVertexMap = new ArrayList<>();
    public void runGUI() {
        frame = new JFrame();
        pane = frame.getContentPane();



        render(pane,null, null, null);


    }

    public Container getPane() {
        return pane;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setVertexMap(List<Vertex> vm){
        vertexMap = vm;
        transformedVertexMap = vm;
    }
    public void render(Container pane, List<Vertex> vertexMap, List<Line> lineMap, List<Face> faceMap){
        lineM = lineMap;
        faceM = faceMap;
        vertexM = vertexMap;
        renderer = new JPanel() {
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.BLACK);
                g2.fillRect(0, 0, getWidth(), getHeight());
                BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
                if (lineMap != null){
//                if (faceMap != null){
//                    for (Objects.Vertex v : vertexMap){
//                        transformedVertexMap.set(v.getId() - 1, Rendering3Dimensions.transform(v, alfa, beta));
//                    }
                    g2.translate(getWidth()/2, getHeight() / 2);
                    g2.setColor(Color.WHITE);

                    Map<Integer, Vertex> newPoints = new HashMap<>();
                    for (Vertex v : vertexMap){
                        newPoints.put(v.getId(),Rendering3Dimensions.transform(v, alfa, beta, zoom));
                    }

                    for (Line l : lineMap){
                        Path2D path = new Path2D.Double();
//                        Objects.Vertex v1 = Rendering3Dimensions.transform(l.getV1(), alfa, beta, zoom);
//                        Objects.Vertex v2 = Rendering3Dimensions.transform(l.getV2(), alfa, beta, zoom);
//                        path.moveTo(v1.getX(), v1.getY());
//                        path.lineTo(v2.getX(), v2.getY());
                        path.moveTo( newPoints.get(l.getPoint1()).getX() , newPoints.get(l.getPoint1()).getY());
                        path.lineTo( newPoints.get(l.getPoint2()).getX() , newPoints.get(l.getPoint2()).getY());
                        path.closePath();
                        g2.draw(path);
                    }
//                    for (Objects.Face f : faceMap){
////                        System.out.println(f);
//                        Objects.Vertex v1 = Rendering3Dimensions.transform(f.getV1(), alfa, beta, zoom);
//                        Objects.Vertex v2 = Rendering3Dimensions.transform(f.getV2(), alfa, beta, zoom);
//                        Objects.Vertex v3 = Rendering3Dimensions.transform(f.getV3(), alfa, beta, zoom);
//
//                        double V1x = v1.getX() + getWidth() / 2.0;
//                        double V2x = v2.getX() + getWidth() / 2.0;
//                        double V3x = v3.getX() + getWidth() / 2.0;
//                        double V1y = v1.getY() + getHeight() / 2.0;
//                        double V2y = v2.getY() + getHeight() / 2.0;
//                        double V3y = v3.getY() + getHeight() / 2.0;
//
//                        int minX = (int) Math.max(0, Math.ceil(Math.min(V1x, Math.min(V2x,V3x))));
//                        int maxX = (int) Math.min(renderer.getWidth()-1,Math.floor(Math.max(V1x, Math.max(V2x, V3x))));
////                        System.out.println(minX + " " +  maxX);
//                        int minY = (int) Math.max(0, Math.ceil(Math.min(V1y, Math.min(V2y, V3y))));
//                        int maxY = (int) Math.min(renderer.getHeight()-1,Math.floor(Math.max(V1y, Math.max(V2y, V3y))));
//
//                        for (int y = minY; y <= maxY; y++){
//                            for (int x = minX; x <= maxX; x++) {
//                                Objects.Vertex p = new Objects.Vertex(8008135, x, y, 0);
//
//                                boolean V1 = Rendering3Dimensions.sameSide(v1, v2, v3, p);
//                                boolean V2 = Rendering3Dimensions.sameSide(v2, v3, v1, p);
//                                boolean V3 = Rendering3Dimensions.sameSide(v3, v1, v2, p);
////                                if (V3 && V2 && V1){
////                                System.out.println(x + " " + y);
////                                RGBColor color = #fffdff;
//                                img.setRGB(x,y, -000001 );
////                                System.out.println(img.getRGB(x, y));
////                                    System.out.println("pixel set");
////                                }
//                            }
//                        }
//                        g2.drawImage(img, 100,100 ,null);
//                    }


                }

//                System.out.println( renderer.isFocusable());
                double oldZoom = 0.0;
                renderer.addMouseWheelListener(new MouseWheelListener() {
                    @Override
                    public void mouseWheelMoved(MouseWheelEvent e) {

                        zoom += e.getPreciseWheelRotation() / 10;
//                        System.out.println(zoom);
                        if (oldZoom - zoom > 1 || oldZoom - zoom < 1)
                        render(pane,vertexM, lineM, faceM);
                    }
                });

                renderer.addMouseMotionListener(new MouseMotionListener() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        double yi = 180.0 / renderer.getHeight();
                        double xi = 180.0 / renderer.getWidth();
//                        alfa =(int)( yi * e.getY());
                        beta = - (xi * e.getX() /13);
//
                        List<Vertex> vertexList = new ArrayList<>();
                        render(pane, vertexM, lineM, faceM);
//                        renderer.repaint();
                    }

                    @Override
                    public void mouseMoved(MouseEvent e) {

                    }
                });

            }



        };
        pane.add(renderer, BorderLayout.CENTER);
        frame.setSize(600,600);
        frame.setVisible(true);
    }

}
