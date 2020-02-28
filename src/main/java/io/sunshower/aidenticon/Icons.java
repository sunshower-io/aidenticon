package io.sunshower.aidenticon;


import java.util.ArrayList;
import java.util.List;

import static io.sunshower.aidenticon.Utilities.parseHex;

public class Icons {

  static void generate(
      Renderer renderer,
      String hash,
      double x,
      double y,
      double size,
      Configuration configuration) {
    if (configuration.hasBackgroundColor()) {
      renderer.setBackgroundColor(configuration.getBackgroundColor());
    }

    double padding = computePadding(configuration);

    size -= padding * 2;

    final Graphics graphics = new Graphics(renderer);

    double cell = (int) Math.round(size / 4);

    x += padding + size / 2 - cell * 2;
    y += padding + size / 2 - cell * 2;

    var hue = parseHex(hash, -7) / 0xFFFFFFFF;
    var availableColors = Theme.colorTheme(hue, configuration);
    List<Integer> selectedColorIndexes = new ArrayList<>();
    int index;

    for (var i = 0; i < 3; i++) {
      index = parseHex(hash, 8 + i, 1) % availableColors.length;
      if (isDuplicate(new int[] {0, 4}, index, selectedColorIndexes)
          || // Disallow dark gray and dark color combo
          isDuplicate(
              new int[] {2, 3},
              index,
              selectedColorIndexes)) { // Disallow light gray and light color combo
        index = 1;
      }
      selectedColorIndexes.add(index);
    }


    renderShape(
            graphics,
            hash,
            renderer,
            0,
            Shapes.Outer,
            2,
            3,
    new int[][]{
                    {1, 0}, {2, 0}, {2, 3}, {1, 3}, {0, 1}, {3, 1}, {3, 2}, {0, 2}
    }, availableColors,
            selectedColorIndexes,
            (int) x, (int) y, (int) cell);

    renderShape(
            graphics,
            hash,
            renderer,
            1,
            Shapes.Outer,
            4,
            5,
            new int[][]{
                    {0, 0}, {3, 0}, {3, 3}, {0, 3}
            }, availableColors,
            selectedColorIndexes,
            (int) x, (int) y, (int) cell);


    renderShape(
            graphics,
            hash,
            renderer,
            2,
            Shapes.Outer,
            1,
            -1,
            new int[][]{
                    {1, 1}, {2, 1}, {2, 2}, {1, 2}
            }, availableColors,
            selectedColorIndexes,
            (int) x, (int) y, (int) cell);

    renderer.finish();
  }



  static boolean isDuplicate(int[] values, int value, List<Integer> selectedColorIndexes) {
    for (int i = 0; i < values.length; i++) {
      if (values[i] == value) {
        for (int j = 0; j < values.length; j++) {
          if (selectedColorIndexes.contains(values[j])) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private static double computePadding(Configuration configuration) {
    return configuration.getPadding();
  }

  static void renderShape(
      Graphics graphics,
      String hash,
      Renderer renderer,
      int colorIndex,
      Shapes.ShapeTemplate[] shapes,
      int index,
      int rotationIndex,
      int[][] positions,
      String[] availableColors,
      List<Integer> selectedColorIndexes,
      int x,
      int y,
      int cell) {
    int r = rotationIndex >= 0 ? parseHex(hash, rotationIndex, 1) : 0;
    Shapes.ShapeTemplate shape = shapes[parseHex(hash, index, 1) % shapes.length];
    int i;

    renderer.beginShape(availableColors[selectedColorIndexes.get(colorIndex)]);

    for (i = 0; i < positions.length; i++) {
      graphics.setTransformation(
          new Transformation(
              x + positions[i][0] * cell, y + positions[i][1] * cell, cell, r++ % 4));
      shape.render(graphics, cell, i);
    }

    renderer.endShape();
  }
}
