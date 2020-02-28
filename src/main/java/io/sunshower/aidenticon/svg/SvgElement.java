package io.sunshower.aidenticon.svg;

public class SvgElement {

  final int size;
  final Node element;

  public SvgElement(Node element, int size) {
    this.size = size;
    this.element = element;

    element.attr("width", size);
    element.attr("height", size);
    element.attr("viewBox", String.format("0 0 %d %d", size, size));
    element.attr("preserveAspectRatio", "xMidYMid meet");
  }

  public SvgElement() {
    this(new Node("svg"), 100);
  }

  public SvgElement setBackground(String fill, int opacity) {

    if (opacity >= 0) {
      element
          .child("rect")
          .attr("width", "100%")
          .attr("height", "100%")
          .attr("fill", fill)
          .attr("opacity", opacity);
    }
    return this;
  }

  public SvgElement append(String color, String data) {
    element.child("path").attr("fill", color).attr("d", data);
    return this;
  }
}
