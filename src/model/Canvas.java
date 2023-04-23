package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * The type model.Canvas.
 */
public class Canvas {
  private List<String> commands;
  private List<SnapShots> snapshotsCollection;
  private List<ShapePosition> list1;
  private double canvasX;
  private double canvasY;

  /**
   * Instantiates a new model.Canvas.
   *
   * @param canvasX the canvas x
   * @param canvasY the canvas y
   */
  public Canvas(double canvasX, double canvasY) {
    this.canvasX = canvasX;
    this.canvasY = canvasY;
    this.commands = new ArrayList<>();
    this.snapshotsCollection = new ArrayList<>();
    this.list1 = new ArrayList<>();
  }

  /**
   * Create shape shape.
   *
   * @param kind   the kind
   * @param name   the name
   * @param param1 the param 1
   * @param param2 the param 2
   * @param color  the color
   * @return the shape
   */
  public IShape createShape(String kind, String name, double param1, double param2, Color color) {
    if (kind == "" || kind == null) {
      throw new IllegalArgumentException("Invalid kind");
    }
    if (param1 == 0 || param2 == 0) {
      throw new IllegalArgumentException("Invalid parameter");
    }
    if (kind.equalsIgnoreCase("rectangle")) {
      String string = "Create rectangle " + name + " with width: " + String.valueOf(param1) +
              " height: "
              + String.valueOf(param2) + " and color: " + color.toString() + "\n";
      commands.add(string);
      return new Rectangle(name, color, param1, param2);
    } else if (kind.equalsIgnoreCase("oval")) {
      String string2 = "Create oval" + name + " radius: " + String.valueOf(param1) + " and "
              + String.valueOf(param2) + " and color: "
              + color.toString() + "\n";
      commands.add(string2);
      return new Oval(name, color, param1, param1);
    }
    return null;
  }

  /**
   * Move shape.
   *
   * @param shape   the shape
   * @param newXPos the new x pos
   * @param newYPos the new y pos
   */
  public void moveShape(IShape shape, double newXPos, double newYPos) {
    String string = shape.getName() + " moves to (" + String.valueOf(newXPos) + ","
            + String.valueOf(newYPos) + ")" + "\n";
    commands.add(string);
    for (ShapePosition l1 : this.list1) {
      if (l1.getShape() == shape) {
        l1.resetXPos(newXPos);
        l1.resetYPos(newYPos);
      }
    }
  }

  /**
   * Change shape color.
   *
   * @param shape    the shape
   * @param newColor the new color
   */
  public void changeShapeColor(IShape shape, Color newColor) {
    String string = shape.getName() + " changes from color " + shape.getColor().toString() + " to "
            + newColor.toString() + "\n";
    commands.add(string);
    for (ShapePosition l1 : this.list1) {
      if (l1.getShape() == shape) {
        l1.getShape().changeColor(newColor);
      }
    }
  }

  /**
   * Change shape x.
   *
   * @param shape the shape
   * @param newX  the new x
   */
  public void changeShapeX(IShape shape, double newX) {
    if (shape.getType().equalsIgnoreCase("rectangle")) {
      String string = shape.getName() + " changes width from " + String.valueOf(shape.getXParam())
              + " to " + String.valueOf(newX) + "\n";
      commands.add(string);
    }

    if (shape.getType().equalsIgnoreCase("oval")) {
      String string2 = shape.getName() + " changes x radius from " + String.valueOf(shape.getXParam())
              + " to " + String.valueOf(newX) + "\n";
      commands.add(string2);
    }

    for (ShapePosition l1 : this.list1) {
      if (l1.getShape() == shape) {
        l1.getShape().changeXParam(newX);
      }
    }
  }

  /**
   * Change shape y.
   *
   * @param shape the shape
   * @param newY  the new y
   */
  public void changeShapeY(IShape shape, double newY) {
    if (shape.getType().equalsIgnoreCase("rectangle")) {
      String string = shape.getName() + " changes height from " + String.valueOf(shape.getYParam())
              + " to " + String.valueOf(newY) + "\n";
      commands.add(string);
    }

    if (shape.getType().equalsIgnoreCase("oval")) {
      String string2 = shape.getName() + " changes y radius from " + String.valueOf(shape.getYParam())
              + " to " + String.valueOf(newY) + "\n";
      commands.add(string2);
    }

    for (ShapePosition l1 : this.list1) {
      if (l1.getShape() == shape) {
        l1.getShape().changeYParam(newY);
      }
    }
  }

