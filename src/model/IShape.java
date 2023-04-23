package model;

/**
 * The interface Shape.
 */
public interface IShape {
  /**
   * Gets type.
   *
   * @return the type
   */
  String getType();

  /**
   * Gets color.
   *
   * @return the color
   */
  Color getColor();

  /**
   * Change color.
   *
   * @param newColor the new color
   */
  void changeColor(Color newColor);

  /**
   * Gets x param.
   *
   * @return the x param
   */
  double getXParam();

  /**
   * Gets y param.
   *
   * @return the y param
   */
  double getYParam();

  /**
   * Gets xy ratio.
   *
   * @return the xy ratio
   */
  double getXYRatio();

  /**
   * Gets name.
   *
   * @return the name
   */
  String getName();

  /**
   * Change x param.
   *
   * @param newX the new x
   */
  void changeXParam(double newX);

  /**
   * Change y param.
   *
   * @param newY the new y
   */
  void changeYParam(double newY);

}
