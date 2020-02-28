package io.sunshower.aidenticon.svg;

import org.junit.jupiter.api.Test;

import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NodeTest {

  @Test
  void ensureNodeBuilderWorks() {
    var doc =
        Document.create("test")
            .append(Node.create("a").attr("width", 1).attr("height", 1).child(Node.create("b")));
    assertEquals(doc.root.name, "test");
  }

  @Test
  void ensureWritingWorks() {
    var doc =
        Document.create("test")
            .append(
                Node.create("a")
                    .attr("w", "2")
                    .attr("h", "4")
                    .child("b")
                    .child("c")
                    .child("d")
                    .attr("frapper", 1)
                    .attr("whatever", "cooliobeansio"));
    StringWriter writer = new StringWriter();
    doc.write(new XmlWriter(writer));

    String expected =
        "<test>\n"
            + " <a w=\"2\" h=\"4\">\n"
            + "  <b>\n"
            + "   <c>\n"
            + "    <d frapper=\"1\" whatever=\"cooliobeansio\">\n"
            + "    </d>\n"
            + "   </c>\n"
            + "  </b>\n"
            + " </a>\n"
            + "</test>\n";
    assertEquals(expected, writer.toString());
  }
}
