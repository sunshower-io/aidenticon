package io.sunshower.aidenticon;

public class Point {

  public final double x;
  public final double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }


  public static Point at(double x, double y) {
    return new Point(x, y);
  }
}
