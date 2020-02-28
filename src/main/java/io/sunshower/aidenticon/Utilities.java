package io.sunshower.aidenticon;

import static java.lang.Double.compare;
import static java.lang.Double.max;
import static java.lang.Math.abs;
import static java.lang.Math.ulp;

public class Utilities {

  public static int parseHex(String hash, int i) {
    return parseHex(hash, i, hash.length());
  }

  public static int parseHex(String hash, int startPosition, int octets) {
    return Integer.parseInt(hash.substring(startPosition, octets), 16);
  }

  public static double[] splice(final double[] array, int start) {
    if (start < 0) start += array.length;

    return splice(array, start, array.length - start);
  }

  @SuppressWarnings("unchecked")
  public static double[] splice(final double[] array, int start, final int deleteCount) {
    if (start < 0) start += array.length;

    final double[] spliced = new double[array.length - deleteCount];
    if (start != 0) System.arraycopy(array, 0, spliced, 0, start);

    if (start + deleteCount != array.length)
      System.arraycopy(
          array, start + deleteCount, spliced, start, array.length - start - deleteCount);

    return spliced;
  }

  public static double[] splice(
      final double[] array, int start, final int deleteCount, final double... items) {
    if (start < 0) {
      start += array.length;
    }

    final double[] spliced = new double[array.length - deleteCount + items.length];
    if (start != 0) {
      System.arraycopy(array, 0, spliced, 0, start);
    }

    if (items.length > 0) {
      System.arraycopy(items, 0, spliced, start, items.length);
    }

    if (start + deleteCount != array.length) {
      System.arraycopy(
          array,
          start + deleteCount,
          spliced,
          start + items.length,
          array.length - start - deleteCount);
    }

    return spliced;
  }

  public static boolean equals(double lhs, double rhs) {

    if (lhs == rhs) {
      return true;
    }

    final double eps = max(ulp(lhs), ulp(rhs));

    final double alhs = abs(lhs);
    final double arhs = abs(rhs);
    final double diff = abs(alhs - arhs);
    if (compare(diff, 0D) == 0) {
      return true;
    }

    if (diff <= eps) {
      return true;
    }

    return false;
  }
}
