package io.sunshower.aidenticon.svg;

import io.sunshower.aidenticon.Point;
import io.sunshower.aidenticon.Polygon;

import java.util.Collection;

public class SvgPath {
  private final StringBuilder dataString;

  public SvgPath() {
    dataString = new StringBuilder();
  }

  public SvgPath addCircle(Point center, double diameter, boolean counterclockwise) {
    int sweepFlag = counterclockwise ? 0 : 1;
    double svgRadius = svgValue(diameter / 2);
    double svgDiameter = svgValue(diameter);

    var ds =
        "M"
            + svgValue(center.x)
            + " "
            + svgValue(center.y + diameter / 2)
            + "a"
            + svgRadius
            + ","
            + svgRadius
            + " 0 1,"
            + sweepFlag
            + " "
            + svgDiameter
            + ",0"
            + "a"
            + svgRadius
            + ","
            + svgRadius
            + " 0 1,"
            + sweepFlag
            + " "
            + (-svgDiameter)
            + ",0";
    this.dataString.append(ds);
    return this;
  }

  public SvgPath addPolygon(Polygon polygon) {
    var points = polygon.getPoints();
    var dataString = "M" + svgValue(points.get(0).x) + " " + svgValue(points.get(0).y);
    for (var i = 1; i < points.size(); i++) {
      var pt = points.get(i);
      dataString += "L" + svgValue(pt.x) + " " + svgValue(pt.y);
    }
    this.dataString.append(dataString).append("Z");
    return this;
  }

  static double svgValue(double value) {
    return Math.round((value * 10 + 0.5)) / 10D;
  }
}
