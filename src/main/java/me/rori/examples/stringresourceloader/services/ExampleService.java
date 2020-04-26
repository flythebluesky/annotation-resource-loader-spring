package me.rori.examples.stringresourceloader.services;

import me.rori.examples.stringresourceloader.annotation.StringResourceLoader;
import org.springframework.stereotype.Service;


@Service
public class ExampleService {

  @StringResourceLoader("classpath:somesql.sql")
  public String sqlQuery;

  public String getSqlQuery() {
    return this.sqlQuery;
  }
}