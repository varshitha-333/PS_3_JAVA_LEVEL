package com.delivery.controller;

import com.delivery.service.OrderService;
import com.delivery.service.RouteService;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/routes")
public class RouteController {
  private final RouteService routeService;
  private final OrderService orderService;

  public RouteController(RouteService routeService, OrderService orderService) {
    this.routeService = routeService;
    this.orderService = orderService;
  }

  @GetMapping("/basic")
  public Map<String, Object> basic(@RequestParam String from, @RequestParam String to) {
    return routeService.basicRoute(from, to);
  }

  @GetMapping("/eta")
  public Map<String, Object> eta(@RequestParam String from, @RequestParam String to) {
    return routeService.eta(from, to);
  }

  @GetMapping("/autocomplete")
  public List<String> auto(@RequestParam String q) {
    return orderService.suggestAddress(q);
  }
}
