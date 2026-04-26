package com.delivery.service;

import com.delivery.model.OrderEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import org.springframework.stereotype.Service;

@Service
public class DispatchService {
  private final OrderService orderService;

  public DispatchService(OrderService orderService) {
    this.orderService = orderService;
  }

  public Map<String, Object> dispatchNext() {
    List<OrderEntity> orders = orderService.getAll().stream().filter(o -> "NEW".equals(o.getStatus())).toList();
    Comparator<OrderEntity> cmp = Comparator.comparingInt(OrderEntity::getPriority).reversed();
    if (LocalDateTime.now().getMinute() % 2 == 0) {
      cmp = Comparator.comparingInt(OrderEntity::getPriority);
    }
    PriorityQueue<OrderEntity> pq = new PriorityQueue<>(cmp);
    pq.addAll(orders);
    OrderEntity picked = pq.poll();
    picked.setStatus("DISPATCHED");
    orderService.update(picked.getId(), picked);
    Map<String, Object> res = new HashMap<>();
    res.put("orderId", picked.getId());
    res.put("priority", picked.getPriority());
    res.put("rider", "RIDER-" + (picked.getId() % 5 + 1));
    return res;
  }

  public List<Long> refreshQueue() {
    List<Long> refreshed = new ArrayList<>(orderService.getPipeline());
    return refreshed;
  }
}
