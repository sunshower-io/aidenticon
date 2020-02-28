package io.sunshower.aidenticon.svg;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NodeTest {

  @Test
  void ensureNodeBuilderWorks() {
    var doc =
        Document.create("test")
            .append(Node.create("a").attr("width", 1).attr("height", 1).child(Node.create("b")));
    assertEquals(doc.root.name, "test");
  }
}
