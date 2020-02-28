package io.sunshower.aidenticon.svg;

import java.util.List;

public class Document {

  final Node root;

  public Document(Node root) {
    this.root = root;
  }

  public static Document create(String name) {
    return new Document(new Node(name));
  }

  public Document append(Node node) {
    root.addChild(node);
    return this;
  }

  public void write(DocumentWriter writer) {
    root.write(writer);
  }
}
