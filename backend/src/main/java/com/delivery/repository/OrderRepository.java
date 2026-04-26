package com.delivery.repository;

import com.delivery.model.OrderEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
  List<OrderEntity> findByCustomerContainingIgnoreCaseOrSourceContainingIgnoreCaseOrDestinationContainingIgnoreCase(String customer, String source, String destination);
}
