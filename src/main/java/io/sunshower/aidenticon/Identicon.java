package io.sunshower.aidenticon;

import io.sunshower.aidenticon.svg.SvgElement;
import io.sunshower.aidenticon.svg.SvgRenderer;
import io.sunshower.aidenticon.svg.XmlWriter;

import java.io.StringWriter;
import java.security.MessageDigest;

public class Identicon implements Configuration {
  private IdenticonBuilder configuration;

  public Identicon(IdenticonBuilder identiconBuilder) {
    this.configuration = identiconBuilder;
  }

  public static Identicon withDefaults() {
    return new Identicon(new IdenticonBuilder());
  }

  public Icon create(String data) {

    try {
      byte[] hash = MessageDigest.getInstance("SHA-1").digest(data.getBytes());
      var node = new SvgElement();
      var renderer = new SvgRenderer(node, configuration.size);
      Icons.generate(renderer, Utilities.toHex(hash), 0, 0, configuration.size, this);
      var out = new StringWriter();
      node.getElement().write(new XmlWriter(out));
      return new SvgIcon(out.getBuffer().toString().getBytes());
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  public static IdenticonBuilder size(int size) {
    return new IdenticonBuilder().size(size);
  }

  @Override
  public boolean hasBackgroundColor() {
    return false;
  }

  @Override
  public String getBackgroundColor() {
    return configuration.backgroundColor;
  }

  @Override
  public int getPadding() {
    return configuration.padding;
  }

  @Override
  public double grayscaleSaturation() {
    return configuration.grayscaleSaturation;
  }

  @Override
  public double colorSaturation() {
    return configuration.colorSaturation;
  }

  @Override
  public double grayscaleLightness(int i) {
    return configuration.grayscaleSaturation;
  }

  @Override
  public double colorLightness(double v) {
    return configuration.lightness;
  }

  @Override
  public double hue(double hue) {
    return ((((hue / 360) % 1) + 1) % 1);
  }

  public static final class IdenticonBuilder {

    enum Mode {
      COLOR,
      GRAYSCALE,
    }

    public static final class Defaults {
      /** default value for identicon size */
      public static final int SIZE = 100;

      /** default value for lightness */
      public static final double LIGHTNESS = 1D;

      /** default value for color saturation */
      public static final double COLOR_SATURATION = 1D;

      /** default value for grayscale saturation */
      public static final double GRAYSCALE_SATURATION = 1D;

      /** default value for padding */
      public static final int PADDING = 0;

      public static final String BACKGROUND_COLOR = "0xFFFFFF";
    }

    /** are we configuring COLOR or GRAYSCALE? */
    private Mode mode;

    /** the (square) size of this identicon. Defaults to 100x100 px */
    private int size;

    /** the padding of this identicon. Defaults to 0 */
    private int padding;

    /** the brightness of this identicon. Defaults to 1 (no brightness/dimness) */
    private double lightness;

    /** the color saturation of this identicon. Defaults to 1 */
    private double colorSaturation;

    /** the grayscale saturation of this identicon. Defaults to 1 */
    private double grayscaleSaturation;

    /** background color */
    private String backgroundColor;

    IdenticonBuilder() {
      size = Defaults.SIZE;
      padding = Defaults.PADDING;
      lightness = Defaults.LIGHTNESS;
      colorSaturation = Defaults.COLOR_SATURATION;
      backgroundColor = Defaults.BACKGROUND_COLOR;
    }

    public IdenticonBuilder size(int size) {
      if (size <= 0) {
        throw new IllegalArgumentException("Error: size must be > 0");
      }
      this.size = size;
      return this;
    }

    public IdenticonBuilder lightness(double lightness) {
      if (lightness < 0D) {
        throw new IllegalArgumentException("Error: lightness must be >= 0");
      }

      this.lightness = lightness;
      return this;
    }

    /** places builder into COLOR mode (configures COLOR instead of GRAYSCALE) */
    public IdenticonBuilder color() {
      checkMode();
      this.mode = Mode.COLOR;
      return this;
    }

    public IdenticonBuilder saturation(double saturation) {
      if (mode == null || mode == Mode.COLOR) {
        this.colorSaturation = saturation;
      } else {
        this.grayscaleSaturation = saturation;
      }
      return this;
    }

    public IdenticonBuilder grayscale() {
      checkMode();
      this.mode = Mode.GRAYSCALE;
      return this;
    }

    public IdenticonBuilder padding(int padding) {
      if (padding < 0) {
        throw new IllegalArgumentException("Error:  padding must be > 0");
      }
      this.padding = padding;
      return this;
    }

    public Identicon create() {
      return new Identicon(this);
    }

    private void checkMode() {
      if (this.mode != null) {
        throw new IllegalStateException("Error: reconfiguring identicon");
      }
    }
  }
}
