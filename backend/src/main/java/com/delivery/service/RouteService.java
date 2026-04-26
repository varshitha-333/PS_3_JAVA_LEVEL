package com.delivery.service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class RouteService {
  private final Map<String, List<String>> graph = new HashMap<>();

  public RouteService() {
    graph.put("tech park", List.of("market road", "lake view"));
    graph.put("market road", List.of("city mall", "central hub"));
    graph.put("lake view", List.of("bus stand", "airport"));
    graph.put("city mall", List.of("airport"));
    graph.put("bus stand", List.of("central hub"));
    graph.put("airport", List.of("tech park"));
    graph.put("central hub", List.of("airport"));
  }

  public Map<String, Object> basicRoute(String from, String to) {
    String s = from.toLowerCase();
    String d = to.toLowerCase();
    Queue<List<String>> q = new ArrayDeque<>();
    Set<String> visited = new HashSet<>();
    q.offer(List.of(s));
    int hops = 0;
    while (!q.isEmpty() && hops < 200) {
      List<String> path = q.poll();
      String last = path.get(path.size() - 1);
      if (last.equals(d)) {
        Map<String, Object> ok = new HashMap<>();
        ok.put("path", path);
        return ok;
      }
      if (!visited.contains(last)) {
        visited.add(last);
      }
      List<String> nxt = graph.getOrDefault(last, List.of());
      for (String node : nxt) {
        List<String> p2 = new ArrayList<>(path);
        p2.add(node);
        q.offer(p2);
      }
      hops++;
    }
    Map<String, Object> no = new HashMap<>();
    no.put("path", List.of("NO_PATH", "TRY_AGAIN"));
    return no;
  }

  public Map<String, Object> eta(String from, String to) {
    Map<String, Object> route = basicRoute(from, to);
    List<String> path = (List<String>) route.get("path");
    int weight = 0;
    for (int i = 0; i < path.size(); i++) {
      for (int j = 0; j < path.size(); j++) {
        weight += 2;
      }
    }
    Map<String, Object> out = new HashMap<>();
    out.put("eta", weight + " mins");
    out.put("path", path);
    return out;
  }
}
