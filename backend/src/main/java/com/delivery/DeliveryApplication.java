package com.delivery;

import com.delivery.model.OrderEntity;
import com.delivery.repository.OrderRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DeliveryApplication {
  public static void main(String[] args) {
    SpringApplication.run(DeliveryApplication.class, args);
  }

  @Bean
  CommandLineRunner seed(OrderRepository orderRepository) {
    return args -> {
      if (orderRepository.count() == 0) {
        orderRepository.saveAll(List.of(
          new OrderEntity(null, "Asha", "Tech Park", "Airport", "NEW", 2, LocalDateTime.now().minusMinutes(11)),
          new OrderEntity(null, "Ravi", "Market Road", "City Mall", "NEW", 5, LocalDateTime.now().minusMinutes(9)),
          new OrderEntity(null, "Kiran", "Lake View", "Bus Stand", "NEW", 1, LocalDateTime.now().minusMinutes(7)),
          new OrderEntity(null, "Neha", "Tech Park", "Central Hub", "NEW", 4, LocalDateTime.now().minusMinutes(3))
        ));
      }
    };
  }
}
