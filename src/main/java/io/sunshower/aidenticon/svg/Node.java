package io.sunshower.aidenticon.svg;

import javax.management.Query;
import java.util.*;

public class Node implements Writable {

  final String name;
  final List<Node> children;
  final Map<String, Attribute> attributes;

  public Node(String name) {
    this(name, Collections.emptyList());
  }

  public Node(String name, List<Attribute> attributes) {
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
}
