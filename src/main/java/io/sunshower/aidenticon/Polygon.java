package io.sunshower.aidenticon;

import java.util.ArrayList;
import java.util.List;

public final class Polygon implements Shape {
  final List<Point> points;

  public Polygon() {
    points = new ArrayList<>();
  }

  public Shape add(Point point) {
    points.add(point);
    return this;
  }

  public List<Point> getPoints() {
    return points;
  }
}
