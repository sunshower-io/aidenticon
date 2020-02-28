package io.sunshower.aidenticon;

public class Circle implements Shape {
  final Point center;

  public Point getCenter() {
    return center;
  }

  public double getRadius() {
    return radius;
  }

  final double radius;

  public Circle(Point center, double size) {
    this.center = center;
    this.radius = size;
  }


}
