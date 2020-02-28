package io.sunshower.aidenticon;

public class Shapes {

  @FunctionalInterface
  public interface ShapeTemplate {
    void render(Graphics g, double cell, int index);
  }

  static final ShapeTemplate[] Center = {
    (g, cell, index) -> {
      double k = cell * 0.42;
      g.addPolygon(new double[] {0, 0, cell, 0, cell, cell - k * 2, cell - k, cell, 0, cell});
    },
    (g, cell, index) -> {
      double w = (cell * 0.5), h = (cell * 0.8);
      g.addTriangle(cell - w, 0, w, h, 2);
    },
    (g, cell, index) -> {
      double s = (cell / 3);
      g.addRectangle(s, s, cell - s, cell - s);
    },
    (g, cell, index) -> {
      double inner = cell * 0.1, outer = cell < 6 ? 1 : cell < 8 ? 2 : ((cell * 0.25));
      inner = inner > 1 ? (inner) : inner > 0.5 ? 1 : inner;

      g.addRectangle(outer, outer, cell - inner - outer, cell - inner - outer);
    },
    (g, cell, index) -> {
      double m = (cell * 0.15), s = (cell * 0.5);
      g.addCircle(cell - s - m, cell - s - m, s);
    },
    (g, cell, index) -> {
      double inner = cell * 0.1, outer = inner * 4;

      if (outer > 3) {
        outer = Math.floor(outer);
      }
      g.addRectangle(0, 0, cell, cell);
      g.addPolygon(
          new double[] {
            outer, outer, cell - inner, outer, outer + (cell - outer - inner) / 2, cell - inner
          },
          true);
    },
    (g, cell, index) ->
        g.addPolygon(
            new double[] {
              0, 0, cell, 0, cell, cell * 0.7, cell * 0.4, cell * 0.4, cell * 0.7, cell, 0, cell
            }),
    (g, cell, index) -> g.addTriangle(cell / 2, cell / 2, cell / 2, cell / 2, 3),
    (g, cell, index) -> {
      g.addRectangle(0, 0, cell, cell / 2);
      g.addRectangle(0, cell / 2, cell / 2, cell / 2);
      g.addTriangle(cell / 2, cell / 2, cell / 2, cell / 2, 1);
    },
    (g, cell, index) -> {
      double inner = cell * 0.14, outer = cell < 4 ? 1 : cell < 6 ? 2 : ((cell * 0.35));
      inner = cell < 8 ? inner : Math.floor(inner);
      g.addRectangle(0, 0, cell, cell);
      g.addRectangle(outer, outer, cell - outer - inner, cell - outer - inner, true);
    },
    (g, cell, index) -> {
      double inner = cell * 0.12, outer = inner * 3;

      g.addRectangle(0, 0, cell, cell);
      g.addCircle(outer, outer, cell - inner - outer, true);
    },
    (g, cell, index) -> g.addTriangle(cell / 2, cell / 2, cell / 2, cell / 2, 3),
    (g, cell, index) -> {
      var m = cell * 0.25;
      g.addRectangle(0, 0, cell, cell);
      g.addRhombus(m, m, cell - m, cell - m, true);
    },
    (g, cell, index) -> {
      double m = cell * 0.4, s = cell * 1.2;
      if (index == -1) {
        g.addCircle(m, m, s);
      }
    },
  };

  static final ShapeTemplate[] Outer = {
    (g, cell, index) -> g.addTriangle(0, 0, cell, cell, 0),
    (g, cell, index) -> g.addTriangle(0, cell / 2, cell, cell / 2, 0),
    (g, cell, index) -> g.addRhombus(0, 0, cell, cell),
    (g, cell, index) -> {
      var m = cell / 6;
      g.addCircle(m, m, cell - 2 * m);
    }
  };
}
