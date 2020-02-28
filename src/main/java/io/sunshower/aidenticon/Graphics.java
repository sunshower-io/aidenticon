package io.sunshower.aidenticon;

import java.util.Objects;

public class Graphics {

  final Renderer renderer;
  Transformation transformation;

  public Graphics(Renderer renderer) {
    this.renderer = renderer;
    this.transformation = Transformation.IDENTITY;
  }

  /**
   * add a polygon
   *
   * @param shape the polygon's points
   * @param invert swap the point-order
   * @return this
   */
  public Graphics addPolygon(double[] shape, boolean invert) {

    final double di = invert ? -2 : 2;
    final Polygon polygon = new Polygon();
    for (int i = invert ? shape.length - 2 : 0; i < shape.length && i >= 0; i += di) {
      polygon.add(transformation.apply(shape[0], shape[i + 1]));
    }
    renderer.add(polygon);
    return this;
  }

  /**
   * add a circle
   *
   * @param x
   * @param y
   * @param size
   * @param invert
   * @return
   */
  public Graphics addCircle(double x, double y, double size, boolean invert) {
    final Point center = transformation.apply(x, y, size, size);
    renderer.add(new Circle(center, size), invert);

    return this;
  }

  /**
   * add a rectangle
   *
   * @param x
   * @param y
   * @param w
   * @param h
   * @param invert
   * @return
   */
  public Graphics addRectangle(double x, double y, double w, double h, boolean invert) {
    return addPolygon(new double[] {x, y, x + w, y, x + w, y + h, x, y + h}, invert);
  }

  /**
   * add a triangle
   *
   * @param x
   * @param y
   * @param w
   * @param h
   * @param rotation
   * @param invert
   * @return
   */
  public Graphics addTriangle(
      double x, double y, double w, double h, double rotation, boolean invert) {
    double[] points = {x + w, y, x + w, y + h, x, y + h, x, y};
    return addPolygon(Utilities.splice(points, (((int) rotation % 4) * 2), 2), invert);
  }

  /**
   * add a rhombus
   *
   * @param x
   * @param y
   * @param w
   * @param h
   * @param invert
   * @return
   */
  public Graphics addRhombus(double x, double y, double w, double h, boolean invert) {
    return this.addPolygon(
        new double[] {x + w / 2, y, x + w, y + h / 2, x + w / 2, y + h, x, y + h / 2}, invert);
  }

  public Graphics addPolygon(double[] doubles) {
    return addPolygon(doubles, false);
  }

  public Graphics addTriangle(double v, double i, double w, double h, int i1) {
    return addTriangle(v, i, w, h, i1, false);
  }

  public Graphics addRectangle(double s, double s1, double v, double v1) {
    return addRectangle(s, s1, v, v1, false);
  }

  public Graphics addCircle(double v, double v1, double s) {
    return addCircle(v, v1, s, false);
  }

  public Graphics addRhombus(int i, int i1, double cell, double cell1) {
    return addRhombus(i, i1, cell, cell1, false);
  }

  public void setTransformation(Transformation transformation) {
    Objects.requireNonNull(transformation);
    this.transformation = transformation;
  }
}
