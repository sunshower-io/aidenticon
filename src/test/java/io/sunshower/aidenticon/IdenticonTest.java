package io.sunshower.aidenticon;

import org.junit.jupiter.api.Test;

class IdenticonTest {

  @Test
  void ensureSimpleExampleWorks() {
    var identicon = Identicon.withDefaults();
    identicon.create("hello world");
  }

  @Test
  void ensureCompleteBuilderWorks() {
    var identicon = Identicon.size(100).lightness(1).color().saturation(1).padding(4).create();
    var icon = identicon.create("Nicholas");
    System.out.println(new String(icon.getData()));
  }
}
