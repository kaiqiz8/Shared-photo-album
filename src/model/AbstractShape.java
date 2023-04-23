package model;

/**
 * The type Abstract shape.
 */
public abstract class AbstractShape implements IShape {
  private String name;
  private Color color;
  private double xParam;
  private double yParam;

  /**
   * Instantiates a new Abstract shape.
   *
   * @param name   the name
   * @param color  the color
   * @param xParam the x param
   * @param yParam the y param
   */
  public AbstractShape(String name, Color color, double xParam, double yParam) {
    this.name = name;
    this.color = color;
    this.xParam = xParam;
    this.yParam = yParam;
  }

  @Override
  public Color getColor() {
    Color currentColor = new Color(this.color.getR(), this.color.getG(), this.color.getB());
    return currentColor;
//    return this.color;
  }

  @Override
  public void changeColor(Color newColor) {
    this.color = newColor;
  }

  @Override
  public double getXParam() {
    return this.xParam;
  }

  @Override
  public double getYParam() {
    return this.yParam;
  }

  @Override
  public double getXYRatio() {
    return this.xParam / this.yParam;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void changeXParam(double newX) {
    this.xParam = newX;
  }

  @Override
  public void changeYParam(double newY) {
    this.yParam = newY;
  }

}
