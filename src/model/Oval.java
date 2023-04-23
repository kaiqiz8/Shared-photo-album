package model;

/**
 * The type model.Oval.
 */
public class Oval extends AbstractShape {
  /**
   * Instantiates a new model.Oval.
   *
   * @param name   the name
   * @param color  the color
   * @param xParam the x param
   * @param yParam the y param
   */
  public Oval(String name, Color color, double xParam, double yParam) {
    super(name, color, xParam, yParam);
  }

  @Override
  public String getType() {
    return "Oval";
  }
}
