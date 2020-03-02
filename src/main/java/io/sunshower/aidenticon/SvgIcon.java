package io.sunshower.aidenticon;

class SvgIcon implements Icon {
  final byte[] data;

  SvgIcon(byte[] data) {
    this.data = data;
  }

  @Override
  public byte[] getData() {
    return data;
  }

  @Override
  public String toURL() {
    return null;
  }
}
