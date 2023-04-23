import org.junit.Before;
import org.junit.Test;

import model.Canvas;
import model.Color;
import model.IShape;
import model.Oval;
import model.Rectangle;
import model.ShapePosition;
import model.SnapShots;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * The type model.Canvas test.
 */
public class CanvasTest {
  private Canvas canvas1;
  private IShape r1;
  private IShape r2;
  private IShape o1;
  private IShape o2;
  private Color c1;
  private Color c2;
  private Color c3;
  private Color c4;

  /**
   * Sets up.
   */
  @Before
  public void setUp() {
    c1 = new Color(0, 0, 0);
    c2 = new Color(1, 0, 0);
    c3 = new Color(0, 1, 0);
    c4 = new Color(0, 0, 1);

    canvas1 = new Canvas(100, 100);
    r1 = new Rectangle("r1", c1, 10, 10);
    r2 = new Rectangle("r2", c2, 10, 10);
    o1 = new Oval("o1", c3, 10, 10);
    o2 = new Oval("o2", c4, 10, 10);
    canvas1.placeShape(r1, 10, 10);
    canvas1.placeShape(r2, 20, 20);
    canvas1.placeShape(o1, 30, 30);
    canvas1.placeShape(o2, 40, 40);
  }

  /**
   * Create shape.
   */
  @Test
  public void createShape() {
    IShape o3 = canvas1.createShape("oval", "o3", 20, 10, c1);
    IShape o4 = canvas1.createShape("oval", "o4", 20, 10, c2);
    assertEquals("(0.0,0.0,0.0)", o3.getColor().toString());
    assertEquals("(1.0,0.0,0.0)", o4.getColor().toString());
    assertNull(canvas1.createShape("ovals", "o5", 10, 10, c3));
    assertNull(canvas1.createShape("rectangles", "r5", 10, 10, c4));
  }

  /**
   * Illegal create.
   */
  @Test(expected = IllegalArgumentException.class)
  public void illegalCreate() {
//    c1.createShape("ovals", 10, 10, model.Color.YELLOW);
    canvas1.createShape("", "r1", 10, 10, c1);
    canvas1.createShape(null, "r1", 10, 10, c1);

  }

  /**
   * Move shape.
   */
  @Test
  public void moveShape() {
    canvas1.moveShape(r1, 50, 50);
    assertEquals(50, canvas1.getShapePosition(r1).getXPos(), 0.001);
    assertEquals(50, canvas1.getShapePosition(r1).getYPos(), 0.001);
    canvas1.moveShape(r2, 60, 60);
    canvas1.moveShape(r2, 65, 65);
    assertEquals(65, canvas1.getShapePosition(r2).getXPos(), 0.001);
    assertEquals(65, canvas1.getShapePosition(r2).getYPos(), 0.001);
  }

  /**
   * Change shape color.
   */
  @Test
  public void changeShapeColor() {
    canvas1.changeShapeColor(r1, c2);
    canvas1.changeShapeColor(r2, c3);
    assertEquals("(1.0,0.0,0.0)", r1.getColor().toString());
    assertEquals("(0.0,1.0,0.0)", r2.getColor().toString());
  }

  /**
   * Change shape x.
   */
  @Test
  public void changeShapeX() {
    canvas1.changeShapeX(r1, 15);
    assertEquals(15, r1.getXParam(), 0.001);
    canvas1.changeShapeX(r2, 18);
    assertEquals(18, r2.getXParam(), 0.001);
  }

  /**
   * Change shape y.
   */
  @Test
  public void changeShapeY() {
    canvas1.changeShapeY(o1, 20);
    assertEquals(20, o1.getYParam(), 0.001);
    canvas1.changeShapeY(o2, 25);
    assertEquals(25, o2.getYParam(), 0.001);
  }

  /**
   * Place shape.
   */
  @Test
  public void placeShape() {
    assertEquals("[r1, r2, o1, o2]", canvas1.getAllShapes().toString());
    assertEquals(4, canvas1.getAllShapes().size(), 0.001);
    IShape o3 = canvas1.createShape("oval", "o3", 20, 10, c1);
    IShape o4 = canvas1.createShape("oval", "o4", 20, 10, c2);
    canvas1.placeShape(o3, 35, 35);
    canvas1.placeShape(o4, 25, 20);
    assertEquals("[r1, r2, o1, o2, o3, o4]", canvas1.getAllShapes().toString());
    assertEquals(6, canvas1.getAllShapes().size(), 0.001);
  }

