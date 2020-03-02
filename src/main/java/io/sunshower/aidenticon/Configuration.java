package io.sunshower.aidenticon;

public interface Configuration {

  boolean hasBackgroundColor();

  String getBackgroundColor();

  int getPadding();

  double grayscaleSaturation();

  double colorSaturation();

  double grayscaleLightness(int i);

  double colorLightness(double v);

  double hue(double h);
}
