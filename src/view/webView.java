package view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Canvas;
import model.ShapePosition;
import model.SnapShots;

public class webView {
  private File file;
  private Canvas canvas1;
//  private List<String> allSnaps = new ArrayList<>();
  private String path;
  private int xSize;
  private int ySize;

  public webView(String path, Canvas canvas1, int xSize, int ySize){
    this.file = new File(path);
    this.canvas1 = canvas1;
    this.path = path;
    this.xSize = xSize;
    this.ySize = ySize;
//    allSnaps = new ArrayList<>();
  }

  public String formatSVG(int xSize, int ySize) {
    String body = "";
    List<SnapShots> list = canvas1.getSnapshotsCollection();
    System.out.println("first print all snaps\n");
//    System.out.println(allSnaps.toString());
    System.out.println("\n");
    for (int ind = 0; ind < canvas1.getSnapshotsCollection().size(); ind++) {
      System.out.println(canvas1.getSnapshotsCollection().get(ind).getID() + "-No-" + ind);
      body += "<div>\n<h2>" + canvas1.getSnapshotsCollection().get(ind).getID();
      body += "</h2>\n";
      body += "<h4> Description:" + canvas1.getSnapshotsCollection().get(ind).getDescription() + "</h4>\n";

//    body += "<rect id = bg x=\"0\" y = \"0\" width=\"1100\" height=\"1100\" fill = rgb(137, 207, 240)> </rect>\n";
      body += "<svg width=\"" + this.xSize + "\" height = \"" + this.ySize + "\">\n";

//      body += "<rect id = bg x=\"0\" y = \"0\" width=\"1000\" height=\"1000\"  style=\"fill:rgb(137,207,240);stroke-width:5;stroke:rgb(255,0,0)\"> </rect>\n";
      body += "<rect id = bg x=\"0\" y = \"0\" width=\"" + this.xSize + "\" height=\""+ this.ySize+"\"  style=\"fill:rgb(137,207,240);stroke-width:5;stroke:rgb(255,0,0)\"> </rect>\n";
//    style="fill:rgb(0,0,255);stroke-width:3;stroke:rgb(0,0,0)
      for (ShapePosition sp : canvas1.getSnapshotsCollection().get(ind).getShapePositions()) {
//        System.out.println(canvas1.getSnapshotsCollection().get(ind).getShapePositions());
        System.out.println(sp);
        if (sp.getShape().getType().equalsIgnoreCase("rectangle")) {
          body += "<rect id=\"" + sp.getShape().getName() + "\" x=\"" + sp.getXPos() + "\" y=\"" + sp.getYPos()
                  + "\" width=\"" + sp.getShape().getXParam() + "\" height =\"" + sp.getShape().getYParam() + "\" fill=\"rgb"
                  + sp.getShape().getColor().toString() + "\">\n" + "</rect>\n";
        }

        if (sp.getShape().getType().equalsIgnoreCase("oval")) {
          body += "<ellipse id= \"" + sp.getShape().getName() + "\" cx=\"" + sp.getXPos() + "\" cy=\"" + sp.getYPos()
                  + "\" rx=\"" + sp.getShape().getXParam() + "\" ry =\"" + sp.getShape().getYParam() + "\" fill=\"rgb"
                  + sp.getShape().getColor().toString() + "\">\n" + "</ellipse>\n";
        }
      }
      body += "</svg>\n</div>\n";
//      allSnaps.add(body);
    }
//    System.out.printf(allSnaps.toString());
    return body;
  }

//    SnapShots snap = list.get(list.size()-1);
//    body += "<div>\n<h2>" + snap.getID();
//    body += "</h2>\n";
//    body += "<h4> Description:"+ snap.getDescription() +"</h4>\n";
//
////    body += "<rect id = bg x=\"0\" y = \"0\" width=\"1100\" height=\"1100\" fill = rgb(137, 207, 240)> </rect>\n";
//    body += "<svg width=\"1000\" height = \"1000\">\n";
//
//    body += "<rect id = bg x=\"0\" y = \"0\" width=\"1000\" height=\"1000\"  style=\"fill:rgb(137,207,240);stroke-width:5;stroke:rgb(255,0,0)\"> </rect>\n";
////    style="fill:rgb(0,0,255);stroke-width:3;stroke:rgb(0,0,0)
//    for (ShapePosition sp: canvas1.getAllShapes()){
//      if (sp.getShape().getType().equalsIgnoreCase("rectangle")){
//        body += "<rect id=\""+sp.getShape().getName() +"\" x=\"" + sp.getXPos()+"\" y=\"" +sp.getYPos()
//                +"\" width=\"" + sp.getShape().getXParam() +"\" height =\"" + sp.getShape().getYParam() + "\" fill=\"rgb"
//                + sp.getShape().getColor().toString() +"\">\n" +"</rect>\n";
//      }
//
//      if (sp.getShape().getType().equalsIgnoreCase("oval")){
//        body += "<ellipse id= \""+sp.getShape().getName() +"\" cx=\"" + sp.getXPos()+"\" cy=\"" +sp.getYPos()
//                +"\" rx=\"" + sp.getShape().getXParam() +"\" ry =\"" + sp.getShape().getYParam() + "\" fill=\"rgb"
//                + sp.getShape().getColor().toString() +"\">\n" +"</ellipse>\n";;
//      }
//    }
//    body += "</svg>\n</div>\n";
//    allSnaps.add(body);
//    return body;
//  }

  public String initialWeb(int xSize, int ySize){
    String header = "<!DOCTYPE html>\n<html>\n<body>\n";
//    System.out.printf(canvas1.getAllShapes().toString());
//    for (SnapShots snap: canvas1.getSnapshotsCollection()){
//      body += "<div>\n<h2>" + snap.getDescription() +"</h2>\n<svg width=\"1000\" height = \"1000\">\n";
//      for (ShapePosition sp: snap.getAllShapePosition()){
//        if (sp.getShape().getType().equalsIgnoreCase("rectangle")){
//          body += "<rect id=\""+sp.getShape().getName() +"\" x=\"" + sp.getXPos()+"\" y=\"" +sp.getYPos()
//                  +"\" width=\"" + sp.getShape().getXParam() +"\" height =\"" + sp.getShape().getYParam() + "\" fill=\"rgb"
//                  + sp.getShape().getColor().toString() +"\">\n" +"</rect>\n";
//        }
//        if (sp.getShape().getType().equalsIgnoreCase("oval")){
//          body += "<ellipse id= \""+sp.getShape().getName() +"\" cx=\"" + sp.getXPos()+"\" cy=\"" +sp.getYPos()
//                  +"\" rx=\"" + sp.getShape().getXParam() +"\" ry =\"" + sp.getShape().getYParam() + "\" fill=\"rgb"
//                  + sp.getShape().getColor().toString() +"\">\n" +"</ellipse>\n";;
//        }
//      }
//      body += "</svg>\n</div>\n";
//    }


//    System.out.printf(snap.toString());

//    body += "</svg>\n</div>\n";
    String body = formatSVG(xSize,ySize);
    System.out.printf(body);
    header += body;
//    for (String s: allSnaps){
//      header += s;
////      System.out.printf(s +"\n");
//    }

    header += "</body>\n</html>\n";
//    System.out.printf(header);
    try {
//      File output = new File(
//              "/Users/zhangkaiqi/Documents/NEU-MSCS-Align/CS5004/HWs/Assignment9-V2/src/view/web-old.html");
      File output = new File(path);
      BufferedWriter writer = new BufferedWriter(new FileWriter(output));
      writer.write(header);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return header;
  }
}


