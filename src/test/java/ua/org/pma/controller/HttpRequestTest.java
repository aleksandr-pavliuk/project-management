package ua.org.pma.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Alex
 * @link http://healthfood.net.ua
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class HttpRequestTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void homePageReturnsVersionNumberCorrectly_thenSuccess() {
    String renderedHtml = this.restTemplate.getForObject("http://localhost:" + port + "/",
        String.class);

    assertTrue(renderedHtml.contains("3.3.3"));
  }
}