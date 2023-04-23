package model;

/**
 * The type model.Color.
 */
public class Color {
  private double r;
  private double g;
  private double b;

  /**
   * Instantiates a new model.Color.
   *
   * @param r the r
   * @param g the g
   * @param b the b
   */
  public Color(double r, double g, double b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Gets r.
   *
   * @return the r
   */
  public double getR() {
    return this.r;
  }

  /**
   * Gets g.
   *
   * @return the g
   */
  public double getG() {
    return this.g;
  }

  /**
   * Gets b.
   *
   * @return the b
   */
  public double getB() {
    return this.b;
  }

  @Override
  public String toString() {
    return "(" + this.r + "," + this.g + "," + this.b + ")";
  }

}
