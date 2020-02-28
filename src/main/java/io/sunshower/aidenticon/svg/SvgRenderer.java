package io.sunshower.aidenticon.svg;

import io.sunshower.aidenticon.Circle;
import io.sunshower.aidenticon.Polygon;
import io.sunshower.aidenticon.Renderer;
import io.sunshower.aidenticon.Utilities;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SvgRenderer implements Renderer {
  static final Pattern pattern = Pattern.compile("^(#......)(..)?");

  private SvgPath path;
  final SvgElement element;
  final Map<String, SvgPath> pathsByColor;

  public SvgRenderer(SvgElement element, int size) {
    this.element = element;
    this.pathsByColor = new HashMap<>();
  }

  @Override
  public void setBackgroundColor(String color) {
    var capture = pattern.matcher(color);
    if (capture.groupCount() == 2) {
      int opacity = Utilities.parseHex(capture.group(1), 0) / 255;
      this.element.setBackground(capture.group(0), opacity);
    } else {
      this.element.setBackground(capture.group(0), 1);
    }
  }

  @Override
  public void add(Polygon polygon) {
    path.addPolygon(polygon);
  }

  @Override
  public void add(Circle circle, boolean invert) {
    path.addCircle(circle.getCenter(), circle.getRadius(), invert);
  }

  @Override
  public void beginShape(String availableColor) {
    path = pathsByColor.computeIfAbsent(availableColor, t -> new SvgPath());
  }

  @Override
  public void endShape() {}

  @Override
  public void finish() {
    for (var path : pathsByColor.entrySet()) {
      element.append(path.getKey(), path.getValue().getDataString());
    }
  }
}
