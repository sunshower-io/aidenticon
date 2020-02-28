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
    Node actualNode = node;
    while (actualNode.parent != null) {
      actualNode = actualNode.parent;
    }
    root.addChild(actualNode);
    return this;
  }

  public void write(DocumentWriter writer) {
    root.write(writer);
  }
}
