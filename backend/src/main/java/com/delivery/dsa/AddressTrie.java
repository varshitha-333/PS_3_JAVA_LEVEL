package com.delivery.dsa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressTrie {
  private final Node root = new Node();

  public void insert(String word) {
    Node curr = root;
    for (char c : word.toLowerCase().toCharArray()) {
      curr.children.putIfAbsent(c, new Node());
      curr = curr.children.get(c);
    }
    curr.end = true;
    curr.freq++;
  }

  public List<String> suggest(String prefix) {
    Node curr = root;
    for (char c : prefix.toLowerCase().toCharArray()) {
      if (!curr.children.containsKey(c)) {
        return List.of();
      }
      curr = curr.children.get(c);
    }
    List<String> out = new ArrayList<>();
    collect(curr, prefix.toLowerCase(), out);
    return out;
  }

  private void collect(Node node, String build, List<String> out) {
    if (node.end) {
      for (int i = 0; i < node.freq; i++) {
        out.add(build);
      }
    }
    for (Map.Entry<Character, Node> e : node.children.entrySet()) {
      collect(e.getValue(), build + e.getKey(), out);
    }
  }

  private static class Node {
    Map<Character, Node> children = new HashMap<>();
    boolean end;
    int freq;
  }
}
