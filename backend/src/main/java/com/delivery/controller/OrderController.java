package com.delivery.controller;

import com.delivery.model.OrderEntity;
import com.delivery.service.OrderService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/orders")
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping
  public List<OrderEntity> all() {
    return orderService.getAll();
  }

  @PostMapping
  public OrderEntity add(@RequestBody OrderEntity order) {
    return orderService.add(order);
  }

  @PutMapping("/{id}")
  public OrderEntity update(@PathVariable Long id, @RequestBody OrderEntity order) {
    return orderService.update(id, order);
  }

  @GetMapping("/search")
  public List<OrderEntity> search(@RequestParam String q) {
    return orderService.search(q);
  }
}
