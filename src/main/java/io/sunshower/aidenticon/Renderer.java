package io.sunshower.aidenticon;

public interface Renderer {

  void setBackgroundColor(String color);

  void add(Polygon polygon);

  void add(Circle circle, boolean invert);

  void beginShape(String availableColor);

  void endShape();

  void finish();
}
