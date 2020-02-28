package io.sunshower.aidenticon.svg;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class XmlWriter implements DocumentWriter {
  final Writer writer;
  private int indent;

  public XmlWriter() {
    this(new StringWriter());
  }

  public XmlWriter(Writer writer) {
    this.writer = writer;
  }

  @Override
  public void beginElement(String name) {
    try {
      writeIndent();
      writer.append("<").append(name);
      indent++;
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  @Override
  public void endElement(String name) {
    try {
      indent--;
      writeIndent();
      writer.write("</");
      writer.write(name);
      writer.write(">");
      writer.write("\n");
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  @Override
  public void closePrelude() {
    try {
      writer.write(">\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void write(String s) {
    try {
      writer.write(s);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  private void writeIndent() throws IOException {
    for (int i = 0; i < indent; i++) {
      writer.write(" ");
    }
  }
}