  /**
   * Gets all shapes.
   */
  @Test
  public void getAllShapes() {
    assertEquals("[r1, r2, o1, o2]", canvas1.getAllShapes().toString());
    IShape o3 = canvas1.createShape("oval", "o3", 20, 10, c1);
    IShape o4 = canvas1.createShape("oval", "o4", 20, 10, c2);
    canvas1.placeShape(o3, 30, 40);
    canvas1.placeShape(o4, 50, 30);
    assertEquals("[r1, r2, o1, o2, o3, o4]", canvas1.getAllShapes().toString());
  }

  /**
   * Take snapshot.
   */
  @Test
  public void takeSnapshot() {
    assertEquals(0, canvas1.showAllSnapshots().size(), 0.001);
    assertEquals("2022", canvas1.takeSnapshot("").getYear());
    assertEquals(4, canvas1.takeSnapshot("").getShapeNum(), 0.001);
    canvas1.takeSnapshot("");
    assertEquals(3, canvas1.showAllSnapshots().size(), 0.001);
  }

  /**
   * Show all snapshots.
   */
  @Test
  public void showAllSnapshots() {
    canvas1.takeSnapshot("");
    canvas1.takeSnapshot("second snap");
    assertEquals(2, canvas1.showAllSnapshots().size());
  }

  /**
   * History.
   */
  @Test
  public void history() {
    assertEquals("[Place rectangle r1 with corner at (10.0,10.0)\n" +
            ", Place rectangle r2 with corner at (20.0,20.0)\n" +
            ", Place oval o1 with center at (30.0,30.0)\n" +
            ", Place oval o2 with center at (40.0,40.0)\n" +
            "]", canvas1.history().toString());
    canvas1.takeSnapshot("first snap");
    canvas1.moveShape(r1, 60, 60);
    canvas1.changeShapeColor(o1, c4);
    assertEquals("[Place rectangle r1 with corner at (10.0,10.0)\n" +
            ", Place rectangle r2 with corner at (20.0,20.0)\n" +
            ", Place oval o1 with center at (30.0,30.0)\n" +
            ", Place oval o2 with center at (40.0,40.0)\n" +
            ", Take a Snapshot \n" +
            ", r1 moves to (60.0,60.0)\n" +
            ", o1 changes from color (0.0,1.0,0.0) to (0.0,0.0,1.0)\n" +
            "]", canvas1.history().toString());
  }

  /**
   * Reset.
   */
  @Test
  public void reset() {
    assertEquals("[r1, r2, o1, o2]", canvas1.getAllShapes().toString());
    canvas1.takeSnapshot("first snap");
    System.out.printf(canvas1.history().toString());
    assertEquals(1, canvas1.showAllSnapshots().size(), 0.001);
    assertEquals(5, canvas1.history().size(), 0.001);
    canvas1.reset();
    assertEquals(0, canvas1.getAllShapes().size());
    assertEquals(0, canvas1.showAllSnapshots().size(), 0.001);
    assertEquals(0, canvas1.history().size(), 0.001);
  }

  @Test
  public void testGetAllShapePosition(){
    canvas1.takeSnapshot("first");
    for (SnapShots snap: canvas1.getSnapshotsCollection()){
      for (ShapePosition sp: snap.getShapePositions()){
        System.out.printf(sp.getShape().getName() +"\n");
      }
//      System.out.printf(sp.getShapePositions().toString());
    }
    System.out.printf("\n");

    IShape o3 = canvas1.createShape("oval", "o3", 20, 10, c1);
    IShape o4 = canvas1.createShape("oval", "o4", 20, 10, c2);
    canvas1.placeShape(o3, 20, 100);
    canvas1.takeSnapshot("1.5");

    for (SnapShots snap: canvas1.getSnapshotsCollection()){
      for (ShapePosition sp: snap.getShapePositions()){
        System.out.printf(sp.getShape().getName() +"\n");
      }
      System.out.printf("\n");
//      System.out.printf(sp.getShapePositions().toString());
    }
    System.out.printf("\n");

    canvas1.placeShape(o4, 10, 30);
    canvas1.takeSnapshot("second");
//    for (SnapShots sp: canvas1.getSnapshotsCollection()){
//      System.out.printf(sp.getShapePositions().toString() +"\n");
//    }

    for (SnapShots snap: canvas1.getSnapshotsCollection()){
      for (ShapePosition sp: snap.getShapePositions()){
        System.out.printf(sp.getShape().getName() +"\n");
      }
      System.out.printf("\n");
//      System.out.printf(sp.getShapePositions().toString());
    }
    System.out.printf("\n");
//    assertEquals("", canvas1.getSnapshotsCollection().get(0).getAllShapePosition());
  }

  @Test
  public void remove(){
    System.out.printf(canvas1.getAllShapes().toString());
    canvas1.remove(r1);
    System.out.printf(canvas1.getAllShapes().toString());
  }
}