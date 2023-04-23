package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.swing.*;
import model.Canvas;
import model.Color;
import model.IShape;
import view.swingPhotoViewer;
import view.webView;

public class Controller implements IController{
  private Scanner in;
  private String[] input;

  public Controller(String[] args){
    this.input = args;
//    this.in = new Scanner(in);
  }

  @Override
  public void go() throws FileNotFoundException {
    //iterate through input,
    String inputFile = null;
    String viewType = null;
    String outputFileName = null;
    double xSize = 0;
    double ySize = 0;

    double xPos, yPos, width, height, r, g, b, newXPos, newYPos, newWidth, newHeight, newR, newG, newB;
    String ID, type;
    Canvas canvas1;
    Map<String, IShape> shapes = new HashMap<>();
    List<String> list = new ArrayList<String>(Arrays.asList(this.input));
    System.out.printf(list.toString());
//    try {
      Iterator<String> iterator = Arrays.stream(input).iterator();
      while (iterator.hasNext()){
        String command = iterator.next();
        if (command.equalsIgnoreCase("-in")){
          inputFile = iterator.next();
          System.out.println(inputFile);
          list.remove(command);
          list.remove(inputFile);
        }
        if (command.equalsIgnoreCase("-view") || command.equalsIgnoreCase("-v")){
          viewType = iterator.next();
          System.out.println(viewType);
          list.remove(command);
          list.remove(viewType);
        }
        if (command.equalsIgnoreCase("-out")){
          outputFileName = iterator.next();
          System.out.println(outputFileName);
          list.remove(command);
          list.remove(outputFileName);
        }
      }
      System.out.printf("test \n");
      System.out.printf(viewType +" view type here\n");
      System.out.printf(list.toString());
      if (!viewType.equalsIgnoreCase("web") && !viewType.equalsIgnoreCase("graphical")){
        throw new IllegalArgumentException("Illegal view type");
      }
      if (inputFile == null || viewType == null){
        throw new IllegalArgumentException("Illegal input or view type");
      }
      if (viewType.equalsIgnoreCase("web") && outputFileName == null){
        throw new IllegalArgumentException("You must specify output file path");
      }

      if (list.size() == 0 || list.size() == 1){
        xSize = 1000;
        ySize = 1000;
      } else if (list.size() == 2){
        try{
          xSize = Double.parseDouble(list.get(0));
          ySize = Double.parseDouble(list.get(1));

          if (xSize < 800 || ySize < 800){
            throw new IllegalArgumentException("Canvas size too small");
          }

        } catch (NumberFormatException e){
          System.out.println(e);
        }
      }
      System.out.printf(xSize +"\n");

    File file = new File(inputFile);
    if (file.exists()){
      System.out.printf("File exists\n");
    } else {
      System.out.printf("File does not exist\n");
    }
    try {
      Scanner in = new Scanner(file);
      canvas1 = new Canvas(xSize, ySize);


//      if (viewType.equalsIgnoreCase("web")){
//        webView view1 = new webView(outputFileName, canvas1);
//      }
//      webView view1 = new webView(outputFileName, canvas1);

//      swing swing1 = new swing(canvas1, xSize, ySize);
      while(in.hasNextLine()){
        String str = in.nextLine();
        if (str.startsWith("#")){
          continue;
        }
        else {
          Scanner command = new Scanner(str);
          if (command.hasNext()){
            String com = command.next();

            if (com.equalsIgnoreCase("shape")){
              ID = command.next();
              type = command.next();
              xPos = Double.parseDouble(command.next());
              yPos = Double.parseDouble(command.next());
              width = Double.parseDouble(command.next());
              height = Double.parseDouble(command.next());

              r = Double.parseDouble(command.next());
              g = Double.parseDouble(command.next());
              b = Double.parseDouble(command.next());
              Color color = new Color(r, g, b);
              shapes.put(ID, canvas1.createShape(type, ID, width, height, color));
              canvas1.placeShape(shapes.get(ID), xPos, yPos);
            }
            if (com.equalsIgnoreCase("snapshot")){
              String description = "";
              while (command.hasNext()){
                if (description == ""){
                  description = command.next();
                } else {
                  description = description + " " + command.next();
                }
              }
              description = description + "\n";
              canvas1.takeSnapshot(description);
//              if (viewType.equalsIgnoreCase("web")){
////                webView view1 = new webView(outputFileName, canvas1);
//                System.out.printf("web viewer selected");
////                view1.initialWeb();
//              }
            }
            if (com.equalsIgnoreCase("move")){
              ID = command.next();
              newXPos = Double.parseDouble(command.next());
              newYPos = Double.parseDouble(command.next());
//                System.out.printf(ID + " " + newXPos + " " + newYPos +"\n");
              canvas1.moveShape(shapes.get(ID), newXPos, newYPos);
            }
            if (com.equalsIgnoreCase("resize")){
              ID = command.next();
              newWidth = Double.parseDouble(command.next());
              newHeight = Double.parseDouble(command.next());
              canvas1.changeShapeX(shapes.get(ID), newWidth);
              canvas1.changeShapeY(shapes.get(ID), newHeight);
            }
            if (com.equalsIgnoreCase("color")){
              ID = command.next();
              newR = Double.parseDouble(command.next());
              newG = Double.parseDouble(command.next());
              newB = Double.parseDouble(command.next());
              Color newColor = new Color(newR, newG, newB);
              canvas1.changeShapeColor(shapes.get(ID), newColor);
            }
            if (com.equalsIgnoreCase("remove")){
              ID = command.next();
              canvas1.remove(shapes.get(ID));
            }
          }
        }
      }
      if (viewType.equalsIgnoreCase("graphical")){
        SwingUtilities.invokeLater(new Runnable() {
          @Override
          public void run() {
            new swingPhotoViewer(canvas1); // Let the constructor do the job
          }
        });
      }
      if (viewType.equalsIgnoreCase("web")){
        webView view1 = new webView(outputFileName, canvas1, (int)xSize, (int)ySize);
//        canvas1.getSnapshotsCollection();
//        for (int ind = 0; ind < canvas1.getSnapshotsCollection().size(); ind++){
//          System.out.println(canvas1.getSnapshotsCollection().get(ind).getID() + "-No-" + ind);
//          System.out.println("test "+canvas1.getSnapshotsCollection().get(ind).getShapePositions());
//        }
        view1.initialWeb((int)xSize, (int)ySize);
      }
    } catch (FileNotFoundException e){
      System.out.println(e);
    }
  }
}

