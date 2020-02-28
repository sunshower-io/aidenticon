package io.sunshower.aidenticon;

public class Transformation {

  public static final Transformation IDENTITY = new Transformation(0, 0, 0, 0);

  public final double x;
  public final double y;
  public final double size;
  public final double rotation;

  public Transformation(double x, double y, double size, double rotation) {
    this.x = x;
    this.y = y;
    this.size = size;
    this.rotation = rotation;
  }

  /** @return */
  public Point apply(double x, double y, double width, double height) {
    final double right = this.x + this.size;
    final double bottom = this.y + this.size;

    if (Utilities.equals(rotation, 1D)) {
      return Point.at(right - y - height, this.y + x);
    }

    if (Utilities.equals(rotation, 2D)) {
      return Point.at(right - x - width, bottom - y - height);
    }

    if (Utilities.equals(rotation, 3D)) {
      return Point.at(this.x + y, bottom - x - width);
    }

    return Point.at(this.x + x, this.y + y);
  }


  public Point apply(double x, double y) {
    return apply(x, y, 0, 0);
  }
}
