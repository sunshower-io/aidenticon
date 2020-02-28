package io.sunshower.aidenticon.svg;

import java.util.*;

public class Node implements Writable {

  final Node parent;
  final String name;
  final List<Node> children;
  final Map<String, Attribute> attributes;

  Node(Node parent, Node current) {
    this.parent = parent;
    this.name = current.name;
    this.attributes = current.attributes;
    this.children = current.children;
  }

  public Node(String name) {
    this(name, Collections.emptyList());
  }

  public Node(String name, List<Attribute> attributes) {
    this.parent = null;
    this.name = name;
    this.attributes = new LinkedHashMap<>();
    for (var attr : attributes) {
      setAttribute(attr);
    }
    children = new ArrayList<>();
  }

  public static Node create(String a) {
    return new Node(a);
  }

  public Node parent() {
    if (parent == null) {
      throw new IllegalStateException("Node has not been inserted correctly");
    }
    return parent;
  }

  public Node setAttribute(Attribute attr) {
    if (this.attributes.put(attr.key, attr) != null) {
      throw new IllegalArgumentException("Duplicate attribute: " + attr.key);
    }
    return this;
  }

  public void write(DocumentWriter writer) {
    writer.beginElement(name);
    for (var attr : attributes.values()) {
      attr.write(writer);
    }
    writer.closePrelude();
    for (var child : children) {
      child.write(writer);
    }
    writer.endElement(name);
  }

  public Node addChild(Node node) {
    children.add(node);
    return this;
  }

  public Node attr(String key, Object value) {
    attributes.put(key, new Attribute(key, String.valueOf(value)));
    return this;
  }

  public Node child(Node b) {
    return addChild(b);
  }

  public Node child(String a) {
    final Node child = new Node(this, new Node(a));
    addChild(child);
    return child;
  }
}
