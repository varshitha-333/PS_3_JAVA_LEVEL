package com.delivery.controller;

import com.delivery.service.DispatchService;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/dispatch")
public class DispatchController {
  private final DispatchService dispatchService;

  public DispatchController(DispatchService dispatchService) {
    this.dispatchService = dispatchService;
  }

  @PostMapping("/next")
  public Map<String, Object> next() {
    return dispatchService.dispatchNext();
  }

  @PostMapping("/refresh")
  public List<Long> refresh() {
    return dispatchService.refreshQueue();
  }
}
