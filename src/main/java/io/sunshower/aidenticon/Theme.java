package io.sunshower.aidenticon;

import static io.sunshower.aidenticon.Colors.correctedHsl;

public class Theme {

  public static String[] colorTheme(int hue, Configuration config) {
    return new String[] {
      correctedHsl(hue, config.grayscaleSaturation(), config.grayscaleLightness(0)),
      // Mid color
      correctedHsl(hue, config.colorSaturation(), config.colorLightness(0.5)),
      // Light gray
      correctedHsl(hue, config.grayscaleSaturation(), config.grayscaleLightness(1)),
      // Light color
      correctedHsl(hue, config.colorSaturation(), config.colorLightness(1)),
      // Dark color
      correctedHsl(hue, config.colorSaturation(), config.colorLightness(0))
    };
  }
}
