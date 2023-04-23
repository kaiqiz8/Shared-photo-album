package model;

import java.util.Objects;

/**
 * The type Shape position.
 */
public class ShapePosition {
  private double canvasX;
  private double canvasY;
  private double xPos;
  private double yPos;
  private IShape shape;

  /**
   * Instantiates a new Shape position.
   *
   * @param shape   the shape
   * @param xPos    the x pos
   * @param yPos    the y pos
   * @param canvasX the canvas x
   * @param canvasY the canvas y
   */
  public ShapePosition(IShape shape, double xPos, double yPos, double canvasX, double canvasY) {
    this.shape = shape;
    this.xPos = xPos;
    this.yPos = yPos;
    this.canvasX = canvasX;
    this.canvasY = canvasY;
  }

  /**
   * Gets x pos.
   *
   * @return the x pos
   */
  public double getXPos() {
    return this.xPos;
  }

  /**
   * Gets y pos.
   *
   * @return the y pos
   */
  public double getYPos() {
    return this.yPos;
  }

  /**
   * Gets shape.
   *
   * @return the shape
   */
  public IShape getShape() {
    return this.shape;
  }

  /**
   * Reset x pos.
   *
   * @param newXPos the new x pos
   */
  public void resetXPos(double newXPos) {
    this.xPos = newXPos;
  }

  /**
   * Reset y pos.
   *
   * @param newYPos the new y pos
   */
  public void resetYPos(double newYPos) {
    this.yPos = newYPos;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ShapePosition that = (ShapePosition) o;
    return Double.compare(that.canvasX, canvasX) == 0 && Double.compare(that.canvasY, canvasY) == 0 && Double.compare(that.xPos, xPos) == 0 && Double.compare(that.yPos, yPos) == 0 && shape.equals(that.shape);
  }

  @Override
  public int hashCode() {
    return Objects.hash(canvasX, canvasY, xPos, yPos, shape);
  }

  @Override
  public String toString() {
    return this.shape.getName();
  }


}
