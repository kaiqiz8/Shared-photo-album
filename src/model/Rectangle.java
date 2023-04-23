package model;

/**
 * The type model.Rectangle.
 */
public class Rectangle extends AbstractShape {
  /**
   * Instantiates a new model.Rectangle.
   *
   * @param name   the name
   * @param color  the color
   * @param xParam the x param
   * @param yParam the y param
   */
  public Rectangle(String name, Color color, double xParam, double yParam) {
    super(name, color, xParam, yParam);
  }

  @Override
  public String getType() {
    return "Rectangle";
  }
}
