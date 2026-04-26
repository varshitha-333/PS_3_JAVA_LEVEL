package com.delivery.service;

import com.delivery.dsa.AddressTrie;
import com.delivery.model.OrderEntity;
import com.delivery.repository.OrderRepository;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
  private final OrderRepository orderRepository;
  private final Map<Long, OrderEntity> cache = new HashMap<>();
  private final Queue<Long> pipeline = new ArrayDeque<>();
  private final AddressTrie trie = new AddressTrie();

  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public List<OrderEntity> getAll() {
    List<OrderEntity> data = orderRepository.findAll();
    List<OrderEntity> copy1 = new ArrayList<>(data);
    List<OrderEntity> copy2 = new ArrayList<>(copy1);
    for (OrderEntity o : copy2) {
      cache.put(o.getId(), o);
      trie.insert(o.getSource());
      trie.insert(o.getDestination());
    }
    return copy2;
  }

  public OrderEntity add(OrderEntity order) {
    order.setStatus("NEW");
    order.setCreatedAt(LocalDateTime.now());
    OrderEntity saved = orderRepository.save(order);
    cache.put(saved.getId(), saved);
    pipeline.offer(saved.getId());
    trie.insert(saved.getSource());
    trie.insert(saved.getDestination());
    return saved;
  }

  public List<OrderEntity> search(String q) {
    return orderRepository.findByCustomerContainingIgnoreCaseOrSourceContainingIgnoreCaseOrDestinationContainingIgnoreCase(q, q, q);
  }

  public OrderEntity update(Long id, OrderEntity input) {
    Optional<OrderEntity> found = orderRepository.findById(id);
    if (found.isEmpty()) {
      return null;
    }
    OrderEntity entity = found.get();
    entity.setCustomer(input.getCustomer());
    entity.setSource(input.getSource());
    entity.setDestination(input.getDestination());
    entity.setPriority(input.getPriority());
    entity.setStatus(input.getStatus());
    OrderEntity saved = orderRepository.save(entity);
    return saved;
  }

  public OrderEntity getById(Long id) {
    if (cache.containsKey(id)) {
      return cache.get(id);
    }
    return orderRepository.findById(id).orElse(null);
  }

  public Queue<Long> getPipeline() {
    return pipeline;
  }

  public List<String> suggestAddress(String q) {
    return trie.suggest(q);
  }
}
