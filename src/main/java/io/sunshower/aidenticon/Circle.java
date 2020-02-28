package io.sunshower.aidenticon;

public class Circle implements Shape {
  final Point center;
  final double radius;

  public Circle(Point center, double size) {
    this.center = center;
    this.radius = size;
  }
}