  /**
   * Place shape.
   *
   * @param shape the shape
   * @param xPos  the x pos
   * @param yPos  the y pos
   */
  public void placeShape(IShape shape, double xPos, double yPos) {
    if (xPos > this.canvasX || yPos > this.canvasY || xPos < 0 || yPos < 0) {
      throw new IllegalArgumentException("Illegal x or y position");
    }

    if (shape.getType().equalsIgnoreCase("oval")) {
//      if (xPos < shape.getXParam() || yPos < shape.getYParam() || xPos > this.canvasX - xPos
//              || yPos > this.canvasY - yPos) {
//        throw new IllegalArgumentException("Oval outside of bound");
//      }
//      else {
        String string = "Place oval " + shape.getName() + " with center at (" + String.valueOf(xPos)
                + "," + String.valueOf(yPos) + ")\n";
        commands.add(string);
//      }
    }

    if (shape.getType().equalsIgnoreCase("rectangle")) {
//      if (xPos > this.canvasX - shape.getXParam() || yPos > this.canvasY - shape.getYParam()) {
//        throw new IllegalArgumentException("model.Rectangle outside of bound");
//      } else {
        String string2 = "Place rectangle " + shape.getName() + " with corner at (" + String.valueOf(xPos)
                + "," + String.valueOf(yPos) + ")\n";
        commands.add(string2);
//      }
    }
    ShapePosition shapes = new ShapePosition(shape, xPos, yPos, canvasX, canvasY);
    this.list1.add(shapes);
  }

  /**
   * Gets all shapes.
   *
   * @return the all shapes
   */
  public List<ShapePosition> getAllShapes() {
    List<ShapePosition> list = this.list1.stream().toList();
    return list;
  }

  /**
   * Gets shape position.
   *
   * @param shape the shape
   * @return the shape position
   */
  public ShapePosition getShapePosition(IShape shape) {
    for (ShapePosition l1 : this.list1) {
      if (l1.getShape() == shape) {
        return l1;
      }
    }
    return null;
  }

  /**
   * Take snapshot snap shots.
   *
   * @param description the description
   * @return the snap shots
   */
  public SnapShots takeSnapshot(String description) {
    String string = "Take a Snapshot \n";
    commands.add(string);
    List<ShapePosition> shapes = Collections.unmodifiableList(getAllShapes());
    SnapShots s1 = new SnapShots(shapes, description);
    snapshotsCollection.add(s1);
    return s1;
  }

  /**
   * Show all snapshots list.
   *
   * @return the list
   */
  public List<String> showAllSnapshots() {
    String allsnapinfo = "";
    List<String> allsnap = new ArrayList<>();
    Iterator<SnapShots> iterator = snapshotsCollection.iterator();
    while (iterator.hasNext()) {
      allsnap.add(iterator.next().getID());
    }
    return allsnap;
  }

  public List<SnapShots> getSnapshotsCollection(){
    List<SnapShots> list = this.snapshotsCollection.stream().toList();
    return list;
//    return this.snapshotsCollection;
  }

  /**
   * History list.
   *
   * @return the list
   */
  public List<String> history() {
    return commands;
  }

  /**
   * Reset.
   */
  public void reset() {
    System.out.printf("List of snapshots taken before reset: ");
    System.out.printf(showAllSnapshots().toString());
    this.list1.clear();
    snapshotsCollection.clear();
    commands.clear();
  }
//  public void remove(model.IShape shape){
//    for (model.ShapePosition l1 : list1) {
//      if (l1.getShape() == shape) list1.remove(l1);
//    }
//  }

  public void remove(IShape shape){
    String string = "Remove shape " + shape.getName();
    commands.add(string);
    ShapePosition removeshape = null;
    for (ShapePosition s1: this.list1){
      if (s1.getShape() == shape){
//        list1.remove(s1);
        removeshape = s1;
      }
    }
    this.list1.remove(removeshape);
  }

  public double getCanvasX() {
    return this.canvasX;
  }

  public double getCanvasY(){
    return this.canvasY;
  }
}
