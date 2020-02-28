package io.sunshower.aidenticon.svg;

import io.sunshower.aidenticon.Circle;
import io.sunshower.aidenticon.Polygon;
import io.sunshower.aidenticon.Renderer;

public class SvgRenderer implements Renderer {

  final Document document;

  public SvgRenderer() {
    document = Document.create("svg");
  }

  @Override
  public void setBackgroundColor(int color) {

  }

  @Override
  public void add(Polygon polygon) {}

  @Override
  public void add(Circle circle, boolean invert) {}

  @Override
  public void beginShape(String availableColor) {}

  @Override
  public void endShape() {}

  @Override
  public void finish() {}
}
