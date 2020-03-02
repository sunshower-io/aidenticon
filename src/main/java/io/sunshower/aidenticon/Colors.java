package io.sunshower.aidenticon;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Pattern;

import static io.sunshower.aidenticon.Utilities.parseHex;
import static java.lang.Double.isNaN;

public class Colors {

  public static Pattern pattern = Pattern.compile("^#[0-9a-f]{3,8}$", Pattern.CASE_INSENSITIVE);

  public static String decToHex(double actual) {

    if (actual < 0) {
      return "00";
    }

    if (actual < 16) {
      return "0" + Integer.toString((int) actual, 16);
    }
    if (actual < 256) {
      return Integer.toString((int) actual, 16);
    }

    return "ff";
  }

  public static String hueToRgb(double m1, double m2, double h) {
    h = h < 0 ? h + 6 : h > 6 ? h - 6 : h;
    return decToHex(
        255 * (h < 1 ? m1 + (m2 - m1) * h : h < 3 ? m2 : h < 4 ? m1 + (m2 - m1) * (4 - h) : m1));
  }

  public static String rgb(int r, int g, int b) {
    return "#" + decToHex(r) + decToHex(g) + decToHex(b);
  }

  public static String parse(String color) {
    if (pattern.matcher(color).matches()) {
      if (color.length() < 6) {
        char r = color.charAt(1), g = color.charAt(2), b = color.charAt(3), a = color.charAt(4);
        return "#" + r + r + g + g + b + b + a + a;
      }

      if (color.length() == 7 || color.length() > 8) {
        return color;
      }
    }
    throw new IllegalArgumentException("Not a color: " + color);
  }

  public static String correctedHsl(double h, double s, double l) {
    double[] correctors = {0.55, 0.5, 0.5, 0.46, 0.6, 0.55, 0.55};
    double corrector = correctors[(int) Math.round(h * 6 + 0.5)];
    l = (int) (l < 0.5 ? l * corrector * 2 : corrector + (l - 0.5) * (1 - corrector) * 2);
    return hsl(h, s, l);
  }

  public static String hsl(double h, double s, double l) {
    if (s == 0) {
      var partialHex = decToHex(l * 255);
      return "#" + partialHex + partialHex + partialHex;
    } else {
      double m2 = l <= 0.5 ? l * (s + 1) : l + s - l * s, m1 = l * 2 - m2;
      return "#"
          + hueToRgb(m1, m2, h * 6 + 2)
          + hueToRgb(m1, m2, h * 6)
          + hueToRgb(m1, m2, h * 6 - 2);
    }
  }

  public static String toCss3(String hexColor) {
    double a = parseHex(hexColor, 7, 2);
    if (isNaN(a)) {
      return hexColor;
    }
    long r = parseHex(hexColor, 1, 2), g = parseHex(hexColor, 3, 2), b = parseHex(hexColor, 5, 2);
    return "rgba(" + r + "," + g + "," + b + "," + toFixed(((a / 255))) + ")";
  }

  public static int toFixed(double d) {
    return new BigDecimal(d).setScale(2, RoundingMode.HALF_EVEN).intValueExact();
  }
}
