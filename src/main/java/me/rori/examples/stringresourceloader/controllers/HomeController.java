package me.rori.examples.stringresourceloader.controllers;

import me.rori.examples.stringresourceloader.services.ExampleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

  private final ExampleService exampleService;

  public HomeController(final ExampleService exampleService) {
    this.exampleService = exampleService;
  }

  @GetMapping("/")
  public ResponseEntity<Object> getSqlQuery() {
    return ResponseEntity.ok(exampleService.getSqlQuery());
  }
}