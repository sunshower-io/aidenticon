package io.sunshower.aidenticon.svg;

public class Attribute implements Writable {

  final String key;
  final String value;

  public Attribute(String key, String value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public void write(DocumentWriter writer) {

  }
}
