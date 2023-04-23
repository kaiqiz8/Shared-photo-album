package model;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * The type Snap shots.
 */
public class SnapShots {
  private List<ShapePosition> list1;
  private String description;

  /**
   * Instantiates a new Snap shots.
   *
   * @param list        the list
   * @param description the description
   */
  public SnapShots(List<ShapePosition> list, String description) {
    this.list1 = list;
    this.description = description;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public String getID() {
    Date date = Calendar.getInstance().getTime();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSS");
    return formatter.format(date);
  }

  public List<ShapePosition> getShapePositions(){
    List<ShapePosition> list = this.list1.stream().toList();
    return list;
  }

  /**
   * Gets time step.
   *
   * @return the time step
   */
  public String getTimeStep() {
    Date date = Calendar.getInstance().getTime();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");
    return formatter.format(date);
  }

  public String getDescription(){
    return this.description;
  }


  /**
   * Gets year.
   *
   * @return the year
   */
  public String getYear() {
    Date date = Calendar.getInstance().getTime();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
    return formatter.format(date);
  }

  /**
   * Gets shape num.
   *
   * @return the shape num
   */
  public int getShapeNum() {
    return this.list1.size();
  }


  @Override
  public String toString() {
    String output;
    output = "Printing Snapshots\nSnapshot ID: " + getID() + "\nTimestamp: " + getTimeStep()
            + "\nDescription: " + this.description + "\nShape Information: \n";
    Iterator<ShapePosition> iterator = this.list1.iterator();
    DecimalFormat df = new DecimalFormat("0.00");
    int i = 0;
    while (i < this.list1.size()) {
      if (this.list1.get(i).getShape().getName() == "O") {
//        output += df.format(this.list1.get(i).getxPos());
        output += "Name: O\nType: model.Oval\nCenter:(" + df.format(this.list1.get(i).getXPos()) + "," + df.format(this.list1.get(i).getYPos()) + "), "
                + "X radius: " + df.format(this.list1.get(i).getShape().getXParam()) + ", Y radius: "
                + df.format(this.list1.get(i).getShape().getYParam()) + ", model.Color: (" + df.format(this.list1.get(i).getShape().getColor().getR())
                + ", " + df.format(this.list1.get(i).getShape().getColor().getG()) + ", " + df.format(this.list1.get(i).getShape().getColor().getB()) + ") "
                + "\n\n";
      } else if (this.list1.get(i).getShape().getName() == "R") {
        output += "Name: R\nType: model.Rectangle\nMin corner:(" + df.format(this.list1.get(i).getXPos()) + "," + df.format(this.list1.get(i).getYPos()) + "), "
                + "Width: " + df.format(this.list1.get(i).getShape().getXParam()) + ", Height: "
                + df.format(this.list1.get(i).getShape().getYParam()) + ", model.Color: (" + df.format(this.list1.get(i).getShape().getColor().getR())
                + ", " + df.format(this.list1.get(i).getShape().getColor().getG()) + ", " + df.format(this.list1.get(i).getShape().getColor().getB()) + ") "
                + "\n\n";
      }
      i++;
    }
    return output;
  }
}
