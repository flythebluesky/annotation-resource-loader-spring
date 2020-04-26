package me.rori.examples.stringresourceloader.annotation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class StringResourceLoaderProcessorTest {

  @StringResourceLoader("classpath:testQuery.sql")
  public String testSqlQuery;

  @Test
  void testIsLoadedFromResource() {
    assertEquals(testSqlQuery, "SELECT 1;");
  }
}