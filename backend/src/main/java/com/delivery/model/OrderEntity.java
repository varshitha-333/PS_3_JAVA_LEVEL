package com.delivery.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class OrderEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String customer;
  private String source;
  private String destination;
  private String status;
  private int priority;
  private LocalDateTime createdAt;

  public OrderEntity() {
  }

  public OrderEntity(Long id, String customer, String source, String destination, String status, int priority, LocalDateTime createdAt) {
    this.id = id;
    this.customer = customer;
    this.source = source;
    this.destination = destination;
    this.status = status;
    this.priority = priority;
    this.createdAt = createdAt;
  }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getCustomer() { return customer; }
  public void setCustomer(String customer) { this.customer = customer; }
  public String getSource() { return source; }
  public void setSource(String source) { this.source = source; }
  public String getDestination() { return destination; }
  public void setDestination(String destination) { this.destination = destination; }
  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }
  public int getPriority() { return priority; }
  public void setPriority(int priority) { this.priority = priority; }
  public LocalDateTime getCreatedAt() { return createdAt; }
  public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
